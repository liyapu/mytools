package com.lyp.learn.base.demo.pk08;

import java.util.concurrent.Callable;

public class MyCall implements Callable<Integer> {
    private int i ;
    @Override
    public Integer call() throws Exception {
        for(; i <= 10 ; i++){
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
        return i;
    }
}
