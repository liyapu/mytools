package com.lyp.learn.base.threads.pk02;

import com.lyp.learn.base.threads.pk01.Object;

//如下demo的4个方法展示了不同使用方法下锁对象
public class SynchronizedDemo {
    private static final Object LOCK = new Object();

    public static synchronized void s1(){
        System.out.println("类同步方法，锁对象是当前Class对象");
    }

    public synchronized void s2() {
        System.out.println("实例同步方法，锁对象是当前对象");
    }

    public void s3() {
        synchronized (LOCK) {
            System.out.println("同步块，锁对象是LOCK对象");
        }
    }

    public void s4() {
        synchronized (SynchronizedDemo.class) {
            System.out.println("同步块，锁对象和静态同步方法的锁对象一样都是当前Class对象");
        }
    }
}
