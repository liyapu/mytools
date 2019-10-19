package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.lyp.learn.guava.eventbus.demo1.listeners.SimpleListener;

/**
 * 简单的，一个订阅方法的
 */
public class SimpleListenerEventBusExample {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        //注册SimpleListener实例到eventBus上
        eventBus.register(new SimpleListener());

        System.out.println("eventBus post String event");
        eventBus.post("simple event test");
    }
}
