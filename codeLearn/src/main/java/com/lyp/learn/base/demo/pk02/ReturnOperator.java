package com.lyp.learn.base.demo.pk02;

public class ReturnOperator {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        int sum = doSum(a,b);
        System.out.println("sum is = " + sum);
    }

    public static int doSum(int num1,int num2){
        int result = num1 + num2;
        return result;
    }
}
