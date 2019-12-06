package com.lyp.learn.base.juc.pk01;


import com.lyp.learn.base.pk04.Person;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Person> arp1 = new AtomicReference<>();
        Person p1 = new Person();
        p1.setName("aa");
        arp1.set(p1);
        System.out.println(arp1.get().getName());

        Person p2 = new Person("bb");
        arp1.compareAndSet(p1,p2);
        System.out.println(arp1.get().getName());

    }
}
