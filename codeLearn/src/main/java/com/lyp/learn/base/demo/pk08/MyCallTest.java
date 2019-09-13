package com.lyp.learn.base.demo.pk08;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallTest {
    public static void main(String[] args) {
        for(int i = 0 ;i <= 10; i++){
            if(i == 2){
                MyCall myCall = new MyCall();
                FutureTask<Integer> futureTask = new FutureTask<>(myCall);
                Thread thread = new Thread(futureTask,"新线程");
                thread.start();
                try {
                    int count = futureTask.get();
                    System.out.println("count is :" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"\t" + i);
        }
    }
}
