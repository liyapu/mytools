package com.lyp.learn.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author liyapu
 * @date 2021-10-30 16:03
 * @desc https://www.jianshu.com/p/6bac52527ca4
 * 3. thenApply 方法
 * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
 *
 * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 * Function<? super T,? extends U>
 * T：上一个任务返回结果的类型
 * U：当前任务的返回值类型
 *
 */
public class CompletableFuture12 {

    public static void main(String[] args) throws Exception {
        testThenApply();
    }

    private static void testThenApply() throws Exception {
        CompletableFuture<Long> cf = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1 = " + result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t * 5;
                System.out.println("result2 = " + result);
                return result;
            }
        });

        //第二个任务依赖第一个任务的结果。
        long result = cf.get();
        System.out.println(result);
    }

}
