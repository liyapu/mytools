package com.lyp.learn.pk06.linkedqueue;

public class MyLinkQueue implements MyQueue{
    //队首指针
    private Node front;
    //队尾指针
    private Node rear;

    public MyLinkQueue(){
        front = rear = null;
    }

    @Override
    public void clear() {
        front = rear = null;
    }

    /**
     * 是否为空，判断队首指针是否为null
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int length() {
        int length = 0;
        Node head = front;
        while(head != null){
            length++;
            head = head.getNext();
        }
        return length;
    }

    @Override
    public Object peek() {
        if(isEmpty()){
            return null;
        }else{
            return front.getItem();
        }
    }

    @Override
    public void offer(Object object) throws Exception {
        Node newNode = new Node(object);
        if(isEmpty()){
            front = rear= newNode;
        }else{
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    /**
     * 删除队首元素时，
     * 判断删除的是否是尾节点
     * @return
     */
    @Override
    public Object poll() {
        if(isEmpty()){
            return null;
        }else{
            Node temp = front;//存储临时队首结点
            front = front.getNext();//队首结点后移一位
            if(temp == rear){//出队结点是队尾结点时，处理一下队尾结点
                rear = null;
            }
            return temp.getItem();
        }
    }

    @Override
    public void display() {
        if(isEmpty()){
            System.out.println("链表队列是空的");
        }else{
            Node head = front;
            while(head != null){
                System.out.print(head.getItem() + ",");
                head = head.getNext();
            }
            System.out.println();
        }
    }
}
