package com.lyp.learn.pk06.sequencequeue;

public class MyCycleSqQueue implements MyQueue {
    //底层存储元素的数组
    private Object [] queueElem ;
    //队首位置
    private int front;
    //队尾位置
    private int rear;

    private static int DEFAULT_LENGHT  = 16;

    public MyCycleSqQueue(){
        this(DEFAULT_LENGHT);
    }

    public MyCycleSqQueue(int length){
        front = rear = 0;
        queueElem = new Object[length];
    }

    @Override
    public void clear() {
        front = rear = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public int length() {
        return (rear - front + queueElem.length) % queueElem.length;
    }

    @Override
    public Object peek() {
        if(isEmpty()){
            return null;
        }else{
            return queueElem[front];
        }
    }

    @Override
    public void offer(Object object) throws Exception {
        if((rear + 1) % queueElem.length == front){
            throw new Exception("offer 元素错误，队列已满");
        }
        //将object存储到rear所指定的数组位置上，使其称为新的队尾元素
        queueElem[rear] = object;
        rear = (rear + 1) % queueElem.length;
    }

    @Override
    public Object poll() {
        if(isEmpty()){
            return null;
        }else{
            Object temp = queueElem[front];
            front = (front+1)%queueElem.length;
            return temp;
        }
    }

    @Override
    public void display() {
        if(isEmpty()){
            System.out.println("队列为空");
        }else{
            for(int i = front ; i != rear; i = (i + 1)%queueElem.length){
                System.out.print(queueElem[i] + " ,");
            }
            System.out.println();
        }
    }
}
