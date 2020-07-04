package com.lyp.learn.recursion;

public class factorialDemo {
    public static void main(String[] args) {
        System.out.println(factorial(0));
        System.out.println(factorial(1));
        System.out.println(factorial(5));
    }

    /**
     * 阶乘方法
     * @param n
     * @return
     */
    public static int factorial(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        return n * factorial(n-1);
    }
}
