package com.lyp.learn.base.pk12;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo3 {
    public static void main(String[] args) {
        int threadCount = 100;
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);

        for(int i = 0; i < threadCount; i++){
            System.out.println(Thread.currentThread().getName() + " 创建工作线程" + i);
            executorService.submit(new Worker(cyclicBarrier));
        }

        executorService.shutdown();
    }
}
