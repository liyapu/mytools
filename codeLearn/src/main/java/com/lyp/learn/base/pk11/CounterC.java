package com.lyp.learn.base.pk11;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterC {
    public static  int num = 0;
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);

    Lock lock = new ReentrantLock();
    public void increase(){
        lock.lock();
        try {
            num++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String []args) throws InterruptedException {
        CounterC counterC = new CounterC();
        //开启30个线程进行累加操作
        for(int i=0;i<30;i++){
            new Thread(){
                @Override
                public void run(){
                    for(int j=0;j<10000;j++){
                        counterC.increase();//自加操作
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        //等待计算线程执行完
        countDownLatch.await();
        System.out.println(num);
    }
}
