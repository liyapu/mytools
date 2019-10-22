package com.lyp.learn.base.pk02;

public class DoWhileOperator {
    public static void main(String[] args) {
        int count = 5; //循环的上限
        int i = 1; //循环的指示器
        do{
            System.out.println("i is : " + i);
            i++;
        }while(i <= 5);
    }
}
