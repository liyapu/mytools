package com.lyp.learn.guava.util.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BucketTest {
    private static Bucket bucket = new Bucket();
    private static AtomicInteger DATA_CREATE = new AtomicInteger();

    public static void main(String[] args) {

        IntStream.rangeClosed(1,3)
                .forEach(i -> new Thread(() ->{
                       while (true){
                           int data = DATA_CREATE.getAndIncrement();
                           try{
                               bucket.submit(data);
                               TimeUnit.MILLISECONDS.sleep(200);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }catch (IllegalStateException e){
                               System.out.println(e.getMessage());
                           }
                       }
                    }).start()
                );

        IntStream.rangeClosed(1,3)
                .forEach(i -> new Thread(() ->{
                                while (true){
                                    bucket.take();
                                    try {
                                        TimeUnit.SECONDS.sleep(1);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                        }).start()
                );
    }
}
