package com.lyp.learn.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @author liyapu
 * @date 2021-10-30 16:03
 * @desc https://www.jianshu.com/p/6bac52527ca4
 * 4、 handle 方法
 * handle 是执行任务完成时对结果的处理。
 * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
 *
 * public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
 *
 *
 */
public class CompletableFuture13 {

    public static void main(String[] args) throws Exception {
        testHandle();
    }

    /**
     * 从示例中可以看出，在 handle 中可以根据任务是否有异常来进行做相应的后续处理操作。
     * 而 thenApply 方法，如果上个任务出现错误，则不会执行 thenApply 方法。
     */

    public static void testHandle() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
//                int i = 10 / 0;
                int i = 10 / 2;
                return new Random().nextInt(10);
            }
        }).handle((param, throwable) -> {
            int result = -1;
            if (throwable == null) {
                result = param * 2;
            } else {
                System.out.println(throwable.getMessage());
            }
            return result;
        });
        System.out.println(future.get());
    }

}
