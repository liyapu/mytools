package com.lyp.learn.dp.pattern.Templatemethod2;

/**
 * 客户端使用
 */
public class Client {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        coffee.create();

        System.out.println("-----------");
        Beverage tea = new Tea();
        tea.create();
    }
}
