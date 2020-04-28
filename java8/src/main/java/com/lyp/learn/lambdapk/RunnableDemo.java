package com.lyp.learn.lambdapk;

public class RunnableDemo {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("aaaaaaaaaa");
            }
        };
        new Thread(runnable).start();

        //匿名内部类实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("bbbbbbbbbbbb");
            }
        }).start();

        //Lambda
        Runnable runnable2 = () -> System.out.println("cccccccccc");
        new Thread(runnable2).start();

        new Thread(() -> System.out.println("dddddddd")).start();
    }
}
