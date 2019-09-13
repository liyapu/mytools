package com.lyp.learn.base.demo.pk10;

class ThreadA extends Thread{

    public ThreadA(String name){
        super(name);
    }

    @Override
    public void run(){
        //获取ThreadA 类型的 实例锁
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " call notify");
            //唤醒当前的wait线程
            notify();
        }
    }
}
public class WaitTest {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");

        try {
            //获取 ThreadA 类型的 实例 t1对象锁
            synchronized (t1){
                System.out.println(Thread.currentThread().getName() + " start t1");
                //启动线程 t1，由于main线程的synchronized持有t1对象锁，
                //所以，即使t1线程启动，由于无法获取到锁，也不会执行
                t1.start();
                System.out.println(Thread.currentThread().getName() + " call wait");
                //main线程调用 t1.wait方法，当前主线程阻塞，并释放t1对象锁
                t1.wait();
                //t1对象上调用notify方法，唤醒主线程，并开始继续执行
                System.out.println(Thread.currentThread().getName() + " continue");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
