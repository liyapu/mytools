package com.lyp.learn.dp.pattern.singlepattern2;

/**
 * 饿汉式单例模式
 * 第一次使用时，才进行初始化
 */
public class Singleton {
    //私有静态属性，开始为null
    private static Singleton instance = null;

    //私有构造方法，保证外界无法直接实例化
    private Singleton(){
    }

    //静态方法，通过此方法获取实例，
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
