package com.lyp.learn.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;

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
            int t = new Random().nextInt(8);
            System.out.println("t1=" + t);
            return t;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 10;
            System.out.println("t2=" + t);
            return t;
        }));
        System.out.println("thenCompose result : " + f.get());
    }


    /**
     * thenApply 和 thenCompose的区别：
     * <p>
     * thenApply转换的是泛型中的类型，返回的是同一个CompletableFuture；
     * thenCompose将内部的CompletableFuture调用展开来并使用上一个CompletableFuture调用的结果在下一步的CompletableFuture调用中进行运算，是生成一个新的CompletableFuture。
     */
    @Test
    public void testThenCompose2() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(8);
            System.out.println("t1=" + t);
            return t;
        }).thenApply(result -> {
            int temp = result * 100;
            System.out.println("temp = " + temp);
            return temp;
        });
        System.out.println("testThenCompose2 cf = " + cf.join());
    }


}
