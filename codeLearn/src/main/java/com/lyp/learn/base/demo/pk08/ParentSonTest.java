package com.lyp.learn.base.demo.pk08;

class ParentService{
    public synchronized void parentMethod(){
        System.out.println("ParentService  method .....");
    }
}

class SonService extends ParentService{
    public synchronized void sonMethod(){
        System.out.println("SonService method .....");
        parentMethod();
    }
}

class PsThread extends Thread{

    @Override
    public void run(){
        SonService sonService = new SonService();
        sonService.sonMethod();
    }
}

public class ParentSonTest {
    public static void main(String[] args) {
        PsThread psThread = new PsThread();
        psThread.start();
    }


}
