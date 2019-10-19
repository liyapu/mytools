package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.lyp.learn.guava.eventbus.demo1.listeners.ConcreteListener;

/**
 * 继承的订阅
 * Listener多级继承，注册最具体的，那么它的父类都会接收到这个事件
 */
public class InheritListenerEventBusExample {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        //注册ConcreteListener实例到eventBus上
        eventBus.register(new ConcreteListener());

        System.out.println("eventBus post String event");
        eventBus.post("concrete event test");
    }
}
