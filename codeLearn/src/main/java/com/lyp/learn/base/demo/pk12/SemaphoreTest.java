package com.lyp.learn.base.demo.pk12;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static final int THREAD_COUNT = 30;
    public static final int SEMAPHORE_COUNT = 10;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        Semaphore semaphore = new Semaphore(SEMAPHORE_COUNT);

        for(int i = 1; i <= THREAD_COUNT; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " save db");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }

                }
            });
        }

        executorService.shutdown();
    }
}
