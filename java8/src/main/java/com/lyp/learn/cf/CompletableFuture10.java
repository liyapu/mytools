package com.lyp.learn.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author liyapu
 * @date 2021-10-30 16:03
 * @desc  https://www.jianshu.com/p/6bac52527ca4
 *
 *   1、 runAsync 和 supplyAsync方法
 *     CompletableFuture 提供了四个静态方法来创建一个异步操作。
 *          public static CompletableFuture<Void> runAsync(Runnable runnable)
 *          public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 *          public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 *          public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 *          没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。如果指定线程池，则使用指定的线程池运行。以下所有的方法都类同。
 *              runAsync方法不支持返回值。
 *              supplyAsync可以支持返回值。
 *
 *              xxx 执行当前任务的线程执行继续执行的任务
 *              xxxAsync 使用底层线程池或者指定的线程池去执行
 *
 */
public class CompletableFuture10 {

    public static void main(String[] args) throws Exception {
//        runAsync();

        supplyAsync();
    }

    //无返回值
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });

        future.get();
    }

    //有返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = "+time);
    }
}
