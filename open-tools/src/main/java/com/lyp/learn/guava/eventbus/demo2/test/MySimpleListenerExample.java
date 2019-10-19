package com.lyp.learn.guava.eventbus.demo2.test;

import com.lyp.learn.guava.eventbus.demo2.MyEventBus;

public class MySimpleListenerExample {
    public static void main(String[] args) {
        MyEventBus myEventBus = new MyEventBus();
        myEventBus.register(new MySimpleListener());
        myEventBus.register(new MySimpleListener2());

//        myEventBus.post("xxxxxxxxx");
//        myEventBus.post("xxxxxxxxx","test-topic");
        myEventBus.post(100,"test-topic");
    }
}
