package com.lyp.learn.base.jucatguigu.base;

/**
 * @author liyapu
 * @date 2022-06-03 16:43
 * @description
 */
public class DaemonDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out
                .println(Thread.currentThread().getName() + "\t开始运行, 是否是守护线程 " + Thread.currentThread().isDaemon());
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out
                .println(Thread.currentThread().getName() + "\t开始运行, 是否是守护线程 " + Thread.currentThread().isDaemon());
        }, "t2");
        //设置线程为 守护线程,必须再 start启动方法之前设置
        t2.setDaemon(true);
        t2.start();
    }
}
