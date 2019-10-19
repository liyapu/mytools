package com.lyp.learn.guava.eventbus.demo2.test;

import com.lyp.learn.guava.eventbus.demo2.MySubscribe;

public class MySimpleListener {

    @MySubscribe
    public void test1(String event){
        System.out.println("MySimpleListener...test1....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test2(String event){
        System.out.println("MySimpleListener...test2....receive ：" + event);
    }
}
