package com.lyp.learn.base.threads.safe.fieldparam;

/**
 * 实例变量线程安全问题模拟：
 */
public class FieldTest implements Runnable {

    //实例变量
    private  int instance_i;

    @Override
    public void run() {
        instance_i = 4;
        System.out.println(Thread.currentThread().getName() + " 获取 instance_i = " + instance_i);
        instance_i = 10;
        System.out.println(Thread.currentThread().getName() + " 获取 instance_i * 2 = " + (instance_i*2));
    }

    public static void main(String[] args) {
        FieldTest st = new FieldTest();
        int threadCount = 300;
        //启动尽量多的线程才能很容易的模拟问题
        for(int i = 0; i <= threadCount; i++){
            /**
             * 每个线程对在对象 st 中运行，模拟单例情况
             * 按照本文开头的分析，犹如静态变量那样，每个线程都在修改同一个对象的实例变量，肯定会出现线程安全问题。
             * instance_i = 10
             * instance_i * 2 = 8
             */
//            new Thread(st).start();

            /**
             * 非单列对象，线程安全
             * 模拟非单例情况，会发现不存在线程安全问题
             */
            new Thread(new FieldTest()).start();
        }

    }
}
