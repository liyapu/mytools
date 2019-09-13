package com.lyp.learn.pk02.double2;

public interface MyList {

    void clear();

    boolean isEmpty();

    int length();

    Object get(int i) throws Exception;

    Object get2(int i) throws Exception;

    void addFirst(Object object) throws Exception;

    void addTail(Object object) throws Exception;

    void insert(int i, Object object) throws Exception;

    void set(int i, Object object) throws Exception;

    void remove(int i) throws Exception;

    void remove(Object object) throws Exception;

    int lastIndexOf(Object object);

    int indexOf(Object object);

    boolean contains(Object object);

    void display();

    void displayDesc();

}
