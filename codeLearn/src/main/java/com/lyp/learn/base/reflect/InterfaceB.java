package com.lyp.learn.base.reflect;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-08 18:01
 */
public interface InterfaceB {

    static String info(){
        return "InterfaceB info ";
    }

    default String play(){
        return "InterfaceB play";
    }
}
