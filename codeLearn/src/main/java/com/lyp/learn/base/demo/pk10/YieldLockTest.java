package com.lyp.learn.base.demo.pk10;

public class YieldLockTest {
    private static Object lock = new Object();

    public static void main(String[] args) {
        ThreadE t1 = new ThreadE("t1");
        ThreadE t2 = new ThreadE("t2");
        t1.start();
        t2.start();
    }


    static class ThreadE extends Thread{
        public ThreadE(String name){
            super(name);
        }

        @Override
        public void run(){
            synchronized (lock){
                for(int i = 1 ; i <= 10; i++){
                    System.out.printf("%s \t %d \t %d \n",getName(),getPriority(),i);
                    if(i % 3 == 0){
                        yield();
                    }
                }
            }
        }
    }
}
