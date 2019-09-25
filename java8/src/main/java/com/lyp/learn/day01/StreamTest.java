package com.lyp.learn.day01;

import com.lyp.learn.ppt.Apple;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream API 的操作步骤：
 * 1. 创建 Stream
 * 2. 中间操作
 * 3. 终止操作(终端操作)
 */
public class StreamTest {

    //1. 创建 Stream
    @Test
    public void test1() {
        //1. Collection 提供了两个方法  stream() 与 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream(); //获取一个顺序流
        Stream<String> parallelStream = list.parallelStream(); //获取一个并行流

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3. 通过 Stream 类中静态方法 of()
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

        //4. 创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);

    }

    //2. 中间操作
    List<Apple> inventory = Arrays.asList(new Apple("green", 80, "黄土高原"),
            new Apple("green", 200, "黄河故道"),
            new Apple("red", 160, "渤海湾"),
            new Apple("yellow", 20, "渤海湾")
    );

    /**
     * 筛选与切片
     * filter——接收 Lambda ， 从流中排除某些元素。
     * limit——截断流，使其元素不超过给定数量。
     * skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */

    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void test2() {
        //所有的中间操作不会做任何的处理
        Stream<Apple> stream = inventory.stream()
                .filter((a) -> {
                    System.out.println("测试中间操作");
                    return a.getWeight() >= 100;
                });

        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test3() {
        Iterator<Apple> it = inventory.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void test4() {
        inventory.stream()
                .filter((a) -> {
                    System.out.println("短路！"); // &&  ||
                    return a.getWeight() >= 50;
                }).limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        inventory.parallelStream()
                .filter((a) -> a.getWeight() >= 50)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test6() {
        inventory.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 流中流
     * map 返回外层的流
     * filterCharacter 的返回值是一个流
     *
     * 映射
     * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    List<String> words = Arrays.asList("aa","bbb","cc");
    @Test
    public void testStreamStream(){
        Stream<Stream<Character>> streamStream = words.stream()
                .map(StreamTest::filterCharacter);

        streamStream.forEach(stream -> {
            stream.forEach(System.out::println);
        });
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for(Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    @Test
    public void testFlapMap(){
        Stream<Character> cs = words.stream()
                .flatMap(StreamTest::filterCharacter);

        cs.forEach(System.out::println);
    }

    @Test
    public void testList(){
        List numList = new ArrayList<>();
        numList.add("11");
        numList.add("22");
        numList.add(words);
        numList.add(33);
        numList.add(44);
        numList.addAll(words);

        System.out.println(numList);
    }
    /**
	*	sorted()——自然排序
	*	sorted(Comparator com)——定制排序
    */
}
