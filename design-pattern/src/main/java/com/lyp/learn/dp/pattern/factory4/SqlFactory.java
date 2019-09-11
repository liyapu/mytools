package com.lyp.learn.dp.pattern.factory4;

public interface SqlFactory {

    //用于访问User表的对象
    IUser createUser();

    //用于访问Login表的对象
    ILogin createLogin();

}
