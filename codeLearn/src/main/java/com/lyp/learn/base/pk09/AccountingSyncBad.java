package com.lyp.learn.base.pk09;

public class AccountingSyncBad implements Runnable{
    static int i = 0;

    private  void increase(){
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
        Thread t1 = new Thread(new AccountingSyncBad());
        //new新实例
        Thread t2 = new Thread(new AccountingSyncBad());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(AccountingSyncBad.i); // 输出 1208961
    }
}
