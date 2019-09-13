package com.lyp.learn.pk05.sequencestack;


public class MyArrayStack implements MyStack {
    //底层数组存储
    private Object [] stackElem;
    //指向栈顶位置
    private int top;

    /**
     * 栈的默认初始化大小
     */
    private static final int DEFAULT_LENGTH = 16;

    public MyArrayStack() throws Exception {
        this(DEFAULT_LENGTH);
    }

    public MyArrayStack(int length) throws Exception {
        if(length <= 0){
            throw new Exception("MyArrayStack Construct lenght err. length = " + length);
        }
        top = 0;
        stackElem = new Object[length];
    }

    @Override
    public void clear() {
        top = 0;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public int length() {
        return top;
    }

    @Override
    public Object peek() {
        if(isEmpty()){
            return null;
        }
        return stackElem[top-1];
    }

    @Override
    public void push(Object object) throws Exception {
        if(top == stackElem.length){
            enlargeLength();
        }
        stackElem[top++] = object;
    }

    private void enlargeLength() {
        int length = stackElem.length;
        int newLength = length + length / 2;
        Object [] newStackElem = new Object[newLength];
        for(int i = 0 ;i < length; i++){
            newStackElem[i] = stackElem[i];
        }
        stackElem = newStackElem;
    }

    @Override
    public Object pop() {
        if(isEmpty()){
            return null;
        }
        return stackElem[--top];
    }

    @Override
    public void displayAsc() {
        int length = top - 1;
        for(; length >= 0 ;length--){
            System.out.print(stackElem[length] + ",");
        }
        System.out.println();
    }

    @Override
    public void displayDesc() {
        for(int i = 0 ; i < top; i++){
            System.out.print(stackElem[i] + " ,");
        }
        System.out.println();
    }
}
