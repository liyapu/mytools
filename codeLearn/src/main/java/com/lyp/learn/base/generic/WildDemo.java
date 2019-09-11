package com.lyp.learn.base.generic;

import java.util.ArrayList;
import java.util.Iterator;

public class WildDemo {


    public static <T> void printColl1(ArrayList<T> al){
        Iterator<T> it = al.iterator();
        while(it.hasNext())
        {
            T t = it.next();
            System.out.println(t.toString());
        }
    }

    //Unexpected wildcard
    //public static <?>  void printColl2(ArrayList<?> al) {
    public static  void printColl2(ArrayList<?> al) {
    Iterator<?> it = al.iterator();
        while (it.hasNext()) {
            //Cannot resolve symbol 'i'
            //? i = it.next();
            System.out.println(it.next().toString());
        }
    }
}
