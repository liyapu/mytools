package com.lyp.learn.base.jucatguigu.cf03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author liyapu
 * @date 2022-09-12 13:44
 * @description CompletableFuture详解~join与get的区别
 * 一.相同点：
 * join()和get()方法都是用来获取CompletableFuture异步之后的返回值
 * 二.区别：
 * 1.join()方法抛出的是uncheck异常（即RuntimeException),不会强制开发者抛出，
 * 　　会将异常包装成CompletionException异常 /CancellationException异常，但是本质原因还是代码内存在的真正的异常，
 * 2.get()方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch）
 */
public class CompletableFutureJoinAndGet {

    public static void main(String[] args) {
        testGet();
        testJoin();
        testChain();
    }


    private static void testGet() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            return "hello 123";
        });

        String result = null;
        try {
            result = cf.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println("发生异常-------");
        }
        System.out.println(" get result = " + result);
    }

    private static void testJoin() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            return " abc abc";
        });

        String result = cf.join();
        System.out.println("join result = " + result);
    }

    private static void testChain() {
        //链式chain写法
        Student student = new Student(
        );
        student.setId(1).setStudentName("张三").setMajor("英语");
        System.out.println("testChain = " + student);
    }
}
