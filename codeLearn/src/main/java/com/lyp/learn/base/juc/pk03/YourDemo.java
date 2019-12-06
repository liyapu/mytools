package com.lyp.learn.base.juc.pk03;


import com.lyp.learn.base.threads.pk01.Thread;

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