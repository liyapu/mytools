//package com.lyp.learn.collectionpk;
//
//
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Stream;
//
///**
// */
//public class StreamTest {
//
//    //jdk 9 中针对于Stream 新添加了4个方法
//    /**
//     * 1.takeWhile()
//     *   从流中一直获取判定器为真的元素，一旦遇到元素为假，就终止处理了
//     *
//     *   用于从 Stream 中获取一部分数据，接收一个 Predicate 来进行选择。
//     *   在有序的 Stream 中，takeWhile 返回从开头开始的尽量多的元素
//     */
//    @Test
//    public void test1(){
//        List<Integer> list = Arrays.asList(45,56,33,77,44,98,76,78,33);
//
//        Stream<Integer> stream = list.stream();
//
//        stream.takeWhile(x -> x < 70).forEach(System.out::println);
//
//        System.out.println("----------------");
//
//        //起始部分没有符合的，什么都不会有
//        Arrays.asList(45,56,33,77,44,98,76,78,33)
//        .stream().takeWhile(x -> x < 40).forEach(System.out::println);
//
//        System.out.println("---------------");
//
//        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7,8);
//        list1.stream().takeWhile(x -> x < 5).forEach(System.out::println);
//    }
//
//    /**
//     * 2. dropWhile 的行为与 takeWhile 相反，返回剩余的元素
//     */
//    @Test
//    public void test2(){
//        List<Integer> list = Arrays.asList(45,56,33,77,44,98,76,78,33);
//
//        Stream<Integer> stream = list.stream();
//
//        stream.dropWhile(x -> x < 70).forEach(System.out::println);
//
//        System.out.println();
//
//        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7,8,1,2);
//        list1.stream().dropWhile(x -> x < 5).forEach(System.out::println);
//    }
//
//    /**
//     * 3. ofNullable
//     *
//     *  Java 8 中 Stream 不能完全为 null，否则会报空指针异常
//     *
//     * Java 9 中的 ofNullable 方法允许我们创建一个单元素 Stream，可以包含一个非空元素，也可 以创建一个空 Stream
//     *   ofNullable(T t): t可以为null
//     */
//    @Test
//    public void test3(){
//
//        Stream<Integer> stream1 = Stream.of(1, 2, 3, null,4,5);
//        stream1.forEach(System.out::println);
//
//        System.out.println();
//
//        //如果只有单个元素，此元素不能为null.否则报NullPointerException
//        //Stream<Object> stream2 = Stream.of(null);
//
//        //jdk 9 :新增ofNullable(T t):
//        Stream<String> stream3 = Stream.ofNullable("Tom");
//        System.out.println(stream3.count());//1
//
//        Stream<String> stream4 = Stream.ofNullable(null);
//        System.out.println(stream4.count());//0
//
//    }
//
//
//    /**
//     * 4. iterator()重载的使用:
//     */
//    @Test
//    public void test4(){
//        //复习：Stream的实例化：①通过集合的stream() ②通过数组工具类：Arrays ③ Stream中静态方法：of() ④iterator() / generate()
//
//        // 通过 limit 开控制终止方式
//        Stream.iterate(0,x -> x + 1)
//                .limit(5)
//                .forEach(System.out::println);
//
//        System.out.println();
//
//        // x -> x < 10 来控制终止方式
//        Stream.iterate(0,x -> x < 10,x -> x + 1)
//                .forEach(System.out::println);
//    }
//}
