package com.lyp.learn.base.juc.pk01;

import java.util.concurrent.CountDownLatch;

public class CounterD {
    public  int num = 0;
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);

    public synchronized void increase(){
        num++;
    }
    public static void main(String []args) throws InterruptedException {
        CounterD counterD = new CounterD();
        //开启30个线程进行累加操作
        for(int i=0;i<30;i++){
            new Thread(){
                @Override
                public void run(){
                    for(int j=0;j<10000;j++){
                        counterD.increase();//自加操作
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        //等待计算线程执行完
        countDownLatch.await();
        System.out.println(counterD.num);
    }
}
