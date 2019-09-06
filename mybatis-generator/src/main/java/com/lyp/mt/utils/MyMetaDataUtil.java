package com.lyp.mt.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyMetaDataUtil {

    private static final Logger logger = LoggerFactory.getLogger(MyMetaDataUtil.class);

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root$123";

    private static final String SQL = "SELECT * FROM ";// 数据库操作


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



    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableMetas() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            DatabaseMetaData meta = conn.getMetaData();
            //目录名称; 数据库名; 表名称; 表类型;
            rs = meta.getTables(catalog(), schemaPattern(), tableNamePattern(), types());
            while(rs.next()){
                tableNames.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            logger.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                logger.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    protected static String catalog(){
        return null;
    }

    protected static String schemaPattern() {
        //mysql的schemaPattern为数据库名称
        String url = URL;
        //jdbc:mysql://localhost:3306/iso_db?useUnicode=true&characterEncoding=UTF-8
        String database = url.substring(url.indexOf("/", 13) + 1);
        int index = database.indexOf("?");
        if(index > 0){
            database = database.substring(0, index);
        }
        return database;
    }

    /**
     * a table name pattern; must match the table name as it is stored in the database
     */
    protected static String tableNamePattern(){
        return "%";
    }


    /**
     * a list of table types, which must be from the list of table types returned from ,to include; null returns all types
     */
    protected static String[] types(){

        return new String[]{"TABLE"};
//        return new String[]{"TABLE", "VIEW"};
    }


    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableMetas2() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            JSONArray jsonArray = new JSONArray();
            while (rs.next()) {
                ResultSet colRs = db.getColumns(null, "%", rs.getString("TABLE_NAME"), "%");
                while (colRs.next()) {

//                    logger.info("column name:{} comment:{}", colRs.getString("COLUMN_NAME"), colRs.getString("Comment"));
                    System.out.println("-------"+ colRs.getString("COLUMN_NAME") + "::::::" + colRs.getString("Comment"));
                    tableNames.add(" colRs.getString(\"COLUMN_NAME\") + \"::::::\" + colRs.getString(\"Comment\")");
                }
            }
        } catch (SQLException e) {
            logger.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                logger.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    public static void processColumns() {

    }


    /**
     * 获取表中字段的所有注释
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) throws IOException {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        JSONObject property = new JSONObject(100, true);
        int count = 0;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                String colName = rs.getString("Field");
                String colComment = rs.getString("Comment");
                logger.info("column name:{} comment:{}", colName, colComment);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "string");
                jsonObject.put("description", colComment);
                property.put(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, colName), jsonObject);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    logger.error("getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        logger.info("total columns count:{}", count);
        //Logs.json(property);
        File file = new File("fields.json");
        FileUtils.writeStringToFile(file, JSON.toJSONString(property, true), "utf-8");
        return columnComments;
    }

    /**
     * 获取表中所有字段名称
     *
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            logger.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    logger.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            logger.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    logger.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }


    public static void main(String[] args) throws IOException {
        List<String> tableNames = getTableMetas2();
        for(String tn : tableNames){
            System.out.println("tableName -----> " + tn);
        }

//        tableNames.add("file_mapping");
//        tableNames.add("table_show_field");
//        System.out.println("tableNames:" + tableNames);
//        for (String tableName : tableNames) {
//            System.out.println("ColumnNames:" + getColumnNames(tableName));
//            System.out.println("ColumnTypes:" + getColumnTypes(tableName));
//            System.out.println("ColumnComments:" + getColumnComments(tableName));
//        }
        //getColumnComments("file_mapping");
//        getColumnComments("financial_indicator_industry_ranking");

    }


}
