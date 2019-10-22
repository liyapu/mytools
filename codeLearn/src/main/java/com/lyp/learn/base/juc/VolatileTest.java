package com.lyp.learn.base.juc;

/**
 * volatile 关键字
 *
 * flag 在 ThreadDemo 和 Main 线程中共享，在两个线程中都用到了flag
 * flag 是共享变量
 * 解决方式：
 * 1. 加锁
 *    synchronized
 * 2. 使用 volatile 关键字
 */
public class VolatileTest {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while(true){
            //后续ThreadDemo 线程更改flag,Main线程可能不会再从主存读取flag了
            if(td.isFlag()){
                System.out.println("main thread read ThreadDemo flag is ture");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable{
    boolean flag = false;
//  volatile  boolean  flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag is = " + isFlag());
    }
}
