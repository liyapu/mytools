package com.lyp.learn;

/**
 *@author: liyapu
 *@description:
 *@date 2020-08-11 16:24
 */
public class DealLockTest {

    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (p1){
                        synchronized (p2){
                            System.out.println("p1 p2");
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (p2){
                        synchronized (p1){
                            System.out.println("p2 p1");
                        }
                    }
                }
            }
        }).start();

        System.out.println("main------");

    }
}

class Person{

}
