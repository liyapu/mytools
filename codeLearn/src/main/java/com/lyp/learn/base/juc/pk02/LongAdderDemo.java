package com.lyp.learn.base.juc.pk02;


import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.add(10L);
        System.out.println(longAdder);
    }
}
