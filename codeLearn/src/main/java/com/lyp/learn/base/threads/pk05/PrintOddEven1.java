package com.lyp.learn.base.threads.pk05;

/**
 * 两个线程交替打印奇偶数
 * odd  奇数
 * even 偶数
 */
public class PrintOddEven1 {

    private static volatile Integer MAX = 10;
    private static volatile Integer num = 1;
    private static Object obj = new Object();

    /**
     * 创建两个线程，一个线程负责打印奇数，另一个线程打印偶数，
     * 两个线程竞争同一个对象锁，每次打印一个数字后释放锁，
     * 然后另一个线程拿到锁打印下一个数字。
     *
     * 两个线程，每时每刻都满足 while 循环的执行条件，忙等待，不好
     * 都执行了 [1-MAX] 的遍历，只是通过 if 条件的判断，选择性输出了而已
     *
     */
    public static void main(String[] args) {

        new Thread(() -> {
            while (num <= MAX) {
                synchronized (obj) {
                    //num 是奇数时，才打印
                    if (num % 2 == 1) {
                        //打印之后自增1
                        System.out.println(Thread.currentThread().getName() + " " + num++);
                    }
                }
                System.out.println("奇----------------------");

            }
        }, "奇数线程").start();


        new Thread(() -> {
            while (num <= MAX) {
                synchronized (obj) {
                    if (num % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "   " + num++);
                    }
                }
                System.out.println("偶---------------------");
            }
        }, "偶数线程").start();


    }
}
