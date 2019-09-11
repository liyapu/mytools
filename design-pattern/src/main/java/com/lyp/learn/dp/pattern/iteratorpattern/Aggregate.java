package com.lyp.learn.dp.pattern.iteratorpattern;

/**
 * 抽象聚集
 */
public interface Aggregate {
    //添加元素
    void add(Object obj);

    //创建具体的迭代器
    Iterator createIterator();
}
