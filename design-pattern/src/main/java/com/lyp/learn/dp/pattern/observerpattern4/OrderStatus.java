package com.lyp.learn.dp.pattern.observerpattern4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyapu
 * @date 2021-08-05 17:46
 * @desc
 */
public enum OrderStatus implements StatusChangePublisher {
    CREATE_SUCCESS("创建成功"),
    REFUND_SUCCESS("退款成功"),
    ;
    private String desc;

    OrderStatus(String desc){
        this.desc = desc;
    }

    private List<OderStatusChangeObserver> observers = new ArrayList<>();

    @Override
    public void register(OderStatusChangeObserver oderStatusChangeObserver) {
        observers.add(oderStatusChangeObserver);
    }

    @Override
    public void unregister(OderStatusChangeObserver oderStatusChangeObserver) {
        observers.remove(oderStatusChangeObserver);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(oderStatusChangeObserver -> oderStatusChangeObserver.onStatusChange(this));
    }
}
