package com.lyp.learn.base.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericTest3 {
    public static void main(String[] args) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        System.out.println("classStringArrayList :" + classStringArrayList);
        System.out.println("classIntegerArrayList : " + classIntegerArrayList);
        System.out.println(classStringArrayList.equals(classIntegerArrayList));
    }
}
