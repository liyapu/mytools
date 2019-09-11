package com.lyp.learn.dp.pattern.factory4;

public class MysqlUser implements IUser {
    @Override
    public void insert(User user) {
        System.out.println("在 mysql 中的 user 表中插入一条元素");
    }

    @Override
    public User getUser(int id) {
        System.out.println("在 mysql 中的 user 表得到id为"+id+"的一条数据");
        return null;
    }
}
