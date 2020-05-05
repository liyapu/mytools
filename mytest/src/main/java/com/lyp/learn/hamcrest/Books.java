package com.lyp.learn.hamcrest;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能: 内存书架
 *
 */
public class Books {
    final Map<Integer, String> map = new ConcurrentHashMap<>();

    /**
     * 功能: 存书，并返回书的id
     */
    int add(String title) {
        final Integer nextId = this.map.size() + 1;
        this.map.put(nextId, title);
        return nextId;
    }

    /**
     * 功能:  根据书的id读取书名
     */
    String title(int id) {
        return this.map.get(id);
    }
}