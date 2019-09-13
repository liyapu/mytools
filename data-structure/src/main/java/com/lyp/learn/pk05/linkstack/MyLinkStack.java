package com.lyp.learn.pk05.linkstack;

public class MyLinkStack implements MyStack {
    //当前栈顶结点
    private Node top;
    //当前长度
    private int curLength;

    public MyLinkStack(){
        top = null;
        curLength = 0;
    }

    @Override
    public void clear() {
        top = null;
        curLength = 0;
    }

    @Override
    public boolean isEmpty() {
        return curLength == 0;
    }

    @Override
    public int length() {
        return curLength;
    }

    @Override
    public Object peek() {
        if(null == top){
            return null;
        }
        return top.getItem();
    }

    @Override
    public void push(Object object) throws Exception {
        Node newNode = new Node(object, top);
        top = newNode;
        curLength++;
    }

    @Override
    public Object pop() {
        Node temp = top;
        top = top.getNext();
        curLength--;
        return temp.getItem();
    }

    @Override
    public void displayAsc() {
        if(isEmpty()){
            return;
        }

        Node temp = top;
        while(temp != null){
            System.out.print(temp.getItem() + " ,");
            temp = temp.getNext();
        }
    }

    @Override
    public void displayDesc() {
        if(isEmpty()){
            return;
        }

        Node temp = top;
        while(temp != null){
            System.out.print(temp.getItem() + " ,");
            temp = temp.getNext();
        }
        System.out.println();
    }
}
