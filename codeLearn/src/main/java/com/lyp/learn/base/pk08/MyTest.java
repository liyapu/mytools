package com.lyp.learn.base.pk08;

public class MyTest {
    public static void main(String[] args) {
        for(int i = 0 ; i <= 10; i++){
            if(i == 2){
                MyThread myThread1 = new MyThread();
                MyThread myThread2 = new MyThread();
                //启动线程
                myThread1.start();
                myThread2.start();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
    }
}
