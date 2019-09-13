package com.lyp.learn.base.demo.pk01;

public class VariableScope {
    public static void main(String[] args) {
        //num 的作用域在 main{}中
        int num = 10;
        if(num == 10){
            // sum 的作用域在 if{}中
            int sum = num * num;
            System.out.println(sum + " = " + num + " * " + num);
        }
        //sum 的作用域在 if{}中，此处超出了sum的作用域，访问不到
        //Cannot resolve symbol 'sum'
       // sum = 1;
        System.out.println("num is :" + num);
    }
}
