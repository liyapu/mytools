package com.lyp.learn.guava.cache;

public class LinkedHashLRUCacheTest {
    public static void main(String[] args) {
        LinkedHashLRUCache<String,String> cache = new LinkedHashLRUCache<>(3);
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
