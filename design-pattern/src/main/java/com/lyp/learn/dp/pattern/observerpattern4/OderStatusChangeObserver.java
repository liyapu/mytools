package com.lyp.learn.dp.pattern.observerpattern4;

/**
 * @author liyapu
 * @date 2021-08-05 17:45
 * @desc
 */
public interface OderStatusChangeObserver {

    void onStatusChange(OrderStatus orderStatus);
}
