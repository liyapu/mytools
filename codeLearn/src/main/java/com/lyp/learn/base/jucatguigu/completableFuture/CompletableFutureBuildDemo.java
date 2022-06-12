package com.lyp.learn.base.jucatguigu.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liyapu
 * @date 2022-06-12 15:51
 * @description
 */
public class CompletableFutureBuildDemo {

    /**
     * CompletableFuture
     * 通过静态方法获取时
     * 1. runAsync 无返回值
     * runAsync(Runnable runnable)
     * runAsync(Runnable runnable, Executor executor)
     * <p>
     * Executor executor 参数说明：
     * 如果没有指定 Executor的方法，直接使用默认的 ForkJoinPool.commonPool()作为它的线程池执行异步代码
     * 如果指定了线程池，则使用我们自定义的或者特别指定的线程池执行异步代码
     * 2  supplyAsync 有返回值
     * supplyAsync(Supplier<U> supplier)
     * supplyAsync(Supplier<U> supplier, Executor executor)
     * <p>
     * <p>
     * 从 Java8 开始引入 CompletableFuture,它是 Future的功能增强版，减少阻塞和轮询
     * 可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法
     *
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //0. 创建方式,通过构造器，一般不用
        //CompletableFuture completableFuture = new CompletableFuture();

        //1. 创建方式  runAsync 无返回值
        //CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    System.out.println("子线程======" + Thread.currentThread().getName());
        //});
        //
        //System.out.println(voidCompletableFuture1.get());
        //System.out.println("main线程======" + Thread.currentThread().getName());
        //System.out.println("------------");
        //System.out.println();
        //System.out.println();

        //1. 创建方式  runAsync 无返回值，指定线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> {
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    System.out.println("子线程======" + Thread.currentThread().getName());
        //}, executorService);
        //System.out.println("main线程======" + Thread.currentThread().getName());

        //2. 创建方式  supplyAsync 有返回值
        //CompletableFuture<String> strCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
        //    try {
        //        TimeUnit.SECONDS.sleep(1);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    System.out.println("子线程======" + Thread.currentThread().getName());
        //    return "hello supplyAsync";
        //});
        //System.out.println("异步回调结果：" + strCompletableFuture1.get());
        //System.out.println("main线程======" + Thread.currentThread().getName());

        CompletableFuture<String> strCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程======" + Thread.currentThread().getName());
            return "hello supplyAsync";
        }, executorService);
        System.out.println("异步回调结果：" + strCompletableFuture2.get());
        System.out.println("main线程======" + Thread.currentThread().getName());

        executorService.shutdown();
    }
}
