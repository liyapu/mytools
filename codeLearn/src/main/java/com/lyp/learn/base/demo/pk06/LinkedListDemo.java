package com.lyp.learn.base.demo.pk06;

import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList strLink = new LinkedList();
        strLink.add("a");
        strLink.add("b");
        strLink.add("c");
        strLink.add("d");

        Object [] objArr1 = strLink.toArray();
        //String [] strArr2 =  strLink.toArray(new String [0]);


        LinkedList strLink2 = new LinkedList(strLink);
        strLink2.add("aaa");
        strLink2.add("bbb");

        LinkedList strLink3 = new LinkedList();
        strLink3.add("a11");
        strLink3.add("a22");
        strLink3.add("a33");
        strLink3.add("a44");
        strLink3.addAll(strLink2);
        strLink3.addAll(2,strLink2);
        strLink3.add("a55");




    }
}
