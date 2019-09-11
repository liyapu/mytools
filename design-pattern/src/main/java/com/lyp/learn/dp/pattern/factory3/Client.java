package com.lyp.learn.dp.pattern.factory3;

public class Client {
    public static void main(String[] args) {
        SqlFactory sqlFactory = new MysqlFactory();
        IUser iUser = sqlFactory.createUser();

        iUser.getUser(1);
        iUser.insert(new User());

        System.out.println("------------------------------------");

        SqlFactory sqlFactory2 = new OracleFactory();
        IUser iUser2 = sqlFactory2.createUser();

        iUser2.getUser(1);
        iUser2.insert(new User());
    }

}
