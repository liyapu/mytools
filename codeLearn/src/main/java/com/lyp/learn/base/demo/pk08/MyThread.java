package com.lyp.learn.base.demo.pk08;

public class MyThread extends Thread {
    private int i ;

    //重写run方法
    @Override
    public void run() {
        for(; i <= 10 ; i++){
            //获取当前线程的名字
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

}
