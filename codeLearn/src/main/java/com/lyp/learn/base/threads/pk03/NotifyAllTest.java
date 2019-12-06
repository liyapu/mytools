package com.lyp.learn.base.threads.pk03;


import com.lyp.learn.base.threads.pk01.Object;

public class NotifyAllTest {

    private static Object obj  = new Object();

    public static void main(String[] args) {
        ThreadC t1 = new ThreadC("t1");
        ThreadC t2 = new ThreadC("t2");
        ThreadC t3 = new ThreadC("t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            //主线程等待3s
            System.out.println(Thread.currentThread().getName() + " sleep 3s");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (obj){
            System.out.println(Thread.currentThread().getName() + " notifyAll");
            //唤醒obj对象锁上的线程
            obj.notifyAll();
        }

    }


  static  class ThreadC extends Thread{
        public ThreadC(String name){
            super(name);
        }

        @Override
        public void run(){
            try {
                //获取对象锁obj,执行
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName() + " wait 开始");
                    //wait等待，交出对象锁
                    obj.wait();
                    //等待obj.notify 或 obj.notifyAll之后，并且此线程获取到obj对象锁，才会开始执行
                    System.out.println(Thread.currentThread().getName() + " wait 结束 ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
