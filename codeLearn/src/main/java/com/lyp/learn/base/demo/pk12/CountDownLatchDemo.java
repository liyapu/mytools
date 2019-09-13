package com.lyp.learn.base.demo.pk12;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private static int threadCount = 3;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(threadCount);

        System.out.println(Thread.currentThread().getName() + " start ....");
        for(int i = 1 ; i <= threadCount; i++){
            Work work = new Work(latch);
            new Thread(work).start();
        }

        //阻塞线程，不在往下执行
        latch.await();

        System.out.println(Thread.currentThread().getName() + " end....");

    }
}



class Work implements Runnable{
    private CountDownLatch latch;

    public Work(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            doWork();
        }catch (Exception e){
            //这里报错，countDown 还是会减一，有错也不知道了
        }finally {
            latch.countDown();
        }
    }

    private void doWork() {
        System.out.println(Thread.currentThread().getName() + " do work");
    }
}
