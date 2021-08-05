package com.lyp.learn.dp.pattern.observerpattern4;

/**
 * @author liyapu
 * @date 2021-08-05 17:56
 * @desc
 */
public  class CreateProcessWorkFlow {

    public void createOrder(Object param){
        System.out.println("创建订单");
        OrderStatus.CREATE_SUCCESS.notifyObservers();
    }

}
