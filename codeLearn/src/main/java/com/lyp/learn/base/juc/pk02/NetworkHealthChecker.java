package com.lyp.learn.base.juc.pk02;


import java.util.concurrent.CountDownLatch;

public class NetworkHealthChecker extends BaseHealthChecker {

    public NetworkHealthChecker(CountDownLatch latch) {
        super(latch,"NetworkHealthChecker");
    }

    @Override
    protected void verifyService() {
        System.out.println(getServiceName() + " check....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getServiceName() + " is up");
    }
}
