package com.lyp.learn.dp.pattern.observerpattern3;

/**
 * 具体观察者
 */
public class OderStatusChangeObserverForCreateIndex implements OderStatusChangeObserver{
    @Override
    public void onStatusChange(OrderStatus orderStatus) {
        System.out.println("订单索引更新成功");
    }
}
