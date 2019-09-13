package com.lyp.learn.base.demo.pk12;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.IntBinaryOperator;
import java.util.function.UnaryOperator;

class Person{
    public int id;
    public volatile  int age;
    private volatile  int score;
    public  volatile Integer height;
    public String name;
    public volatile String address;

    public Person() {
    }

    public Person(int id, int age, int score, String name, String address,Integer height) {
        this.id = id;
        this.age = age;
        this.score = score;
        this.name = name;
        this.address = address;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

public class AtomicLongFieldUpdaterDemo {
    public static void main(String[] args) {
        System.out.println("--------person age-----------------------");
        // volatile 修饰的 int 类型的  可以访问到的
        AtomicIntegerFieldUpdater<Person> integerFieldUpdaterAge = AtomicIntegerFieldUpdater.newUpdater(Person.class,"age");
        Person p1 = new Person(1,11,111,"小明","商丘",170);
        System.out.println(p1.getAge());
        integerFieldUpdaterAge.incrementAndGet(p1);
        System.out.println(p1.getAge());
        System.out.println(integerFieldUpdaterAge.addAndGet(p1,8));

        IntBinaryOperator intBinaryOperator = (x,y) -> x * x + y;
        integerFieldUpdaterAge.accumulateAndGet(p1,10,intBinaryOperator);
        System.out.println(p1.getAge());

        System.out.println("--------person id-----------------------");
        //Exception in thread "main" java.lang.IllegalArgumentException: Must be volatile type
        //AtomicIntegerFieldUpdater<Person> integerFieldUpdaterId = AtomicIntegerFieldUpdater.newUpdater(Person.class,"id");
        System.out.println(p1.getId());

        System.out.println("--------person score-----------------------");
        //java.lang.RuntimeException: java.lang.IllegalAccessException: Class com.lyp.learn.demo.pk12.AtomicLongFieldUpdaterDemo can
        // not access a member of class com.lyp.learn.demo.pk12.Person with modifiers "private volatile"
       // AtomicIntegerFieldUpdater integerFieldUpdaterScore = AtomicIntegerFieldUpdater.newUpdater(Person.class,"score");
        System.out.println(p1.getScore());

        System.out.println("--------person height----------------------");
        //Interger 类型的，必须用引用字段的AtomicReferenceFieldUpdater
        //Exception in thread "main" java.lang.IllegalArgumentException: Must be integer type
        //AtomicIntegerFieldUpdater integerFieldUpdaterHeight = AtomicIntegerFieldUpdater.newUpdater(Person.class,"height");
        System.out.println(p1.getHeight());
        AtomicReferenceFieldUpdater referenceFieldHeight = AtomicReferenceFieldUpdater.newUpdater(Person.class,Integer.class,"height");
        referenceFieldHeight.compareAndSet(p1,170,175);
        System.out.println(p1.getHeight());
        referenceFieldHeight.set(p1,180);
        System.out.println(p1.getHeight());


        System.out.println("--------person address-----------------------");
        AtomicReferenceFieldUpdater referenceFieldUpdaterAddress =
                AtomicReferenceFieldUpdater.newUpdater(Person.class,String.class,"address");
        System.out.println(p1.getAddress());
        referenceFieldUpdaterAddress.compareAndSet(p1,"商丘","河南商丘aabb");
        System.out.println(p1.getAddress());

        UnaryOperator<String> unaryOperator = (x) -> x.toUpperCase();
        referenceFieldUpdaterAddress.updateAndGet(p1,unaryOperator);
        System.out.println(p1.getAddress());




    }
}
