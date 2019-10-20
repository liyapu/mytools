package com.lyp.learn.guava.cache;


import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LinkedListLRUCache<K,V> implements LRUCache<K,V> {
    private  final  LinkedList<K> keys = new LinkedList<>();
    private  final Map<K,V> cache = new HashMap<>();

    private final  int limit;

    public LinkedListLRUCache(int limit){
        this.limit = limit;
    }
    @Override
    public void put(K key, V value) {
        //参数校验，后面都要加，增加方法的健壮性
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        if(size() >= limit){
            K removeKey = keys.removeFirst();
            cache.remove(removeKey);
        }
        keys.addLast(key);
        cache.put(key,value);
    }

    @Override
    public V get(K key) {
        boolean exist = keys.remove(key);
        if(exist){
            V v = cache.get(key);
            keys.addLast(key);
            return v;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        boolean exist = keys.remove(key);
        if(exist){
            cache.remove(key);
        }
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public int limit() {
        return limit;
    }

    @Override
    public void clear() {
        keys.clear();
        cache.clear();
    }

    @Override
    public String toString() {
        return "LinkedListLRUCache{" +
                "list=" + keys +
                ", map=" + cache +
                '}';
    }
}
