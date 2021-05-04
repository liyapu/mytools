package com.lyp.learn.base.threads.pk05;

/**
 * 两个线程交替打印一个字符串
 */
public class PrintStr2 {
    //控制哪个线程访问的状态位
    private static volatile int state = 1;
    //下标访问的索引
    private static volatile int index = 0;
    //临界区的对象锁
    private static Object obj = new Object();


    public static void main(String[] args) {
        String str = "我是用来测试的字符串啊!";

        new Thread(() -> {
            while (index < str.length()) {
                synchronized (obj) {
                    if (state == 1) {
                        System.out.println(Thread.currentThread().getName() + " " + str.charAt(index++));
                        System.out.println(Thread.currentThread().getName() + " " + str.charAt(index++));
                        state = 0;
                        obj.notifyAll();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "a ").start();

        new Thread(() -> {
            while (index < str.length()) {
                synchronized (obj) {
                    if (state == 0) {
                        System.out.println(Thread.currentThread().getName() + "     " + str.charAt(index++));
                        System.out.println(Thread.currentThread().getName() + "     " + str.charAt(index++));
                        state = 1;
                        obj.notifyAll();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "b ").start();

    }
}
