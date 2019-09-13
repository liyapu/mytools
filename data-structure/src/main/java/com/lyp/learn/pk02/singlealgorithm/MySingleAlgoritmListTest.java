package com.lyp.learn.pk02.singlealgorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-05-21 08:30
 */
class MySingleAlgoritmListTest {
    MySingleAlgoritmList list = null;

    @BeforeEach
    void test(){
        list = new MySingleAlgoritmList();
    }
    @Test
    void clear() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void length1() throws Exception {
        System.out.println(list.length());
        list.addOfHeadInsertionOfHead("a");
        list.addOfHeadInsertionOfHead("b");
        list.addOfHeadInsertionOfHead("c");
        list.addOfHeadInsertionOfHead("d");
        System.out.println(list.length());
    }

    @Test
    void length2() {
    }

    @Test
    void get() {
    }

    @Test
    void add() {
    }

    @Test
    void insert() {
    }

    @Test
    void set() {
    }

    @Test
    void remove() {
    }

    @Test
    void remove2() {
    }

    @Test
    void remove1() {
    }

    @Test
    void remove21() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void findLastNodeItem() throws Exception {
        list.addTailInsertionOfHeadTail("a");
        list.addTailInsertionOfHeadTail("b");
        list.addTailInsertionOfHeadTail("c");
        list.addTailInsertionOfHeadTail("d");
        list.addTailInsertionOfHeadTail("e");
        list.addTailInsertionOfHeadTail("f");
        list.display();
        System.out.println(list.findLastNodeItem(0));
        System.out.println(list.findLastNodeItem(2));
        System.out.println(list.findLastNodeItem(5));

        System.out.println(list.findLastNodeItem2(0));
        System.out.println(list.findLastNodeItem2(2));
        System.out.println(list.findLastNodeItem2(5));

        System.out.println(list.findLastNodeItem3(1));
        System.out.println(list.findLastNodeItem3(2));
        System.out.println(list.findLastNodeItem3(5));
    }

    @Test
    void findMiddleItem() throws Exception {
        System.out.println(list.findMiddleItem());
        list.addTailInsertionOfHeadTail("a");
        list.addTailInsertionOfHeadTail("b");
        list.addTailInsertionOfHeadTail("c");
        list.addTailInsertionOfHeadTail("d");
        list.addTailInsertionOfHeadTail("e");
        list.display();
        System.out.println(list.findMiddleItem());
        list.addTailInsertionOfHeadTail("f");
        list.display();
        System.out.println(list.findMiddleItem());
    }

