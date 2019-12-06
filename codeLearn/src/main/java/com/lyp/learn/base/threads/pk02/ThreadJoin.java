package com.lyp.learn.base.threads.pk02;

public class ThreadJoin extends Thread {

    public ThreadJoin(String name){
        super(name);
    }

    @Override
    public void run(){
        for(int i = 0 ; i <= 10; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }
}
