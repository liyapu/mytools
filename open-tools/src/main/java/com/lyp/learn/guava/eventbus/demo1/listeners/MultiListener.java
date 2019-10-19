package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.Subscribe;

/**
 *  @Subscribe  订阅eventBus中的Stirng 类型的 event,并且要注册上
 *
 *  两个String 订阅方法，都会受到String 类型的event
 */
public class MultiListener {

    @Subscribe
    public void strTask1(String event){
        System.out.println("MultiListener strTask1 receive event:" + event);
    }

    @Subscribe
    public void strTask2(String event){
        System.out.println("MultiListener strTask2 receive event:" + event);
    }

    @Subscribe
    //int 类型不能受到，Integer类型才可以
//    public void intTask1(int event){
    public void intTask1(Integer event){
        System.out.println("MultiListener intTask1 receive event:" + event);
    }
}
