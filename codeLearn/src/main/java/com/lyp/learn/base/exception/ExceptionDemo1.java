package com.lyp.learn.base.exception;

public class ExceptionDemo1 {
    public static void main(String[] args) {
        System.out.println("main method 开始------");
        new ExceptionDemo1().test();
        System.out.println("main method 结束------");
    }

    public void test(){
        System.out.println("test method 开始....");
        divideZero();
        System.out.println("test method 结束....");
    }

    public void divideZero(){
        int a = 10;
        int b = 0;
        int result = a / b;
        System.out.println("result is :" + result);
    }


}
