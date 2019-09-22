package com.lyp.learn.ppt;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamSummaryDemo {
    List<Apple> inventory = Arrays.asList(
            new Apple("green",80,"黄土高原"),
            new Apple("green",200,"黄河故道"),
            new Apple("red",160,"渤海湾"),
            new Apple("yellow",20,"渤海湾")
    );

    /**
     * 求和
     */
    @Test
    public void test1() {
        System.out.println("-----------求和-------------");

        OptionalInt weightSum1 = inventory.stream()
                                            .mapToInt(Apple::getWeight)
                                            .reduce((a, b) -> a + b);
        System.out.println(weightSum1.orElse(0));

        int weightSum2 = inventory.stream()
                                    .mapToInt(Apple::getWeight)
                                    .reduce(0, (a, b) -> a + b);
        System.out.println(weightSum2);

        int weightSum3 = inventory.stream()
                .collect(Collectors.reducing(0, Apple::getWeight, (x, y) -> x + y));
        System.out.println(weightSum3);

        int weightSum4 = inventory.stream()
                                .map(Apple::getWeight)
                                .reduce(0, Integer::sum);
        System.out.println(weightSum4);


        int weightSum5 = inventory.stream()
                .collect(Collectors.reducing(0, Apple::getWeight, Integer::sum));
        System.out.println(weightSum5);

        int weightSum6 = inventory.stream()
                                    .map(Apple::getWeight)
                                    .reduce(Integer::sum)
                                    .orElse(0);
        System.out.println(weightSum6);

        int weightSum7 = inventory.stream()
                .collect(Collectors.summingInt(Apple::getWeight));
        System.out.println(weightSum7);

        int weightSum8 = inventory.stream()
                                    .mapToInt(Apple::getWeight)
                                    .sum();
        System.out.println(weightSum8);
    }

    /**
     * 求最大值
     */
    @Test
    public void test2() {
        System.out.println("-----------求最大值-------------");

        Optional<Apple> weightApple = inventory.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Apple::getWeight)));
        System.out.println(weightApple.isPresent() ? weightApple.get() : "err");

        Optional<Apple> weightApple3 = inventory.stream()
                .collect(Collectors.maxBy((a1, a2) -> a1.getWeight().compareTo(a2.getWeight())));
        System.out.println(weightApple3.isPresent() ? weightApple3.get() : "err");

        Optional<Apple> weightApple4 = inventory.stream()
                .collect(Collectors.maxBy((a1, a2) -> Integer.compare(a1.getWeight(), a2.getWeight())));
        System.out.println(weightApple4.isPresent() ? weightApple4.get() : "err");

        Optional<Apple> weightApple2 = inventory.stream()
                                                .max(Comparator.comparingInt(Apple::getWeight));
        System.out.println(weightApple2.isPresent() ? weightApple2.get() : "err");

        OptionalInt weightMax1 = inventory.stream()
                                            .mapToInt(Apple::getWeight)
                                            .reduce((x, y) -> x >= y ? x : y);
        System.out.println(weightMax1.isPresent() ? weightMax1.getAsInt() : weightMax1.orElse(0));
        System.out.println(weightMax1.orElse(0));

        OptionalInt weightMax2 = inventory.stream()
                                            .mapToInt(Apple::getWeight)
                                            .reduce(Integer::max);
        System.out.println(weightMax2.orElse(0));

        OptionalInt wightMax3 = inventory.stream()
                                            .mapToInt(Apple::getWeight)
                                            .max();
        System.out.println(wightMax3.orElse(0));

        Optional<Integer> weightMax4 = inventory.stream()
                .map(Apple::getWeight)
                .collect(Collectors.maxBy(Integer::compareTo));
        System.out.println(weightMax4.orElse(0));


    }

    /**
     * 求最小值
     */
    @Test
    public void test3() {
        System.out.println("-----------求最小值-------------");

        Optional<Apple> appleWeight1 = inventory.stream()
                .collect(Collectors.minBy(Comparator.comparing(Apple::getWeight)));
        System.out.println(appleWeight1.isPresent() ? appleWeight1.get() : "apple min err");

        Optional<Apple> appleWeight2 = inventory.stream()
                                            .min(Comparator.comparingInt(Apple::getWeight));
        System.out.println(appleWeight2.isPresent() ? appleWeight2.get() : "apple min err");

        OptionalInt weightMin1 = inventory.stream()
                                            .mapToInt(Apple::getWeight)
                                            .reduce((x, y) -> x > y ? y : x);
        System.out.println(weightMin1.isPresent() ? weightMin1.getAsInt() : weightMin1.orElse(0));

        OptionalInt weightMin2 = inventory.stream()
                                            .mapToInt(Apple::getWeight)
                                            .reduce(Integer::min);
        System.out.println(weightMin2.isPresent() ? weightMin2.getAsInt() : weightMin2.orElse(0));

        OptionalInt weightMin3 = inventory.stream()
                                            .mapToInt(Apple::getWeight)
                                            .min();
        System.out.println(weightMin3.orElse(Integer.MIN_VALUE));


    }

    /**
     * 求平均值
     */
    @Test
    public void test4() {
        System.out.println("-----------求平均值-------------");
        Double appleAverageDouble1 = inventory.stream()
                .collect(Collectors.averagingInt(Apple::getWeight));
        System.out.println(appleAverageDouble1);

        OptionalDouble appleAverageDouble2 = inventory.stream()
                                                        .mapToInt(Apple::getWeight)
                                                        .average();
        System.out.println(appleAverageDouble2.orElse(0));
    }

    /**
     * 求数量
     */
    @Test
    public void test5(){
        System.out.println("-----------求数量-------------");
        /**
         * 你可以把流中每个元素都映射成数字1，然后用reduce求和。
         * 这相当于按顺序数流中的元素个数。
         */
        int size = inventory.size();
        System.out.println(size);

        Optional weightCount1 = inventory.stream()
                                            .map(d -> 1)
                                            .reduce((x,y) -> x + y);
        System.out.println(weightCount1.isPresent() ? weightCount1.get() : weightCount1.orElse("error"));

        Long weightCount2 = inventory.stream()
                                        .mapToInt(Apple::getWeight)
                                        .count();
        System.out.println(weightCount2);

        long weightCount3 = inventory.stream()
                                     .collect(Collectors.counting());
        System.out.println(weightCount3);

        Long weightCount4 = inventory.stream()
                                        .count();
        System.out.println(weightCount4);
    }

    /**
     * summarizing 工厂，获取常用统计值
     */
    @Test
    public void test6(){
        System.out.println("----------summarizing 工厂，获取常用统计值-----------");
        IntSummaryStatistics intSummaryStatistics = inventory.stream()
                                                    .collect(Collectors.summarizingInt(Apple::getWeight));
        System.out.println(intSummaryStatistics);
        System.out.println("sum is : " + intSummaryStatistics.getSum());
        System.out.println("max is : " + intSummaryStatistics.getMax());
        System.out.println("min is : " + intSummaryStatistics.getMin());
        System.out.println("count is : " + intSummaryStatistics.getCount());
        System.out.println("average is : " + intSummaryStatistics.getAverage());
        System.out.println();

        LongSummaryStatistics longSummaryStatistics = inventory.stream()
                                            .collect(Collectors.summarizingLong(Apple::getWeight));
        System.out.println(longSummaryStatistics);
    }

    /**
     * 数值流
     */
    @Test
    public void test7(){
        System.out.println("------------数值流--------------------");
        // 生成 [start,end) 区间数字
        int [] intArr =  IntStream.range(1,10)
                                     .toArray();
        System.out.println(Arrays.toString(intArr));

        // 生成 [start,end] 区间数字
        int [] intArr2 = IntStream.rangeClosed(1,10)
                                     .toArray();
        System.out.println(Arrays.toString(intArr2));
        System.out.println();

    }

    /**
     * IntStream
     * 如果IntStream是一个空对象，那么sum可以返回0,而min,max是一个null ,
     * 所以这里sum的返回值用的是int，
     * 而min和max返回值是OptionalInt，用于规避值为null的情况，所以一般可以这么写.
     * IntStream.of(1,2,3,4,5).max().ifPresent(System.out::println);
     */
}
