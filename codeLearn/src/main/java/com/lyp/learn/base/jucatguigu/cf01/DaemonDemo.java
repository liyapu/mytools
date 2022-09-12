package com.lyp.learn.base.jucatguigu.cf01;

/**
 * @author liyapu
 * @date 2022-06-03 16:43
 * @description
 *    如果用户线程全部结束意味着程序需要完成的业务操作已经结束了，守护线程随着JVM一同结束工作。
 *    setDaemon(true)方法必须再start()之前设置，否则报IllegalThreadStateException异常
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
