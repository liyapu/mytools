package com.lyp.learn.base.threads.pk02;


public class MyRun implements Runnable {
    private  int i ;
    @Override
    public void run() {
        for(; i <= 10; i++){
            System.out.println(Thread.currentThread().getName()+" : " + i);
        }
    }
}
