package com.lyp.learn.list.singlecircle;

public class MySingleCircleLinkedListTest {
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        MySingleCircleLinkedList linkedList = new MySingleCircleLinkedList();
        System.out.println(linkedList.length());
        System.out.println(linkedList.isEmpty());
        linkedList.insert(0,"aaa");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println(linkedList.length());
        linkedList.display();
        linkedList.remove(0);
        linkedList.display();
        linkedList.display2();
        System.out.println(linkedList.length());
        System.out.println(linkedList.isEmpty());
        linkedList.remove("b");
        System.out.println(linkedList.indexOf("c"));
        linkedList.display();
        linkedList.insert(0,"aa");
        linkedList.display2();

    }
}
