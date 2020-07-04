package com.lyp.learn.list.sequence1;

public interface MyList {

    void clear();

    boolean isEmpty();

    int length();

    Object get(int i) throws Exception;

    void add(Object object) throws Exception;

    void insert(int i, Object object) throws Exception;

    void set(int i, Object object) throws Exception;

    void remove(int i) throws Exception;

    void remove(Object object) throws Exception;

    int lastIndexOf(Object object);

    int indexOf(Object object);

    boolean contains(Object object);

    void display();

}
