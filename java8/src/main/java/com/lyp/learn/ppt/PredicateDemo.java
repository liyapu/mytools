package com.lyp.learn.ppt;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {
    List<Integer> nums =  Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);

    Predicate<Integer> p1 = n -> n > 5;
    Predicate<Integer> p2 = n -> n < 15;
    Predicate<Integer> p3 = n -> n % 2 == 0;

    Predicate<Integer> p4 = n -> n < 5;
    Predicate<Integer> p5 = n -> n > 10;

    Predicate<Integer> p6 = n -> n < 10;
    Predicate<Integer> p7 = n -> n > 15;

    /**
     * Predicate<T> 接受一个输入参数，返回一个布尔值结果。
     * 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）
     * 可以用于接口请求参数校验、判断新老数据是否有变化需要进行更新操作。
     * add--与、or--或、negate--非
     *
     */

    /**
     * test
     * 判断 boolean test(T t);
     * 在给定的参数上评估这个谓词,如果输入参数与谓词匹配，则为true，否则为false
     */
    @Test
    public void test1(){
       List<Integer> numList = nums.stream()
                                    .filter(n -> n < 5)
                                    .collect(Collectors.toList());
        System.out.println(numList);

        List<Integer> numList2 = nums.stream()
                                        .filter(p4)
                                        .collect(Collectors.toList());
        System.out.println(numList2);
    }

    @Test
    public void test11(){
        //输出小于5的数字
        List<Integer> numList = PredicateDemo.conditionFilter(nums, integer -> integer < 5);
        numList.forEach(System.out::println);
        System.out.println("-------");
        System.out.println();

        //输出所有数字
        List<Integer> numList2 = PredicateDemo.conditionFilter(nums, integer -> true);
        numList2.forEach(System.out::println);
    }

    //高度抽象的方法定义，复用性高
    public static List<Integer> conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * and
     * 通过这个predicate和它的参数predicate 返回一个短路逻辑与的判断结果，
     * 当去计算这个复合的predicate时，如果当前的predicate 结果是false，那么就不会计算它的参数other的值。
     * 如果这二个其中任何一个抛出异常，具体的处理交给调用的人，如果抛出了异常，它将不会被执行
     */
    @Test
    public void test2(){
        List<Integer> numList1 = nums.stream()
                                        .filter(n -> n > 5 && n < 15 && n % 2 == 0)
                                        .collect(Collectors.toList());
        System.out.println(numList1);

        List<Integer> numList2 = nums.stream()
                                        .filter(p1.and(p2).and(p3))
                                        .collect(Collectors.toList());
        System.out.println(numList2);
    }

    /**
     * negate
     * 返回一个predicate 代表了这个predicate的逻辑非
     */
    @Test
    public void test3(){
        List<Integer> numList = nums.stream()
                                    .filter(p1.negate())
                                    .collect(Collectors.toList());
        System.out.println(numList);
    }

    /**
     * or
     * 通过这个predicate和它的参数predicate 返回一个短路逻辑或的判断结果，
     * 当计算这个组合的predicate，如果这个predicate是true ，那么它的参数other将不会计算。
     * 如果这二个其中任何一个抛出异常，具体的处理交给调用的人，如果抛出了异常，它将不会被执行
     */
    @Test
    public void test4(){
        List<Integer> numList = nums.stream()
                                    .filter(p4.or(p5))
                                    .collect(Collectors.toList());
        System.out.println(numList);
    }

    /**
     *  and和or方法是按照在表达式链中的位置，从左向右确定优先级的。
     *  因此， a.and(b).or(c) 可以看作 (a && b) || c
     */
    @Test
    public void test5(){
        List<Integer> numList = nums.stream()
                                    .filter(p1.and(p5).or(p3))
                                    .collect(Collectors.toList());
        System.out.println(numList);
    }

    /**
     *  and和or方法是按照在表达式链中的位置，从左向右确定优先级的。
     *  因此， a.or(b).and(c)可以看作(a || b) && c
     */
    @Test
    public void test51(){
        List<Integer> numList = nums.stream()
                                    .filter(p6.or(p7).and(p3))
                                    .collect(Collectors.toList());
        System.out.println(numList);
    }

    @Test
    public void test6(){
        List<Integer> numList = nums.stream()
                                    .filter(p5.and(p3.negate()))
                                    .collect(Collectors.toList());
        System.out.println(numList);
    }

    /**
     * isEqual
     * 返回一个谓词，根据Objects.equals(Object，Object)测试两个参数是否相等。
     */
    @Test
    public void test7(){
        boolean boo1 = Predicate.isEqual("aa").test("aa");
        System.out.println(boo1);

        boolean boo2 = Predicate.isEqual("aa").test("bb");
        System.out.println(boo2);

    }
}
