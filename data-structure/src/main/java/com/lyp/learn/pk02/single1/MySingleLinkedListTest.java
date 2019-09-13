package com.lyp.learn.pk02.single1;

public class MySingleLinkedListTest {
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        MySingleLinkedList linkedList = new MySingleLinkedList();
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.length());

        linkedList.add("a");
        linkedList.add("b");
        System.out.println(linkedList.get(1));
        linkedList.display();
        linkedList.insert(0,"aa");
        linkedList.display();
        System.out.println(linkedList.length());
        System.out.println(linkedList.isEmpty());
        linkedList.insert(1,"bb");
        linkedList.display();
        System.out.println("---------------");
        System.out.println(linkedList.contains("b"));
        System.out.println(linkedList.contains("cc"));
        System.out.println(linkedList.indexOf("bb"));
        System.out.println(linkedList.indexOf("bbbb"));
        linkedList.display2();
        System.out.println("------remove--------");
        linkedList.remove(0);
        linkedList.display();
        linkedList.remove(1);
        linkedList.display();
        System.out.println("-------remove object------------");
        linkedList.remove("cc");
        linkedList.remove("a");
        linkedList.display();





    }
}
