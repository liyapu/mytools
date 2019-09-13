package com.lyp.learn.base.demo.pk10.pk3;

public class MainTest {
    public static void main(String[] args) {
        //仓库对象，也是线程锁对象，下面的生产与消费线程，均持有对对象
        Storage storage = new Storage();

        Producer p1 = new Producer("p1",storage);
        Producer p2 = new Producer("p2",storage);
        Producer p3 = new Producer("p3",storage);

        Consumer c1 = new Consumer("c1",storage);
        Consumer c2 = new Consumer("c2",storage);
        Consumer c3 = new Consumer("c3",storage);

        p1.start();
        p2.start();
        p3.start();

        c1.start();
        c2.start();
        c3.start();
    }
}
