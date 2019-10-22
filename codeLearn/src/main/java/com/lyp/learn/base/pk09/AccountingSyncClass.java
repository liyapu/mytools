package com.lyp.learn.base.pk09;

public class AccountingSyncClass implements Runnable{
    //共享资源(临界资源)
    static int i = 0;

    /**
     * 作用于静态方法,锁是当前class对象,
     * 也就是 AccountingSyncClass类对应的class对象
     */
    private static synchronized  void increase(){
        i++;
    }

    /**
     * 非静态,访问时锁不一样不会发生互斥
     */
    public synchronized void increase4Obj(){
        i++;
    }

    @Override
    public void run() {
        for(int k = 0; k < 1000000; k++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1 = new Thread(new AccountingSyncClass());
        //new新实例
        Thread t2 = new Thread(new AccountingSyncClass());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i); //输出 2000000
    }
}
