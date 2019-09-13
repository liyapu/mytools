package com.lyp.learn.base.demo.pk10;

public class SleepLockTest{

    private static Object obj = new Object();

    public static void main(String[] args){
        ThreadG t1 = new ThreadG("t1");
        ThreadG t2 = new ThreadG("t2");
        t1.start();
        t2.start();
    }

    static class ThreadG extends Thread{
        public ThreadG(String name){
            super(name);
        }
        public void run(){
            // 获取obj对象的同步锁
            synchronized (obj) {
                try {
                    for(int i=1; i <= 10; i++){
                        System.out.printf("%s: %d\n", this.getName(), i);
                        // i能被3整除时，休眠1秒
                        if (i % 3 == 0)
                            Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}