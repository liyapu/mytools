package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 */
public class DeadEventListener {

    /**
     * DeadEvent subscribe 要求参数必须是 DeadEvent
     */
    @Subscribe
    public void doAction(DeadEvent event){
        System.out.println(event.getEvent());
        System.out.println(event.getSource());
    }
}
