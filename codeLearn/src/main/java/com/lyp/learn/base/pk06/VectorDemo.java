package com.lyp.learn.base.pk06;

import java.util.Enumeration;
import java.util.ListIterator;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> strVector = new Vector<>();
        strVector.add("a");
        strVector.add("b");
        strVector.add("c");
        System.out.println("strVector.capacity() : " + strVector.capacity());
        System.out.println("strVector.size() : " + strVector.size());
        strVector.add("d");
        strVector.add("e");
        strVector.trimToSize();
        strVector.add("f");
        strVector.add("g");
        strVector.add("h");
        strVector.add("i");
        strVector.add("j");
        strVector.add("k");
        strVector.add("l");
        strVector.add("m");
        strVector.add("n");
        strVector.add("o");

        Object [] objArr = new Object[strVector.size()];
        strVector.copyInto(objArr);
        System.out.println(objArr.length);

        for(String s : strVector){
            System.out.println("for---" + s);
        }

        ListIterator<String> strListIter = strVector.listIterator();
        while (strListIter.hasNext()){
            System.out.println("listIter--- " + strListIter.next());
        }

        Enumeration<String> strEnum = strVector.elements();

        while (strEnum.hasMoreElements()){
            System.out.println("strEnum------" + strEnum.nextElement());
        }

    }
}
