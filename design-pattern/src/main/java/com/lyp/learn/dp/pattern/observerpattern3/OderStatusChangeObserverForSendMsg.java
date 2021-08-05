package com.lyp.learn.dp.pattern.observerpattern3;

/**
 * 具体观察者
 */
public class OderStatusChangeObserverForSendMsg implements OderStatusChangeObserver{
    @Override
    public void onStatusChange(OrderStatus orderStatus) {
        System.out.printf("发送短信成功");
    }
}
