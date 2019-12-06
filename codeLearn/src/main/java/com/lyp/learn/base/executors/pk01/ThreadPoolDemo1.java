package com.lyp.learn.base.executors.pk01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        // 创建一个可重用固定线程数的线程池
        //线程池的容量是2。即，线程池中最多能同时运行2个线程
        ExecutorService executorPool = Executors.newFixedThreadPool(2);
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        ThreadDemo1 td1 = new ThreadDemo1();
        ThreadDemo1 td2 = new ThreadDemo1();
        ThreadDemo1 td3 = new ThreadDemo1();
        ThreadDemo1 td4 = new ThreadDemo1();
        ThreadDemo1 td5 = new ThreadDemo1();
        // 将线程放入池中进行执行
        executorPool.execute(td1);
        executorPool.execute(td2);
        executorPool.execute(td3);
        executorPool.execute(td4);
        executorPool.execute(td5);

        // 关闭线程池
        executorPool.shutdown();
    }

}


class ThreadDemo1 extends Thread {

    @Override
    public void run(){
        try {

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "   is runing" );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}