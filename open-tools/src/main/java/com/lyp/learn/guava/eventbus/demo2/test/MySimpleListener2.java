package com.lyp.learn.guava.eventbus.demo2.test;

import com.lyp.learn.guava.eventbus.demo2.MySubscribe;

import java.util.concurrent.TimeUnit;

public class MySimpleListener2 {

    @MySubscribe
    public void test1(String event){
        System.out.println("MySimpleListener2...test1....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test2(String event){
        try {
//            TimeUnit.MINUTES.sleep(1);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MySimpleListener2...test2....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test3(Integer event){
        System.out.println("MySimpleListener2...test3....receive ：" + event);
    }

    @MySubscribe(topic = "test-topic")
    public void test4(String event){
        System.out.println("MySimpleListener2...test4....receive ：" + event);
    }
}
