package com.lyp.learn.dp.pattern.factory3;

public class MysqlFactory implements SqlFactory{
    @Override
    public IUser createUser() {
        //访问mysql中User表的对象
        return new MysqlUser();

    }
}
