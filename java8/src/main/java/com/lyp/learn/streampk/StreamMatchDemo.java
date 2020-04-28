package com.lyp.learn.streampk;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamMatchDemo {

    List<Integer> nums = Arrays.asList(30,70,20,80,30,90);

    /**
     * 模式匹配 查找
     */

    /**
     * allMatch
     * 检查是否匹配所有元素。
     * true: 如果所有的元素都满足给定断言，或者此流为空；
     * 反之，false
     */
    @Test
    public void test1() {
        System.out.println("----查找------------allMatch-------------");
        boolean allMatchBoolean = nums.stream()
                                        .allMatch(num -> num > 10);
        System.out.println(allMatchBoolean);

        boolean allMatchBoolean2 = nums.stream()
                                         .allMatch(num -> num > 50);
        System.out.println(allMatchBoolean2);
    }

    /**
     * noneMatch
     * 检查是否没有匹配所有元素
     * true: 如果没有元素满足给定断言，或者此流为空；
     * 反之，false
     */
    @Test
    public void test2() {
        System.out.println("----查找------------noneMatch-------------");
        boolean noneMatchBoolean = nums.stream()
                                        .noneMatch(num -> num > 100);
        System.out.println(noneMatchBoolean);

        boolean noneMatchBoolean2 = nums.stream()
                                          .noneMatch(num -> num < 50);
        System.out.println(noneMatchBoolean2);
    }

    /**
     * anyMatch
     * 检查是否至少匹配所有元素。
     * true: 如果有任意一个元素满足给定断言；
     * 反之,false
     */
    @Test
    public void test3() {
        System.out.println("----查找------------anyMatch-------------");
        boolean anyMatchBoolean = nums.stream()
                                        .anyMatch(num -> num > 50);
        System.out.println(anyMatchBoolean);
        boolean anyMatchBoolean2 = nums.stream()
                                        .anyMatch(num -> num > 100);
        System.out.println(anyMatchBoolean2);
        System.out.println();
    }

    /**
     * findFirst
     * 返回第一个元素
     * 注意：findFirst 返回的是一个Optional的对像，他将我们的Integer封装了一层，这是为了避免空指针。
     * 而且这个对象为我们提供了一个orElse方法，就是当我们得到的这个对象为空时，我们可以传入一个新得对象去替代它。
     */
    @Test
    public void test4() {
        System.out.println("----查找------------findFirst-------------");
        Optional<Integer> findNumber = nums.stream().findFirst();
        System.out.println(findNumber.orElse(Integer.MIN_VALUE));
    }

    /**
     * findAny
     * 返回当前流中任意元素。
     * 对并行流十分有效
     * 只要在任何片段发现了第一个匹配元素就会结束整个运算
     */
    @Test
    public void test5(){

        System.out.println("----查找------------findAny-------------");
        Optional<Integer> findNumber = nums.stream().findAny();
        System.out.println(findNumber.isPresent() ? findNumber.get() : "没有找到");

        nums.stream()
                .findAny()
                .ifPresent(numTemp -> System.out.println(numTemp.intValue()));
    }

    /**
     * limit
     * 截短流 限制个数
     * 返回一个长度不超过给定参数maxSize大小的stream
     */
    @Test
    public void test6(){
        List<Integer> limitCount1 = nums.stream()
                                        .limit(2)
                                        .collect(Collectors.toList());
        System.out.println(limitCount1);

        List<Integer> limitCount2 = nums.stream()
                                        .limit(4)
                                        .collect(Collectors.toList());
        System.out.println(limitCount2);
    }

    /**
     * skip
     * 流还支持skip(n)方法，返回一个扔掉了前n个元素的流。
     * 如果流中元素不足n个，则返回一个空流。
     */
    @Test
    public void test7(){
        List<Integer> skipNums = nums.stream()
                                        .skip(2)
                                        .collect(Collectors.toList());
        System.out.println(skipNums);

        List<Integer> skipNumss = nums.stream()
                                        .skip(4)
                                        .collect(Collectors.toList());
        System.out.println(skipNumss);

        List<Integer> skipNumsss = nums.stream()
                                        .skip(1000)
                                        .collect(Collectors.toList());
        System.out.println(skipNumsss);
    }

}
