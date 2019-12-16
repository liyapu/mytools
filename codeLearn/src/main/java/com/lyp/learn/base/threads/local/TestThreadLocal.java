package com.lyp.learn.base.threads.local;

import lombok.SneakyThrows;

import java.util.Random;

public class TestThreadLocal {

        public static class MyRunnable1 implements Runnable {

            private int count = 0;

            @SneakyThrows
            @Override
            public void run() {

                count = new Random().nextInt(10000);
                System.out.println(Thread.currentThread().getName() + " : " + count);
            }
        }

    /**
     * count 变量公用，线程打印的都一样
     */
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
