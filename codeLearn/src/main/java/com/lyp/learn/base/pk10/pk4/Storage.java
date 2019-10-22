package com.lyp.learn.base.pk10.pk4;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Storage {

    //仓库存储载体
    private LinkedList<Object> list = new LinkedList<>();

    //仓库不满
    private final Semaphore notFull = new Semaphore(10);
    //仓库不空
    private final Semaphore notEmpty = new Semaphore(0);
    //互斥锁
    private final Semaphore mutex = new Semaphore(1);

    public void produce(){
        try {
            notFull.acquire();
            mutex.acquire();
            list.add(new Object());
            System.out.println(Thread.currentThread().getName() + " 生产1个产品,现size=" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mutex.release();
            notEmpty.release();
        }
    }

    public void consume(){
        try {
            notEmpty.acquire();
            mutex.acquire();
            list.remove();
            System.out.println(Thread.currentThread().getName() + " 消费1个产品,现size="+list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notFull.release();
        }
    }

}
