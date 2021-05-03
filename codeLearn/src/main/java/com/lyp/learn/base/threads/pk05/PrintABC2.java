package com.lyp.learn.base.threads.pk05;


import java.util.concurrent.Semaphore;

public class PrintABC2 {

    private static Integer MAX = 10;
    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);

    public static void main(String[] args) {

        new Thread(() ->{
            try {
                for (int i = 1; i <= MAX; i++) {
                    A.acquire();
                    System.out.print("A");
                    B.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                for (int i = 1; i <= MAX ; i++) {
                    B.acquire();
                    System.out.print("B");
                    C.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            try {
                for (int i = 1; i <= MAX ; i++) {
                    C.acquire();
                    System.out.println("C");
                    A.release();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
