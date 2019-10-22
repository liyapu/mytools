package com.lyp.learn.base.pk09;


public class YourDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("myThreadName");
        System.out.println(Thread.currentThread().getName()+" call myThread.run()");
        myThread.run();

        System.out.println();

        System.out.println(Thread.currentThread().getName()+" call myThread.start()");
        myThread.start();
    }
}


class MyThread extends Thread{

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "  is running");
    }
}