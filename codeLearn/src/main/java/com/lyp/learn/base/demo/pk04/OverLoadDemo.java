package com.lyp.learn.base.demo.pk04;

public class OverLoadDemo {

    /**
     * 三个info方法构成重载
     */
    public void info(){
        System.out.println("info method params is none");
    }
    public void info(String msg){
        System.out.println("info method params is one : " + msg);
    }

    public void info(String str1,String str2){
        System.out.println("info method params is two : " + str1 + ",: " + str2);
    }

    /**
     * 两个sum方法构成重载
     * @param a
     * @param b
     */
    public void sum(int a,int b){
        System.out.println( " sum int is :" + (a + b));
    }

    public void sum(short a,short b){
        System.out.println( " sum short is :" + (a + b));
    }

    public static void main(String[] args) {
        OverLoadDemo old = new OverLoadDemo();
        old.info();
        old.info("hello");
        old.info("hello","world");
        System.out.println();

        old.sum(6,8);
        /**
         * 因为编译器一看到整数，就会把它当作int类型。
         * 所以当把整数传进来的时候，编译器首先调用的是sum(int a , int b)这个方法。
         * 而要想调用sum(short a ,short b)这个方法,要按下面的方式写
         */
        short a = 1, b = 2;
        old.sum(a,b);
    }

}
