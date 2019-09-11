package com.lyp.learn.dp.pattern.factory5;

public class OracleLogin implements ILogin {
    @Override
    public void insert(Login login) {
        System.out.println("对 oracle 里的 login 表插入了一条数据");
    }

    @Override
    public Login getLogin(int id) {
        System.out.println("通过 id 在 oracle 里的 login 表得到了一条数据");
        return null;
    }
}
