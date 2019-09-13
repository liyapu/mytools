package com.lyp.learn.base.demo.pk12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStartup {
    public static volatile ApplicationStartup instance;

    public static List<BaseHealthChecker> serviceList;
    public static CountDownLatch latch;

    private ApplicationStartup(){

    }

    public static ApplicationStartup getInstance(){
        if(instance == null){
            synchronized (ApplicationStartup.class){
                if(instance == null){
                    instance = new ApplicationStartup();
                }
            }
        }
        return instance;
    }

    public static boolean checkExternalServices() throws InterruptedException {
        latch = new CountDownLatch(2);

        serviceList = new ArrayList<>();
        serviceList.add(new NetworkHealthChecker(latch));
        serviceList.add(new DatabaseHealthChecker(latch));

        Executor executor = Executors.newFixedThreadPool(serviceList.size());

        for(final BaseHealthChecker bhc : serviceList){
            executor.execute(bhc);
        }

        latch.await();

        for(BaseHealthChecker bhc : serviceList){
            if(!bhc.isServiceUp()){
                return false;
            }
        }

        return true;
    }


}
