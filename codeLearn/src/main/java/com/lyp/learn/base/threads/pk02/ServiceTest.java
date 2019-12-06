package com.lyp.learn.base.threads.pk02;


class Service{

    public synchronized void testWait(Object lock){
        System.out.println(Thread.currentThread().getName() + " wait 开始");
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " wait 结束");

    }

    public synchronized void testNotify(Object lock){
        System.out.println(Thread.currentThread().getName() + " notify 开始");
        try {
            lock.notify();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " nofity 结束");

    }
}

class ServiceWait extends Thread {
    private Object lock;

    public ServiceWait(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testWait(lock);
    }
}

 class ServiceNotify extends Thread {
    private Object lock;

    public ServiceNotify(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testNotify(lock);
    }
}

public class ServiceTest {
    public static void main(String[] args) {
        Object lock = new Object();

        ServiceWait serviceWait = new ServiceWait(lock);
        serviceWait.start();
        ServiceNotify serviceNotify = new ServiceNotify(lock);
        serviceNotify.start();

    }
}
