package com.lyp.learn.streampk;


import com.lyp.learn.bean.Apple;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamFilterDemo {
    List<Apple> inventory = Arrays.asList(
            new Apple("green", 80, "黄土高原"),
            new Apple("green", 200, "黄河故道"),
            new Apple("red", 160, "渤海湾"),
            new Apple("yellow", 20, "渤海湾")
    );

    //找出 weight >= 50 的苹果
    @Test
    public void test1() {
        System.out.println(filterWeightMoreThan50(inventory));
    }

    /**
     * 找出 weight >= 50
     * 旧的写法，遍历循环找出
     */
    public static List<Apple> filterWeightMoreThan50(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() >= 50) {
                result.add(apple);
            }
        }
        return result;
    }

    //找出 weight >= 150 的苹果
    @Test
    public void test2() {
        System.out.println(filterWeightMoreThan150(inventory));
    }

    /**
     * 找出 weight >= 150
     * 旧的写法，遍历循环找出
     */
    public static List<Apple> filterWeightMoreThan150(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() >= 150) {
                result.add(apple);
            }
        }
        return result;
    }

    //weight >= amount
    @Test
    public void test3() {
        System.out.println(StreamFilterDemo.filterWeightMoreThanAmount(inventory, 50));
        System.out.println(StreamFilterDemo.filterWeightMoreThanAmount(inventory, 150));
    }

    /**
     * 找出 weight >= amount
     * 旧的写法，遍历循环找出
     */
    public static List<Apple> filterWeightMoreThanAmount(List<Apple> inventory, int amount) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() >= amount) {
                result.add(apple);
            }
        }
        return result;
    }

    //找出绿色苹果
    @Test
    public void test4() {
        System.out.println(StreamFilterDemo.filterGreenApples(inventory));
    }

    /**
     * 旧的写法，遍历循环找出 绿苹果
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void test5() {
        System.out.println(StreamFilterDemo.filterApplesByColor(inventory, "green"));
        System.out.println(StreamFilterDemo.filterApplesByColor(inventory, "red"));
    }

    /**
     * 旧的写法，遍历循环找出 指定颜色的苹果
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    //java 8 语法 ，找苹果
    @Test
    public void test6() {
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
    public void test7() {
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
    public void test8() {
        //Stream陷阱 distinct()会一直等待产生的结果去重，将distinct()和limit(6)调换位置，先限制结果集再去重就可以了
        IntStream.iterate(0, i -> (i + 1) % 2).distinct().limit(6).forEach(System.out::println);
    }

    @Test
    public void test9() {
        //Stream陷阱 distinct()会一直等待产生的结果去重，将distinct()和limit(6)调换位置，先限制结果集再去重就可以了
        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
    }


    /**
     *   collect 规约，收集元素
     *      collect(Collector c)
     *      将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
     *
     *   Collector 接口中方法的实现决定了如何对流执行收集的操作(如收集到 List、Set、Map)。
     *     另外， Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例，具体方法与实例如下表：
     */
    @Test
    public void test10() {
        //List 收集
        List<String> listColors = inventory.stream()
                .map(Apple::getColor)
                .collect(Collectors.toList());
        System.out.println(listColors);

        //ArrayList 收集
        List<String> listColors2 = inventory.stream()
                .map(Apple::getColor)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(listColors2);

        //HashSet 收集
        Set<String> setColors = inventory.stream()
                .map(Apple::getColor)
                .collect(Collectors.toSet());
        System.out.println(setColors);

        //LinkedList 收集  使用 Collectors.toCollection 指定类型的集合收集
        //我们可以自己制定结果容器的类型Collectors的toCollection接受一个Supplier函数式接口类型参数，
        // 可以直接使用构造方法引用的方式
        List<String> linkedListColors = inventory.stream()
                .map(Apple::getColor)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(linkedListColors);

        //TreeSet 收集  使用指定类型的集合收集
        Set<String> treeSetColors = inventory.stream()
                .map(Apple::getColor)
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSetColors);

        TreeSet<Apple> treeSetApple = inventory.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Apple::getWeight))));
        System.out.println("treeSetApple::" + treeSetApple);

        //TreeMap 收集  使用指定类型的集合收集
        TreeMap<String, String> sonTreeMap = inventory.stream()
                .collect(Collectors.toMap(Apple::getColor, Apple::getAddress, (k1, k2) -> k2, TreeMap::new));
        System.out.println("sonTreeMap ::" + sonTreeMap);

        //linkedHashMap 收集  使用指定类型的集合收集
        LinkedHashMap<String, String> linkedHashMap = inventory.stream()
                .collect(Collectors.toMap(Apple::getColor, Apple::getAddress, (k1, k2) -> k2, LinkedHashMap::new));
        System.out.println("linkedHashMap ::" + linkedHashMap);

        //使用数组 收集
        String[] arrayColors = inventory.stream()
                .map(Apple::getColor)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(arrayColors));

        Integer[] intWeights = inventory.stream()
                .map(Apple::getWeight)
                .filter(w -> w % 80 == 0)
                .toArray(Integer[]::new);
        System.out.println(intWeights);

        //String 类型输出
        String appleColorStr = inventory.stream()
                .map(Apple::getColor)
                .collect(Collectors.joining());
        System.out.println(appleColorStr);

        String appleColorStr2 = inventory.stream()
                .map(Apple::getColor)
                .collect(Collectors.joining(","));
        System.out.println(appleColorStr2);

        String appleColorStr3 = inventory.stream()
                .map(Apple::getColor)
                .collect(Collectors.joining(",", "^^^^", "$$$$"));
        System.out.println(appleColorStr3);
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
    public void test11() {
        Stream<String> stream1 = Stream.of("hello", "world", "good", "morning");
        List<String> strList1 = stream1.collect(() -> new ArrayList<>(), (list1, item) -> list1.add(item), (list2, list1) -> list2.addAll(list1));
        strList1.forEach(System.out::println);
        System.out.println();


        Stream<String> stream2 = Stream.of("list1", "list2", "list3");
        List<String> strList2 = stream2.collect(Collectors.toList());
        strList2.forEach(System.out::println);

    }

    /**
     * list 转 map
     * key 中没有重复值
     */
    @Test
    public void testListToMap1() {
        Map<Integer, String> map = inventory.stream()
                .collect(Collectors.toMap(Apple::getWeight, Apple::getAddress));
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * key中含有重复值
     * 在list转为map时，作为key的值有可能重复，
     * 这时候流的处理会抛出个异常：Java.lang.IllegalStateException:Duplicate key。
     *
     * 这时候就要在toMap方法中指定当key冲突时key的选择。(这里是选择第二个key覆盖第一个key)
     */
    @Test
    public void testListToMap2() {
        Map<String, String> map = inventory.stream()
                //下面这句有异常
//                .collect(Collectors.toMap(Apple::getColor, Apple::getAddress));
                .collect(Collectors.toMap(Apple::getColor, Apple::getAddress, (k1, k2) -> k2));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * 合并冲突key的结果
     */
    @Test
    public void testListToMap3() {
        Map<String, String> map = inventory.stream()
                .collect(Collectors.toMap(Apple::getColor, Apple::getAddress, (k1, k2) -> k1 + "," + k2));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * 收集对象实体本身
     * 在开发过程中我们也需要有时候对自己的list中的实体按照其中的一个字段进行分组（比如 id ->List），
     * 这时候要设置map的value值是实体本身。
     */
    @Test
    public void testListToMap4() {
        Map<Integer, Apple> map = inventory.stream()
                .collect(Collectors.toMap(Apple::getWeight, apple -> apple));

        for (Map.Entry<Integer, Apple> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * 收集对象实体本身
     * apple -> apple 是一个返回本身的lambda表达式，
     * 其实还可以使用Function接口中的一个默认方法 Function.identity()，这个方法返回自身对象，更加简洁
     *
     * Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式。
     */
    @Test
    public void testListToMap5() {
        Map<Integer, Apple> map = inventory.stream()
                .collect(Collectors.toMap(Apple::getWeight, Function.identity()));

        for (Map.Entry<Integer, Apple> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * LinkedHashMap  有序map  按照插入顺序
     *     正序
     */
    @Test
    public void testListToMap6() {
        LinkedHashMap<Integer, String> linkedHashMap = inventory.stream()
                .sorted(Comparator.comparing(Apple::getWeight))
                .collect(Collectors.toMap(Apple::getWeight, Apple::getColor, (v1, v2) -> v1, LinkedHashMap::new));
        System.out.println(linkedHashMap);

    }

    /**
     * LinkedHashMap  有序map  按照插入顺序
     *     倒序
     */
    @Test
    public void testListToMap7() {
        LinkedHashMap<Integer, String> linkedHashMap = inventory.stream()
                .sorted(Comparator.comparing(Apple::getWeight).reversed())
                .collect(Collectors.toMap(Apple::getWeight, Apple::getColor, (v1, v2) -> v1, LinkedHashMap::new));
        System.out.println(linkedHashMap);

    }

    //====================== Collectors.collectingAndThen =====================

    /**
     *  Stream中有两个个方法collect和collectingAndThen用于对流中的数据进行处理，可以对流中的数据进行聚合操作
     *
     *   <R, A> R collect(Collector<? super T, A, R> collector);
     *
     *   // collectingAndThen : 将流中的数据通过Collector计算，最终的结果在通过Function再最终处理一下
     *   public static<T,A,R,RR> Collector<T,A,RR> collectingAndThen(Collector<T,A,R> downstream,Function<R,RR> finisher);
     */

    @Test
    public void testCat1() {
        List<String> strList = Arrays.asList("a", "b", "c");
        String str = strList.stream().collect(Collectors.joining(","));
        System.out.println(str);

        // Collectors.joining(",")的结果是：a,b,c  然后再将结果 x + "d"操作, 最终返回a,b,cd
        String str2 = Stream.of("a", "b", "c")
                .collect(Collectors.collectingAndThen(Collectors.joining(","), x -> x + "d"));
        System.out.println(str2);
    }

    /**
     * 查找重复元素
     */
    @Test
    public void findRepeat01(){
        List<Apple> inventory = Arrays.asList(
                new Apple("green", 80, "黄土高原"),
                new Apple("green", 200, "黄河故道"),
                new Apple("red", 160, "渤海湾"),
                new Apple("yellow", 20, "渤海湾"),
                new Apple("red", 160, "渤海湾")
        );
        //已经添加过的
        Set<String> colorSeen = new HashSet<>();

        String repeatColor = inventory
            .stream()
            .filter(apple -> !colorSeen.add(apple.getColor()))
            .map(Apple::getColor)
            .collect(Collectors.joining(","));
        System.out.println("repeatColor = " + repeatColor);
    }

    /**
     * 原来的对象
     */
    @Test
    public void testStreamNew() {
        Apple apple = inventory.get(0);
        System.out.println(apple);

        List<Apple> inventoryNew = inventory.stream()
            .peek(a -> a.setAddress("aaa"))
            .collect(Collectors.toList());
        Apple appleNew = inventoryNew.get(0);
        System.out.println(appleNew);

        System.out.println(apple == appleNew);
    }

    /**
     * 新对象
     */
    @Test
    public void testStreamNew2() {
        Apple apple = inventory.get(0);
        System.out.println(apple);

        List<Apple> inventoryNew = inventory.stream()
                .map(a -> {
                    Apple aa = new Apple();
                    aa.setAddress(a.getAddress());
                    return aa;
                })
                .collect(Collectors.toList());
        Apple appleNew = inventoryNew.get(0);
        System.out.println(appleNew);

        System.out.println(apple == appleNew);
    }


    /**
     * 查找重复的
     */
    @Test
    public void testFindRepeat() {
        List<String> repetitionSkuIdList = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("repetitionSkuIdList = " + repetitionSkuIdList);
    }


}
