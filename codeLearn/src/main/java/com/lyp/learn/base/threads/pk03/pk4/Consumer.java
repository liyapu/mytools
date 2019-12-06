package com.lyp.learn.base.threads.pk03.pk4;


public class Consumer extends Thread {
    //持有仓库对象，对象锁也是此对象
    private Storage storage;

    public Consumer(String name, Storage storage){
        super(name);
        this.storage = storage;
    }

    @Override
    public void run(){
        //死循环，一直消费
        while (true){
            try {
                //睡眠1.5s
                Thread.sleep(1500);
                //消费产品
                storage.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
