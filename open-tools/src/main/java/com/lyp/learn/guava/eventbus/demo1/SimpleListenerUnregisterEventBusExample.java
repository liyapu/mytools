package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.lyp.learn.guava.eventbus.demo1.listeners.SimpleListener;

/**
 * unregister
 * 不注册某个
 */
public class SimpleListenerUnregisterEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        //注册SimpleListener实例到eventBus上
        final SimpleListener simpleListener = new SimpleListener();
        eventBus.register(simpleListener);

        eventBus.post("simple event test");
        eventBus.post("simple event test");

        eventBus.unregister(simpleListener);
        eventBus.post("simple event test");
        eventBus.post("simple event test");
        eventBus.post("simple event test");
    }
}
