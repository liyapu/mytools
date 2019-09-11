package com.lyp.learn.dp.pattern.factory3;

public interface SqlFactory {

    //用于访问User表的对象
    IUser createUser();

}
