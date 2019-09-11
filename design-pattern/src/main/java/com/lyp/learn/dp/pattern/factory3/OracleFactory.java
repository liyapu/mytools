package com.lyp.learn.dp.pattern.factory3;

public class OracleFactory implements SqlFactory{
    @Override
    public IUser createUser() {
        //访问oracle中User表的对象
        return new OracleUser();

    }
}
