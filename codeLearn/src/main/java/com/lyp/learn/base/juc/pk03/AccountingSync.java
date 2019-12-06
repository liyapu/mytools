package com.lyp.learn.base.juc.pk03;

public class AccountingSync implements Runnable {
    //共享资源(临界资源) i ,j
    int i  = 0;
    static int j = 0;

    /**
     * synchronized 修饰实例方法
     */
    private synchronized void increase(){
        i++;
        j++;
    }

    @Override
    public void run() {
        for(int k = 0; k < 1000000; k++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AccountingSync instance = new AccountingSync();

        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(instance.i);  // 输出 2000000
        System.out.println(AccountingSync.j); //输出 2000000
    }
}
