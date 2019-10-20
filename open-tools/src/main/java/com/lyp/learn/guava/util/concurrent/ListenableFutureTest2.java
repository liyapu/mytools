package com.lyp.learn.guava.util.concurrent;


import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ListenableFutureTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<Integer> future = listeningExecutorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        System.out.println("========立刻输出====");
        Futures.addCallback(future,new MyCallback(),executorService);
    }

    /**
     * 接收结果
     */
    static class MyCallback implements FutureCallback<Integer>{

        @Override
        public void onSuccess(@Nullable Integer result) {
            System.out.println("future done result : " + result);
        }

        @Override
        public void onFailure(Throwable t) {
            System.out.println("future err " + t.getMessage());
        }
    }
}
