package com.lyp.learn.base.pk12;

public class MyLocalTest {

    public static void main(String[] args) throws InterruptedException {
        MyLocal myLocal = new MyLocal();

        myLocal.setLocal();
        System.out.println(myLocal.getLongLocal());
        System.out.println(myLocal.getStrLocal());
        System.out.println("--------------------");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                myLocal.setLocal();
                System.out.println(myLocal.getLongLocal());
                System.out.println(myLocal.getStrLocal());
            }
        });
        thread.start();
        thread.join();

        System.out.println(".................");
        System.out.println(myLocal.getLongLocal());
        System.out.println(myLocal.getStrLocal());

    }
}
