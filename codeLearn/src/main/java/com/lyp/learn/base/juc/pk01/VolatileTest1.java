package com.lyp.learn.base.juc.pk01;

public class VolatileTest1 {
    private volatile int count = 0;

    public void increase(){
        count++;
    }

    public static void main(String[] args) {
        VolatileTest1 vt = new VolatileTest1();
        for(int i = 1; i <= 10; i++){
            new Thread().start();
        }

        while (Thread.activeCount() >= 1){
            Thread.yield();
        }

        System.out.println(vt.count);
    }
}
