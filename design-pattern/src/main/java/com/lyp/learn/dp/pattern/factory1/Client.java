package com.lyp.learn.dp.pattern.factory1;

public class Client {
    public static void main(String[] args) throws Exception {
        Operation add = EasyFactory.createOperation("+");
        Operation sub = EasyFactory.createOperation("-");
        Operation mul = EasyFactory.createOperation("*");
        Operation div = EasyFactory.createOperation("/");

        System.out.println(add.getResult(20,10));
        System.out.println(sub.getResult(20,10));
        System.out.println(mul.getResult(20,10));
        System.out.println(div.getResult(20,10));
    }
}
