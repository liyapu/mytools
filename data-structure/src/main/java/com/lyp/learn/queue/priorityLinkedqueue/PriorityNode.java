package com.lyp.learn.queue.priorityLinkedqueue;

public class PriorityNode {
    private Object item;
    private int priority;

    public PriorityNode(){

    }

    public PriorityNode(Object item, int priority){
        this.item = item;
        this.priority = priority;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "PriorityNode{" +
                "item=" + item +
                ", priority=" + priority +
                '}' + "\r\n";
    }
}
