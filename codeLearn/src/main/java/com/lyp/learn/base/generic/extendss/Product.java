package com.lyp.learn.base.generic.extendss;

/**
 * 上限边界是 Number 类
 * @param <T>
 */
public class Product<T extends Number> {
    private T key;

    public Product(T key){
        this.key = key;
    }

    public T getKey() {
        return key;
    }
}
