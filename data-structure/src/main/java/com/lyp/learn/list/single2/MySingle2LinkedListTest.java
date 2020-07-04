package com.lyp.learn.list.single2;

public class MySingle2LinkedListTest {

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        MySingle2LinkedList linkedList = new MySingle2LinkedList();
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.length());

        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add(null);
        linkedList.add("d");
        linkedList.display();
        linkedList.set(1,"aa");
        linkedList.display();
        System.out.println(linkedList.contains("c"));
        System.out.println(linkedList.contains("cc"));
        System.out.println(linkedList.contains(null));
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.length());
        System.out.println(linkedList.get(2));
        linkedList.insert(1,"bb");
        linkedList.display();
        System.out.println("---------clear---------");
        linkedList.clear();
        linkedList.display();
        System.out.println("----------------");
        linkedList.add("aaa");
        System.out.println(linkedList.length());
        linkedList.display();

    }
}
