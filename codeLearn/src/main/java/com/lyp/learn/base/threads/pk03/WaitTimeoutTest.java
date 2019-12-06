package com.lyp.learn.base.threads.pk03;

class ThreadB extends Thread {
    public ThreadB(String name){
        super(name);
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " run ");
        // 死循环，不断运行。
        while(true){

        }
    }
}

public class WaitTimeoutTest {
    public static void main(String[] args) {
        ThreadB t1 = new ThreadB("t1");

        try {
            synchronized (t1){
                //启动t1线程
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒 或 notifyAll()唤醒，或超过3000ms延时；然后才被唤醒。
                System.out.println(Thread.currentThread().getName() + " wait 3s");
                t1.wait(3000);
                //大约3秒之后...输出“main continue”
                System.out.println(Thread.currentThread().getName() + " continue ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
