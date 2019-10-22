package com.lyp.learn.base.pk13;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DiscardPolicyDemo {
    private static final int THREADS_SIZE =1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) {
        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，
        // "线程池"的阻塞队列容量为1(CAPACITY)。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE,THREADS_SIZE,
                0,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(CAPACITY));
        // 设置线程池的拒绝策略为"丢弃"
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 新建10个任务，并将它们添加到线程池中
        for(int i = 1 ; i <= 10 ;i++){
            MyRunnable task = new MyRunnable("task-" + i);
            pool.execute(task);
            //System.out.println(pool.getCompletedTaskCount());
        }

        // 关闭线程池
        pool.shutdown();
    }
}

class MyRunnable implements Runnable{
    private String name;

    public MyRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is runnig : " + this.name);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}