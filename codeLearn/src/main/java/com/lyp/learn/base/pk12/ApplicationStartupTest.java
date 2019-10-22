package com.lyp.learn.base.pk12;

public class ApplicationStartupTest {
    public static void main(String[] args) throws InterruptedException {

        ApplicationStartup applicationStartup = ApplicationStartup.getInstance();
        boolean isAllUp = ApplicationStartup.checkExternalServices();
        System.out.println(isAllUp);
    }
}
