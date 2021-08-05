package com.lyp.learn.dp.pattern.observerpattern4;

/**
 * @author liyapu
 * @date 2021-08-05 17:53
 * @desc
 */
public class OderStatusChangeObserverForSendMsg implements OderStatusChangeObserver {
    @Override
    public void onStatusChange(OrderStatus orderStatus) {
        System.out.printf("发送短信成功");
    }
}
