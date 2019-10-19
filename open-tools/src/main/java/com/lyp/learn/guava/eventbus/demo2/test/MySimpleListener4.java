package com.lyp.learn.guava.eventbus.demo2.test;

import com.lyp.learn.guava.eventbus.demo2.MySubscribe;

public class MySimpleListener4 {

    @MySubscribe(topic = "test-topic")
    public void test1(String event){
        System.out.println("MySimpleListener4...test1....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test2(String event){
        System.out.println("MySimpleListener4...test2....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test3(String event){
        System.out.println("MySimpleListener4...test3....receive ：" + event);
    }
}
