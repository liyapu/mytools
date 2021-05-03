package com.lyp.learn.base.threads.pk05;

/**
 * 三个线程交替打印 1,2,3,
 */
public class PrintOneTwoThree {

    private static volatile Integer MAX = 10;
    private static volatile Integer num = 1;
    private static Object obj = new Object();

    /**
     * 创建三个线程，一个线程负责打印1，另一个线程打印2，另一个线程打印3，
     * 三个线程竞争同一个对象锁，每次打印一个数字后释放锁，
     * 然后另一个线程拿到锁打印下一个数字。
     *
     * 三个线程，每时每刻都满足 while 循环的执行条件，忙等待，不好
     * 都执行了 [1-MAX] 的遍历，只是通过 if 条件的判断，选择性输出了而已
     *
     */
    public static void main(String[] args) {

        new Thread(() -> {
            while (num <= MAX) {
                synchronized (obj) {
                    //num % 3 是 1 时，才打印
                    if (num % 3 == 1) {
                        //打印之后自增1
                        System.out.println(Thread.currentThread().getName() + " " + num++);
                    }
                }
                System.out.println("one----------------------");

            }
        }, "one线程").start();


        new Thread(() -> {
            while (num <= MAX) {
                synchronized (obj) {
                    if (num % 3 == 2) {
                        System.out.println(Thread.currentThread().getName() + "   " + num++);
                    }
                }
                System.out.println("two---------------------");
            }
        }, "two线程").start();


        new Thread(() ->{
            while (num <= MAX){
                synchronized(obj){
                    if(num % 3 == 0){
                        System.out.println(Thread.currentThread().getName()+"    " + num++);
                    }
                }
                System.out.println("three---------------------");
            }
        },"three线程").start();


    }
}
