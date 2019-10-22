package com.lyp.learn.base.pk08;

class SynchronizedIn extends Thread{

    @Override
    public void run(){
        method1();
    }

    private synchronized void method1(){
        System.out.println("method1");
        method2();
    }

    private synchronized void method2(){
        System.out.println("method2");
        method3();
    }

    private synchronized void method3(){
        System.out.println("method3");
    }
}

public class SynchronizedInTest {
    public static void main(String[] args) {
        SynchronizedIn synchronizedIn = new SynchronizedIn();
        synchronizedIn.start();
    }
}
