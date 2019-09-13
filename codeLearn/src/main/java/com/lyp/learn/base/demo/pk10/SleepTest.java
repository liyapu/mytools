package com.lyp.learn.base.demo.pk10;

class ThreadF extends Thread{
    public ThreadF(String name){
        super(name);
    }
    @Override
    public void run(){
        try {
            for(int i = 1; i <= 10; i++){
                System.out.println(Thread.currentThread().getName() + " " + i);
                if(i % 3 == 0){
                    //能被3整除，休眠1s,打印到3,6,9之后，等待1s,再继续往下打印
                    //Thread.sleep(1000);
                    interrupt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class SleepTest {
    public static void main(String[] args) {
        ThreadF t1 = new ThreadF("t1");
        t1.start();
    }
}
