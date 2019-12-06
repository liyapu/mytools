package com.lyp.learn.base.juc.pk01;

public class TestVolatile extends Thread {
    boolean status = false;

    /**
     * 状态切换为true
     */
    public void changeStatus(){
        status = true;
    }

    /**
     * 若状态为true，则running。
     */
    @Override
    public void run(){
        if(status){
            System.out.println("running....");
        }
    }

    public static void main(String[] args) {
        TestVolatile t1 = new TestVolatile();
        t1.changeStatus();
        t1.start();

    }
}
