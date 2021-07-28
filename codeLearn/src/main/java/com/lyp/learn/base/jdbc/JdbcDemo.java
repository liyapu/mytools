package com.lyp.learn.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * jdbc 原生链接方式
 */
public class JdbcDemo {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "Root$123";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //注册driver
            Class.forName(JDBC_DRIVER);
            //打开链接
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //执行sql
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
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
            conn.close();
        }catch(SQLException se){ //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){ //Handle errors for Class.forName
            e.printStackTrace();
        }finally{ //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){ }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}