package com.lyp.learn.base.pk14.test2;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-06-10 14:36
 */
public class OuNum implements Runnable {

    private TwoThread twoThread;

    public OuNum(TwoThread tw){
        twoThread = twoThread;
    }


    @Override
    public void run() {
        synchronized (TwoThread.class){
            while(twoThread.start <= 100){
                if(twoThread.flag){
                    System.out.println(Thread.currentThread().getName() + " : " + twoThread.start++);
                    twoThread.flag = false;
                    TwoThread.class.notify();
                }else{
                    try {
                        TwoThread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
