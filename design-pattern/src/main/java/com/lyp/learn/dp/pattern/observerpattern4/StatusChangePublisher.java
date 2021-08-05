package com.lyp.learn.dp.pattern.observerpattern4;

/**
 * @author liyapu
 * @date 2021-08-05 17:43
 * @desc
 */
public interface StatusChangePublisher {

    void register(OderStatusChangeObserver oderStatusChangeObserver);

    void unregister(OderStatusChangeObserver oderStatusChangeObserver);

    void notifyObservers();
}
