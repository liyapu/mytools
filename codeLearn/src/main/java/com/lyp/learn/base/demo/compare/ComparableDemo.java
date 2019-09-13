package com.lyp.learn.base.demo.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ComparableDemo {
    public static void main(String[] args) {
        /**
         * JDK的大量的类包括常见的 String、Byte、Char、Date等都实现了Comparable接口
         * Integer,String 类，都是实现 Comparable 接口的
         */
        Integer [] intArr = {5,8,2,6,9,3,8,1,7};
        System.out.println(Arrays.toString(intArr));
        Arrays.sort(intArr);
        System.out.println(Arrays.toString(intArr));
        System.out.println();


        List<Integer> intList = new ArrayList<>();
        intList.add(6);
        intList.add(3);
        intList.add(8);
        intList.add(5);
        intList.add(2);
        intList.add(9);
        intList.add(1);
        intList.add(6);
        System.out.println(intList);
        Collections.sort(intList);
        System.out.println(intList);
        System.out.println();

        String [] strArr = new String [] {"hh","kk","dd","aa","ee","pp","mm","xx"};
        System.out.println(Arrays.toString(strArr));
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr));
        System.out.println();

        List<String> strList = new ArrayList<>();
        strList.add("ff");
        strList.add("dd");
        strList.add("kk");
        strList.add("aa");
        strList.add("hh");
        strList.add("bb");
        System.out.println(strList);
        Collections.sort(strList);
        System.out.println(strList);
        System.out.println();

        /**
         * 自己实现 Comparable 接口的 compareTo 方法
         */
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("张三",20,"北京"));
        personList.add(new Person("李四",18,"天津"));
        personList.add(new Person("王五",26,"河南"));
        personList.add(new Person("赵田",15,"山东"));
        System.out.println(personList);
        Collections.sort(personList);
        System.out.println(personList);
        System.out.println();

    }
}
