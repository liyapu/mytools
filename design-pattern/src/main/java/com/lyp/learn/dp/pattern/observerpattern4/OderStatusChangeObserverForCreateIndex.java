package com.lyp.learn.dp.pattern.observerpattern4;

/**
 * @author liyapu
 * @date 2021-08-05 17:51
 * @desc
 */
public class OderStatusChangeObserverForCreateIndex implements OderStatusChangeObserver {
    @Override
    public void onStatusChange(OrderStatus orderStatus) {
        System.out.println("订单索引更新成功");
    }
}
