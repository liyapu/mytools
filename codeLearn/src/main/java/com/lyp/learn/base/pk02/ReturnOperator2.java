package com.lyp.learn.base.pk02;

public class ReturnOperator2 {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        compareNum(a,b);
        System.out.println("比较大小之后执行的");
    }

    public static void compareNum(int num1,int num2){
        if(num1 >= num2){
            System.out.println(num1 + " 大于等于 " + num2);
        }else{
            System.out.println(num1 + " 小于 " + num2);
        }
    }
}
