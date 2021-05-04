package com.lyp.learn.base.threads.pk05;

public class PrintOddEven9 {

    //表示打印的数字
    public static volatile Integer num = 1;
    //数字的最大值
    public static Integer MAX = 20;
    //表示是否是奇数打印
    public static volatile boolean isOdd = true;
    //临界区使用的锁
    public static Object obj = new Object();


    public static void main(String[] args) {
        new Thread(() -> {
            while (num <= MAX) {
                synchronized (obj) {
                    if (isOdd) {
                        System.out.println(Thread.currentThread().getName() + " " + num++);
                        isOdd = false;
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
        }, "奇数线程").start();


        new Thread(() -> {
            while (num <= MAX) {
                synchronized (obj) {
                    if (!isOdd) {
                        System.out.println(Thread.currentThread().getName() + "   " + num++);
                        isOdd = true;
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
        }, "偶数线程").start();

    }
}
