package com.lyp.learn.base.threads.pk05;

/**
 * 两个线程交替打印奇偶数
 * odd  奇数
 * even 偶数
 *
 * 当一个线程打印进入时，使用wait()进行阻塞该线程；
 * 另外一个线程进入时，在进入利用notify() 或者是notifyAll()进行释放线程。
 *
 * wait()：一旦执行此方法，当前线程就会进入阻塞状态，并释放同步监视器。
 * notify():一旦执行此方法，就会唤醒被wait()的一个线程，如果有多个线程就会唤醒优先级高的那个。
 * notifyAll():一旦执行此方法，就会唤醒所有被wait()的所有的线程。
 * 说明：
 *    三个方法必须使用在同步代码块中或者是使用在同步方法中
 *    三个方法的调用者必须是同步代码块或者是同步方法中的同步监视器，否则会出现 IllegalMonitorStateException 异常
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
