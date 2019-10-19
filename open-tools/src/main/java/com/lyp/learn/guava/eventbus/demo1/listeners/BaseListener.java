package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.Subscribe;

/**
 * 基础的 Listener
 */
public  class BaseListener extends AbstractListener{

    @Subscribe
    public void baseTask(String event){
        System.out.println("BaseListener baseTask receive event:"+ event);
    }
}
