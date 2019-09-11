package com.lyp.learn.dp.pattern.observerpattern2;

/**
 * 抽象主题
 */
public interface Subject {
    //登记一个新的观察者
    void attach(Observer obj);

    //删除一个登记过的观察者
    void detach(Object obj);

    //通知所有登记过的观察者对象
    void notifyObserver();
}
