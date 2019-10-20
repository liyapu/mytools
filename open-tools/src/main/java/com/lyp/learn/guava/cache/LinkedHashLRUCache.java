package com.lyp.learn.guava.cache;

import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LinkedHashLRUCache<K,V> implements LRUCache<K,V> {
    /**
     * 通过内部类 InternalLRUCache 继承 LinkedHashMap
     * 而不是 LinkedHashLRUCache 直接继承 LinkedHashMap，
     * 是不让LinkedHashLRUCache暴露 LinkedHashMap中方法，让外部仅仅看到LRUCache中的方法
     * 从而达到很好的封装
     * @param <K>
     * @param <V>
     */
    static class InternalLRUCache<K,V> extends LinkedHashMap<K, V> {
        private final int limit;
        public InternalLRUCache(int limit){
            super(16,0.75F,true);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
            return size() > limit;
        }
    }

    private final int limit;

    private InternalLRUCache<K,V> internalLRUCache;

    public LinkedHashLRUCache(int limit){
        Preconditions.checkArgument(limit > 0, "limit must big than 0");
        this.limit = limit;
        this.internalLRUCache = new InternalLRUCache<>(limit);
    }

    @Override
    public void put(K key, V value) {
        this.internalLRUCache.put(key,value);
    }

    @Override
    public V get(K key) {
        return this.internalLRUCache.get(key);
    }

    @Override
    public void remove(K key) {
         this.internalLRUCache.remove(key);
    }

    @Override
    public int size() {
        return this.internalLRUCache.size();
    }

    @Override
    public int limit() {
        return this.internalLRUCache.limit;
    }

    @Override
    public void clear() {
        this.internalLRUCache.clear();
    }

    @Override
    public String toString() {
       return this.internalLRUCache.toString();
    }
}
