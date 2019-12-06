package com.lyp.learn.base.executors.pk01;

public class SequenceThread extends Thread {

    private SequenceNumber sn;
    public SequenceThread(SequenceNumber sn) {
        this.sn = sn;
    }
    // 线程产生序列号
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()   + ", sn = " + sn.getNextNum());
        }
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        // 三个线程产生各自的序列号
        SequenceThread t1 = new SequenceThread(sn);
        SequenceThread t2 = new SequenceThread(sn);
        SequenceThread t3 = new SequenceThread(sn);
        t1.start();
        t2.start();
        t3.start();
    }
}
