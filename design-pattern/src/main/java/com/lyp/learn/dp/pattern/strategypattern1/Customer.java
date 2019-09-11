package com.lyp.learn.dp.pattern.strategypattern1;

/**
 * 消费者类
 * 构造一个消费者类，其拥有支付pay方法。
 */
public class Customer {

    public void pay(PayType payType,int money){
        payType.getCommonPay().pay(money);
    }
}
