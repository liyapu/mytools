package com.lyp.learn.cf;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author liyapu
 * @date 2021-10-30 16:03
 * @desc https://www.jianshu.com/p/6bac52527ca4
 * 5、 thenAccept 消费处理结果
 *    接收任务的处理结果，并消费处理，无返回结果。
 *       public CompletionStage<Void> thenAccept(Consumer<? super T> action);
 *       public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
 *       public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
 *
 *
 */
public class CompletableFuture14 {

    public static void main(String[] args) throws Exception {
        testThenAccept();
    }

    /**
     * 从示例代码中可以看出，该方法只是消费执行完成的任务，并可以根据上面的任务返回的结果进行处理。并没有后续的输错操作。
     */
    public static void testThenAccept() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenAccept(integer -> {
            System.out.println(integer);
        });
        future.get();
    }
}
