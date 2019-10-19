package com.lyp.learn.guava.eventbus.demo2.test;

import com.lyp.learn.guava.eventbus.demo2.MyAsyncEventBus;
import com.lyp.learn.guava.eventbus.demo2.MyEventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MySyncSimpleListenerExample {
    public static void main(String[] args) {
        //异步执行
        MyAsyncEventBus asyncEventBus = new MyAsyncEventBus((ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
        //同步执行
//        MyEventBus asyncEventBus = new MyEventBus();
        asyncEventBus.register(new MySimpleListener());
        asyncEventBus.register(new MySimpleListener2());
        asyncEventBus.register(new MySimpleListener4());

        asyncEventBus.post("888","test-topic");
    }
}
