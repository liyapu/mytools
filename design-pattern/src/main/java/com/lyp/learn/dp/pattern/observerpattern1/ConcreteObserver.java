package com.lyp.learn.dp.pattern.observerpattern1;

/**
 * 具体观察者
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        System.out.println("观察者收到了通知，并进行自身更新");
    }
}
