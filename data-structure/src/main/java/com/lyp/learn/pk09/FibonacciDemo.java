package com.lyp.learn.pk09;

public class FibonacciDemo {
    public static void main(String[] args) {
        int n = 10;
        for(int i = 1; i <= n; i++){
            System.out.print(fibonacci(i) + ",");
        }
    }

    public static int fibonacci(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
