package com.lyp.learn.base.threads.pk04.test1;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 此种方式，不行，实现不了
 * 用一个变量的不同状态控制， 错误的
 */
public class ABCOut {
    static Lock lock = new ReentrantLock();
    static volatile Integer flag = 0;

    //错误，实现不了
    public static void main(String[] args) {
        Integer num = 10;
        new Thread(new AThread(lock, flag), "A").start();
        new Thread(new BThread(lock, flag), "B").start();
        new Thread(new CThread(lock, flag), "C").start();

    }
}

class AThread implements Runnable {
    Lock lock = null;
    volatile Integer flag = 0;

    public AThread(Lock lock, Integer flag) {
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " flag = " + flag);
                if (flag == 0) {
                    System.out.println(Thread.currentThread().getName());
                    i++;
                    flag = 1;
                    lock.notifyAll();
                }
            }
        }
    }
}


class BThread implements Runnable {
    Lock lock = null;
    volatile Integer flag = 0;

    public BThread(Lock lock, Integer flag) {
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " flag = " + flag);
                if (flag == 1) {
                    System.out.println(Thread.currentThread().getName());
                    i++;
                    flag = 2;
                    lock.notifyAll();
                }
            }
        }
    }
}


class CThread implements Runnable {
    Lock lock = null;
    volatile Integer flag = 0;

    public CThread(Lock lock, Integer flag) {
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " flag = " + flag);
                if (flag == 2) {
                    System.out.println(Thread.currentThread().getName());
                    i++;
                    flag = 0;
                    lock.notifyAll();
                }
            }
        }
    }
}