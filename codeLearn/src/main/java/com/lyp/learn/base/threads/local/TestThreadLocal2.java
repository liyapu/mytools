package com.lyp.learn.base.threads.local;


import lombok.SneakyThrows;

import java.util.Random;

public class TestThreadLocal2 {

        public static class MyRunnable1 implements Runnable {

            private ThreadLocal<Integer> threadlocal = new ThreadLocal<Integer>();

            @SneakyThrows
            @Override
            public void run() {
                threadlocal.set(new Random().nextInt(10000));
                System.out.println(Thread.currentThread().getName() + " : " + threadlocal.get());
            }
        }

        public static void main(String[] args) {
            MyRunnable1 runnable = new MyRunnable1();

            Thread thread1 = new Thread(runnable);
            Thread thread2 = new Thread(runnable);
            Thread thread3 = new Thread(runnable);
            thread1.start();
            thread2.start();
            thread3.start();
        }
}
