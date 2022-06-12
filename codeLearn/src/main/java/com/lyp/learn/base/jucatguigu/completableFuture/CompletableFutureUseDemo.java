package com.lyp.learn.base.jucatguigu.completableFuture;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author liyapu
 * @date 2022-06-12 17:03
 * @description
 */
public class CompletableFutureUseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //future1();
        completableFuture1();
    }

    /**
     * CompletableFuture 模拟 FutureTask 实现的功能
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println();
            System.out.println("子线程===" + Thread.currentThread().getName() + " come in .....");
            int num = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程===产生 num " + num);
            System.out.println();
            return num;
        });

        System.out.println("main线程 ==== 去做其它事情 == " + Thread.currentThread().getName());
        System.out.println("main线程 获取结果 === " + integerCompletableFuture.get());
    }

    private static void completableFuture1() throws InterruptedException {
        // 线程池使用默认的
        //CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
        //    System.out.println();
        //    System.out.println("子线程===" + Thread.currentThread().getName() + " come in .....");
        //    int num = ThreadLocalRandom.current().nextInt(10);
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    System.out.println("子线程===产生 num " + num);
        //    int i = 1 / 0;
        //    System.out.println();
        //    return num;
        //}).whenComplete((resultNum, throwException) -> {
        //    System.out.println("whenComplete ====--- come in ");
        //    if (Objects.isNull(throwException)) {
        //        // 异常为空，即没有异常时
        //        System.out.println("whenComplete ==== 获取结果 ： " + resultNum);
        //    }
        //    System.out.println("whenComplete ====---  " + throwException.getCause());
        //
        //}).exceptionally(ex -> {
        //    ex.printStackTrace();
        //    System.out.println("exceptionally 异常：" + ex.getCause() + "\t" + ex.getMessage());
        //    return null;
        //});

        //线程池，使用自己 指定的
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println();
            System.out.println("子线程===" + Thread.currentThread().getName() + " come in .....");
            int num = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程===产生 num " + num);
            int i = 1 / 0;
            System.out.println();
            return num;
        }, executorService).whenComplete((resultNum, throwException) -> {
            System.out.println("whenComplete ====--- come in ");
            if (Objects.isNull(throwException)) {
                // 异常为空，即没有异常时
                System.out.println("whenComplete ==== 获取结果 ： " + resultNum);
            }
            System.out.println("whenComplete ====---  " + throwException.getCause());

        }).exceptionally(ex -> {
            ex.printStackTrace();
            System.out.println("exceptionally 异常：" + ex.getCause() + "\t" + ex.getMessage());
            return null;
        });

        //注意：
        //主线程不要立刻结束，否则 CompletableFuture 默认使用的线程池会立刻关闭，导致 whenComplete 拿不到结果
        //故：这里暂停3秒钟
        TimeUnit.SECONDS.sleep(3);
    }


}
