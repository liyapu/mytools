package com.lyp.learn.dp.pattern.observerpattern3;

/**
 * @author liyapu
 * @date 2021-08-05 17:57
 * @desc
 */
public  class RefundProcessWorkFlow {

    public void refund(Object param){
        System.out.println("退款成功");
        OrderStatus.REFUND_SUCCESS.notifyObservers();
    }
}
