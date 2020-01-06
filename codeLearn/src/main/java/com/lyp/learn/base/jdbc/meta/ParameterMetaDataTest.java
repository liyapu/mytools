package com.lyp.learn.base.jdbc.meta;

import com.lyp.learn.base.jdbc.DbConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-05 22:15
 *
 *  ParameterMetaData 参数元数据 是右PreparedStatement对象通过getParameterMetaData方法获取而来，
 *                     主要是针对PrepareStatement对象和其预编译的SQL命令语句提供一些信息，比如占位符参数的个数，
 *                     获取指定位置占位符的SQL类型参数等等
 *
 *   getParameterCount 获取预编译SQL语句中占位符的个数
 */
public class ParameterMetaDataTest {

    @Test
    public void test01() throws Exception {
        Connection connection = DbConnectionUtil.getConnection();
        String sql = "SELECT * FROM employee WHERE id = ? ";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1,1088250446457389058L);

        //获取参数元数据
        ParameterMetaData psMetaData = ps.getParameterMetaData();
        int parameterCount = psMetaData.getParameterCount();
        System.out.println("parameterCount = " + parameterCount);
        connection.close();
    }











}
