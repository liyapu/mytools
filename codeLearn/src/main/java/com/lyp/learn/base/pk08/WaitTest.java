package com.lyp.learn.base.pk08;
// WaitTest.java的源码
class ThreadA extends Thread{

    public ThreadA(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+" call notify()");
            // 唤醒当前的wait线程
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //notify();
            System.out.println(Thread.currentThread().getName()+" 2222222222");

        }
    }
}

public class WaitTest {

    public static void main(String[] args) {

        ServiceWait t1 = new ServiceWait("t1");
        Object obj = new Object();
        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName()+" wait()");
                t1.wait();

                //System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" continue 222");

        }
    }
}