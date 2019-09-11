package com.lyp.learn.dp.pattern.observerpattern1;

/**
 * 应用程序
 */
public class Client {
    public static void main(String[] args) {
        //创建一个主题对象(被观察者)
        ConcreteSubject subject = new ConcreteSubject();
        //创建一个观察者
        Observer observer = new ConcreteObserver();

        //登记观察者
        subject.attach(observer);
        //被观察者边表状态
        subject.change();
    }
}
