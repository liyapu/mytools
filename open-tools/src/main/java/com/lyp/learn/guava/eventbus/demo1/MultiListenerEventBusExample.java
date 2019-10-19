package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.lyp.learn.guava.eventbus.demo1.listeners.MultiListener;

/**
 * 多个订阅方法的
 */
public class MultiListenerEventBusExample {

    public static void main(String[] args) {
      final   EventBus eventBus = new EventBus();
        //注册MultiListener实例到eventBus上
        eventBus.register(new MultiListener());

        System.out.println("eventBus post String event");
        eventBus.post("multi event test");

        System.out.println("eventBus post int event");
        eventBus.post(1000);
    }
}
