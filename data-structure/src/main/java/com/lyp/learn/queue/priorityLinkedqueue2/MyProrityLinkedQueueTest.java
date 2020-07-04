package com.lyp.learn.queue.priorityLinkedqueue2;
/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-05-27 00:36
 */
public class MyProrityLinkedQueueTest {
    public static void main(String[] args) throws Exception {
        MyPriorityLinkedQueue queue = new MyPriorityLinkedQueue();
        queue.offer(new Node("李世民",8));
        queue.offer(new Node("张飞",7));
        queue.offer(new Node("魏征",4));
        queue.offer(new Node("关羽"));
        queue.offer(new Node("嬴政",2));
        queue.offer(new Node("刘彻",6));
        queue.display();
    }
}
