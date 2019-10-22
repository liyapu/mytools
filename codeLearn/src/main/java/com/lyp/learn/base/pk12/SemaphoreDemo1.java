package com.lyp.learn.base.pk12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo1 {
    public static void main(String[] args) {
         Semaphore semaphore = new Semaphore(10);
        //创建线程池
        ExecutorService servicePool = Executors.newFixedThreadPool(3);
        //在线程池中执行任务
        servicePool.execute(new SemaphoreThread(semaphore,5));
        servicePool.execute(new SemaphoreThread(semaphore,4));
        servicePool.execute(new SemaphoreThread(semaphore,7));
        //关闭池
        servicePool.shutdown();

    }
}
