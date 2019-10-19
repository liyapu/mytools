package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.lyp.learn.guava.eventbus.demo1.listeners.DeadEventListener;

public class DeadEventListenerEventBusExample {
    public static void main(String[] args) {
//        EventBus eventBus = new EventBus();
        EventBus eventBus = new EventBus("event bus name set");
        eventBus.register(new DeadEventListener());

        eventBus.post("hello");
    }
}
