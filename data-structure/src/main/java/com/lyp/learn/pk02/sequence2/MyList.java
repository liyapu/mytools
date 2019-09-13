package com.lyp.learn.pk02.sequence2;

public interface MyList<T> {

    void clear();

    boolean isEmpty();

    int length();

    T get(int i) throws Exception;

    void add(T t) throws Exception;

    void insert(int i, T t) throws Exception;

    void set(int i, T t) throws Exception;

    void remove(int i) throws Exception;

    void remove(T t) throws Exception;

    int lastIndexOf(T t);

    int indexOf(T t);

    boolean contains(T t);

    void display();

}
