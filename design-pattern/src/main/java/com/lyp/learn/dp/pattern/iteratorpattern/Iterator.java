package com.lyp.learn.dp.pattern.iteratorpattern;

/**
 * 抽象迭代器
 */
public interface Iterator {
    //返回下一个元素
    Object next();

    //是否还有下一个元素
    boolean hasNext();
}
