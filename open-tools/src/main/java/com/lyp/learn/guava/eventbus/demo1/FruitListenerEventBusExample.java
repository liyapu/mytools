package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.lyp.learn.guava.eventbus.demo1.events.Apple;
import com.lyp.learn.guava.eventbus.demo1.events.Fruit;
import com.lyp.learn.guava.eventbus.demo1.listeners.FruitEaterListener;
import com.lyp.learn.guava.eventbus.demo1.listeners.SimpleListener;

/**
 * Event 有继承关系的
 */
public class FruitListenerEventBusExample {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());

        eventBus.post(new Apple("red apple"));

        System.out.println("-------------------");
        eventBus.post(new Fruit("yellow banana"));
    }
}
