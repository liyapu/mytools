package com.lyp.learn.dp.pattern.observerpattern1;

/**
 * 应用程序
 * 观察者模式（Observer Design Pattern）也被称为发布订阅模式（Publish-Subscribe Design Pattern）。在GoF的《设计模式》一书中，它的定义是这样的：
 * Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
 * 翻译成中文就是：在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。
 * 一般情况下，被依赖的对象叫作被观察者（Observable），依赖的对象叫作观察者（Observer）。不过，在实际的项目开发中，这两种对象的称呼是比较灵活的，有各种不同的叫法，比如：Subject-Observer、Publisher-Subscriber、Producer-Consumer、EventEmitter-EventListener、Dispatcher-Listener。不管怎么称呼，只要应用场景符合刚刚给出的定义，都可以看作观察者模式。
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
