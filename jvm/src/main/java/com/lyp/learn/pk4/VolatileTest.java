package com.lyp.learn.pk4;

/**
 * @author: liyapu
 * @description:  volatile 保证变量的可见性
 * @date 2020-05-03 21:43
 *
 *  JMM
 */
public class VolatileTest {

    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        new Thread(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.updateNumber();
            System.out.println(Thread.currentThread().getName() + " update number .now number is : " + myNumber.number);
        },"A").start();

        new Thread(() ->{
            while (myNumber.number == 10){

            }
            System.out.println(Thread.currentThread().getName() + " is end " );
        },"B").start();

        System.out.println("main is over");

    }
}


class MyNumber{
    // number 不加 volatile , 线程A 更新 number 之后，线程B得不到通知，不会停止
//    int number =10;

    // 加上 volatile 之后，线程B 就可以看到 线程A 修改的值了，这就是 volatile 具有可见性的测试
    volatile int number = 10;

    public void updateNumber(){
        this.number = 88;
    }
}