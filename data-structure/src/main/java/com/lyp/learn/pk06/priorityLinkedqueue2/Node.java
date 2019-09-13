package com.lyp.learn.pk06.priorityLinkedqueue2;

public class Node {
    private Object item;
    //[1,10],优先级默认为5
    private int priority;
    private Node next;

    public Node(Object item){
        this(item,5,null);
    }
    public Node(Object item, int priority){
        this(item,priority,null);
    }

    public Node(Object item, int priority, Node next){
        this.item = item;
        this.priority = priority;
        this.next = next;
    }

    public Node(Object item, Node next){
        this.item = item;
        this.next = next;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", priority=" + priority +
                ", next=" + next +
                '}'+"\r\n";
    }
}
