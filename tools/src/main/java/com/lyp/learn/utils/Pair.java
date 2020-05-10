package com.lyp.learn.utils;

import java.util.Objects;

public class Pair<K, V> {
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

    public static <K, V> Pair<K, V> of(K code, V name) {
        Pair<K, V> pair = new Pair<>(code, name);
        return pair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return code.equals(pair.code) &&
                name.equals(pair.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "code=" + code +
                ", name=" + name +
                '}';
    }
}


