package com.lyp.learn.base.pk02;

public class BreakOperator2 {
    public static void main(String[] args) {
        outer: //标签
        for(int i = 1 ; i <= 5; i++){
            for(int j = 1; j <= 6; j++){
                if(j == 3){
                    break outer;
                }
                System.out.println("i = " + i + " , j = " + j);
            }
        }
        System.out.println("循环体外部的代码.......");
    }
}
