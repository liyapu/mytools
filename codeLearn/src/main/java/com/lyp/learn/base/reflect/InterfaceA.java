package com.lyp.learn.base.reflect;

public interface InterfaceA {
    String hello = "interface A hello";

    Integer amount = 1;
    int  sum = 100;

    /**
     * 打印信息，抽象方法
     */
    String printMessage();

    /**
     * 接口中的静态方法
     * @return
     */
    static String sayHello(){
        return hello;
    }

    /**
     * 接口中的默认实现方法
     */
    default String info(){
        return "InterfaceA info";
    }
}
