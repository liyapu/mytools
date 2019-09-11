package com.lyp.learn.dp.pattern.observerpattern2;

import java.util.LinkedList;
import java.util.List;

/**
 * 具体主题
 */
public class ConcreteSubject implements Subject {

    //注意到这个List集合的泛型参数为Observer接口，
    // 设计原则：面向接口编程而不是面向实现编程
    private List<Observer> list = new LinkedList<>();

    private String message;


    @Override
    public void attach(Observer obj) {
        list.add(obj);
    }

    @Override
    public void detach(Object obj) {
        list.remove(obj);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : list){
            observer.update(message);
        }
    }

    //业务方法，改变状态
    public void updateMessage(String s) {
        this.message = s;
        System.out.println("被观察者状态变化了，微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
