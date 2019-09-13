package com.lyp.learn.pk05.sequencestack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class MyArrayStackTest {

    MyArrayStack stack = new MyArrayStack();

    public MyArrayStackTest() throws Exception {
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void clear() throws Exception {
        System.out.println(stack.length());
        System.out.println(stack.isEmpty());
        stack.push("a");
        stack.push("b");
        stack.clear();
        stack.push("c");
        System.out.println(stack.length());
        System.out.println(stack.isEmpty());
        stack.push(100);
        stack.push(222);
        stack.displayDesc();
    }

    @Test
    void isEmpty() {
    }

    @Test
    void length() {
    }

    @Test
    void peek() {
    }

    @Test
    void push() throws Exception {
        System.out.println(stack.length());
        System.out.println(stack.isEmpty());
        stack.push("a");
        stack.push("b");
        stack.push(100);
        stack.push(150);
        stack.displayDesc();
        System.out.println(stack.length());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.length());
        stack.displayDesc();
        System.out.println();
    }

    @Test
    void pop() throws Exception {
        stack.push(100);
        stack.push(200);
        System.out.println(stack.length());
        System.out.println(stack.isEmpty());
        stack.displayDesc();
        stack.pop();
        stack.pop();
        stack.displayDesc();
        System.out.println(stack.length());
        System.out.println(stack.isEmpty());

        for(int i = 1 ; i <= 100; i++){
            stack.push(i);
        }
        stack.displayDesc();
    }
}