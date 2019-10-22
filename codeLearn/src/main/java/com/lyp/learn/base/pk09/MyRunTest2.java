package com.lyp.learn.base.pk09;

public class MyRunTest2{
    public static void main(String[] args) {
        MyRun2 myRun = new MyRun2();

        Thread t1 = new Thread(myRun);
        Thread t2 = new Thread(myRun);

        t1.start();
        t2.start();
    }
}

/**
 * Runnable的例子中可能会多出10张票。
 * 是因为t1, t2共用一个任务，同时对ticket进行操作；可能导致并发问题。
 * 例如，t1和t2在开始时读取到的ticket的值都是10，
 * 而t1卖了一张票之后，ticket=9；此时t2进行卖票，而t2之前读到的票的张数是10，所以，t2卖完之后也是9张。
 * 这样的话，问题就出现了：原本10张票，t1和t2各自都买了一张票之后，却还剩下9张！
 * 这样就导致了"实际卖出的票可能会多于10张"！
 *
 * 加入synchronized之后，只有thread-0或thread-1（谁先启动获得对象锁，谁执行）一个线程在工作。
 * 在主线程中创建了t1,t2这两个线程，它们共用一个MyRun2任务对象。
 * thread-0启动之后，一直占着"MyThread同步锁"，而一个对象有且只有一个同步锁；
 * 因此，只有thread-0在工作。
 *
 */
class MyRun2 implements Runnable{
    private Object objLock = new Object();
    private  int ticket = 10;
    @Override
    public void run() {
        synchronized (objLock){
            for(int i = 0 ;i <= 20 ;i++){
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName() + " 卖票 " + ticket--);
                }
            }
        }
    }
}
