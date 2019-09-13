package com.lyp.learn.base.demo.pk06;

import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("a");
        hashSet.add("b");
        hashSet.add("c");
        hashSet.add("d");
        hashSet.add(null);
        hashSet.add("e");
        hashSet.add("f");
        hashSet.add("g");
        hashSet.add("h");
        hashSet.add("i");
        hashSet.add("j");
        hashSet.add("a");
        hashSet.add("k");
    }
}
