package com.lyp.learn.base.jdbc.meta;

import org.junit.Test;

import java.sql.*;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-05 19:31
 *
 * DatabaseMetaData 接口主要是用来得到关于数据库的元信息
 *
 */
public class DatabaseMetaDataTest {

    /**
     *
     */
    @Test
    public void test01() throws SQLException {
        Connection connection = DbConnectionUtil.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println("数据库是否支持存储过程 : " + metaData.supportsStoredProcedures() );
        System.out.println("连接数据库的url : " + metaData.getURL());
        System.out.println("连接数据库的driver : " + metaData.getDriverName());
        System.out.println("连接数据库的driver version : " + metaData.getDriverVersion());
        System.out.println("连接数据库的username : " + metaData.getUserName());
        System.out.println("数据库产品名 : " + metaData.getDatabaseProductName());
        System.out.println("数据库版本号 : " + metaData.getDatabaseProductVersion());
        System.out.println("数据库是否只允许读操作 : " + metaData.isReadOnly());
        System.out.println("数据库是否支持事务 : " + metaData.supportsTransactions());

        System.out.println();
        //获得当前数据库的类型信息
        ResultSet typeInfo = metaData.getTypeInfo();
        ResultSetMetaData metaData1 = typeInfo.getMetaData();
        //总共有多少列
        int columnCount1 = metaData1.getColumnCount();
        for(int i = 1; i < columnCount1; i++){
            System.out.println("列 " + i + ": " + metaData1.getColumnName(i) + " , " + metaData1.getColumnTypeName(i) +" (" + metaData1.getColumnDisplaySize(i) + ")");
        }


        connection.close();
    }


    /**
     * 获取所有的数据库列表
     * #没有指定数据库，通过DatabaseMetaData元数据库可以获取到所有的数据库
     * db.url=jdbc:mysql://localhost:3306?serverTimezone=UTC&characterEncoding=utf8&nullCatalogMeansCurrent=true&tinyInt1isBit=false
     *
     * information_schema ===== information_schema
     * mybatisplus ===== mybatisplus
     * mysql ===== mysql
     * mytest ===== mytest
     * performance_schema ===== performance_schema
     * sys ===== sys
     * temp ===== temp
     * test ===== test
     */
    @Test
    public void test02() throws SQLException {
        Connection connection = DbConnectionUtil.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        //获取所有的数据库列表
        ResultSet rs = metaData.getCatalogs();
        while (rs.next()){
            /*
               在这里打断点，
                        看 rs 里的 fields ,看看有几个，然后再写取值           (mysql 旧的驱动)
                        看 rs 里的 columnDefinition 里的 fields,看看有几个  （mysql 新的驱动）
               取值方式：
                        通过列名，在fields中可以看到，columnName 或者 看getTables()方法的注释，列出了每列的名字
                        通过下标，下标是从 1 开始的，可以看到fields中大小，就知道了大小
             */
            System.out.println(rs.getString(1) + " ===== " + rs.getString("TABLE_CAT"));
        }

        rs.close();
        connection.close();
    }

    /**
     * 获取指定数据库中的所有表
     * #指定了具体的数据库
     * db.url=jdbc:mysql://localhost:3306/mybatisplus?serverTimezone=UTC&characterEncoding=utf8&nullCatalogMeansCurrent=true&tinyInt1isBit=false
     *
     * mybatisplus , null , employee , TABLE ,  , null , null , null , null , null
     * mybatisplus , null , employee , TABLE ,  , null , null , null , null , null
     * mybatisplus , null , user , TABLE ,  , null , null , null , null , null
     * mybatisplus , null , user , TABLE ,  , null , null , null , null , null
     * mybatisplus , null , worker , TABLE ,  , null , null , null , null , null
     * mybatisplus , null , worker , TABLE ,  , null , null , null , null , null
     *
     */
    @Test
    public void test03() throws SQLException {
        Connection connection = DbConnectionUtil.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        //获取指定数据中的表信息
        /*
             getTables(String catalog, String schemaPattern,String tableNamePattern, String types[] ）
                catalog:（目录，目录薄）当前操作的数据库,可以指定操作的数据库名称
                    mysql:
                            3306后指定的数据库 mybatisplus,
                            url中已经指定了，这里可以传null
                    oracle：
                            xxx:1521:orcl
                           1521后的orcl

               schema:
                    mysql:
                        :null
                    oracle
                        :用户名(大写)

                tableNamePattern:
                     null 查询所有表
                     "" 查询目标表

                types 类型
                      TABLE ：表
                      VIEW : 视图
                      ALIAS ：别名
         */
        ResultSet rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (rs.next()){
            System.out.println(rs.getString(1) + " , " + rs.getString(2) + " , " +  rs.getString(3) + " , " + rs.getString(4) + " , " +  rs.getString(5) + " , " + rs.getString(6) + " , " +  rs.getString(7) + " , " + rs.getString(8) + " , " + rs.getString(9) + " , " + rs.getString(10) );
            System.out.println(rs.getString("TABLE_CAT") + " , " + rs.getString("TABLE_SCHEM") + " , " +  rs.getString("TABLE_NAME") + " , " + rs.getString("TABLE_TYPE") + " , " +  rs.getString("REMARKS") + " , " + rs.getString("TYPE_CAT") + " , " +  rs.getString("TYPE_SCHEM") + " , " + rs.getString("TYPE_NAME") + " , " + rs.getString("SELF_REFERENCING_COL_NAME") + " , " + rs.getString("REF_GENERATION") );
        }

        rs.close();
        connection.close();
    }

    /**
     * 获取指定表中的字段信息
     */
    @Test
    public void test04() throws SQLException {
        Connection connection = DbConnectionUtil.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        /*
         getColumns(String catalog, String schemaPattern,String tableNamePattern, String columnNamePattern)
         */
        ResultSet rs = metaData.getColumns(null,null,"employee",null);

        ResultSetMetaData rsMetaData = rs.getMetaData();
        //获取列的长度
        int columnCount = rsMetaData.getColumnCount();
        System.out.println(columnCount);

        while (rs.next()){
            StringBuffer sb = new StringBuffer();
            for(int i = 1; i <= columnCount; i++){
                sb.append(rs.getString(i)).append(" , ");
            }
            System.out.println(sb.toString());

            System.out.println(rs.getString("COLUMN_NAME") + " , " + rs.getString("REMARKS") + " , " + rs.getString("TYPE_NAME") + " (" + rs.getString("COLUMN_SIZE") + ")");
        }

        rs.close();
        connection.close();
    }

    /**
     * 获取指定表中主键
     */
    @Test
    public void test05() throws SQLException {
        Connection connection = DbConnectionUtil.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        ResultSet pk = metaData.getPrimaryKeys(null, null, "worker");
        while (pk.next()){
            System.out.println(pk.getString("COLUMN_NAME") + " , " + pk.getString("KEY_SEQ") + " , " + pk.getString("PK_NAME"));
        }

        pk.close();
        connection.close();
    }
}
