package com.lyp.mt.mg.utils;

import com.lyp.mt.yapi.entity.FieldEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.sql.*;
import java.text.Collator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MySqlMetaDataUtil {

    private static final Logger logger = LoggerFactory.getLogger(MySqlMetaDataUtil.class);

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf8";
    private static final String URL = "jdbc:mysql://localhost:3306/golden_palm?serverTimezone=UTC&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root$123";


//    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String URL = "jdbc:mysql:///golden_palm?allowMultiQueries=true&characterEncoding=utf8";
//    private static final String USERNAME = "";
//    private static final String PASSWORD = "";

    //排除的字段
    static List<String> excludeFields = new ArrayList<>();

    static {
        excludeFields.add("id");
        excludeFields.add("valid");
        excludeFields.add("createTime");
        excludeFields.add("updateTime");
        excludeFields.add("tableName");
        excludeFields.add("create_time");
        excludeFields.add("update_time");
        excludeFields.add("table_name");
    }


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
     * 获取当前的数据库名称
     * @return
     */
    public static String getCurrentDbName() {
        String dbName = "";
        String sql = "SELECT database()";
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                dbName = resultSet.getString("database()");
                break;
            }
        } catch (SQLException e) {
            System.out.println("getCurrentDbName error. " + e);
        } finally {
           closeConnection(connection);
        }
        return dbName;
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
     * 获取当前数据库的所有表的创建语句
     * @return
     *
     */
    public static Map<String,String> getCreateTableSql(){
        Map<String,String> result = new HashMap<>();
        List<String> currentDbTables = getCurrentDbTables();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            for(String tableName : currentDbTables){
                String sql = "SHOW CREATE TABLE " + tableName;
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    //通过数字下标获取表建表语句
                    String createTableSql = resultSet.getString(2);
                    result.put(tableName,createTableSql);
                }
            }
        } catch (Exception e) {
            System.out.println("getCurrentDbTables error." + e);
        } finally {
            close(connection,null,resultSet);
        }
        return result;
    }

    /**
     * 获取当前数据库的所有表注释
     * @return
     *
     */
    public static Map<String,String> getTableComments(){
        Map<String,String> result = new HashMap<>();
        List<String> currentDbTables = getCurrentDbTables();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            String keyComment = "COMMENT='";

            for(String tableName : currentDbTables){
                String sql = "SHOW CREATE TABLE " + tableName;
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    //通过数字下标获取表建表语句
                    String createTableSql = resultSet.getString(2);
                    int start = createTableSql.indexOf(keyComment);
                    if(start < 0){
                        continue;
                    }
                    String tableComment = createTableSql.substring(start+keyComment.length(),createTableSql.length()-1);
                    result.put(tableName,tableComment);
                }
            }
        } catch (Exception e) {
            System.out.println("getCurrentDbTables error." + e);
        } finally {
            close(connection,null,resultSet);
        }
        return result;
    }

    /**
     * 获取当前数据库的所有表注释
     * @return
     *
     */
    public static Map<String,String> getTableComments2() {
        Map<String,String> result = new HashMap<>();
        String dbName = getCurrentDbName();
        String sql = "SELECT table_name,table_comment FROM information_schema.TABLES WHERE table_schema = '"+ dbName +"' ";
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
                String tableComment = resultSet.getString(2);
                result.put(tableName,tableComment);
            }
        } catch (Exception e) {
            System.out.println("getCurrentDbTables error." + e);
        } finally {
            close(connection,null,resultSet);
        }
        return result;
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
                sb.append(" ").append("AS").append(" ").append(FieldUtils.lineToHump(fieldName));
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

