package com.lyp.learn.thread.test1;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-05-10 21:05
 */
public class Demo {
    public static void main(String[] args) {
        MyThread my1 = new MyThread();
        MyThread my2 = new MyThread();
        MyThread my3 = new MyThread();

        my1.start();
        my2.start();
        my3.start();

    }

}

class MyThread extends  Thread{
    private static Integer num = 100;
    @Override
    public void run() {
        num  = --num;
        System.out.println(num);
    }
}
