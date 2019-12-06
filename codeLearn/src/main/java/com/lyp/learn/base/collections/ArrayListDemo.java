package com.lyp.learn.base.collections;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");
        strList.add("d");
        strList.add("e");
        strList.add("f");
        strList.add("g");
        strList.add("h");
        strList.add("i");
        strList.add("j");
        strList.add("k");
        strList.add("l");
        strList.add("m");
        strList.add("n");
        strList.add("o");
        strList.add("p");

        Object[]  objArr = strList.toArray();
        String [] strArr = new String[50];
        for(int i = 0; i < strArr.length; i++){
            strArr[i] = i + "";
        }
        String [] strArr2 = strList.toArray(strArr);
        System.out.println(strArr.length);
        System.out.println(strArr2.length);
        for(int i =0 ;i < strArr2.length; i++){
            System.out.println("==== " + strArr2[i]);
        }
        String [] strArr3 = strList.toArray(new String[0]);
        System.out.println(strArr3.length);

        List<String> strList3= strList.subList(2,6);
        strList3.set(3,"ddddddd3333ddd");

        ArrayList<String> strList2 = new ArrayList<>();
        strList2.add("c");
        strList2.add("f");
        strList2.add("h");
        strList.removeAll(strList2);
        //strList.retainAll(strList2);

        ArrayList<String> strList4 = new ArrayList<>(6);
        strList4.add("aa");
        strList4.add("bb");





        //=================集合遍历
        System.out.println("--------strIter-------------");
        Iterator<String> strIter  = strList.iterator();
        while (strIter.hasNext()){
            System.out.println(strIter.next());
        }
        System.out.println("--------strListIter---------------");
        ListIterator<String> strListIter = strList.listIterator();
        while (strListIter.hasNext()){
            String s  = strListIter.next();
            System.out.println(s);
            if("h".equals(s)){
                //使用 iterator 迭代器，可以调用remove方法，在遍历时进行删除元素和添加元素
                strListIter.remove();
                strListIter.add("aaaaaaaaaa");
                //ConcurrentModificationException
                //strList.remove(s);
            }
        }
        System.out.println("--------for---------------");
        for(String s : strList){
            System.out.println(s);
            if("g".equals(s)){
                //ConcurrentModificationException
               // strList.remove(s);
            }
        }

        System.out.println("------for size-----------");
        for(int i = 0; i < strList.size(); i++){
            System.out.println(strList.get(i));
        }
    }
}
