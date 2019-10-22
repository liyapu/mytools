package com.lyp.learn.base.pk10.pk3;

import java.util.concurrent.LinkedBlockingQueue;

public class Storage {
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(10);

    public void produce(){
        try {
            list.put(new Object());
            System.out.println(Thread.currentThread().getName() + " 生产1个产品，现 size = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume(){
        try {
            list.take();
            System.out.println(Thread.currentThread().getName() + " 消费1个产品，现size = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
