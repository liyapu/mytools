package com.lyp.learn.base.demo.pk10;

class ThreadM extends Thread{
    private volatile boolean flag = true;

    public ThreadM(String name){
        super(name);
    }

    public void stopTask(){
        flag = false;
    }

    @Override
    public void run(){
        synchronized (this) {
            try { //异常处理放 while循环外部
                int i = 0;
                while (flag) {
                    i++;
                    System.out.println(Thread.currentThread().getName() + " ( " + this.getState() + " )" + " loop " + i);
                    //休眠100ms,处于阻塞状态，此时被调用 interrupt 方法，会抛出异常
                    sleep(1);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " ( " + this.getState() + " )" + " catch InterruptedException ");
                e.printStackTrace();
            }
        }
    }
}

public class InterruptTest3 {
    public static void main(String[] args) throws InterruptedException {
        ThreadM t1 = new ThreadM("t1");
        System.out.println(t1.getName() + " ( " + t1.getState() + " )" + " new ");

        t1.start();
        System.out.println(t1.getName() + " ( " + t1.getState() + " )" + " start ");

        Thread.sleep(10);
        t1.stopTask();
        System.out.println(t1.getName() + " ( " + t1.getState() + " )" + " interrupt ");
        Thread.sleep(100);
        System.out.println(t1.getName() + " ( " + t1.getState() + " )" + " interrupt sleep 1s ");


    }
}
