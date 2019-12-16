package com.lyp.learn.base.threads.safe.fieldparam;

/**
 * 局部变量线程安全问题模拟：
 */
public class LocalTest implements Runnable {


    @Override
    public void run() {
        int local_i = 4;
        System.out.println(Thread.currentThread().getName() + " 获取 local_i = " + local_i);
        local_i = 10;
        System.out.println(Thread.currentThread().getName() + " 获取 local_i * 2 = " + (local_i*2));
    }

    public static void main(String[] args) {
        LocalTest st = new LocalTest();
        int threadCount = 300;
        //启动尽量多的线程才能很容易的模拟问题
        for(int i = 0; i <= threadCount; i++){
            /**
             * 每个线程对在对象 st 中运行，模拟单例情况
             * 线程安全
             */
            new Thread(st).start();


        }

    }
}