//        fieldEntities.forEach((FieldEntity fe) -> System.out.println(fe.getComment() + "=======" + fe.getField() + "======" + fe.getTableName()));
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
        List<FieldEntity> manualTranslates = manualTranslate();
        fieldEntities.addAll(manualTranslates);

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
                if(isMatchComment(feo, fet)){
                    Matcher m = pn.matcher(feo.getType());
                    if(m.find()){
                        System.out.println(String.format(sql,tableName,feo.getField(),fet.getField(),m.group(1),feo.getComment()));
                        sqls.add(String.format(sql,tableName,feo.getField(),fet.getField(),m.group(1),feo.getComment()));
                    }
                    break;
                }
            }
        }
        executeBatchSql(sqls);
    }

    private static boolean isMatchComment(FieldEntity feo, FieldEntity fet) {
        String originComment = feo.getComment().trim();
        String waitComment = fet.getComment().trim();
        if(StringUtils.isAnyBlank(originComment,waitComment)){
            return false;
        }
        if(originComment.equals(waitComment)){
            return true;
        }
        String unitYuan = "单位：元";
        originComment = originComment.replaceAll(unitYuan,"").trim();
        waitComment = waitComment.replaceAll(unitYuan,"").trim();
        if(originComment.equals(waitComment)){
            return true;
        }
        String among = "其中";
        originComment = originComment.replaceAll(among,"").trim();
        waitComment = waitComment.replaceAll(among,"").trim();
        if(originComment.equals(waitComment)){
            return true;
        }
        String unitStock = "单位：股";
        originComment = originComment.replaceAll(unitStock,"").trim();
        waitComment = waitComment.replaceAll(unitStock,"").trim();
        if(originComment.equals(waitComment)){
            return true;
        }
        String unitPercent = "单位：%";
        originComment = originComment.replaceAll(unitPercent,"").trim();
        waitComment = waitComment.replaceAll(unitPercent,"").trim();
        if(originComment.equals(waitComment)){
            return true;
        }
        String numStartReg = "^\\s*[①|②|③|④|⑤|⑥|⑦|⑧|⑨|⑩|1|2|3|4|5|6|7|8|9|一|二|三|四|五|六|七|八|九|十]*\\s*[、|：|;|,]";
        originComment = originComment.replaceAll(numStartReg,"").trim();
        waitComment = waitComment.replaceAll(numStartReg,"").trim();
        if(originComment.equals(waitComment)){
            return true;
        }

        String specialChar = "[:|：|\\_|,|;|、|\\(|\\)|\\（|\\）]";
        originComment = originComment.replaceAll(specialChar,"").trim();
        waitComment = waitComment.replaceAll(specialChar,"").trim();
//        System.out.println("waitComment ="+waitComment);
        if(originComment.equals(waitComment)){
            return true;
        }

        return  false;
    }



    /**
     * 自己手工翻译，可以让多个表使用这个源，达到一个翻译词，更新多个表的工能
     * @return
     */
    public static List<FieldEntity> manualTranslate(){
        List<FieldEntity> fieldEntities = new ArrayList<>();
//        fieldEntities.add(new FieldEntity("avarage_10","10日平均（MA10）"));
//        fieldEntities.add(new FieldEntity("avarage_120","120日均价"));
        fieldEntities.add(new FieldEntity("net_profit_cash_flow","1、将净利润调节为经营活动现金流量："));
        fieldEntities.add(new FieldEntity("major_invest_activity","2、不涉及现金收支的重大投资和筹资活动："));
//        fieldEntities.add(new FieldEntity("avarage_30","30日平均（MA30）"));
        fieldEntities.add(new FieldEntity("cash_net_change","现金及现金等价物净变动情况"));
        fieldEntities.add(new FieldEntity("avarage_week_52","52周均价（360日）均价"));
        fieldEntities.add(new FieldEntity("avarge_5","5日平均（MA5）"));
        fieldEntities.add(new FieldEntity("ev_ebitda","EV/EBITDA"));
        fieldEntities.add(new FieldEntity("isin_code","ISIN代码"));
        fieldEntities.add(new FieldEntity("peg","PEG"));
        fieldEntities.add(new FieldEntity("a_share_count","Ａ股户数"));
        fieldEntities.add(new FieldEntity("b_share_count","Ｂ股户数"));
        fieldEntities.add(new FieldEntity("h_share_count","Ｈ股户数"));
        fieldEntities.add(new FieldEntity("office_address","办公地址"));
        fieldEntities.add(new FieldEntity("insurance_income","保险业务收入"));
        fieldEntities.add(new FieldEntity("report_date","报告年度 YYYY-03-31为一季度，YYYY-06-30为中报，YYYY-09-30为三季报，YYYY-12-31为年报"));
        fieldEntities.add(new FieldEntity("current_profit_upper","本期净利润上限"));
        fieldEntities.add(new FieldEntity("current_profit_lower","本期净利润下限"));
        fieldEntities.add(new FieldEntity("current_net_profit_upper","本期净利润增减幅上限"));
        fieldEntities.add(new FieldEntity("current_net_profit_lower","本期净利润增减幅下限"));
        fieldEntities.add(new FieldEntity("today_security_repay","本日融券偿还量"));
        fieldEntities.add(new FieldEntity("today_security_sell","本日融券卖出量"));
        fieldEntities.add(new FieldEntity("today_security_margin","本日融券余量"));
        fieldEntities.add(new FieldEntity("today_finance_payment","本日融资偿还额"));
        fieldEntities.add(new FieldEntity("today_finance_purchase","本日融资买入额"));
        fieldEntities.add(new FieldEntity("today_finance_balance","本日融资余额"));
        fieldEntities.add(new FieldEntity("change_stock_count","变动后持股数量"));
        fieldEntities.add(new FieldEntity("change_current_stock_count","变动后持有流通股数量"));
        fieldEntities.add(new FieldEntity("change_ratio","变动后占比"));
        fieldEntities.add(new FieldEntity("change_count","变动数量"));
        fieldEntities.add(new FieldEntity("change_count_ratio","变动数量占总股本比例"));
        fieldEntities.add(new FieldEntity("city","城市"));
        fieldEntities.add(new FieldEntity("transaction_amount","成交金额"));
        fieldEntities.add(new FieldEntity("transaction_count","成交数量"));
        fieldEntities.add(new FieldEntity("continue_operating_profit","持续经营净利润 2018年新增科目"));
        fieldEntities.add(new FieldEntity("secretary_fax","董秘传真"));
        fieldEntities.add(new FieldEntity("secretary_phone","董秘电话"));
        fieldEntities.add(new FieldEntity("secretary_email","董秘邮箱"));
        fieldEntities.add(new FieldEntity("chairman","董事长"));
        fieldEntities.add(new FieldEntity("director_secretary","董事会秘书"));
        fieldEntities.add(new FieldEntity("independent_director","独立董事"));
        fieldEntities.add(new FieldEntity("operating_expense","营业支出"));
        fieldEntities.add(new FieldEntity("issue_total_stock","发行总股本"));
        fieldEntities.add(new FieldEntity("company_fax","公司传真"));
        fieldEntities.add(new FieldEntity("company_phone","公司电话"));
        fieldEntities.add(new FieldEntity("company_email","公司电子邮件地址"));
        fieldEntities.add(new FieldEntity("company_profile","公司简介"));
        fieldEntities.add(new FieldEntity("company_website","公司网站"));
        fieldEntities.add(new FieldEntity("fair_value_change_profit","公允价值变动收益"));
        fieldEntities.add(new FieldEntity("shareholder_id","股东ID"));
        fieldEntities.add(new FieldEntity("shareholder_holding_ratio","股东持股比例"));
        fieldEntities.add(new FieldEntity("shareholder_holding_ratio_report","股东持股比例比上报告期增减"));
        fieldEntities.add(new FieldEntity("shareholder_holding_number","股东持股数量"));
        fieldEntities.add(new FieldEntity("shareholder_total","股东总户数"));
        fieldEntities.add(new FieldEntity("dividend_lyr","股息率LYR"));
        fieldEntities.add(new FieldEntity("dividend_ttm","股息率TTM"));
        fieldEntities.add(new FieldEntity("avg_holding_ratio","户均持股比例 单位：‰  户均持股比例=1*1000%/股东人数"));
        fieldEntities.add(new FieldEntity("avg_holding","户均持股 户均持股：同期总股本/同期股东户数"));
        fieldEntities.add(new FieldEntity("turnover_rate","换手率"));
        fieldEntities.add(new FieldEntity("account_firm","会计师事务所"));
        fieldEntities.add(new FieldEntity("basic_profitability","基本获利能力(EBIT)"));
        fieldEntities.add(new FieldEntity("separate_premium","减：分出保费"));
        fieldEntities.add(new FieldEntity("amortize_duty_deposit","减：摊回保险责任准备金"));
        fieldEntities.add(new FieldEntity("amortize_cession_charge","减：摊回分保费用"));
        fieldEntities.add(new FieldEntity("amortize_claim_pay","减：摊回赔付支出"));
        fieldEntities.add(new FieldEntity("transaction_date","交易日期"));
        fieldEntities.add(new FieldEntity("transaction_address","交易所"));
        fieldEntities.add(new FieldEntity("today_open_price","今日开盘价"));
        fieldEntities.add(new FieldEntity("control_ratio","控股比例"));
        fieldEntities.add(new FieldEntity("control_count","控股数量(万股)"));
        fieldEntities.add(new FieldEntity("control_type","控制类型 包括：家族控制、单独控制、一致行动人；选择时程序自动同时将名称填入"));
        fieldEntities.add(new FieldEntity("control_type_code","控制类型编码"));
        fieldEntities.add(new FieldEntity("transfer_stock","流通股本"));
        fieldEntities.add(new FieldEntity("law_firm","律师事务所"));
        fieldEntities.add(new FieldEntity("target_price_upper","目标价格（上限）"));
        fieldEntities.add(new FieldEntity("target_price_lower","目标价格（下限）"));
        fieldEntities.add(new FieldEntity("claim_pay","赔付支出"));
        fieldEntities.add(new FieldEntity("rating_change","评级变化"));
        fieldEntities.add(new FieldEntity("other_income","其他收益"));
        fieldEntities.add(new FieldEntity("other_business_cost","其他业务成本"));
        fieldEntities.add(new FieldEntity("other_business_income","其他业务收入"));
        fieldEntities.add(new FieldEntity("other_income","其它收益 根据最新会计准则，2017年11月28日增加此字段"));
        fieldEntities.add(new FieldEntity("entrust_client_net_income","其中:委托客户管理资产业务净收入"));
        fieldEntities.add(new FieldEntity("premium_income","其中：分保费收入"));
        fieldEntities.add(new FieldEntity("top_ten_shareholder","前十大股东"));
        fieldEntities.add(new FieldEntity("previous_invest_rating","前一次投资评级"));
        fieldEntities.add(new FieldEntity("margin_trading_amount","融券余量金额"));
        fieldEntities.add(new FieldEntity("margin_balance","融资融券余额"));
        fieldEntities.add(new FieldEntity("listing_date","上市日期"));
        fieldEntities.add(new FieldEntity("listing_status","上市状态"));
        fieldEntities.add(new FieldEntity("industry_first_name","申万行业一级名称"));
        fieldEntities.add(new FieldEntity("industry_second_name","申万行业二级名称"));
        fieldEntities.add(new FieldEntity("industry_three_name","申万行业三级名称"));
        fieldEntities.add(new FieldEntity("province","省份"));
        fieldEntities.add(new FieldEntity("actual_controller_id","实际控制人ID"));
        fieldEntities.add(new FieldEntity("actual_controller_type","实际控制人类型 自然人、国有、集体、国企、外企"));
        fieldEntities.add(new FieldEntity("actual_controller_name","实际控制人名称 "));
        fieldEntities.add(new FieldEntity("first_rating","是否首次评级"));
        fieldEntities.add(new FieldEntity("pb_lf","市净率LF"));
        fieldEntities.add(new FieldEntity("pcf_lyr_operate","市现率LYR（经营）"));
        fieldEntities.add(new FieldEntity("pcf_lyr_total","市现率LYR（总）"));
        fieldEntities.add(new FieldEntity("pcf_ttm_operate","市现率TTM（经营）"));
        fieldEntities.add(new FieldEntity("pcf_ttm_total","市现率TTM（总）"));
        fieldEntities.add(new FieldEntity("ps_lyr","市销率LYR"));
        fieldEntities.add(new FieldEntity("ps_ttm","市销率TTM"));
        fieldEntities.add(new FieldEntity("pe","市盈率"));
        fieldEntities.add(new FieldEntity("pe_lyr","市盈率LYR"));
        fieldEntities.add(new FieldEntity("pe_lyr_deduct","市盈率LYR（扣非）"));
        fieldEntities.add(new FieldEntity("pe_ttm","市盈率TTM"));
        fieldEntities.add(new FieldEntity("pe_ttm_deduct","市盈率TTM（扣非）"));
        fieldEntities.add(new FieldEntity("close_price","收盘价"));
        fieldEntities.add(new FieldEntity("draw_duty_deposit","提取保险责任准备金"));
        fieldEntities.add(new FieldEntity("draw_unexpire_deposit","提取未到期责任准备金"));
        fieldEntities.add(new FieldEntity("invest_rating","投资评级"));
        fieldEntities.add(new FieldEntity("invest_rating_adjust","投资评级（经调整）"));
        fieldEntities.add(new FieldEntity("research_institution_short","研究机构简称"));
        fieldEntities.add(new FieldEntity("research_name","研究员名称"));
        fieldEntities.add(new FieldEntity("achievement_change_reason","业绩变化原因"));
        fieldEntities.add(new FieldEntity("achievement_type","业绩类型 包括：业绩小幅增长（50%以下）；业绩大幅增长（50%以上）；业绩预盈；业绩小幅下降（50%以下）；业绩大幅下降（50%以上）；业绩预亏；业绩持平；不确定；选择时程序自动将名称同时填入"));
        fieldEntities.add(new FieldEntity("achievement_type_code","业绩类型编码 通过公共编码表选择采集，对应的总类编码为035"));
        fieldEntities.add(new FieldEntity("achievement_forecast_content","业绩预告内容"));
        fieldEntities.add(new FieldEntity("business_management_fee","业务及管理费"));
        fieldEntities.add(new FieldEntity("english_abbreviation","英文简称"));
        fieldEntities.add(new FieldEntity("add_minus_price_cap","增（减）持价格上限"));
        fieldEntities.add(new FieldEntity("delisting_date","摘牌日期"));
        fieldEntities.add(new FieldEntity("rise_fall","涨跌"));
        fieldEntities.add(new FieldEntity("rise_fall_range","涨跌幅"));
        fieldEntities.add(new FieldEntity("sfc_one_name","证监会一级行业名称"));
        fieldEntities.add(new FieldEntity("sfc_two_name","证监会二级行业名称"));
        fieldEntities.add(new FieldEntity("security_type","证券类别"));
        fieldEntities.add(new FieldEntity("security_represent","证券事务代表"));
        fieldEntities.add(new FieldEntity("direct_holder_id","直接持有人ID"));
        fieldEntities.add(new FieldEntity("direct_holder_name","直接持有人名称 "));
        fieldEntities.add(new FieldEntity("direct_controller_id","直接控制人ID"));
        fieldEntities.add(new FieldEntity("direct_controller_name","直接控制人名称 实际控制人可能直接或间接控制该上市公司。当实际控制人直接控制该上市公司时，实际控制人的下一层级单位仍为实际控制人本身，当实际控制人间接通过其他公司持有该上市公司股票时，实际控制人的下一层级单位为实际控制人直接持股的公司名称。当同时存在直接或间接持股该上市公司时，每条关系链上的下一层级均要列出。"));
        fieldEntities.add(new FieldEntity("termination_net_profit","终止经营净利润 2018年新增科目"));
        fieldEntities.add(new FieldEntity("main_business","主营业务 "));
        fieldEntities.add(new FieldEntity("register_address","注册地址"));
        fieldEntities.add(new FieldEntity("asset_disposal_profit","资产处置收益 2018年新增科目"));
        fieldEntities.add(new FieldEntity("total_num","总笔数"));
        fieldEntities.add(new FieldEntity("general_manager","总经理"));
        fieldEntities.add(new FieldEntity("minimum_deal_price","最低成交价"));
        fieldEntities.add(new FieldEntity("maximum_deal_price","最高成交价"));
        fieldEntities.add(new FieldEntity("yesterday_close_price","昨收盘价"));
        return fieldEntities;
    }

    /**
     * 获取以 _origin 结尾的表名
     * @return
     */
    public List<String> getOriginEndTables(){
        List<String> currentDbTables = getCurrentDbTables();
        List<String> originTables = currentDbTables.stream()
                .filter(s -> s.endsWith("origin"))
                .collect(Collectors.toList());
        return originTables;
    }

    /**
     * 获取以 _origin 结尾的表名
     * 然后去掉 _origin 的表集合
     * @return
     */
    public List<String> getOriginEndTablesOfRemoveOrigin(){
        List<String> currentDbTables = getCurrentDbTables();

        List<String> originTables = currentDbTables.stream()
                .filter(s -> s.endsWith("_origin"))
                .collect(Collectors.toList());

        List<String> originOfRemoveTables = originTables.stream()
                .map(s -> s.replace("_origin", ""))
                .collect(Collectors.toList());
        return originOfRemoveTables;
    }

    @Test
    public void outputOriginEndTableGeneratorConfig(){
        List<String> originEndTables = getOriginEndTables();
        for(String tableName : originEndTables){
            String template = "<table tableName=\""+ tableName +"\">\n" +
                    "      <ignoreColumn column=\"id\"/>\n" +
                    "      <ignoreColumn column=\"valid\"/>\n" +
                    "      <ignoreColumn column=\"create_time\"/>\n" +
                    "      <ignoreColumn column=\"update_time\"/>\n" +
                    "</table>\n" +
                    "  ";
            System.out.println(template);
        }
    }

    @Test
    public void outputOriginEndRemoveOriginTableGeneratorConfig(){
        List<String> originEndTables = getOriginEndTablesOfRemoveOrigin();
        for(String tableName : originEndTables){
            String template = "<table tableName=\""+ tableName +"\">\n" +
                    "      <ignoreColumn column=\"id\"/>\n" +
                    "      <ignoreColumn column=\"valid\"/>\n" +
                    "      <ignoreColumn column=\"create_time\"/>\n" +
                    "      <ignoreColumn column=\"update_time\"/>\n" +
                    "</table>\n" +
                    "  ";
            System.out.println(template);
        }
    }

    /**
     * 构造插入 table_show_field 表sql
     */
    @Test
    public void buildInsertTableShowField(){
//        String dbTableName = "report_balance";
//        String dbTableName = "report_profit";
//        String dbTableName = "report_cash_flow";
//        String dbTableName = "financial_balance_y2007";
//        String dbTableName = "financial_cash_flow_y2007";
//        String dbTableName = "financial_profit_y2007";
//        String dbTableName = "quarter_cash_flow";
//        String dbTableName = "quarter_financial_indicator";
//        String dbTableName = "quarter_financial_profit";
//        String dbTableName = "ttm_cash_flow";
//        String dbTableName = "ttm_financial_profit";
//        String dbTableName = "company_initial_stock";
//        String dbTableName = "company_share_plan";
//        String dbTableName = "company_share_impl";
//        String dbTableName = "company_stock_plan";
//        String dbTableName = "company_stock_impl";
//        String dbTableName = "dividend_indicator";
//        String dbTableName = "performance_expectation";
//        String dbTableName = "investment_rating";
//        String dbTableName = "shareholding_concentration";
//        String dbTableName = "shareholder_controller";
//        String dbTableName = "company_stock_change";
//        String dbTableName = "executive_stock_change";
//        String dbTableName = "shareholder_stock_change";
        String dbTableName = "stock_basic";
        List<FieldEntity> fieldEntities = listByTableNameSql(dbTableName);
        int order = 0;
        for(FieldEntity fe : fieldEntities){
            String fieldName = FieldUtils.lineToHump(fe.getField());
            String comment = fe.getComment();

            if(excludeFields.contains(fieldName)){
                continue;
            }
            if(comment.contains("单位：元")){
                comment = comment.replace("单位：元","(元)").trim();
            }
            if(comment.contains("quarterly:一季报 three_quarterly:三季报 semi_annual:半年报 annual:年报")){
                comment = comment.replace("quarterly:一季报 three_quarterly:三季报 semi_annual:半年报 annual:年报","").trim();
            }
            if(comment.contains("2017/2018/2019")){
                comment = comment.replace("2017/2018/2019","").trim();
            }
            if(comment.contains("  q1th:一季度；q2nd:二季度；q3rd:三季度；q4th:四季度;")){
                comment = comment.replace("  q1th:一季度；q2nd:二季度；q3rd:三季度；q4th:四季度;","").trim();
            }
            order += 5;
            String sql = "INSERT INTO `table_show_field` (`table_name`,`field_name`,`field_show`,`field_order`,`valid`,`create_time`,`update_time`) VALUES ('"+dbTableName+"','"+fieldName+"','"+comment+"',"+order+",1,now(),now());";
            System.out.println(sql);
        }

    }


    @Test
    public void getOriginEndTable(){
        List<String> stockBasic = getTableOriginField("stock_basic");
        System.out.println(stockBasic);
    }

    public static void main(String[] args) {

        List<String> currentDbTables = getCurrentDbTables();
        for(String originTableName : currentDbTables){
            System.out.println(originTableName);
        }

//        FieldEntity feo = new FieldEntity();
////        feo.setComment("固定资产折旧、油气资产折耗、生产性生物资产折旧 单位：元");
////        feo.setComment("手续费及佣金支出");
////        feo.setComment("每股收益");
//        feo.setComment("10日平均（MA10）");
//        FieldEntity fet = new FieldEntity();
//////        fet.setComment("固定资产折旧、油气资产折耗、生产性生物资产折旧");
//////        fet.setComment("其中:手续费及佣金支出");
//////        fet.setComment("六、每一股份的一三五期间收1益：一三五七");
//        fet.setComment("30日平均（MA30） ");
//        System.out.println(isMatchComment(feo,fet));

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
            //listAllTablesTranslated();

//            System.out.println("start--------");
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
//            System.out.println("done------");
//              listAllTablesNoTranslate();

//            List<FieldEntity> fieldEntities = listAllTablesTranslated();
//            fieldEntities.forEach((FieldEntity fe) -> System.out.println(fe.getComment() + "=======" + fe.getField() + "======" + fe.getTableName()));
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


    @Test
    public void test01(){
        System.out.println(getTableComments());
        System.out.println("-------------");
        System.out.println(getTableComments2());
        System.out.println();
        System.out.println(getCurrentDbName());

    }

}
