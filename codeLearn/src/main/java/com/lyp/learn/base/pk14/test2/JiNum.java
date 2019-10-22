package com.lyp.learn.base.pk14.test2;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-06-10 14:31
 */
public class JiNum implements Runnable {

    private TwoThread twoThread;

    public JiNum(TwoThread twoThread){
        this.twoThread = twoThread;
    }


    @Override
    public void run() {
        synchronized (TwoThread.class){
            while(twoThread.start <= 100){
                if(!twoThread.flag){
                    System.out.println(Thread.currentThread().getName() + " : " + twoThread.start++);
                    twoThread.flag = true;
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
