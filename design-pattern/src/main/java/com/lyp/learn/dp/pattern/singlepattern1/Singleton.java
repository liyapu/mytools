package com.lyp.learn.dp.pattern.singlepattern1;

/**
 * 单例设计模式（Singleton Design Pattern）理解起来非常简单。一个类只允许创建一个对象（或者实例），那这个类就是一个单例类，
 * 这种设计模式就叫作单例设计模式，简称单例模式。
 * <p>
 * 饿汉式单例模式
 * 一上来就进行初始化
 * <p>
 * 序列化可能会破坏单例模式，比较每次反序列化一个序列化的对象实例时都会创建一个新的实例
 */
public class Singleton {
    //静态实例，可以类级别引用
    private static Singleton instance = new Singleton();

    //构造方法私有，保证外部无法直接实例化
    private Singleton(){
    }

    //静态方法，通过该方法获得实例对象
    public static Singleton getInstance(){
        return instance;
    }
}
