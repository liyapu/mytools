package com.lyp.learn.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @author liyapu
 * @date 2021-10-30 16:03
 * @desc https://www.jianshu.com/p/6bac52527ca4
 *
 * 6、thenRun 方法
 * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
 *
 * public CompletionStage<Void> thenRun(Runnable action);
 * public CompletionStage<Void> thenRunAsync(Runnable action);
 * public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
 *
 *
 */
public class CompletableFuture15 {

    public static void main(String[] args) throws Exception {
        testThenRun();
    }

    /**
     * 该方法同 thenAccept 方法类似。不同的是上个任务处理完成后，并不会把计算的结果传给 thenRun 方法。只是处理玩任务后，执行 thenAccept 的后续操作。
     */
    public static void testThenRun() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenRun(() -> {
            System.out.println("thenRun ...");
        });
        future.get();
    }
}
