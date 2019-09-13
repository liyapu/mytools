package com.lyp.learn.pk02.single1;

public interface MyList {

    void clear();

    boolean isEmpty();

    int length();

    Object get(int i) throws Exception;

    void add(Object object) throws Exception;

    void insert(int i, Object object) throws Exception;

    void set(int i, Object object) throws Exception;

    void remove(int i) throws Exception;

    void remove2(int i) throws Exception;

    void remove(Object object) throws Exception;

    void remove2(Object object) throws Exception;

    int indexOf(Object object);

    boolean contains(Object object);

    void display();

    void display2();

}
