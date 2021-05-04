package com.lyp.learn.base.threads.pk05;

import java.util.concurrent.Semaphore;

/**
 *  通过 信号量 来实现两个线程交替打印奇偶数
 *
 * Semaphore信号量方式
 * 1、基本思路
 * Semaphore又称信号量，是操作系统中的一个概念，在Java并发编程中，信号量控制的是线程并发的数量。
 *
 * public Semaphore(int permits)
 * 其中参数permits就是允许同时运行的线程数目;
 * Semaphore是用来保护一个或者多个共享资源的访问，Semaphore内部维护了一个计数器，其值为可以访问的共享资源的个数。
 * 一个线程要访问共享资源，先获得信号量，
 *     如果信号量的计数器值大于1，意味着有共享资源可以访问，则使其计数器值减去1，再访问共享资源。
 *     如果计数器值为0,线程进入休眠。
 * 当某个线程使用完共享资源后，释放信号量，并将信号量内部的计数器加1，之前进入休眠的线程将被唤醒并再次试图获得信号量。
 *
 * Semaphore使用时需要先构建一个参数来指定共享资源的数量，Semaphore构造完成后即是获取Semaphore、共享资源使用完毕后释放Semaphore。
 *
 * Semaphore semaphore = new Semaphore(3,true);
 * semaphore.acquire();
 * //do something here
 * semaphore.release();
 */
public class PrintOddEven5 {
    private static volatile Integer MAX = 5;
    private static volatile Integer num = 1;

    // 以A开始的信号量,初始信号量数量为1
    private static Semaphore A = new Semaphore(1);
    // B信号量,A完成后开始,初始信号数量为0
    private static Semaphore B = new Semaphore(0);

    /**
     */
    public static void main(String[] args) {

        // 简洁易看的，release 操作没有放到 finally块中的
        new Thread(() -> {
            try {
                for (int i = 1; i <= MAX; i++) {
                    // A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                    A.acquire();
                    System.out.println(Thread.currentThread().getName() + " " + num++);
                    // B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                    B.release();
                    System.out.println("奇----------------------");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "奇数线程").start();


        new Thread(() -> {
            try {
                for (int i = 1; i <= MAX; i++) {
                    B.acquire();
                    System.out.println(Thread.currentThread().getName() + "  " + num++);
                    A.release();
                    System.out.println("偶---------------------");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "偶数线程").start();


//        new Thread(() -> {
//            for (int i = 1; i <= MAX; i++) {
//                    try {
//                        a.acquire();
//                        System.out.println(Thread.currentThread().getName() + " " + num++);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }finally {
//                        //释放 放到 finally 中
//                        b.release();
//                        System.out.println("奇----------------------");
//
//                    }
//
//            }
//        }, "奇数线程").start();
//
//
//        new Thread(() -> {
//            for (int i = 1; i <= MAX ; i++) {
//                try {
//                    b.acquire();
//                    System.out.println(Thread.currentThread().getName() + "  " + num++);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    a.release();
//                    System.out.println("偶---------------------");
//                }
//            }
//
//
//        }, "偶数线程").start();

    }
}
