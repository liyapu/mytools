package com.lyp.learn.dp.pattern.strategypattern1;

/**
 *
 */
public class Client {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.pay(PayType.ALI_APY,100);

        System.out.println();
        customer.pay(PayType.WX_PAY,100);

        System.out.println();
        customer.pay(PayType.UNION_PAY,100);
    }
}
