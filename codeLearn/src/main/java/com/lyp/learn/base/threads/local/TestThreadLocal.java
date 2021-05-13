package com.lyp.learn.base.threads.local;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * 一个ThreadLocal和面试官大战30个回合
 * https://xw.qq.com/partner/sxs/20210511A01RAQ/20210511A01RAQ00?ADTAG=sxs&pgv_ref=sxs
 *
 * ThreadLocal 提供了线程独有的局部变量，可以在整个线程存活的过程中随时取用，极大地方便了一些逻辑的实现。常见的ThreadLocal用法有：
 *
 * 存储单个线程上下文信息。比如存储id等；
 * 使变量线程安全。变量既然成为了每个线程内部的局部变量，自然就不会存在并发问题了；
 * 减少参数传递。比如做一个trace工具，能够输出工程从开始到结束的整个一次处理过程中所有的信息，从而方便debug。由于需要在工程各处随时取用，可放入ThreadLocal
 *
 * ThreadLocal 容易造成内存泄漏，用完之后，记得 调用 remove 方法，进行清除，防止内存泄漏
 *
 */
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
