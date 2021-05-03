package com.lyp.learn.base.threads.pk05;

/**
 * 两个线程交替打印奇偶数
 * odd  奇数
 * even 偶数
 */
public class PrintOddEven2 {

    private static volatile Integer MAX = 10;
    private static volatile Integer num = 1;
    private static Object obj = new Object();

    /**
     * 这种实现方式的原理就是线程1打印之后唤醒其他线程，然后让出锁，自己进入休眠状态。
     * 因为进入了休眠状态就不会与其他线程抢锁，此时只有线程2在获取锁，所以线程2必然会拿到锁。
     * 线程2以同样的逻辑执行，唤醒线程1并让出自己持有的锁，自己进入休眠状态。
     * 这样来来回回，持续执行直到任务完成。就达到了两个线程交替获取锁的效果了。
     */
    public static void main(String[] args) {

        Runnable run = () -> {
            while (num <= MAX) {
                //获取锁
                synchronized (obj) {
                    //获取到锁就打印
                    System.out.println(Thread.currentThread().getName() + " " + num++);
                    //唤醒其它线程
                    obj.notifyAll();
                    try {
                        //自己进入等待状态
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        new Thread(run, "奇数线程").start();

        new Thread(run, "偶数线程").start();

    }
}
