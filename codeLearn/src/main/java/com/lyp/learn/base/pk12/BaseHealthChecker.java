package com.lyp.learn.base.pk12;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable {
    private CountDownLatch latch;
    private String serviceName;
    private boolean serviceUp;

    public BaseHealthChecker(CountDownLatch latch,String serviceName){
        super();
        this.latch = latch;
        this.serviceName = serviceName;
        this.serviceUp = false;
    }

    @Override
    public void run(){
        try {
            verifyService();
            serviceUp = true;
        } catch (Exception e) {
            serviceUp = false;
            e.printStackTrace();
        } finally {
            if(latch != null){
                latch.countDown();
            }
        }
    }

    public String getServiceName(){
        return serviceName;
    }

    public boolean isServiceUp(){
        return serviceUp;
    }

    //此抽象方法被具体的检查服务实现
    protected abstract void verifyService();
}
