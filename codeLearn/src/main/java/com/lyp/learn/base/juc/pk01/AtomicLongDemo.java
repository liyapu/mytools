package com.lyp.learn.base.juc.pk01;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

public class AtomicLongDemo {
    public static void main(String[] args) {
        AtomicLong atomic = new AtomicLong();
        System.out.println(atomic.get());
        System.out.println(atomic.getAndIncrement());
        System.out.println(atomic.get());
        System.out.println(atomic.getAndAdd(10));
        System.out.println(atomic.get());
        System.out.println(atomic.compareAndSet(20,22));
        System.out.println(atomic.get());

        System.out.println("-------------------");
        System.out.println(atomic.decrementAndGet());
        System.out.println(atomic.get());

        System.out.println("------------------------");
        LongUnaryOperator longUnaryOperator = (x) -> x * x + 5;
        atomic.updateAndGet(longUnaryOperator);
        System.out.println(atomic.get());

        LongBinaryOperator longBinaryOperator = (x,y) -> x * y;
        atomic.accumulateAndGet(10,longBinaryOperator);
        System.out.println(atomic.get());

    }
}
