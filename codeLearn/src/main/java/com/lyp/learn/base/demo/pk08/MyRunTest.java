package com.lyp.learn.base.demo.pk08;

public class MyRunTest {
    public static void main(String[] args) {
        for(int i = 0; i <= 10; i++){
            if(i == 2){
                MyRun target = new MyRun();
                //将Runnable接口的实现类作为Thread类的target
                Thread thread1 = new Thread(target);
                Thread thread2 = new Thread(target);

                thread1.start();
                thread2.start();
            }
            //System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
    }
}
