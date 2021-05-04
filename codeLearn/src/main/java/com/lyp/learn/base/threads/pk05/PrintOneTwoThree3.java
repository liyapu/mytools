package com.lyp.learn.base.threads.pk05;

import lombok.extern.slf4j.Slf4j;

/**
 * 注：volatile只能保证可见性，无法保证原子性，
 * 所以对于i++或者i=i+1这类的操作，volatile无法保证i的原子性，
 * 但是上面的程序中使用了一个flag变量，这个量也用volatile进行了修饰，
 * 用flag控制if判断里面的代码是否被执行，相当于用flag保证了原子性，
 * 当一个线程修改了flag变量的时候，其他线程是无法执行if判断里面的代码的，只能等到相应的线程把flag修改之后，
 * 下个线程才能执行if里面的代码块。可以把flag看成一个“锁”。
 *
 */
@Slf4j
public class PrintOneTwoThree3 {

    private volatile int i = 0;
    private Thread thread1, thread2, thread3;
    private volatile int flag = 0;

//    private static  int MAX = 10000000;
    private static  int MAX = 1000;

    public static void main(String[] args) throws InterruptedException {
        PrintOneTwoThree3 p = new PrintOneTwoThree3();
        p.runThread();

        log.info("======================aaaaaaaaaaaa=========");
    }

    public void runThread() throws InterruptedException {
        thread1 = new Thread(new Thread1());
        thread2 = new Thread(new Thread2());
        thread3 = new Thread(new Thread3());
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public class Thread1 implements Runnable {

        public void run() {
            while (i < MAX) {
                if (flag == 0) {
                    System.out.println("t1=" + i);
                    i++;
                    flag = 1;
                }
            }
        }

    }

    public class Thread2 implements Runnable {

        public void run() {

            while (i < MAX) {
                if (flag == 1) {
                    System.out.println("t2=" + i);
                    i++;
                    flag = 2;
                }
            }
        }

    }

    public class Thread3 implements Runnable {

        public void run() {

            while (i < MAX) {
                if (flag == 2) {
                    System.out.println("t3=======" + i);
                    i++;
                    flag = 0;
                }
            }
        }

    }

}
