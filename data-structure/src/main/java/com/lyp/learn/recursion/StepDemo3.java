package com.lyp.learn.recursion;

/**
 * n阶台阶，一次走一步、两步或者三步，有多少种走法
 */
public class StepDemo3 {
    public static void main(String[] args) {
        for(int i = 1; i <= 5; i++){
            System.out.println(i + " 阶台阶时，共有走法 " + step(i) );
        }
    }

    /**
     * 计算 n 阶台阶的走法
     * @param n
     * @return
     */
    public static int step(int n){
        if(n <= 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(n == 3){
            return 4;
        }
        return step(n-1) + step(n-2) + step(n-3);
    }
}
