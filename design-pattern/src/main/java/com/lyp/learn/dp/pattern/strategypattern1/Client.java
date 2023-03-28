package com.lyp.learn.dp.pattern.strategypattern1;

/**
 * 策略模式，英文全称是Strategy Design Pattern。在GoF的《设计模式》一书中，它是这样定义的：
 * Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
 * 翻译成中文就是：定义一族算法类，将每个算法分别封装起来，让它们可以互相替换。策略模式可以使算法的变化独立于使用它们的客户端（这里的客户端代指使用算法的代码）。
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
