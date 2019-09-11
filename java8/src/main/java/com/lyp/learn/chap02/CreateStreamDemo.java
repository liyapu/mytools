package com.lyp.learn.chap02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStreamDemo {
    public static void main(String[] args) {
        //由值创建流
        System.out.println("----------由值创建流---------------");
        Stream<String> strStream = Stream.of("aa","bb","ff","bb","gg");
        strStream.forEach(s -> System.out.print(s +", "));
        System.out.println("\r\n");

        //创建一个空流
        Stream<String> strStream2 = Stream.empty();

        //由数组创建流
        System.out.println("--------由数组创建流---------");
        int [] intArr = {5,3,2,7,8,3};
        IntStream intStream = Arrays.stream(intArr);
        int intSum = intStream.sum();
        System.out.println(intSum);
        System.out.println();

        //由文件生成流
        System.out.println("--------由文件生成流------------");
//        long uniqueWords = 1;
//        try(
//                //todo Paths 路径，读取文件，待学习解决
//           Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.forName("utf-8"))
//        ) {
//            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
//                    .distinct()
//                    .count();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//        System.out.println(uniqueWords);
        System.out.println();


        //由函数生成流：创建无限流
        System.out.println("-----------由函数生成流：创建无限流---------------");
        List<Integer> intList =  Stream.iterate(5, n -> n + 5)
                                        .limit(10)
                                        .collect(Collectors.toList());
        System.out.println(intList);
        //斐波纳契数列
        Stream.iterate(new int [] {0,1},t -> new int [] {t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(t -> System.out.print(t + " "));

        System.out.println();
        //斐波纳契元组序列
        Stream.iterate(new int [] {0,1}, t -> new int [] {t[1],t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.print("(" + t[0] + "," + t[1] + ")  " ));

        System.out.println();

        Stream.generate(Math::random)
                .limit(5)
                .forEach(t -> System.out.print(t + " , "));
        System.out.println();

    }
}
