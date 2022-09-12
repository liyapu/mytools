package com.lyp.learn.cf;

import java.util.concurrent.CompletableFuture;

/**
 * @author liyapu
 * @date 2021-10-30 16:03
 * @desc https://www.jianshu.com/p/6bac52527ca4
 *
 * 7、thenCombine 合并任务
 *     thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
 *
 *
 * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,?extends V> fn);
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? superU,? extends V> fn);
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? superU,? extends V> fn,Executor executor);
 *
 *
 */
public class CompletableFuture16 {

    public static void main(String[] args) throws Exception {
        testThenCombine();
    }

    private static void testThenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "world");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t + " " + u);
        System.out.println(result.get());
    }

}
