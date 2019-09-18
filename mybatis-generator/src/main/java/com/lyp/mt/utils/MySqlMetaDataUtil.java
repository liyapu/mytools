package com.lyp.mt.utils;

import com.lyp.mt.entity.FieldEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.Collator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySqlMetaDataUtil {

    private static final Logger logger = LoggerFactory.getLogger(MySqlMetaDataUtil.class);

//    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf8";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "Root$123";


    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://47.94.211.209:8306/golden_palm?allowMultiQueries=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Tusdao@mysql2019*";

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

    /**
     * 执行一条sql
     */
    public static void  executeOneSql(String sql){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("执行成功：：：：" + sql);
        } catch (Exception e) {
            System.out.println("executeOneSql error." + e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 执行一批sql
     */
    public static void  executeBatchSql(List<String> sqls){
        Connection connection = null;
        Statement statement = null;
        String sqlTemp = "";
        try {
            connection = getConnection();
            statement = connection.createStatement();
            for(String sql : sqls){
                sqlTemp = sql;
                statement.execute(sql);
                System.out.println("执行成功：：：：" + sql);
            }
        } catch (Exception e) {
            System.out.println("executeBatchSql sqlTemp: " + sqlTemp + " error." + e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 获取表字段的原始字段列表
     * @param tableName
     * @return
     */
    public static List<String> getTableOriginField(String tableName){
            List<String> fields = new ArrayList<>();
            List<FieldEntity> fieldEntities = listByTableNameSql(tableName);
            for(FieldEntity fe : fieldEntities){
                fields.add(fe.getField());
            }
            return fields;
    }


    /**
     * 获取表字段的原始字段列表，以字符串形式返回
     *id,file_url,file_path,retry_times,valid
     * @param tableName
     * @return
     */
    public static String getTableOriginFieldStr(String tableName){
        StringBuilder sb = new StringBuilder();
        List<FieldEntity> fieldEntities = listByTableNameSql(tableName);
        for(int i = 0; i  < fieldEntities.size(); i++){
            if(i > 0){
                sb.append(",");
            }
            sb.append(fieldEntities.get(i).getField());
        }
        return sb.toString();
    }

    /**
     * 获取表字段的原始字段列表和驼峰命名字段，以字符串形式返回
     * id,file_url AS fileUrl,file_path AS filePath,retry_times AS retryTimes,valid
     * @param tableName
     * @return
     */
    public static String getTableOriginFieldAndHump(String tableName){
        StringBuilder sb = new StringBuilder();
        List<FieldEntity> fieldEntities = listByTableNameSql(tableName);
        for(int i = 0; i  < fieldEntities.size(); i++){
            if(i > 0){
                sb.append(",");
            }
            String fieldName = fieldEntities.get(i).getField();
            sb.append(fieldName);
            if(fieldName.contains("_")){
                sb.append(" ").append("AS").append(" ").append(FieldUtil.lineToHump(fieldName));
            }
        }
        return sb.toString();
    }


    static Pattern p = Pattern.compile("(AUTO_INCREMENT=\\d*)");

    /**
     * 根据表名，获取建表语句
     * @param tableNames
     * @return
     */
    public static List<String> getCreateTableByTableName(List<String> tableNames){
        List<String> createTables = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        FieldEntity entity = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            for(String tableName : tableNames){
                String sql = "show create table  " + tableName;
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    String createTable = resultSet.getString("Create Table");
                    Matcher m = p.matcher(createTable);
                    if(m.find()){
//                        System.out.println(m.group(1));
                        createTable = createTable.replace(m.group(1),"AUTO_INCREMENT=1");
                    }

                    createTables.add(createTable);
                }
            }

        } catch (Exception e) {
            System.out.println("listByTableName2 error." + e);
        } finally {
            close(connection,null,resultSet);
        }
        return createTables;
    }

    /**
     * 获取数据库中所有表的字段与注释
     */
    public static List<FieldEntity> listAllTablesComment(){
        System.out.println("------------获取数据库中所有表的字段与注释-------");
        Pattern p1 = Pattern.compile("w*\\_origin$");
        List<String> currentDbTables = getCurrentDbTables();
        List<String> excludeField = Arrays.asList("id","valid","create_time","update_time");
        List<FieldEntity> fieldEntities = new ArrayList<>();
        for(String tableName : currentDbTables){
            if(p1.matcher(tableName).find()){
                continue;
            }
            List<FieldEntity> fieldEntityTemp = listByTableNameSql(tableName);
            for(FieldEntity fe : fieldEntityTemp){
                fe.setTableName(tableName);
                if(excludeField.contains(fe.getField())){
                    continue;
                }
                fieldEntities.add(fe);
            }
        }
        //System.out.println("----------fieldEntities size = " + fieldEntities.size());

        Collections.sort(fieldEntities,(FieldEntity fe1,FieldEntity fe2) -> Collator.getInstance(Locale.CHINESE).compare(fe1.getComment(),fe2.getComment()));

        //fieldEntities.forEach((FieldEntity fe) -> System.out.println(fe.getComment() + "  =======  " + fe.getField() + " ====== " + fe.getTableName()));
        //System.out.println("----------fieldEntities size = " + fieldEntities.size());
        return fieldEntities;
    }

    /**
     * 获取已经翻译好的字段
     */
    public static List<FieldEntity> fieldEntityOriginGlobal = null;
    public static List<FieldEntity> listAllTablesTranslated(){
        List<FieldEntity> fieldEntities = new ArrayList<>();
        List<FieldEntity> fieldEntityOrigin = fieldEntityOriginGlobal;
        if(fieldEntityOrigin == null || fieldEntityOrigin.size() <= 0){
            fieldEntityOrigin = listAllTablesComment();
            fieldEntityOriginGlobal = fieldEntityOrigin;
        }

        Pattern p = Pattern.compile("^f\\d+[dvn]$");
        for(FieldEntity fe : fieldEntityOrigin){
            if(p.matcher(fe.getField()).find()){
                //System.out.println("剔除数据：：：："+fe);
                continue;
            }
            fieldEntities.add(fe);
        }
        return fieldEntities;
    }

    /**
     * 获取没有翻译的字段
     */
    public static List<FieldEntity> listAllTablesNoTranslate(){
        List<FieldEntity> fieldEntities = new ArrayList<>();
        List<FieldEntity> fieldEntityOrigin = listAllTablesComment();
        Pattern p = Pattern.compile("^f\\d+[dvn]$");
        for(FieldEntity fe : fieldEntityOrigin){
            if(p.matcher(fe.getField()).find()){
                System.out.println("未翻译数据：：：："+fe);
                fieldEntities.add(fe);
            }
        }
        return fieldEntities;
    }

    /**
     * 翻译 一个字段
     * @param fieldName
     * @return
     */
    public static String translateField(String fieldName){
        List<FieldEntity>  fieldEntities = listAllTablesTranslated();
        for(FieldEntity fe : fieldEntities){
            if(fe.getComment().equals(fieldName)){
                //System.out.println(fieldName + " -----" + fe.getComment() + " ---" + fe.getField());
                return fe.getField();
            }
        }
        return null;
    }

    /**
     * 翻译一个表
     * @param tableName
     * @return
     */
    public static void translateTableField(String tableName){
        List<FieldEntity> fieldEntityTrans = listAllTablesTranslated();
        List<FieldEntity> fieldEntityOrigins = listByTableNameSql(tableName);
        List<String> noFindTrans = new ArrayList<>();
        List<String> sqls = new ArrayList<>();
        String sql = "ALTER TABLE `golden_palm`.`%s`  CHANGE COLUMN `%s` `%s` VARCHAR(%s) NULL DEFAULT NULL COMMENT '%s' ;";
        Pattern p = Pattern.compile("^f\\d+[dvn]$");
        Pattern pn = Pattern.compile("\\((\\d*)\\)");

        for(FieldEntity feo : fieldEntityOrigins){
            if(!p.matcher(feo.getField()).find()){
                continue;
            }
            for(FieldEntity fet : fieldEntityTrans){
                if(fet.getComment().equals(feo.getComment()) || fet.getComment().equals(feo.getComment() + " 单位：元")){
                    Matcher m = pn.matcher(feo.getType());
                    if(m.find()){
//                        System.out.println(String.format(sql,tableName,feo.getField(),fet.getField(),m.group(1),feo.getComment()));
                        sqls.add(String.format(sql,tableName,feo.getField(),fet.getField(),m.group(1),feo.getComment()));
                    }
                    break;
                }
            }
        }
        executeBatchSql(sqls);
    }

    /**
     * 自己手工翻译，可以让多个表使用这个源，达到一个翻译词，更新多个表的工能
     * @return
     */
    public List<FieldEntity> manualTranslate(){
        List<FieldEntity> fieldEntities = new ArrayList<>();
        fieldEntities.add(new FieldEntity("defer_tax_decrease","递延所得税资产减少"));
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

//            String tableName = "file_mapping";
//
//            List<String> fields = getTableOriginField(tableName);
//            for(String s : fields){
//                System.out.println(s);
//            }
//
//            System.out.println(getTableOriginFieldStr(tableName));
//            System.out.println(getTableOriginFieldAndHump(tableName));

//            List<String> tableNames = new ArrayList<>();
//            tableNames.add("file_mapping");
//            tableNames.add("table_show_field");
//            List<String> createTables = getCreateTableByTableName(tableNames);
//            createTables.stream().forEach(s -> System.out.println(s));
//            listAllTablesTranslated();

            System.out.println("start--------");
//            translateTableField("financial_cash_flow_y2007");
//            translateTableField("financial_profit_y2007");
//            translateTableField("investment_rating");
//            translateTableField("performance_expectation");
//            translateTableField("quarter_cash_flow");
//            translateTableField("quarter_financial_indicator");
//            translateTableField("quarter_financial_profit");
//            translateTableField("quote_data");
//            translateTableField("shareholder_controller");
//            translateTableField("shareholder_stock_change");
//            translateTableField("shareholding_concentration");
//            translateTableField("stock_basic");
//            translateTableField("ttm_cash_flow");
//            translateTableField("ttm_financial_profit");
            System.out.println("done------");
              listAllTablesNoTranslate();
//            List<FieldEntity> fieldEntities = listAllTablesTranslated();
//            fieldEntities.forEach((FieldEntity fe) -> System.out.println(fe.getComment() + "  =======  " + fe.getField() + " ====== " + fe.getTableName()));
//            String str = "varchar(20)";
//            Pattern p = Pattern.compile("\\((\\d*)\\)");
//            Matcher m1 = p.matcher(str);
//            if(m1.find()){
//                System.out.println(m1.group(1));
//            }
//            while (m1.find()) {
//                int groupCount = m1.groupCount();
//                System.out.println("groupCount = " + groupCount);
//                for (int i = 0; i <= groupCount; i++) {
//                    System.out.println("i = " + i + "\t开始位置：" + m1.start(i) + "\t 结束位置：" + m1.end(i) + " \t 匹配字符 " + m1.group(i));
//                }
//            }
        } catch (Exception e) {
            System.out.println("main error. " + e);
        }
    }

}
