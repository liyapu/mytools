package com.lyp.learn.queue.linkedqueue;

public class MyLinkQueueTest {
    public static void main(String[] args) throws Exception {
        MyLinkQueue queue = new MyLinkQueue();
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        System.out.println(queue.isEmpty());
        System.out.println(queue.length());
        queue.display();
        System.out.println(queue.poll());
        System.out.println(queue.length());
        queue.display();
        queue.clear();
        System.out.println(queue.length());
        System.out.println(queue.isEmpty());
        queue.offer(100);
        queue.offer(200);
        queue.display();
    }
}
