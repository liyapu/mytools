package com.lyp.learn.base.juc.pk03;


public class Something {
    //static修饰的同步方法
    public static synchronized void staticSynMethodA(){
        for(int i = 0 ;i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }

    public static synchronized void staticSynMethodB(){
        for(int i = 0 ;i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }

    //同步方法
    public synchronized void synMethodA(){
        for(int i = 0 ;i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }

    public synchronized void synMethodB(){
        for(int i = 0 ;i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }

    //实例方法
    public  void instanceMethodA(){
        for(int i = 0 ;i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }

    public void instanceMethodB(){
        for(int i = 0 ;i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " loop " + i);
        }
    }


}
