package com.lyp.learn.dp.pattern.factory5;

public class Client {
    public static void main(String[] args) {
        User user = new User();
        Login login = new Login();

        // 直接得到实际的数据库访问实例，而不存在任何依赖
        IUser iUser = EasyFactory.createUser();
        iUser.getUser(1);
        iUser.insert(user);

        // 直接得到实际的数据库访问实例，而不存在任何依赖
        ILogin iLogin =  EasyFactory.createLogin();
        iLogin.insert(login);
        iLogin.getLogin(1);

    }

}
