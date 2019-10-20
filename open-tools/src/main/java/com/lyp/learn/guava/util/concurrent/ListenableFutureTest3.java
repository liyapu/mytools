package com.lyp.learn.guava.util.concurrent;


import java.util.concurrent.*;

/**
 * java 8  CompletableFuture
 */
public class ListenableFutureTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        },executorService);

        completableFuture.whenComplete((v,c) -> System.out.println("i am future done result : " + v));
    }


}
