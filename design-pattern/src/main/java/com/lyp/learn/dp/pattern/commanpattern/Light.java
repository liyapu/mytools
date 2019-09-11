package com.lyp.learn.dp.pattern.commanpattern;

/**
 * 接受者对象Receiver
 * 具体的电灯类
 */
public class Light {

    /**
     * 开灯
     */
    public void lightOn(){
        System.out.println("电灯打开了");
    }

    /**
     * 关灯
     */
    public void lightOff(){
        System.out.println("电灯关上了");
    }
}
