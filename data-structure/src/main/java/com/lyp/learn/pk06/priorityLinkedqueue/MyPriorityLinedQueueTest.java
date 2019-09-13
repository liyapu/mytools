package com.lyp.learn.pk06.priorityLinkedqueue;

public class MyPriorityLinedQueueTest {
    public static void main(String[] args) throws Exception {
        MyPriorityLinkedQueue queue = new MyPriorityLinkedQueue();
        queue.offer(new PriorityNode(1,20));
        queue.offer(new PriorityNode(2,10));
        queue.offer(new PriorityNode(3,30));
        queue.offer(new PriorityNode(4,15));
        queue.offer(new PriorityNode(5,5));
        queue.offer(new PriorityNode(6,18));
        System.out.println("进程ID\t\t\t\t\t 进程优先级" );
        queue.display();
    }
}
