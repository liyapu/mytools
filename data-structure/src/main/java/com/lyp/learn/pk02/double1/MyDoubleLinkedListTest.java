package com.lyp.learn.pk02.double1;


public class MyDoubleLinkedListTest {
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList();
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.length());

        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
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
        linkedList.add("bbb");
        linkedList.add("c");
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
        linkedList.add("aa");
        linkedList.add("bb");
        linkedList.insert(1,"cc");
        linkedList.display();

    }
}
