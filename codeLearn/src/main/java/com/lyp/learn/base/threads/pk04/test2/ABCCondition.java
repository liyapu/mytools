package com.lyp.learn.base.threads.pk04.test2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * ReentrantLock结合Condition
 * 1、基本思路
 * 与ReentrantLock搭配的通行方式是Condition，如下：
 * private Lock lock = new ReentrantLock();
 * private Condition condition = lock.newCondition();
 * condition.await();//this.wait();
 * condition.signal();//this.notify();
 * condition.signalAll();//this.notifyAll();
 * Condition是被绑定到Lock上的，必须使用lock.newCondition()才能创建一个Condition。从上面的代码可以看出，Synchronized能实现的通信方式，Condition都可以实现，功能类似的代码写在同一行中。
 * 这样解题思路就和第一种方法基本一致，只是采用的方法不同。
 *
 */
public class ABCCondition {
    private static Lock lock = new ReentrantLock();
    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();
    private static int count = 0;
    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 0)//注意这里是不等于0，也就是说在count % 3为0之前，当前线程一直阻塞状态
                    {
                        A.await(); // A释放lock锁
                    }
                    System.out.print("A");
                    count++;
                    B.signal(); // A执行完唤醒B线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 1) {
                        B.await();// B释放lock锁，当前面A线程执行后会通过B.signal()唤醒该线程
                    }
                    System.out.print("B");
                    count++;
                    C.signal();// B执行完唤醒C线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2) {
                        C.await();// C释放lock锁，当前面B线程执行后会通过C.signal()唤醒该线程
                    }
                    System.out.print("C");
                    count++;
                    A.signal();// C执行完唤醒A线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
