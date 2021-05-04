package com.lyp.learn.base.threads.pk05;

/**
 * 有{1,2,3,4,5,6,7,8,9,10}
 * 和{10,20,30,40,50,60,70,80,90,100}两个数组，
 * 我们要实现的是分别打印 1、2、10、20、3、4、30、40…
 */
public class PrintArray1 {
    //控制哪个线程访问的状态位
    private static volatile int state = 1;
    //临界区的对象锁
    private static Object obj = new Object();


    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        new Thread(() -> {
            int i = 0;
            while (i < arr1.length) {
                synchronized (obj) {
                    //通过状态位 state 判断是否该自己打印
                    if (state == 1) {
                        System.out.println(Thread.currentThread().getName() + " " + arr1[i++]);
                        state = 0;
                        //唤醒其它线程
                        obj.notifyAll();
                    } else {
                        try {
                            //阻塞当前线程
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "a ").start();

        new Thread(() -> {
            int j = 0;
            while (j < arr2.length) {
                synchronized (obj) {
                    if (state == 0) {
                        System.out.println(Thread.currentThread().getName() + "     " + arr2[j++]);
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
