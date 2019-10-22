package com.lyp.learn.base.pk09;

public class LockTest2 {
    public static void main(String[] args) {
        Something x = new Something();
        Something y = new Something();

        Thread t1 = new Thread(()-> x.synMethodA(),"t1");
        Thread t2 = new Thread(()-> y.synMethodA(),"t2");

        t1.start();
        t2.start();
    }
}
