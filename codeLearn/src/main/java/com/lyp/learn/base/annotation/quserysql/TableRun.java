package com.lyp.learn.base.annotation.quserysql;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-04 21:48
 */
public class TableRun {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("李亚朴");
        user.setPassword("xxxxx");
        user.setAddress("河南");
        try {
            TableUtil.getQuerySql(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
