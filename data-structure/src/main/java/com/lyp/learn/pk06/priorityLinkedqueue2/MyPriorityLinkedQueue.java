package com.lyp.learn.pk06.priorityLinkedqueue2;

public class MyPriorityLinkedQueue implements MyQueue {

    private Node front;
    private Node rear;

    public MyPriorityLinkedQueue(){
        front = rear = null;
    }

    @Override
    public void clear() {
        front = rear = null;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int length() {
        Node temp = front;
        int length = 0;
        while(temp != null){
            length++;
            temp = temp.getNext();
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
        Node newNode = (Node)object;
        if(isEmpty()){
            front = rear = newNode;
        }else{
            if(newNode.getPriority() <= front.getPriority()){//队首插入
                newNode.setNext(front);
                front = newNode;
            }else if(newNode.getPriority() >= rear.getPriority()){//队尾插入
                rear.setNext(newNode);
                rear = newNode;
            }else{
                Node temp = front;
                while(temp != null && temp.getNext() != null
                        && temp.getNext().getPriority() <= newNode.getPriority()){ //中间插入
                    temp = temp.getNext();
                }
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
            }
        }
    }

    @Override
    public Object poll() {
        if(isEmpty()){
            return null;
        }else{
            Node temp = front;
            front = front.getNext();
            if(temp == rear){
                rear = null;
            }
            return  temp.getItem();
        }
    }

    @Override
    public void display() {
        Node temp = front;
        while(temp != null){
            System.out.print(temp.getItem() + " : " + temp.getPriority() + "\r\n");
            temp = temp.getNext();
        }
        System.out.println();
    }
}
