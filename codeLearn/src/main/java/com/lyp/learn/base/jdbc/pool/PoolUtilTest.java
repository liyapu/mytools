package com.lyp.learn.base.jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liyapu
 * @date 2021-07-28 18:02
 * @desc
 */
public class PoolUtilTest {

    public static void main(String[] args) {
        PoolUtil poolUtil = new PoolUtil();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(()-> {
            try {
                    queryDb(poolUtil);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    private static void queryDb(PoolUtil poolUtil) throws SQLException {
        for (int i = 0; i < 5; i++) {
            System.out.println("iiiiiiiiiiiiiiiii ============= " + i);
            Connection conn = poolUtil.getConnection();
            //执行sql
            Statement stmt = conn.createStatement();
            String sql = " SELECT id, name, address, age, create_time, update_time FROM person ";
            ResultSet rs = stmt.executeQuery(sql);
            //获取结果集
            while(rs.next()){
                //获取具体结果
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                int age = rs.getInt("age");
                Timestamp createTime = rs.getTimestamp("create_time");
                Timestamp updateTime = rs.getTimestamp("update_time");
                //                System.out.println("id \t name \t address \t age \t createTime \t updateTime");
                System.out.println(""+id+" \t "+name+" \t "+address+" \t "+age+" \t "+createTime+" \t "+updateTime+"");
            }
            //关闭资源
            rs.close();
            stmt.close();

            //调用 自定义的释放链接
            poolUtil.releaseConnection(conn);
        }
    }
}
