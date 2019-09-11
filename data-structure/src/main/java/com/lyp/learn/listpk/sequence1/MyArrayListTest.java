package com.lyp.learn.listpk.sequence1;

public class MyArrayListTest {
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        MyArrayList myList = new MyArrayList();
        System.out.println(myList.isEmpty());
        System.out.println(myList.length());

        myList.add("a");
        myList.add("b");
        myList.add("c");
        myList.add("d");
        myList.add("e");
        myList.add("f");

        System.out.println(myList.isEmpty());
        System.out.println(myList.length());
        myList.display();
        System.out.println(myList.indexOf("d"));
        System.out.println(myList.indexOf("dd"));
        System.out.println(myList.lastIndexOf("e"));
        System.out.println(myList.lastIndexOf("ee"));
        System.out.println("---------------");

        myList.insert(2,"cc");
        myList.display();
        System.out.println(myList.get(2));
        System.out.println(myList.contains("cc"));
        System.out.println(myList.contains("ccccc"));
        myList.set(1,"bb");
        myList.display();
        myList.remove("e");
        myList.add(null);
        myList.add("g");
        myList.display();
        System.out.println(myList.indexOf(null));
        myList.clear();
        myList.display();
        System.out.println("-------------");
        for(int i = 1 ; i <= 10; i++){
            myList.add(i*i);
        }
        myList.display();
        System.out.println(myList.length());

    }
}
