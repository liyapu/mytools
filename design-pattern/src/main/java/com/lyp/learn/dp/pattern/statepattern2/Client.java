package com.lyp.learn.dp.pattern.statepattern2;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        //新建电视对象
        TV tv = new TV();
        //换台
        tv.disCCTV2();
        tv.disCCTV3();
        tv.disCCTV1();
    }
}
