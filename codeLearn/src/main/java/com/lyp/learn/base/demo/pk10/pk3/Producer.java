package com.lyp.learn.base.demo.pk10.pk3;


public class Producer extends Thread {
    //持有仓库对象，对象锁也是此对象
    private Storage storage;

    public Producer(String name, Storage storage){
        super(name);
        this.storage = storage;
    }

    @Override
    public void run(){
        //死循环，一直生产
        while (true){
            try {
                //睡眠1s
                Thread.sleep(1000);
                //生产产品
                storage.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
