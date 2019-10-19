package com.lyp.learn.guava.eventbus.demo2.test;

import com.lyp.learn.guava.eventbus.demo2.MySubscribe;

public class MySimpleListener3 {

    @MySubscribe
    public void test1(String event){
        System.out.println("MySimpleListener2...test1....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test2(String event){
        System.out.println("MySimpleListener2...test2....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test3(Integer event){
        throw new RuntimeException();
    }

    @MySubscribe(topic = "test-topic")
    public void test4(Integer event){
        System.out.println("MySimpleListener2...test4....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test5(Integer event){
        System.out.println("MySimpleListener2...test5....receive ：" + event);
    }
}
