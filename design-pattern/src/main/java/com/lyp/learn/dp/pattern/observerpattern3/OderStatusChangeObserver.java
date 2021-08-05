package com.lyp.learn.dp.pattern.observerpattern3;

/**
 *  抽象 观察者
 */
public interface OderStatusChangeObserver {

    void onStatusChange(OrderStatus orderStatus);
}