    @Test
    public void mergeList(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(4);
        list1.add(6);
        list1.add(8);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(6);
        list2.add(7);
        List<Integer> resultList = list.mergeList(list1,list2);
        for(Integer i : resultList){
            System.out.print( i + ",");
        }
     }
    @Test
    public void mergeList2() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(3);
        list1.addTailInsertionOfHeadTail(5);
        list1.addTailInsertionOfHeadTail(7);
        list1.display();
        MySingleAlgoritmList list2 = new MySingleAlgoritmList();
        list2.addTailInsertionOfHeadTail(2);
        list2.addTailInsertionOfHeadTail(3);
        list2.addTailInsertionOfHeadTail(4);
        list2.addTailInsertionOfHeadTail(5);
        list2.addTailInsertionOfHeadTail(6);
        list2.addTailInsertionOfHeadTail(9);
        list2.display();
        Node head = new MySingleAlgoritmList().mergeList(list1.head.getNext(),list2.head.getNext());
        if(head != null){
            Node next = head;
            while(next != null){
                System.out.print(next.getItem()+",");
                next = next.getNext();
            }
        }
    }

    @Test
    public void mergeListRecursion() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(3);
        list1.addTailInsertionOfHeadTail(5);
        list1.addTailInsertionOfHeadTail(7);
        list1.display();
        MySingleAlgoritmList list2 = new MySingleAlgoritmList();
        list2.addTailInsertionOfHeadTail(2);
        list2.addTailInsertionOfHeadTail(3);
        list2.addTailInsertionOfHeadTail(4);
        list2.addTailInsertionOfHeadTail(5);
        list2.addTailInsertionOfHeadTail(6);
        list2.addTailInsertionOfHeadTail(9);
        list2.display();
        Node head = new MySingleAlgoritmList().mergeListRecursion(list1.head.getNext(),list2.head.getNext());
        if(head != null){
            Node next = head;
            while(next != null){
                System.out.print(next.getItem()+",");
                next = next.getNext();
            }
        }

    }

    @Test
    void reverseList() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(3);
        list1.addTailInsertionOfHeadTail(5);
        list1.addTailInsertionOfHeadTail(7);
        list1.addTailInsertionOfHeadTail(9);
        list1.display();

        Node newReverseHead = list1.reverseList();
        Node next = newReverseHead.getNext();
        while(next != null){
            System.out.print(next.getItem()+",");
            next = next.getNext();
        }
    }

    @Test
    void reverseList1() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(2);
        list1.addTailInsertionOfHeadTail(5);
        list1.addTailInsertionOfHeadTail(7);
        list1.addTailInsertionOfHeadTail(9);
        list1.display();

        Node newReverseHead = list1.reverseList1();
        Node next = newReverseHead.getNext();
        while(next != null){
            System.out.print(next.getItem()+",");
            next = next.getNext();
        }
    }
    @Test
    void reverseList2() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(3);
        list1.addTailInsertionOfHeadTail(5);
        list1.addTailInsertionOfHeadTail(7);
        list1.addTailInsertionOfHeadTail(9);
        list1.display();

        Node newReverseHead = list1.reverseList2();
        Node next = newReverseHead.getNext();
        while(next != null){
            System.out.print(next.getItem()+",");
            next = next.getNext();
        }
    }

    @Test
    void reverseList3() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(3);
        list1.addTailInsertionOfHeadTail(5);
        list1.addTailInsertionOfHeadTail(9);
        list1.display();

        Node newReverseHead = list1.reverseList4(list1.head);
        Node next = newReverseHead;
        while(next != null){
            System.out.print(next.getItem()+",");
            next = next.getNext();
        }
    }

    @Test
    void reversePrint() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(3);
        list1.addTailInsertionOfHeadTail(5);
        list1.addTailInsertionOfHeadTail(7);
        list1.addTailInsertionOfHeadTail(9);
        list1.display();

        list1.reversePrint1(list1.head.getNext());
        System.out.println();
        list1.reversePrint2(list1.head.getNext());

    }

    @Test
    void hasCycle() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(2);
        list1.addTailInsertionOfHeadTail(3);
        list1.addTailInsertionOfHeadTail(4);
        list1.addTailInsertionOfHeadTail(5);
        list1.display();
        System.out.println(list1.hasCycle(list1.head));
        System.out.println(list1.getCycleLength(list1.head));
        list1.addTailInsertionOfHeadTail(list1.head);
        System.out.println(list1.hasCycle(list1.head));
        System.out.println(list1.getCycleLength(list1.head));


    }

    @Test
    void cycleStartNode() throws Exception {
        MySingleAlgoritmList list1 = new MySingleAlgoritmList();
        list1.addTailInsertionOfHeadTail(1);
        list1.addTailInsertionOfHeadTail(2);
        list1.addTailInsertionOfHeadTail(3);
        list1.addTailInsertionOfHeadTail(4);
        list1.addTailInsertionOfHeadTail(5);
        list1.display();
        list1.addTailInsertionOfHeadTail(list1.head.getNext().getNext());
        System.out.println(list1.cycleStartNode(list1.head).getItem());
        System.out.println(list1.entryLoop(list1.head).getItem());

    }
    @Test
    void contains() {
    }

    @Test
    void display() {

    }

    @Test
    void display2() {
    }


}