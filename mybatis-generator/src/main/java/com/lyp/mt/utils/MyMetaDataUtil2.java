package com.lyp.mt.utils;

import com.lyp.mt.entity.FieldEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyMetaDataUtil2 {

    private static final Logger logger = LoggerFactory.getLogger(MyMetaDataUtil2.class);

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root$123";


    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            logger.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("close connection failure", e);
            }
        }
    }

    public static void close(Connection connection,Statement statement,ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("close resultSet error." + e);
            }
        }

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("close statement error." + e);
            }
        }

        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("close connection error." + e);
            }
        }
    }

    /**
     * 获取所有的数据库名称
     * 可以执行 SHOW DATABASES 语句，看一下返回数据
     * 通过数字下标 或者 字符串 都可以获取到名字
     * @return
     */
    public static List<String> showAllDatabases() throws SQLException {
        List<String> databases = new ArrayList<>();
        String sql = "SHOW DATABASES";
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
//                System.out.println("showAllDatabases ::::" + resultSet.getString(1));
//                System.out.println("showAllDatabases ::::" + resultSet.getString("database"));
                databases.add(resultSet.getString("database"));
            }
        } catch (SQLException e) {
            System.out.println("showAllDatabases error. " + e);
        } finally {
            if(connection != null){
                connection.close();
            }
        }
        return databases;
    }

    /**
     * 获取所有数据库中的所有表名称
     * @return
     */
    public static List<String> getAllTables(){
        List<String> tableNames = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            //参数1 int resultSetType
            //ResultSet.TYPE_FORWORD_ONLY 结果集的游标只能向下滚动。
            //ResultSet.TYPE_SCROLL_INSENSITIVE 结果集的游标可以上下移动，当数据库变化时，当前结果集不变。
            //ResultSet.TYPE_SCROLL_SENSITIVE 返回可滚动的结果集，当数据库变化时，当前结果集同步改变。

            //参数2 int resultSetConcurrency
            //ResultSet.CONCUR_READ_ONLY 不能用结果集更新数据库中的表。
            //ResultSet.CONCUR_UPDATETABLE 能用结果集更新数据库中的表
//            connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //获取数据库的元数据
            DatabaseMetaData metaData = connection.getMetaData();
            //从元数据中获取到所有的表名
            //目录名称; 数据库名; 表名称; 表类型;
            rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()){
//                System.out.println("getAllTables :::::" + rs.getString("TABLE_NAME") );
                tableNames.add(rs.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            System.out.println("getAllTables error." + e);
        } finally {
            close(connection,null,rs);
        }
        return tableNames;
    }

    /**
     * 获取当前数据库的所有表名称
     * @return
     */
    public static List<String> getCurrentDbTables(){
        List<String> tableNames = new ArrayList<>();
        String sql = "SHOW TABLES";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                //通过数字下标获取表名
                String tableName = resultSet.getString(1);
                //通过列名获取表名，需要拼接 数据库名字 "Tables_in_"+当前数据库名字
//                String tableName = resultSet.getString("Tables_in_test");
//                System.out.println("getCurrentDbTables :::::" + tableName );
                tableNames.add(tableName);
            }
        } catch (Exception e) {
            System.out.println("getCurrentDbTables error." + e);
        } finally {
            close(connection,null,resultSet);
        }
        return tableNames;
    }



    /**
     * 根据表名获取表中所有的字段名称
     * @param tableName
     * @return
     */
    public static List<FieldEntity> listByTableName(String tableName){
        List<FieldEntity> fieldEntities = new ArrayList<>();
        FieldEntity fe = null;
        Connection connection = null;
        try {
            connection = getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet colRet = metaData.getColumns(null,"%", tableName,"%");
            while(colRet.next()) {
                fe = new FieldEntity();
                //列名
                String columnName = colRet.getString("COLUMN_NAME");
                //列的类型
                String columnType = colRet.getString("TYPE_NAME");
                int datasize = colRet.getInt("COLUMN_SIZE");
                //列类型的长度
                int digits = colRet.getInt("DECIMAL_DIGITS");
                int nullable = colRet.getInt("NULLABLE");
                //注释
                String remark = colRet.getString("REMARKS");
//                System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+ nullable + " " +remark );
                fe.setField(columnName);
                fe.setType(columnType);
                fe.setComment(remark);
                fieldEntities.add(fe);
            }

        } catch (Exception e) {
            System.out.println("listByTableName error." + e);
        } finally {
            closeConnection(connection);
        }

        return fieldEntities;
    }


    /**
     * 根据表名获取表中所有的字段名称
     * 通过 sql 语句
     * @param tableName
     * @return
     */
    public static List<FieldEntity> listByTableNameSql(String tableName){
        List<FieldEntity> fieldEntities = new ArrayList<>();
        String sql = "show full columns from " + tableName;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        FieldEntity entity = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                entity = new FieldEntity();
                String field = resultSet.getString(1);
                String type = resultSet.getString(2);
                String comment = resultSet.getString(9);
                entity.setField(field);
                entity.setType(type);
                entity.setComment(comment);
                fieldEntities.add(entity);
            }
        } catch (Exception e) {
            System.out.println("listByTableName2 error." + e);
        } finally {
            close(connection,null,resultSet);
        }
        return fieldEntities;
    }

    public static void main(String[] args) {
        try {
            //获取所有的数据库
//            List<String> databases = showAllDatabases();
//            for(String db : databases){
//                System.out.println(db);
//            }

            //获取所有表名
//            List<String> tableNames = getAllTables();
//            for(String tn : tableNames){
//                System.out.println(tn);
//            }

//            List<FieldEntity> fieldEntities = listByTableName("file_mapping");
//                for(FieldEntity fe : fieldEntities){
//                    System.out.println(fe);
//                }
//            //获取当前数据库的所有表名
////            List<String> tableNames = getCurrentDbTables();
//            for(String tn : tableNames){
//                System.out.println(tn);
//                //listByTableName(tn);
//                List<FieldEntity> fieldEntities = listByTableName2(tn);
//                for(FieldEntity fe : fieldEntities){
//                    System.out.println(fe);
//                }
//                System.out.println("---------------");
//                System.out.println();
//            }



        } catch (Exception e) {
            System.out.println("main error. " + e);
        }
    }

}
