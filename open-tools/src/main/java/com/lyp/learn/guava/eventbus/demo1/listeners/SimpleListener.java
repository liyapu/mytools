package com.lyp.learn.guava.eventbus.demo1.listeners;

import com.google.common.eventbus.Subscribe;

/**
 *  @Subscribe  订阅eventBus中的Stirng 类型的 event,并且要注册上
 *
 */
public class SimpleListener {

    /**
     * 订阅Listener特点
     * 1. SimpleListener 就是一个平常的类
     * 2. 订阅方法要声明@Subscribe注解
     * 3. 方法是public void
     * 4. 方法只能有一个参数
     */
    @Subscribe
    public void doAction(String event){
        System.out.println("SimpleListener doAction receive event:" + event);
    }
}
