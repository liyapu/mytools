package com.lyp.learn.dp.pattern.factory4;

public class Client {
    public static void main(String[] args) {
        User user = new User();
        Login login = new Login();

        // 只需要确定实例化哪一个数据库访问对象给factory
        SqlFactory sqlFactory = new MysqlFactory();

        // 已与具体的数据库访问解除了耦合
        IUser iUser = sqlFactory.createUser();
        iUser.getUser(1);
        iUser.insert(user);

        // 已与具体的数据库访问解除了耦合
        ILogin iLogin = sqlFactory.createLogin();
        iLogin.insert(login);
        iLogin.getLogin(1);

        System.out.println("------------------------------------");

        sqlFactory = new OracleFactory();

        iUser = sqlFactory.createUser();
        iUser.getUser(1);
        iUser.insert(user);

        iLogin = sqlFactory.createLogin();
        iLogin.insert(login);
        iLogin.getLogin(1);
    }

}
