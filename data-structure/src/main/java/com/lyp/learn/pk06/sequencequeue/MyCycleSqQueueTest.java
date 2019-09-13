package com.lyp.learn.pk06.sequencequeue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyCycleSqQueueTest {

    MyCycleSqQueue queue = new MyCycleSqQueue();

    @BeforeEach
    void setUp() {
    }

    @Test
    void clear() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void length() {
    }

    @Test
    void peek() {
    }

    @Test
    void offer() throws Exception {
        MyCycleSqQueue queue = new MyCycleSqQueue();
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

    @Test
    void poll() {
    }

    @Test
    void display() {
    }
}