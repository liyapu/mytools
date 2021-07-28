package com.lyp.learn.base.jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import org.junit.experimental.theories.PotentialAssignment;

/**
 * @author liyapu
 * @date 2021-07-28 17:46
 * @desc
 *
 *  数据库连接池技术详解  https://juejin.cn/post/6844903661605240845
 */
public class PoolUtil {

    // 初始化链接数目
    private final int init_count = 3;
    // 最大链接数
    private final int max_count = 8;
    // 到当前链接数
    private int current_count = 0;
    
    // 链接池
    private LinkedList<Connection> pools = new LinkedList<>();

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "Root$123";

    /**
     * 构造函数
     * 初始化链接放入连接池
     */
    public PoolUtil(){
        for (int i = 0; i < init_count; i++) {
            //记录当前链接数
            current_count++;
            
            Connection connection = createConnection();
            pools.addLast(connection);
        }
    }

    /**
     * 自定义的创建链接函数
     * @return
     */
    private Connection createConnection()  {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("新建了一个链接-----------" + connection) ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取链接
     */
    public Connection getConnection(){
        if(pools.size() > 0){
            //removeFirst删除第一个并且返回
            //现在你一定看懂了我说的为什要用LinkedList了吧，因为下面的这个
            //removeFirst()方法会将集合中的第一个元素删除，但是还会返回第一个元素
            //这样就省去了我们很多不必要的麻烦
            return pools.removeFirst();
        }
        if(current_count < max_count){
            //记录当前使用的链接数
            current_count++;
            //创建链接
            return createConnection();
        }

        throw new RuntimeException("当前链接数已经达到最大链接数");
    }

    /**
     * 释放资源 当用户使用完了连接之后，我们要做的并不是关闭连接，而是将连接重新放入资源池LinkedList之中，
     * 这样就省去了一遍又一遍的连接关闭．这个就是连接池的核心内容
     */
    public void releaseConnection(Connection connection){
        if(pools.size() < init_count){
            pools.addLast(connection);
            current_count--;
        }else{
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        System.out.println("释放了一个链接..........." + connection );
    }
}
