package com.lyp.learn.dp.pattern.singlepattern1;

/**
 * 饿汉式单例模式
 * 一上来就进行初始化
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
