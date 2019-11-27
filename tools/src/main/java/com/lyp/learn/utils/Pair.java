package com.lyp.learn.utils;

/**
 * @author: liyapu
 * @description:
 * @date 2019-11-27 19:33
 */
public class Pair<K,V> {
    private K code;
    private V name;

    public Pair() {
    }

    public Pair(K code, V name) {
        this.code = code;
        this.name = name;
    }

    public K getCode() {
        return code;
    }

    public void setCode(K code) {
        this.code = code;
    }

    public V getName() {
        return name;
    }

    public void setName(V name) {
        this.name = name;
    }

    public static <K,V> Pair<K,V> of(K code,V name){
        Pair<K,V> pair = new Pair<>(code,name);
        return pair;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "code=" + code +
                ", name=" + name +
                '}';
    }
}


