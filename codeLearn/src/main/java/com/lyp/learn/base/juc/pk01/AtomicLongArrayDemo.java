//package com.lyp.learn.base.juc.pk01;
//
//import java.util.concurrent.atomic.AtomicLongArray;
//import java.util.function.LongBinaryOperator;
//import java.util.function.LongUnaryOperator;
//
//public class AtomicLongArrayDemo {
//    public static void main(String[] args) {
//        AtomicLongArray array = new AtomicLongArray(30);
//        System.out.println("--------index 1--------");
//        System.out.println(array.get(1));
//        System.out.println(array.compareAndSet(1,1,10));
//        System.out.println(array.get(1));
//        System.out.println(array.compareAndSet(1,0,10));
//        System.out.println(array.get(1));
//        System.out.println("--------index 2--------");
//        System.out.println(array.addAndGet(2,20));
//
//        System.out.println("--------index 3--------");
//        LongUnaryOperator longUnaryOperator = (x) -> x + 10;
//        System.out.println(array.getAndUpdate(3,longUnaryOperator));
//        System.out.println(array.get(3));
//
//        System.out.println("--------index 4--------");
//        LongUnaryOperator longUnaryOperator2 = (x) -> x + 10;
//        System.out.println(array.updateAndGet(4,longUnaryOperator2));
//        System.out.println(array.get(4));
//
//        System.out.println("--------index 5--------");
//        array.set(5,50);
//        System.out.println(array.get(5));
//        //数组下标的值，作为第一个参数，给定的值作为第二个参数
//        LongBinaryOperator binaryOperator1 = (x,y) -> x * x + y;
//        System.out.println(array.accumulateAndGet(5,10,binaryOperator1));
//
//        System.out.println("--------index 6---------");
//        System.out.println(array.incrementAndGet(6));
//        System.out.println(array.getAndIncrement(6));
//        System.out.println(array.get(6));
//        System.out.println(array.addAndGet(6,60));
//
//
//
//
//
//
//    }
//
//}
