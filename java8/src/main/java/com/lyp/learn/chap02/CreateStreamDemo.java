package com.lyp.learn.chap02;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 获取Stream
 */
public class CreateStreamDemo {

    /**
     * 由值创建流
     */
    @Test
    public void testNum(){
        Stream<String> strStream = Stream.of("aa","bb","ff","bb","gg");
        strStream.forEach(s -> System.out.print(s +", "));
    }

    /**
     * 创建一个空流
     */
    public void testEmptyStream(){
        Stream<String> strStream2 = Stream.empty();
    }

    /**
     * 由数组创建流
     */
    @Test
    public void testArray(){
        int [] intArr = {5,3,2,7,8,3};
        IntStream intStream = Arrays.stream(intArr);
        int intSum = intStream.sum();
        System.out.println(intSum);
    }

    /**
     * 由函数生成流：创建无限流
     */
    @Test
    public void testFunctionStream(){
        List<Integer> intList =  Stream.iterate(5, n -> n + 5)
                                        .limit(10)
                                        .collect(Collectors.toList());
        System.out.println(intList);

        System.out.println("-----------");

        Stream.generate(Math::random)
                .limit(5)
                .forEach(t -> System.out.print(t + " , "));
    }

    /**
     * 由文件生成流
     */
    @Test
    public void  testFileStream(){
        List<String> uniqueWords = new ArrayList<>();
        try(Stream<String> lines = Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("").getPath(),"data.txt"), Charset.forName("utf-8"))) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                                .distinct()
                                .collect(Collectors.toList());
        } catch(IOException e){
            e.printStackTrace();
        }

        uniqueWords.forEach(System.out::println);
    }

    /**
     * 斐波纳契
     */
    @Test
    public void testFeiBoNaQie(){
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
    }

}
