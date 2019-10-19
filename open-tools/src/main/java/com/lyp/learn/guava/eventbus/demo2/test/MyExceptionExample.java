package com.lyp.learn.guava.eventbus.demo2.test;

import com.lyp.learn.guava.eventbus.demo2.MyEventBus;

public class MyExceptionExample {
    public static void main(String[] args) {
        MyEventBus myEventBus = new MyEventBus(((cause, context) -> {
            System.out.println(cause);
            System.out.println("----------------------------");
            System.out.println(context.getSubscribe());
            System.out.println(context.getEvent());
            System.out.println(context.getSource());
            System.out.println(context.getSubscriber());
        }));

        myEventBus.register(new MySimpleListener3());

        myEventBus.post(888,"test-topic");
    }
}
