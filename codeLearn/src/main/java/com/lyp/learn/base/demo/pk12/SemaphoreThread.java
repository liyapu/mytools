package com.lyp.learn.base.demo.pk12;

import java.util.concurrent.Semaphore;

public class SemaphoreThread extends Thread{
    // 信号量
    private Semaphore semaphore;
    // 申请信号量的大小
    private int count;

    public SemaphoreThread(Semaphore semaphore,int count){
        this.semaphore = semaphore;
        this.count = count;
    }

    @Override
    public void run(){
        try {
            // 从信号量中获取count个许可
            semaphore.acquire(count);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " acuire count = " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放给定数目的许可，将其返回到信号量
            semaphore.release(count);
            System.out.println(Thread.currentThread().getName() + " release count = " + count);
        }
    }



}
