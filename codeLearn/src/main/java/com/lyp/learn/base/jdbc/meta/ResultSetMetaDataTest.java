package com.lyp.learn.base.jdbc.meta;

import com.lyp.learn.base.jdbc.DbConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-05 19:57
 *
 *  ResultSetMetaData 主要用来获取结果集的结构
 */
public class ResultSetMetaDataTest {

    @Test
    public void test01() throws SQLException {
        Connection connection = DbConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM employee ";

        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();

        //总共有多少列
        int columnCount = metaData.getColumnCount();
        for(int i = 1; i < columnCount; i++){
            System.out.println("列 " + i + ": " + metaData.getColumnName(i) + " , " + metaData.getColumnTypeName(i) +" (" + metaData.getColumnDisplaySize(i) + ")");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
