package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

public class BlockListener {

    @Subscribe
    public void test1(String event){
        System.out.println("BlockListener test1 event :" + event);
    }

    @Subscribe
    public void test2(String event){
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("BlockListener test2 event :" + event);
    }

    @Subscribe
    public void test3(String event){
        System.out.println("BlockListener test3 event :" + event);
    }

    @Subscribe
    public void test4(String event){
        System.out.println("BlockListener test4 event :" + event);
    }
}
