package com.lyp.learn.thread.test2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁方法
 * 通过ReentrantLock我们可以很方便的进行显式的锁操作，即获取锁和释放锁，对于同一个对象锁而言，
 * 统一时刻只可能有一个线程拿到了这个锁，此时其他线程通过lock.lock()来获取对象锁时都会被阻塞，直到这个线程通过lock.unlock()操作释放这个锁后，其他线程才能拿到这个锁
 *
 * 值得注意的是ReentrantLock是可重入锁，它持有一个锁计数器，当已持有锁的线程再次获得该锁时计数器值加1，每调用一次lock.unlock()时所计数器值减一，直到所计数器值为0，此时线程释放锁。
 */
public class ABCLock {
    private static Lock lock = new ReentrantLock();// 通过JDK5中的Lock锁来保证线程的访问的互斥
    private static int state = 0;//通过state的值来确定是否打印
    static class AK extends Thread {
        @Override
        public void run() {
            //for(;; 无i++)
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("A");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }
    static class BK extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    while (state % 3 == 1) {
                        System.out.print("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }
    static class CK extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {
                        System.out.print("C");
                        System.out.println();
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }
    public static void main(String[] args) {
        new AK().start();
        new BK().start();
        new CK().start();
    }
}
