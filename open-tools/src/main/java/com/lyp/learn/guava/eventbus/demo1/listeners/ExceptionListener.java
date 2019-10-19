package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.Subscribe;

/**
 */
public class ExceptionListener {

    @Subscribe
    public void m1(String event){
        System.out.println("=========m1=======" + event);
    }

    @Subscribe
    public void m2(String event){
        System.out.println("=========m2=======" + event);
    }


    @Subscribe
    public void m3(String event){
        System.out.println("=========m3=======" + event);
        throw new RuntimeException();
    }
}
