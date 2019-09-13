package com.lyp.learn.base.demo.pk12;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo2 {
    public static void main(String[] args) {
        int threadCount = 3;
        CyclicBarrier barrier = new CyclicBarrier(threadCount, new Runnable() {
            @Override
            public void run() {
                System.out.println("cyclibarrier thread count is :" + threadCount);
            }
        });

        System.out.println(Thread.currentThread().getName() + " start");

        for(int i = 0 ; i < threadCount; i++){
            new CyclierThread(barrier).start();
        }

        System.out.println(Thread.currentThread().getName() + " end");
    }
}
