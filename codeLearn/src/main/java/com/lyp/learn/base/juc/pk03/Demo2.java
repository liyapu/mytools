package com.lyp.learn.base.juc.pk03;

class Count {

    // 含有synchronized同步块的方法
    public void synMethod() {
        synchronized(this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synMethod loop " + i);
            }
        }
    }

    // 非同步的方法
    public void nonSynMethod() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " nonSynMethod loop " + i);
        }
    }
}

public class Demo2 {

    public static void main(String[] args) {
        final Count count = new Count(); //下面的两个线程，使用的是同一个count对象，同一个对象锁

        // 新建t1, t1会调用“count对象”的synMethod()方法
        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.synMethod();
                    }
                }, "t1");

        // 新建t2, t2会调用“count对象”的nonSynMethod()方法
        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        count.nonSynMethod();
                    }
                }, "t2");


        t1.start();  // 启动t1
        t2.start();  // 启动t2
    }
}
