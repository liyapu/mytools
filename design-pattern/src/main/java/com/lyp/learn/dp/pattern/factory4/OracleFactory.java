package com.lyp.learn.dp.pattern.factory4;

public class OracleFactory implements SqlFactory {
    @Override
    public IUser createUser() {
        //访问oracle中User表的对象
        return new OracleUser();

    }

    @Override
    public ILogin createLogin() {
        //访问oracle中Login表的对象
        return new OracleLogin();
    }
}
