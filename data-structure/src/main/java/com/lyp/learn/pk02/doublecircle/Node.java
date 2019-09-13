package com.lyp.learn.pk02.doublecircle;

public class Node {

    private Object item;

    private Node prev;

    private Node next;

    public Node(){
    }

    public Node(Object object){
        this(null,object,null);
    }

    public Node(Node prev, Object item){
        this(prev,item,null);
    }

    public Node(Object item, Node next){
        this(null,item,next);
    }

    public Node(Node prev, Object item, Node next){
        this.prev = prev;
        this.item = item;
        this.next = next;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}
