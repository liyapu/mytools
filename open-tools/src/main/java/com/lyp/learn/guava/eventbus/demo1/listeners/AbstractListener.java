package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.Subscribe;

/**
 * 抽象的 Listener
 */
public abstract class AbstractListener {

    @Subscribe
    public void commonsTask(String event){
        System.out.println("AbstractListener commonsTask receive event:"+ event);
    }
}
