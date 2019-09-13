package com.lyp.learn.base.demo.pk06;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<>(16);
        hashMap.put("ABCDEa123abc","11");
        hashMap.put("ABCDFB123abc","1111");
        hashMap.put("a","aaa");
        hashMap.put("a","aaa11");
        hashMap.putIfAbsent("a","aaa22");
        hashMap.put("b","bbb");
        hashMap.put("c","ccc");
        hashMap.put("d","ddd");
        Set<String> set = hashMap.keySet();
        //set.add("e");
        //set.add("f");
        set.remove("d");
        System.out.println("---");
        for(Map.Entry<String,String> entry : hashMap.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
