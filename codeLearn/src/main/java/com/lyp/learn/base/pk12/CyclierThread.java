package com.lyp.learn.base.pk12;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclierThread extends Thread {

    private CyclicBarrier barrier;

    public CyclierThread(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run(){
        try {
            System.out.println(Thread.currentThread().getName() + " wait for cyclicBarrier");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " work....end");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
