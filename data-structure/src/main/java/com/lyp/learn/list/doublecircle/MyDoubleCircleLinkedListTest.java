package com.lyp.learn.list.doublecircle;

public class MyDoubleCircleLinkedListTest {
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        MyDoubleCircleLinkedList linkedList = new MyDoubleCircleLinkedList();
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.length());

        linkedList.addFirst("a");
        linkedList.addFirst("b");
        linkedList.addFirst("c");
        linkedList.addFirst("d");
        linkedList.display();
        System.out.println(linkedList.contains("b"));
        System.out.println(linkedList.contains("bb"));
        System.out.println(linkedList.indexOf("b"));
        System.out.println(linkedList.indexOf("bb"));
        System.out.println("---------------");
        linkedList.display();
        linkedList.insert(1,"cc");
        linkedList.display();
        linkedList.insert(0,"d");
        linkedList.addTail("bbb");
        linkedList.addTail("c");
        linkedList.display();
        System.out.println("---remove-------");
        linkedList.remove(1);
        linkedList.display();
        linkedList.remove(0);
        linkedList.display();
        linkedList.remove("a");
        linkedList.display();
        linkedList.remove(1);
        linkedList.display();
        System.out.println("------------");
        linkedList.clear();
        linkedList.addTail("aa");
        linkedList.addTail("bb");
        linkedList.insert(1,"cc");
        linkedList.display();
        System.out.println(linkedList.length());
        System.out.println(linkedList.isEmpty());
    }
}
