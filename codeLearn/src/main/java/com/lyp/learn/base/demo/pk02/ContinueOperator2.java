package com.lyp.learn.base.demo.pk02;

public class ContinueOperator2 {
    public static void main(String[] args) {
        outer: //标签
        for(int i = 1 ; i <= 5; i++){
            for(int j = 1; j <= 6; j++){
                if(j == 3){
                    continue outer;
                }
                System.out.println("i = " + i + " , j = " + j);
            }
        }
        System.out.println("循环体外部的代码.......");
    }
}