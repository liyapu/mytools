package com.lyp.learn.utils;

import java.util.concurrent.Semaphore;

public class TestNum {

    static volatile Integer count = 1;
    static volatile Integer state = 1;
    static Integer times = 10;
    static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < times;) {
                synchronized (obj) {
                    if (state == 1) {
                        System.out.println(Thread.currentThread().getName() + " " + count++);
                        state = 2;
                        i++;
                        obj.notifyAll();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < times; ) {
                synchronized (obj) {
                    if (state == 2) {
                        System.out.println(Thread.currentThread().getName() + " " + count++);
                        state = 3;
                        i++;
                        obj.notifyAll();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < times; ) {
                synchronized (obj) {
                    if (state == 3) {
                        System.out.println(Thread.currentThread().getName() + " " + count++);
                        state = 1;
                        i++;
                        obj.notifyAll();

                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

    }

//    static Semaphore aSemaphore = new Semaphore(1);
//    static Semaphore bSemaphore = new Semaphore(0);
//    static Semaphore cSemaphore = new Semaphore(0);
//
//    static volatile Integer count  = 1;
//
//    public static void main(String[] args) {
//
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    aSemaphore.acquire();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.print(Thread.currentThread().getName() + " " + count++);
//                bSemaphore.release();
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    bSemaphore.acquire();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.print(Thread.currentThread().getName() + " "+ count++);
//                cSemaphore.release();
//            }
//        }).start();
//
//
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    cSemaphore.acquire();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + " " + count++);
//                aSemaphore.release();
//            }
//        }).start();
//
//    }


}



