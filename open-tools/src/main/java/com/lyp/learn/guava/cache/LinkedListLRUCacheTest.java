package com.lyp.learn.guava.cache;

public class LinkedListLRUCacheTest {
    public static void main(String[] args) {
        LinkedListLRUCache<String,String> cache = new LinkedListLRUCache<>(3);
        cache.put("a","aa");
        cache.put("b","bb");
        cache.put("c","cc");

        System.out.println(cache);

        cache.put("d","dd");
        System.out.println(cache);

        String c = cache.get("b");
        System.out.println(c);
        System.out.println(cache);
        cache.put("e","ee");
        System.out.println(cache);
    }
}
