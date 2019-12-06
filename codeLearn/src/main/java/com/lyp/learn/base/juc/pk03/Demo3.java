package com.lyp.learn.base.juc.pk03;

class Countt{
    public synchronized void synA(){
        for(int i = 0 ; i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }

    public synchronized void synAA(){
        for(int i = 0 ; i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }

    public void synAAA(){
        synchronized (this){
            for(int i = 0 ; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + " loop " + i);
            }
        }
    }


}
public class Demo3 {
    public static void main(String[] args) {
        Countt countt = new Countt(); //下面的三个线程，都用了同一个countt对象

        Thread t1 = new Thread(new Runnable() { //新建线程t1,调synA方法
            @Override
            public void run() {
                countt.synA();
            }
        }, "t1");

        Thread t2 = new Thread(() -> countt.synAA(),"t2");
        Thread t3 = new Thread(() -> countt.synAAA(),"t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
