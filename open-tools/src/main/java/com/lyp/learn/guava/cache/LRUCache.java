package com.lyp.learn.guava.cache;

public interface LRUCache<K,V> {

    void put(K key,V value);

    V get(K key);

    void remove(K key);

    int size();

    int limit();

    void clear();
}
