package com.lyp.learn.base.pk12;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 08:16
 */
public class Worker extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Worker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        super.run();

        try {
            System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "开始执行");
            // 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
