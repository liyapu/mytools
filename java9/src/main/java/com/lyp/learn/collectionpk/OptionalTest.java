//package com.lyp.learn.collectionpk;
//
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Stream;
//
///**
// */
//public class OptionalTest {
//
//    //Optional类中提供了转换为Stream的方法：stream()
//    @Test
//    public void test1() {
//
//        List<String> list = new ArrayList<>();
//        list.add("Tom");
//        list.add("Jerry");
//        list.add("Tim");
//
//        Optional<List<String>> optional = Optional.ofNullable(list);
//
//        Stream<String> stream = optional.stream().flatMap(x -> x.stream());
////        System.out.println(stream.count());
//
//        stream.forEach(System.out::println);
//
//
//    }
//}
