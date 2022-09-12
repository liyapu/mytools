package com.lyp.learn.base.jucatguigu.cf01;

import com.lyp.learn.base.executors.pk01.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author liyapu
 * @date 2022-06-03 17:59
 * @description
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //m1();
        m2();
    }

    private static void m2() throws InterruptedException, ExecutionException {
        //3个任务，分别在线程中执行，请问耗时多少？
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "task1";
        });

        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "task2";
        });

        FutureTask<String> futureTask3 = new FutureTask<String>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "task3";
        });

        executorService.submit(futureTask1);
        executorService.submit(futureTask2);
        executorService.submit(futureTask3);

        String result1 = futureTask1.get();
        String result2 = futureTask2.get();
        String result3 = futureTask3.get();

        long endTime = System.currentTimeMillis();
        System.out.println("-----花费时长:" + (endTime - startTime) + "毫秒");
        System.out.println(Thread.currentThread().getName() + "----线程结束");

        //生产环境，一般没有关闭的，线程池创建后，一直随着jvm启动和关闭
        executorService.shutdown();
    }

    private static void m1() {
        //3个任务，都在main线程中执行，请问耗时多少？
        long startTime = System.currentTimeMillis();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(400);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("-----花费时长:" + (endTime - startTime) + "毫秒");
        System.out.println(Thread.currentThread().getName() + "----线程结束");
    }

}
