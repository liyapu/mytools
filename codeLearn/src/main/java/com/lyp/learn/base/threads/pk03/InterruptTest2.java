package com.lyp.learn.base.threads.pk03;

class ThreadK extends Thread {
    public ThreadK(String name){
        super(name);
    }

    @Override
    public void run(){
        int i = 0;
            while (!isInterrupted()){
                try {
                    i++;
                    System.out.println(Thread.currentThread().getName() + " ( " + this.getState() + " )" + " loop " + i);
                    //休眠100ms,处于阻塞状态，此时被调用 interrupt 方法，会抛出异常
                    //若修改为休眠1ms,主线程调用interrupt时，此线程可能不会处于阻塞状态，就不会抛出异常。但是此时isInterrupted返回true,退出循环
                    sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " ( " + this.getState() + " )" + " catch InterruptedException ");
                    e.printStackTrace();
                    break;
                }
            }
        System.out.println(Thread.currentThread().getName() + " ( " + this.getState() + " )" + " end loop " + i);
    }
}

public class InterruptTest2 {
    public static void main(String[] args) throws InterruptedException {
        ThreadK t1 = new ThreadK("t1");
        System.out.println(t1.getName() + " ( " + t1.getState() + " )" + " new ");

        t1.start();
        System.out.println(t1.getName() + " ( " + t1.getState() + " )" + " start ");

        Thread.sleep(100);
        t1.interrupt();
        System.out.println(t1.getName() + " ( " + t1.getState() + " )" + " interrupt ");
        Thread.sleep(100);
        System.out.println(t1.getName() + " ( " + t1.getState() + " )" + " interrupt sleep 1s ");


    }
}
