package com.lyp.learn.dp.pattern.factory4;

public class MysqlFactory implements SqlFactory {
    @Override
    public IUser createUser() {
        //访问mysql中User表的对象
        return new MysqlUser();

    }

    @Override
    public ILogin createLogin() {
        //访问mysql中Logn表的对象
        return new MysqlLogin();
    }
}
