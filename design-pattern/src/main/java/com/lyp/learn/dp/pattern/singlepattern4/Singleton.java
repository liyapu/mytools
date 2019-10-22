package com.lyp.learn.dp.pattern.singlepattern4;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 08:00
 *
 * 静态内部类
 *
 * 序列化可能会破坏单例模式，比较每次反序列化一个序列化的对象实例时都会创建一个新的实例
 */
public class Singleton {
    //私有静态类
    private static class Holder{
        //私有，全局
        private static Singleton instance = new Singleton();
    }

    private Singleton(){
    }

    public static Singleton getInstance(){
        return Holder.instance;
    }
}
