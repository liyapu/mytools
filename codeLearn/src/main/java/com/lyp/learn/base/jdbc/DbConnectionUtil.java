package com.lyp.learn.base.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-05 19:15
 */
public class DbConnectionUtil {
    //读取数据库连接的参数
    private static Properties properties = null;
    //数据连接
    static  Connection connection = null;


    /**
     * 获取键对应的值
     */
    public  static String getValue(String key){
        if(properties == null){
            properties = new Properties();
            try {
                String userDir = System.getProperty("user.dir");
                String targetDir = userDir + File.separator + "target" + File.separator + "classes" + File.separator;
                System.out.println("targetDir = " + targetDir);
                String fileDir = targetDir + "db/db.properties";
                properties.load(new FileInputStream(fileDir ));
            } catch (IOException e) {
                System.out.println("读取db配置错误："+ e);
            }
        }
        return properties.get(key).toString();
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection(){
        //读取db连接配置
        String dbDriver = getValue("db.driver");
        String dbUrl = getValue("db.url");
        String dbUsername = getValue("db.username");
        String dbPassword = getValue("db.password");

        try {
            //指定驱动程序
            Class.forName(dbDriver);
            //建立数据库连接，通过字符串传参，获取到数据库备注信息，有些数据库可能为空
//             connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            //通过下面这种方式，指定获取数据库的备注信息
            Properties p = new Properties();
            p.put("user",dbUsername);
            p.put("password",dbPassword);
            p.put("remarksReporting","true");//获取数据库的备注信息
            connection = DriverManager.getConnection(dbUrl,p);
        } catch (Exception e) {
            System.out.println("获取连接错误 " + e);
        }
        return connection;
    }

}
