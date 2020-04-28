package com.lyp.learn.lambdapk;


import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class FunctionDemo {

    /**
     * apply
     */
    @Test
    public void test1(){
        Function<String,Integer> f1 = s -> Integer.parseInt(s);
        int result = f1.apply("25");
        System.out.println(result);
    }

    @Test
    public void test11(){
        String result = strHandler("aabb我们",x -> x.toUpperCase());
        System.out.println(result);
    }

    public String strHandler(String str,Function<String,String> function){
       return function.apply(str);
    }

    /**
     * compose
     * compose方法接收一个Function参数before，该方法说明是返回一个组合的函数，
     * 首先会应用before，然后应用当前对象，
     * 换句话说就是先执行before对象的apply，再执行当前对象的apply，将两个执行逻辑串起来
     */
    @Test
    public void test31(){
        Function<Integer,Integer> f1 = n -> n * 2;
        Function<Integer,Integer> f2 = i -> i * i;

        //先执行f2，然后再把结果传给f1
        int result1 = f1.compose(f2).apply(5);
        System.out.println(result1);
    }

    @Test
    public void test3(){
        Function<String,Integer> f1 = s -> Integer.parseInt(s);
        Function<Integer,Double> f2 = i -> Math.sqrt(i);

        Double d1 = f2.compose(f1).apply("25");
        System.out.println(d1);
    }

    /**
     * andThen
     * andThen方法接收一个Function参数after，与compose方法相反，
     * 它是先执行当前对象的apply方法，再执行after对象的方法
     */
    @Test
    public void test32(){
        Function<Integer,Integer> f1 = n -> n * 2;
        Function<Integer,Integer> f2 = i -> i * i;

        //先执行f1,然后再把结果传给f2
        int result1 = f1.andThen(f2).apply(5);
        System.out.println(result1);
    }

    @Test
    public void test33(){
        Function<Integer,Integer> f1 = n -> n * 2;
        Function<Integer,Integer> f2 = i -> i * i;
        Function<Integer,Integer> f3 = i -> --i;

        int result1 = f1.andThen(f2).andThen(f3).apply(5);
        System.out.println(result1);
    }
    @Test
    public void test2(){
        Function<String,Integer> f1 = s -> Integer.parseInt(s);
        Function<Integer,Double> f2 = i -> Math.sqrt(i);

        Double d1 = f1.andThen(f2).apply("25");
        System.out.println(d1);
    }



    /**
     * identity
     * 输入什么，就返回什么
     */
}
