package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.Subscribe;

/**
 * 具体的 Listener
 */
public  class ConcreteListener extends BaseListener{

    @Subscribe
    public void concreteTask(String event){
        System.out.println("ConcreteListener concreteTask receive event:"+ event);
    }
}
