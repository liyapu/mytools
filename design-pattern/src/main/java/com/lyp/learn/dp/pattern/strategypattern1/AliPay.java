package com.lyp.learn.dp.pattern.strategypattern1;

/**
 * 支付宝支付类
 */
public class AliPay implements CommonPay {
    @Override
    public void pay(int money) {
        System.out.println("使用阿里支付 " + money + " 元");
    }
}
