package com.lyp.learn.dp.pattern.observerpattern1;

import java.util.LinkedList;
import java.util.List;

/**
 * 具体主题
 */
public class ConcreteSubject implements Subject{

    private List<Observer> list = new LinkedList<>();


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
            observer.update();
        }
    }

    //业务方法，改变状态
    public void change(){
        System.out.println("被观察者状态变化了");
        //通知
        notifyObserver();
    }
}
