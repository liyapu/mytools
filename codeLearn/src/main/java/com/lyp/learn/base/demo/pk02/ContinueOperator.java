package com.lyp.learn.base.demo.pk02;

public class ContinueOperator {
    public static void main(String[] args) {
        int count = 10;//循环次数
        int target = 3; //寻找的目标
        for(int i = 1; i <= count; i++){
            if(i % target == 0){
                System.out.println("找到目标");
                continue;
            }
            System.out.println("i is " + i);
        }
    }
}
