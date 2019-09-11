package com.lyp.learn.listpk.double2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyDouble2LinkedListTest {
    MyDouble2LinkedList linkedList = new MyDouble2LinkedList();

    @BeforeEach
    void setUp() {
        System.out.println("setup............");
    }

    @AfterEach
    void tearDown() {
        System.out.println("teardown..........");
    }

    @Test
    void clear() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.display();
        linkedList.displayDesc();
        linkedList.clear();
        linkedList.addFirst("aa");
        linkedList.addFirst("bb");
        linkedList.display();
    }

    @Test
    void isEmpty() throws Exception {
        System.out.println(linkedList.isEmpty());
        linkedList.addFirst("aa");
        System.out.println(linkedList.isEmpty());
    }

    @Test
    void length() throws Exception {
        System.out.println(linkedList.length());
        linkedList.addFirst("aa");
        linkedList.addFirst("bb");
        System.out.println(linkedList.length());
    }

    @Test
    void get() throws Exception {
        linkedList.addFirst("aa");
        linkedList.addFirst("bb");
        linkedList.addFirst("cc");
        linkedList.addFirst("dd");
        linkedList.addFirst("ee");

        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(3));
        System.out.println(linkedList.get(4));
    }

    @Test
    void get2() throws Exception {
        linkedList.addFirst("aa");
        linkedList.addFirst("bb");
        linkedList.addFirst("cc");
        linkedList.addFirst("dd");
        linkedList.addFirst("ee");
        linkedList.addFirst("ff");
        linkedList.addFirst("gg");
        linkedList.addFirst("hh");
        linkedList.addFirst("ii");

        System.out.println(linkedList.get2(0));
        System.out.println(linkedList.get2(2));
        System.out.println(linkedList.get2(5));
        System.out.println(linkedList.get2(7));
        System.out.println(linkedList.get2(8));
    }

    @Test
    void addFirst() throws Exception {
        linkedList.addFirst("aa");
        linkedList.addFirst("bb");
        linkedList.addFirst("cc");
        linkedList.addFirst("dd");
        linkedList.addFirst("ee");
        linkedList.display();
    }

    @Test
    void addTail() throws Exception {
        linkedList.addTail("a");
        linkedList.addTail("b");
        linkedList.addTail("c");
        linkedList.addTail("d");
        linkedList.addTail("e");
        linkedList.addTail("f");
        linkedList.addTail("g");
        linkedList.display();
        System.out.println(linkedList.length());
        System.out.println(linkedList.isEmpty());
    }

    @Test
    void insert() throws Exception {
        linkedList.insert(0,"aa");
        linkedList.display();
        linkedList.insert(0,"bb");
        linkedList.display();
        linkedList.insert(1,"cc");
        linkedList.display();
        linkedList.insert(2,"dd");
        linkedList.display();


    }

    @Test
    void set() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst("d");
        linkedList.display();
        linkedList.set(0,"aa");
        linkedList.set(1,"bb");
        linkedList.set(3,"dd");
        linkedList.display();
    }

    @Test
    void remove() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst("d");
        linkedList.display();
        System.out.println(linkedList.length());
        linkedList.remove(0);
        linkedList.display();
        linkedList.remove(2);
        linkedList.display();
        System.out.println(linkedList.length());

    }

    @Test
    void remove1() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst(null);
        linkedList.addFirst("d");
        linkedList.addFirst("a");
        linkedList.addFirst("e");
        linkedList.display();
        linkedList.remove("a");
        linkedList.remove(null);
        linkedList.display();
        linkedList.remove("c");
        linkedList.remove("e");
        linkedList.display();
    }

    @Test
    void lastIndexOf() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst("e");
        linkedList.display();
        System.out.println(linkedList.lastIndexOf("a"));
        System.out.println(linkedList.lastIndexOf("e"));
        System.out.println(linkedList.lastIndexOf("ee"));
    }

    @Test
    void indexOf() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst("e");
        linkedList.display();
        System.out.println(linkedList.indexOf("a"));
        System.out.println(linkedList.indexOf("aa"));
        System.out.println(linkedList.indexOf("e"));
    }

    @Test
    void contains() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst("e");

        System.out.println(linkedList.contains("a"));
        System.out.println(linkedList.contains("aa"));
        System.out.println(linkedList.contains("c"));
        System.out.println(linkedList.contains("cc"));
    }

    @Test
    void display() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst("d");
        linkedList.addFirst("e");
        linkedList.display();
        linkedList.remove(2);
        linkedList.remove("e");
        linkedList.display();

    }

    @Test
    void displayDesc() throws Exception {
        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst("d");
        linkedList.addFirst("e");
        linkedList.addFirst("f");
        linkedList.displayDesc();
        linkedList.remove("a");
        linkedList.insert(0,"aa");
        linkedList.displayDesc();
        System.out.println(linkedList.length());
    }
}