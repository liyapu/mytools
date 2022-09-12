package com.lyp.learn.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author liyapu
 * @date 2021-10-30 16:03
 * @desc https://www.jianshu.com/p/6bac52527ca4
 *
 * 13、thenCompose 方法
 * thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。
 *
 *
 * public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
 * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) ;
 * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executorexecutor) ;
 *
 */
public class CompletableFuture22 {

    public static void main(String[] args) throws Exception {
        testThenCompose();
    }

    private static void testThenCompose() throws Exception {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("t1=" + t);
            return t;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 2;
            System.out.println("t2=" + t);
            return t;
        }));
        System.out.println("thenCompose result : " + f.get());
    }



}
