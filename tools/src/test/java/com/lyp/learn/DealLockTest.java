package com.lyp.learn;

/**
 *@author: liyapu
 *@description:
 *@date 2020-08-11 16:24
 */
public class DealLockTest {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (o1){
                        synchronized (o2){
                            System.out.println("o1 o2");
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (o2){
                        synchronized (o1){
                            System.out.println("o2 o1");
                        }
                    }
                }
            }
        }).start();

        System.out.println("main------");

    }
}
