package com.lyp.learn.guava.eventbus.demo2;

import java.lang.reflect.Method;

public interface MyEventContext {

    String getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent();


//
}
