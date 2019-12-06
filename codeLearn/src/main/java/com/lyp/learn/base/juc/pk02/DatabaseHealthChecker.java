package com.lyp.learn.base.juc.pk02;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker{

    public DatabaseHealthChecker(CountDownLatch latch){
        super(latch,"DatabaseHealthChecker");
    }

    @Override
    protected void verifyService() {
        System.out.println(getServiceName() + " check....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getServiceName() + " is up");    }
}
