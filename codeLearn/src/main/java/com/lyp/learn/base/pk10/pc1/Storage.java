package com.lyp.learn.base.pk10.pc1;

import java.util.LinkedList;

public class Storage {
    //仓库容量
    private static final int MAX_SIZE = 10;
    //仓库存储的载体
    private LinkedList<Object> list = new LinkedList<>();

    /**
     * 生产产品
     */
    public void produce(){
        //加对象锁，并发安全
        synchronized (this){
            //先判断仓库满的情况，wait释放锁
            while(list.size() >= MAX_SIZE){
                System.out.println(Thread.currentThread().getName() + " 生产 仓库满了，size =  " + list.size());
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //仓库不满，生产产品，唤醒等待线程(生产线程：再次生产或者消费线程：去消费)
            list.add(new Object());
            System.out.println(Thread.currentThread().getName() + " 生产1个产品，现size =  " + list.size());
            notifyAll();
        }
    }

    /**
     * 消费产品
     */
    public void consume(){
        //加对象锁，并发安全
        synchronized (this){
            //先判断仓库为空时，wait释放锁
            while(list.size() == 0){
                System.out.println(Thread.currentThread().getName() + " 消费 仓库为空了，现size =  " + list.size());
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //仓库有产品，消费产品，唤醒等待线程(生产线程:去生产或者消费线程：再消费)
            list.remove();
            System.out.println(Thread.currentThread().getName() + " 消费1个产品，现size =  " + list.size());
            notifyAll();
        }
    }
}
