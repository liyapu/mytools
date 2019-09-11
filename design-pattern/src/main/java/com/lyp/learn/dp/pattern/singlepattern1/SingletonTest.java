package com.lyp.learn.dp.pattern.singlepattern1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class SingletonTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
        Set<String> set = Collections.synchronizedSet(new HashSet<String>());

        for(int i = 0; i < 100; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        Singleton singleton = Singleton.getInstance();
                        set.add(singleton.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(String s : set){
            System.out.println(s);
        }

        executorService.shutdown();
    }
}
