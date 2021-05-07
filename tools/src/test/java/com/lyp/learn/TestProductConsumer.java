package com.lyp.learn;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class TestProductConsumer {

    static Semaphore mutex = new Semaphore(1);
    static Semaphore full = new Semaphore(10);
    static Semaphore empty = new Semaphore(0);
    static LinkedList<Integer> store = new LinkedList<>();
    static volatile Integer count  = 1;



    public static void main(String[] args) {
        new Producer().start();
        new Producer().start();
        new Producer().start();
        new Consumer().start();
        new Consumer().start();
        new Consumer().start();
    }

    static class Producer extends Thread{


        @Override
        public void run() {
            try {
               while (true){
                   Producer.create();
//                   Thread.sleep(1000);
               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static   void create() throws InterruptedException {
            full.acquire();
            mutex.acquire();
            int temp = count++;
            store.addLast(temp);
            System.out.println(Thread.currentThread().getName() + " " + temp + " 生产者 size = " + store.size());
            mutex.release();
            empty.release();
        }
    }


    static class Consumer extends Thread {

        public void run(){
            try {
                while (true){
                    Consumer.spend();
//                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public static void spend() throws InterruptedException {
            empty.acquire();
            mutex.acquire();
            int temp = store.removeFirst();
            System.out.println(Thread.currentThread().getName() + "  " + temp + "             消费者 size = " + store.size());
            mutex.release();
            full.release();
        }
    }
}
