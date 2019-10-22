package com.lyp.learn.base.pk10.pk2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    //仓库最大容量
    private final int MAX_SIZE = 10;
    //仓库存储载体
    private LinkedList<Object> list = new LinkedList<>();

    //锁
    private final Lock lock = new ReentrantLock();
    //满的条件
    private final Condition full = lock.newCondition();
    //空的条件
    private final Condition empty = lock.newCondition();

    /**
     * 生产产品
     */
    public void produce(){
        //获取锁
        lock.lock();
        try {
            //仓库满了，满的条件等待 wait,并释放锁
            while(list.size() >= MAX_SIZE){
                System.out.println(Thread.currentThread().getName() + " 生产 仓库满了，现size = " + list.size());
                //满条件等待
                full.await();
            }
            //生产产品
            list.add(new Object());
            System.out.println(Thread.currentThread().getName() + " 生产1个产品，现size = " + list.size());

            //通知生产线程，通知消费线程
            //调用signalAll 通知，而不是notifyAll
            full.signalAll();
            empty.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放锁
            lock.unlock();
        }

    }

    /**
     * 消费产品
     */
    public void consume(){
        lock.lock();
        try {
            //获取锁
            while (list.size() == 0){
                System.out.println(Thread.currentThread().getName() + " 消费 仓库为空，现size = " + list.size());
                //空条件等待
                empty.wait();
            }
            //消费产品
            list.remove();
            System.out.println(Thread.currentThread().getName() + " 消费1个产品，现size = " + list.size());

            //通知生产线程，消费线程，并且释放锁
            full.signalAll();
            empty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}
