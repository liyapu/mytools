package com.lyp.learn.base.pk08;

public class JoinTest1 {
    public static void main(String[] args) throws InterruptedException {
        ThreadJoin t1 = new ThreadJoin("t1");
        ThreadJoin t2 = new ThreadJoin("t2");

        t1.start();
        t1.join();
        //t1.join();

        t2.start();

        for(int i = 0 ; i <= 10; i++){
            System.out.println(Thread.currentThread().getName() + " 主 " + i);
        }

    }
}
