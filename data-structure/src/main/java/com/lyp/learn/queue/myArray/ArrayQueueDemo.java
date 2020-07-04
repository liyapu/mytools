package com.lyp.learn.queue.myArray;

/**
 * 数组实现队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue aq = new ArrayQueue(8);
        aq.add(10);
        aq.add(100);
        aq.add(200);
        aq.add(300);
        aq.add(400);
        aq.add(500);
        aq.add(600);
        aq.add(700);
//        aq.add(800);
//        aq.add(900);

        aq.list();

//        System.out.println(aq.getHeadValue());
//        System.out.println(aq.getHeadValue());
//        System.out.println(aq.getHeadValue());
//        System.out.println(aq.getHeadValue());
        System.out.println();

        aq.list();


    }
}


class ArrayQueue {
    //该队列的最大容量
    private int maxSize;
    //指向队列头的前一个位置
    private int front;
    //指向队列尾的数据
    private int rear;
    //内部保存数据的数组
    private int[] arr;


    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    /**
     * 队列是否满了
     */
    public boolean isFull() {
        return maxSize - 1 == rear;
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
        arr[++rear] = num;
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
        return arr[++front];
    }

    /**
     * 遍历队列
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("-----list----为空--");
            return;
        }
        for (int i = front; i < rear; ) {
            System.out.println(arr[++i]);
        }
        System.out.println();
    }
}