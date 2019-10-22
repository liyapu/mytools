package com.lyp.learn.base.pk09;

public class LockTest1 {
    public static void main(String[] args) {
        Something x = new Something();

        Thread t1 = new Thread(()-> x.synMethodA(),"t1");
        Thread t2 = new Thread(()-> x.synMethodB(),"t2");

        t1.start();
        t2.start();
    }
}
