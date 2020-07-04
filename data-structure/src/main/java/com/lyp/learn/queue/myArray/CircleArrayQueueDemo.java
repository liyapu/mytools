package com.lyp.learn.queue.myArray;

/**
 * @author: liyapu
 * @description:
 * @date 2020-07-04 09:12
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue aq = new CircleArrayQueue(4);
        aq.add(10);
        aq.add(100);
        aq.add(200);
        System.out.println(aq.getHeadValue());
        aq.add(300);
        System.out.println(aq.getHeadValue());

        aq.add(400);
        System.out.println(aq.getHeadValue());

        aq.add(500);
        System.out.println(aq.getHeadValue());

        aq.add(600);
        System.out.println(aq.getHeadValue());

        aq.add(700);
        System.out.println(aq.getHeadValue());

        aq.add(800);
        System.out.println(aq.getHeadValue());

        aq.add(900);
        System.out.println(aq.getHeadValue());


        aq.list();

        System.out.println(aq.getHeadValue());
        System.out.println(aq.getHeadValue());
//        System.out.println(aq.getHeadValue());
//        System.out.println(aq.getHeadValue());
        aq.add(300);
        aq.add(400);

        System.out.println();

        aq.list();
        System.out.println(aq.getHeadValue());
        System.out.println(aq.getHeadValue());
        aq.list();


    }
}


class CircleArrayQueue {
    //该队列的最大容量
    private int maxSize;
    //指向队列头的前一个位置
    private int front;
    //指向队列尾的数据
    private int rear;
    //内部保存数据的数组
    private int[] arr;


    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 队列是否满了
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加元素
     *
     * @param num
     */
    public void add(int num) {
        if (isFull()) {
            throw new IllegalArgumentException("队列已满,不能添加数据了......");
        }
        arr[rear] = num;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取头部元素
     *
     * @return
     */
    public int getHeadValue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空,不能取出数据.......");
        }
        int tempValue = arr[front];
        front = (front + 1) % maxSize;
        return tempValue;
    }

    /**
     * 遍历队列
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("-----list----为空--");
            return;
        }

        System.out.println();
        System.out.println("----遍历队列---");
        int size = this.size();
        for (int i = front; i < front + size; i++) {
            System.out.println(arr[(i + maxSize) % maxSize]);
        }
        System.out.println();
    }

    /**
     * 获取有效元素的个数
     */
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }
}