package com.lyp.learn.ppt;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamFilterDemo {
    List<Apple> inventory = Arrays.asList(
            new Apple("green",80,"黄土高原"),
            new Apple("green",200,"黄河故道"),
            new Apple("red",160,"渤海湾"),
            new Apple("yellow",20,"渤海湾")
    );

    //找出 weight >= 50 的苹果
    @Test
    public void test1(){
        System.out.println(filterWeightMoreThan50(inventory));
    }

    /**
     * 找出 weight >= 50
     * 旧的写法，遍历循环找出
     */
    public static List<Apple> filterWeightMoreThan50(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getWeight() >= 50){
                result.add(apple);
            }
        }
        return result;
    }

    //找出 weight >= 150 的苹果
    @Test
    public void test2(){
        System.out.println(filterWeightMoreThan150(inventory));
    }

    /**
     * 找出 weight >= 150
     * 旧的写法，遍历循环找出
     */
    public static List<Apple> filterWeightMoreThan150(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getWeight() >= 150){
                result.add(apple);
            }
        }
        return result;
    }

    //weight >= amount
    @Test
    public void test3(){
        System.out.println(StreamFilterDemo.filterWeightMoreThanAmount(inventory,50));
        System.out.println(StreamFilterDemo.filterWeightMoreThanAmount(inventory,150));
    }
    /**
     * 找出 weight >= amount
     * 旧的写法，遍历循环找出
     */
    public static List<Apple> filterWeightMoreThanAmount(List<Apple> inventory,int amount){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getWeight() >= amount){
                result.add(apple);
            }
        }
        return result;
    }

    //找出绿色苹果
    @Test
    public void test4(){
        System.out.println(StreamFilterDemo.filterGreenApples(inventory));
    }

    /**
     * 旧的写法，遍历循环找出 绿苹果
     */
    public static  List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void test5(){
        System.out.println(StreamFilterDemo.filterApplesByColor(inventory,"green"));
        System.out.println(StreamFilterDemo.filterApplesByColor(inventory,"red"));
    }

    /**
     * 旧的写法，遍历循环找出 指定颜色的苹果
     */
    public static  List<Apple> filterApplesByColor(List<Apple> inventory, String color){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    //java 8 语法 ，找苹果
    @Test
    public void test6(){
        List<Apple> appleGreenList = inventory.stream()
                                                .filter(apple -> "green".equals(apple.getColor()))
                                                .collect(Collectors.toList());
        System.out.println(appleGreenList);

        List<Apple> appleRedList = inventory.stream()
                                                .filter(a -> "red".equals(a.getColor()))
                                                .collect(Collectors.toList());
        System.out.println(appleRedList);

        List<Apple> appleWeightList = inventory.stream()
                                                .filter(a -> a.getWeight() >= 150)
                                                .collect(Collectors.toList());
        System.out.println(appleWeightList);

        List<Apple> appleGreenRedList = inventory.stream()
                                                    .filter(a -> "green".equals(a.getColor()) && a.getWeight() >= 150)
                                                    .collect(Collectors.toList());
        System.out.println(appleGreenRedList);

    }


    ///===================================结束=======================================================








    //去重操作 distinct
    @Test
    public void test7(){
        List<String> colors = inventory.stream()
                                        .map(a -> a.getColor())
                                        .collect(Collectors.toList());
        System.out.println(colors);

        List<String> distinctColors = inventory.stream()
                                                .map(a -> a.getColor())
                                                .distinct()
                                                .collect(Collectors.toList());
        System.out.println(distinctColors);
    }
    @Test
    public void test8(){
        //Stream陷阱 distinct()会一直等待产生的结果去重，将distinct()和limit(6)调换位置，先限制结果集再去重就可以了
        IntStream.iterate(0, i -> (i + 1) % 2).distinct().limit(6).forEach(System.out::println);
    }

    @Test
    public void test9(){
        //Stream陷阱 distinct()会一直等待产生的结果去重，将distinct()和limit(6)调换位置，先限制结果集再去重就可以了
        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
    }

    //collect 规约，收集元素
    @Test
    public void test10(){
        //ArrayList 收集
        List<String> listColors = inventory.stream()
                                            .map(Apple::getColor)
                                            .collect(Collectors.toList());
        System.out.println(listColors);

        //HashSet 收集
        Set<String> setColors = inventory.stream()
                                            .map(Apple::getColor)
                                            .collect(Collectors.toSet());
        System.out.println(setColors);

        //LinkedList 收集  使用 Collectors.toCollection 指定类型的集合收集
        //我们可以自己制定结果容器的类型Collectors的toCollection接受一个Supplier函数式接口类型参数，
        // 可以直接使用构造方法引用的方式
        List<String> linkedListColors =  inventory.stream()
                                                .map(Apple::getColor)
                                                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(linkedListColors);

        //TreeSet 收集  使用指定类型的集合收集
        Set<String> treeSetColors = inventory.stream()
                                                .map(Apple::getColor)
                                                .map(String::toUpperCase)
                                                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSetColors);

        //使用数组 收集
        String[] arrayColors = inventory.stream()
                                        .map(Apple::getColor)
                                        .toArray(String[]::new);
        System.out.println(Arrays.toString(arrayColors));

        //String 类型输出
        String appleColorStr = inventory.stream()
                .map(Apple::getColor)
                .collect(Collectors.joining(","));
        System.out.println(appleColorStr);
    }

    /**
     * Stream中提供了2个重载的方法
     * <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
     * <R, A> R collect(Collector<? super T, A, R> collector);
     *用来将值进行合并
     * 对这个流中的元素执行一个可变的汇聚的操作，一个可变的汇聚的操作指的是被汇聚的值是一个可变的结果容器，比如ArrayList, 这个集合是通过更新结果状态来合并的，而不是通过替换这个结果来合并的
     *
     * 参数1：Supplier，返回的对象是该方法返回的对象类型。
     * 参数2:BiConsumer,接收的第一个参数可以理解是一个需要汇聚的对象，第二个参数是stream中的某一个元素。
     * 参数3:BiConsumer，相当于是一个合并器，将上一次的结果和装进同一个对象中，用于最后的返回。当然这个对象的类型就是Supplier返回的对象类型。
     *
     * 例如这样：
     * Stream<String> stream1 = Stream.of("hello", "world", "good", "morning");
     * List<String> strList1 = stream1.collect(() -> new ArrayList<>(),(list1,item) -> list1.add(item),(list2,list1) -> list2.addAll(list1));
     * strList1.forEach(System.out::println);
     */
    @Test
    public void test11(){
        Stream<String> stream1 = Stream.of("hello", "world", "good", "morning");
        List<String> strList1 = stream1.collect(() -> new ArrayList<>(),(list1,item) -> list1.add(item),(list2,list1) -> list2.addAll(list1));
        strList1.forEach(System.out::println);
        System.out.println();


        Stream<String> stream2 = Stream.of("list1", "list2", "list3");
        List<String> strList2 = stream2.collect(Collectors.toList());
        strList2.forEach(System.out::println);



    }


}
