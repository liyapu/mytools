package com.lyp.learn.pk5;

/**
 *  -XX:+PrintFlagsInitial -XX:+PrintFlagsFinal -XX:+PrintGCDetails
 *
 *  -XX:+PrintFlagsInitial : 查看所有的参数的默认初始值
 *  -XX:+PrintFlagsFinal  ： 查看索引的参数的最终值(可能会存在修改，不再是初始值)
 */
public class HeapParamTest {
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
