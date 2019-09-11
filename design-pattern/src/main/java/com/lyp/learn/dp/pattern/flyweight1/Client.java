package com.lyp.learn.dp.pattern.flyweight1;

/**
 *
 */
public class Client {
    public static void main(String[] args) {
        Flyweight f1 = FlyweightFactory.getFlyweight("a");
        f1.operation("移动100");

        System.out.println();

        Flyweight f2 = FlyweightFactory.getFlyweight("b");
        f2.operation("移动200");

        System.out.println();
        Flyweight f11 = FlyweightFactory.getFlyweight("a");
        f11.operation("移动10");
    }
}
