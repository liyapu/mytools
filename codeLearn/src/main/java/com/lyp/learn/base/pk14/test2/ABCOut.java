package com.lyp.learn.base.pk14.test2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：有A,B,C三个线程, A线程输出A, B线程输出B, C线程输出C，
 * 要求, 同时启动三个线程, 按顺序输出ABC, 循环10次。
 *
 * 分析
 * 沿用两个线程的交替打印的思路，重点是当第一个线程获取到锁的时候第二个线程在wait状态等待被唤醒，
 * 此时第一个线程完成任务时，主动唤醒第二个线程，自己进入等待状态，不与之竞争。这样就能起到“我做完轮到你，你做完轮到我”的状态。
 * 而三个线程的情况下是无法简单通过一个锁来完成的，因为当多个线程在等待时，我们无法通过 notify 来唤醒指定的线程。如何解决呢？
 *
 * 解决
 * 我们可以使用三个锁 lock1、lock2 和 lock3，分别对应要执行的三个线程 t1、t2 和 t3。
 *
 */
public class ABCOut {
    static volatile Integer num = 0;
    static int times = 10;
    static  Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

     class AOut implements Runnable{
        @Override
        public void run() {
            for(int i = 0 ; i < times; i++){
                lock.lock();
                try{
                    //1.不该输出时，就等待
                    if(num != 0){
                        conditionA.await();
                    }
                    //2.输出
//                    System.out.print("A");
                    System.out.print(Thread.currentThread().getName());

                    //3.变为1，同时唤醒下一个
                    num = 1;
                    conditionB.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    class BOut implements Runnable{
        @Override
        public void run() {
            for(int i = 0 ; i < times; i++){
                lock.lock();
                try{
                    //1.不该输出时，就等待
                    if(num != 1){
                        conditionB.await();
                    }
                    //2.输出
//                    System.out.print("B");
                    System.out.print(Thread.currentThread().getName());

                    //3.变为2，同时唤醒下一个
                    num = 2;
                    conditionC.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }


    class COut implements Runnable{
        @Override
        public void run() {
            for(int i = 0 ; i < times; i++){
                lock.lock();
                try{
                    //1.不该输出时，就等待
                    if(num != 2){
                        conditionC.await();
                    }
                    //2.输出
//                    System.out.print("C");
                    System.out.print(Thread.currentThread().getName());
                    System.out.println();

                    //3.变量变为0，同时唤醒下一个
                    num = 0;
                    conditionA.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    public static void main(String[] args) {
         new ABCOut().start();
    }

    public void start(){
         new Thread(new AOut(),"A").start();
         new Thread(new BOut(),"B").start();
         new Thread(new COut(),"C").start();
    }
}


