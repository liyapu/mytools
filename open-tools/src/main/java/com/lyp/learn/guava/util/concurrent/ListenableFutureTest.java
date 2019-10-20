package com.lyp.learn.guava.util.concurrent;

import java.util.concurrent.*;

public class ListenableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        executorService.shutdown();

        Integer result = future.get();
        System.out.println("====阻塞之后输出========");
        System.out.println("result : " + result);
    }
}
