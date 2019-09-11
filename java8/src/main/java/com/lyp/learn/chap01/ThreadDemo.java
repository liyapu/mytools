package com.lyp.learn.chap01;


public class ThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable say hello world");
            }
        });
        thread.start();

        /**
         * 用Lambda表达式的话
         */
        Thread thread2 = new Thread(() -> System.out.println("runnable say second 2222"));
        thread2.start();

        System.out.println("main thread.....");
    }
}
