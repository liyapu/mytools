package com.lyp.learn.dp.pattern.adapterpattern1;

public class Client {
    public static void main(String[] args) {
        //适配器模式应用
        Target target = new Adapter();
        target.request();
    }
}
