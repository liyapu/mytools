package com.lyp.learn.dp.pattern.iteratorpattern;

/**
 * 测试代码
 */
public class Client {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("张三");
        aggregate.add("李四");
        aggregate.add("王五");

        Iterator iterator = aggregate.createIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
