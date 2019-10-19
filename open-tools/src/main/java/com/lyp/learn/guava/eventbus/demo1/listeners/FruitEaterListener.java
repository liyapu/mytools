package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.Subscribe;
import com.lyp.learn.guava.eventbus.demo1.events.Apple;
import com.lyp.learn.guava.eventbus.demo1.events.Fruit;

/**
 *  吃水果Listener
 */
public class FruitEaterListener {

    @Subscribe
    public void eat(Fruit event){
        System.out.println("Fruit eat :" + event);
    }

    @Subscribe
    public void eat(Apple event){
        System.out.println("Apple eat :" + event);
    }
}
