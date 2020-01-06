package com.lyp.learn.base.jdbc.meta;

import com.lyp.learn.base.jdbc.DbConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-05 19:57
 *
 *  ResultSetMetaData 结果集元数据 是有ResultSet对象通过getMetaData方法获取而来，主要是针对有数据库执行的SQL脚本命令获取的结果集对象ResultSet中提供的一些信息，
 *  比如结果集中的列数，指定列的名称，指定列的SQL的类型等等，可以说这个是对于框架来说非常重要的一个对象。
 *
 *  getColumnCount ：获取结果集中国列项目的个数
 *  getColumnType : 获取指定列的SQL类型对应于Java中Types类的字段
 *  getColumnTypeName ：获取指定列的SQL类型
 *  getClassName ： 获取指定列SQL类型对应与java中的类型(包名加类名)
 */
public class ResultSetMetaDataTest {

    @Test
    public void test01() throws SQLException {
        Connection connection = DbConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM employee ";

        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //总共有多少列
        int columnCount = rsMetaData.getColumnCount();
        for(int i = 1; i < columnCount; i++){
            System.out.println("列 " + i + ": " + rsMetaData.getColumnName(i) + " , " + rsMetaData.getColumnTypeName(i) +" (" + rsMetaData.getColumnDisplaySize(i) + ")  " + rsMetaData.getColumnClassName(i));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
