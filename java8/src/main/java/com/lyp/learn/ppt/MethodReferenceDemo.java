package com.lyp.learn.ppt;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;
/**
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 * 	 若Lambda 的参数列表的第一个参数 x，是实例方法的调用者，第二个参数 y (或无参)是实例方法的参数时，格式： ClassName::MethodName
 *      (x, y) -> x.equals(y)
 *      String::equals
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 *
 */
public class MethodReferenceDemo {
    /**
     * 类 :: 静态方法名
     */
    @Test
    public void test1() {
        Comparator<Integer> com1 = (x, y)->Integer.compare(x,y);
        int comResult1 = com1.compare(30,50);
        System.out.println(comResult1);

        Comparator<Integer> com2 = Integer::compare;
        int comResult2 = com2.compare(50,30);
        System.out.println(comResult2);

        Function<Long, Long> function1 = Math::abs;
        Long result = function1.apply(-3L);
        System.out.println(result);
    }

    /**
     * 类 :: 实例方法名(或称 成员方法名)
     *
     */

    @Test
    public void test2() {
        BiPredicate<String,String> bp1 = (x, y) -> x.equals(y);
        boolean bpResult1 = bp1.test("a","b");
        System.out.println(bpResult1);

        BiPredicate<String,String> bp2 = String::equals;
        boolean bpResult2 = bp2.test("a","a");
        System.out.println(bpResult2);
    }


    /**
     * 对象 :: 实例方法名(或称 成员方法名)
     */
    @Test
    public void test3() {
        Consumer<String> consumer1 = (x) -> System.out.println(x);
        consumer1.accept("hello");

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("world");

        PrintStream printStream = System.out;
        Consumer<String> consumer3 = printStream::println;
        consumer3.accept("lalala");
        System.out.println();

        //这里的隐式lambda表达式（也就是实例方法引用）会从knownNames中捕获String对象，
        // 而它的方法体则会通过Set.contains使用该String对象。
        Set<String> knownNames = new HashSet<>();
        knownNames.add("aa");
        knownNames.add("bb");
        Predicate<String> isKnown = knownNames::contains;

        boolean isKnowBoolean1 = isKnown.test("ab");
        System.out.println(isKnowBoolean1);

        boolean isKnowBoolean2 = isKnown.test("bb");
        System.out.println(isKnowBoolean2);

        List<Apple> inventory = Arrays.asList(
                new Apple("green",80,"黄土高原"),
                new Apple("green",200,"黄河故道"),
                new Apple("red",160,"渤海湾"),
                new Apple("yellow",20,"渤海湾")
        );
        //Apple 中定义了一个非静态的，实例方法 compareByWeight
        Apple apple = new Apple();
        inventory.sort(apple::compareByWeight);
        System.out.println(inventory);

        List<String> appleColors = new ArrayList<>();
        inventory.stream()
                 .map(Apple::getColor)
                 .forEach(appleColors::add);

        System.out.println(appleColors);
    }


    /**
     * 构造器引用
     * 格式：ClassName::new
     */
    @Test
    public void test4(){
        Supplier<String> sup1 = () -> new String();
        Supplier<String> sup2 = String :: new;


        //Function<Integer, StringBuffer> fun = n -> new StringBuffer(n);
        Function<Integer, StringBuffer> fun = StringBuffer::new;
        StringBuffer buffer = fun.apply(10);
        System.out.println(buffer.length());


        Supplier<Apple> supplierApple = Apple::new;
        //使用了Apple类构造方法引用创建了supplier实例，以后通过supplier.get()就可以获取一个Apple类型的对象，
        // 前提是Apple类中存在无参构造方法
        Apple apple = supplierApple.get();
        System.out.println(apple);

        apple.setColor("红色");
        apple.setWeight(100);
        apple.setAddress("黄土高原");
        System.out.println(apple);

    }

    /**
     * 数组引用
     * 格式：Type []::new
     * 数组的构造方法引用,进行初始化数组
     */
    @Test
    public void test5(){
        Function<Integer,String[]> fun1 = (x) -> new String[x];
        String[] strArr1 = fun1.apply(5);
        System.out.println(strArr1.length);
        System.out.println(strArr1[0]);
        System.out.println(strArr1[1]);
        System.out.println(strArr1[4]);
        //java.lang.ArrayIndexOutOfBoundsException: 5
        //System.out.println(strArr1[5]);

        Function<Integer,String[]> fun2 = String[]::new;
        String[] strArr2 = fun2.apply(6);
        System.out.println(strArr2.length);

        IntFunction<int[]> arrayMaker = int[]::new;
        int[] array = arrayMaker.apply(10);
        System.out.println(array.length);

    }

    /**
     * super::引用成员方法
     */
    @Test
    public void test6(){
        Cat c = new Cat();
        c.sayHello();
    }

    //================= super :: demo start====================
    class Animal {
        public void printMessage() {
            System.out.println("Hello ");
        }
    }
    //Animal的子类
    class Cat extends Animal {
        public void sayHello(){
            new Thread(super::printMessage).start();
        }
    }
    //================= super :: demo end====================

    /**
     * this::成员方法名
     */
    @Test
    public void test7(){
        Man man = new Man();
        man.beHappy();
    }
    //================= this :: demo start====================

    //函数式接口
    interface shopping {
        void buy(int money);
    }
    //已存在的类
    class Man{
        //男人将来都要买个属于自己的房子.
        public void buyHouse(int money) {
            System.out.println("买套房子消费:"+money);
        }

        //结婚就得购物,买买买啊.
        public void marry(shopping lambda,int money) {
            lambda.buy(money);
        }

        //开心方法.男人要想开心.就得结婚
        public void beHappy() {
            //通过this引用成员方法.
            marry(this::buyHouse,1000000);
        }
    }
    //================= this :: demo end====================

}
