package com.lyp.learn.dp.pattern.singlepattern3;

/**
 *  枚举实现单例
 */
public enum SingletonEnum {
    SINGLETON;

    public static SingletonEnum getInstance(){
        return SINGLETON;
    }
}
