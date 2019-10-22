package com.lyp.learn.base.pk10;

class ThreadD extends Thread{
    public ThreadD(String name){
        super(name);
    }

    @Override
    public synchronized void run(){
        for(int i = 1 ; i <= 10 ; i++){
            System.out.printf("%s \t %d \t  %d \n",Thread.currentThread().getName(),Thread.currentThread().getPriority(),i);
            if(i % 3 == 0){
                Thread.yield();
            }
        }
    }
}
public class YieldTest {
    public static void main(String[] args) {
        ThreadD t1 = new ThreadD("t1");
        ThreadD t2 = new ThreadD("t2");
        t1.start();
        t2.start();
    }
}
