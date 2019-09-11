package com.lyp.learn.dp.pattern.strategypattern1;

/**
 * 微信支付
 */
public class WxPay implements CommonPay {
    @Override
    public void pay(int money) {
        System.out.println("使用微信支付 " + money + " 元");
    }
}
