package com.lyp.learn.ppt;

import org.junit.jupiter.api.Test;

import java.util.function.*;

public class FunctionParamDemo {
    /**
     * 无参数 : 无返回值
     */

    @Test
    public void test1(){
        Runnable r = () -> System.out.println("aaaaaa");
        new Thread(r).start();
    }

    /**
     * 无参数 : 有返回值
     */
    // Supplier<T> 供给型接口
    @Test
    public void test2(){
        Supplier<String> supplier1 = () -> "aaaaaa";
        String str = supplier1.get();
        System.out.println(str);

        Supplier<String> supplier2 = () -> "返回值";
        String str2 = supplier2.get();
        System.out.println(str2);

        Supplier<Integer> supplier3 = () -> 100;
        System.out.println(supplier3.get());
    }

    /**
     * 一个参数 : 无返回值
     */
    // Consumer<T>  消费型接口
    @Test
    public void test3(){
        Consumer<String> consumer1 = (String param) -> System.out.println(param);
        consumer1.accept("consumer1 aaaa");

        Consumer<Integer> consumer2 = (num) -> System.out.println("数字 是 " + num);
        consumer2.accept(5);

        //只需要一个参数的时候，”->”左边的括号可以省略
        Consumer<Integer> consumer3 = num -> System.out.println("num is :" + num);
        consumer3.accept(8);

        Consumer<Integer> consumer4 = System.out::println;
        consumer4.accept(66);
    }

    /**
     * 一个参数 : 有返回值
     */
    //  Function<T,R>  函数型接口
    @Test
    public void test4(){
        Function<String,Integer> strLength = (String str) -> str.length();
        Integer length1 = strLength.apply("abc");
        System.out.println(length1);

        Function<String,String> funcitonStr = (String a) -> a.toUpperCase();
        String str = funcitonStr.apply("aaaaaa");
        System.out.println(str);
    }

    /**
     * 一个参数 : 返回布尔值
     */
    //  Predicate<T> 段言型接口
    @Test
    public void test5(){
        Predicate<String> predicate1 = (String param) -> "tt".equals(param);
        Boolean boolean1 = predicate1.test("aa");
        System.out.println(boolean1);

        Boolean boolean11 = predicate1.test("tt");
        System.out.println(boolean11);
        System.out.println();

        Predicate<String> predicate2 = (param) -> "tt".equals(param);
        System.out.println(predicate2.test("aa"));
        System.out.println(predicate2.test("tt"));
    }


    /**
     * 二个参数 : 没有返回值
     */
    @Test
    public void test6(){
        BiConsumer<String,String> biConsumer1 = (String a,String b) -> System.out.println(a+b);
        biConsumer1.accept("java","Lambda");

        BiConsumer<String,Integer> biConsumer2 = (a,b) -> System.out.println(a+b);
        biConsumer2.accept("java",666);
    }

    /**
     * 二个参数 : 有个String类型返回值
     */
    @Test
    public void test7(){
        BiFunction<String,String,String> biFunction1 = (String a,String b) -> a.concat(b);
        String str1 = biFunction1.apply("java "," Lambda");
        System.out.println(str1);

        BiFunction<String,String,String> biFunction2 = (a,b) -> a.concat(b);
        String str2 = biFunction2.apply("hello "," world");
        System.out.println(str2);
    }


    //=============================================================================

    /**
     * 一元函数式接口，传入参数和返回值类型相同
     */
    @Test
    public void test8(){
        UnaryOperator<String> unaryOperator = (a) -> a.toUpperCase();
        String str = unaryOperator.apply("aaaaa");
        System.out.println(str);

        String str2 = unaryOperator.apply("aabbcc");
        System.out.println(str2);
    }
}
