package com.lyp.learn.base.juc.pk03;

public class MyRunTest{
    public static void main(String[] args) {
        MyRun myRun = new MyRun();

        Thread thread1 = new Thread(myRun);
        Thread thread2 = new Thread(myRun);

        thread1.start();
        thread2.start();
    }
}

class MyRun implements Runnable {
    //将ticket修改成volatile。这样保证了，每个线程修改了ticket之后，其他线程都能读取到最新的ticket值
    private volatile int ticket = 10;
    @Override
    public void run() {
        for(int i = 0 ;i <= 20 ;i++){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + " 卖票 " + ticket--);
            }
        }
    }
}
