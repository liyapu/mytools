package com.lyp.learn.streampk;

import com.alibaba.fastjson.JSON;
import com.lyp.learn.bean.Apple;
import com.lyp.learn.bean.AppleVO;
import com.lyp.learn.bean.Dish;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class StreamGroupByDemo {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );


    List<Apple> inventory = Arrays.asList(
            new Apple("green",80,"黄土高原"),
            new Apple("green",200,"黄河故道"),
            new Apple("red",160,"渤海湾"),
            new Apple("yellow",20,"渤海湾")
    );


    /**
     * 按颜色分类苹果
     * java7 的写法
     */
    @Test
    public void test1(){
        System.out.println(StreamGroupByDemo.getApplesByColors(inventory));
    }


    /**
     * 按颜色分类苹果
     * 旧的写法
     */
    public static Map<String,List<Apple>> getApplesByColors(List<Apple> inventory){
        Map<String,List<Apple>> applesByColors = new HashMap<>();
        for(Apple apple : inventory){
            String color = apple.getColor();
            List<Apple> appleList = applesByColors.get(color);
            if(null == appleList){
                appleList = new ArrayList<>();
                applesByColors.put(color,appleList);
            }
            appleList.add(apple);
        }
        return applesByColors;
    }

    /**
     * 按颜色分类苹果
     * java 8 写法
     */
    @Test
    public void test2(){
        Map<String,List<Apple>> appleColorList = inventory.stream()
                                .collect(Collectors.groupingBy(Apple::getColor));
        System.out.println(appleColorList);

        //映射为Set
        Map<String,Set<Apple>> appleColorSet = inventory.stream()
                              .collect(Collectors.groupingBy(Apple::getColor,Collectors.toSet()));
        System.out.println(appleColorSet);
    }

    @Test
    public void test3(){
        System.out.println("-----groupingBy-----每类菜中，热量最高的食物----------");
        Map<Dish.Type,Optional<Dish>> caloriesDishMax = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(caloriesDishMax);
        System.out.println(JSON.toJSONString(caloriesDishMax));
        System.out.println();
    }

    @Test
    public void test4(){
        System.out.println("-----groupingBy-----每类菜中，算出热量总和----------");
        Map<Dish.Type,Integer> typeCaloriesSum = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.summingInt(Dish::getCalories)));
        System.out.println(typeCaloriesSum);
        System.out.println();
    }

    @Test
    public void test5(){
        System.out.println("-----groupingBy-----对每类统计个数----------");
        Map<String, Long> appleColorCount = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.counting()));
        System.out.println(appleColorCount);

        //groupingby 对每类求平均值
        Map<String, Double> weightAverageColor = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingDouble(Apple::getWeight)));
        System.out.println(weightAverageColor);

        //groupingby 对每类求和
        Map<String, Double> weightSumColor = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.summingDouble(Apple::getWeight)));
        System.out.println(weightSumColor);

        //groupingby 对每类统计分析
        Map<String, DoubleSummaryStatistics> weightSummaryColor = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.summarizingDouble(Apple::getWeight)));
        System.out.println(weightSummaryColor);

    }

    /**
     * 查找重复元素
     */
    @Test
    public void test5Repeat() {
        List<String> repeatColorList = inventory.stream()
                .collect(Collectors.groupingBy(a -> a.getColor(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println("重复的颜色是： " + repeatColorList);
    }

    @Test
    public void test6() {
        System.out.println("-----groupingBy-----每类菜中，热量分类----------");
        Map<Dish.Type, Set<String>> typeCaloriesSet = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(
                                d -> {
                                    if (d.getCalories() >= 700) {
                                        return "高热量";
                                    } else if (d.getCalories() >= 400) {
                                        return "正常";
                                    } else {
                                        return "低热量";
                                    }
                                },
                                Collectors.toSet()
                        )));
        System.out.println(typeCaloriesSet);
        System.out.println(JSON.toJSONString(typeCaloriesSet));
    }

    @Test
    public void test7(){
        System.out.println("-----groupingBy-----每类菜中，热量分类----------");
        Map<Dish.Type,Set<String>> typeCaloriesSet = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(StreamGroupByDemo::classifyLevelFood, Collectors.toSet())
                ));
        System.out.println(typeCaloriesSet);
        System.out.println(JSON.toJSONString(typeCaloriesSet));
    }


    private static String classifyLevelFood(Dish dish){
        if(dish.getCalories() >= 700){
            return "高热量";
        }else if(dish.getCalories() >= 400){
            return "正常";
        }else{
            return "低热量";
        }
    }

    @Test
    public void test10(){
        Map<String,List<Dish>> caloriesDish = menu.stream()
                .collect(Collectors.groupingBy(StreamGroupByDemo::classifyLevelFood));
        System.out.println(caloriesDish);
        System.out.println(JSON.toJSONString(caloriesDish));
    }



    @Test
    public void test8(){
        // 把收集器的结果转换为另一种类型,取出热量最高的食物
        Map<Dish.Type,Dish> caloriesDishMax2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(caloriesDishMax2);
        System.out.println(JSON.toJSONString(caloriesDishMax2));
        System.out.println();

    }

    @Test
    public void test9(){
        //使用 HashSet
        Map<Dish.Type,Set<String>> typeCaloriesSet2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(StreamGroupByDemo::classifyLevelFood, Collectors.toCollection(HashSet::new))
                ));
        System.out.println(typeCaloriesSet2);
        System.out.println(JSON.toJSONString(typeCaloriesSet2));
        System.out.println();
    }

    @Test
    public void test11(){
        System.out.println("-----groupingBy-----多级分组----------");
        Map<Dish.Type,Map<String,List<Dish>>> typeCaloriesDish = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(StreamGroupByDemo::classifyLevelFood)
                ));

        System.out.println(typeCaloriesDish);
        System.out.println(JSON.toJSONString(typeCaloriesDish));
        System.out.println();
    }

    /**
     * 分组后操作 list
     */
    @Test
    public void test100(){
        Map<String, List<Apple>> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress));
        System.out.println(collect);
    }

    // 自定义 key
    @Test
    public void test101(){
        Map<String, List<Apple>> collect = inventory.stream()
                .collect(Collectors.groupingBy(a -> a.getAddress() + "-" + a.getWeight()));
        System.out.println(collect);
    }

    @Test
    public void test102(){
        Map<String, List<Apple>> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress,Collectors.toList()));
        System.out.println(collect);
    }

    @Test
    public void test103() {
        Map<String, Set<Apple>> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress, Collectors.toSet()));
        System.out.println(collect);
    }

    /**
     * 根据 key 对集合进行分组, 并且 value 的集合为指定属性集合而不是对象集合
     */
    @Test
    public void test104() {
        Map<String, List<String>> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress, Collectors.mapping(Apple::getColor, Collectors.toList())));
        System.out.println(collect);
    }

    /**
     * 根据 key 对集合进行分组, 并且 value 取其中1个字段，映射为 set
     */
    @Test
    public void test1041() {
        Map<String, Set<Integer>> collect = inventory.stream()
                .collect(groupingBy(Apple::getAddress, mapping(Apple::getWeight, Collectors.toSet())));
        System.out.println(collect);
    }


    /**
     * 收集List 之后，再次对 List 进行操作
     * 根据 key 进行分组并且将指定属性值进行拼接作为哈希表的 value 值
     */
    @Test
    public void test105() {
        Map<String, String> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress, Collectors.mapping(Apple::getColor, Collectors.joining("-"))));
        System.out.println(collect);
    }

    @Test
    public void test106(){
        Map<String,String> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress,Collectors.mapping(Apple::getColor,Collectors.joining("-","aa___","___zz"))));
        System.out.println(collect);
    }

    @Test
    public void test107(){
        Map<String,String> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress,Collectors.mapping(Apple::getColor,Collectors.joining(",","[","]"))));
        System.out.println(collect);
    }

    @Test
    public void test108(){
        Map<String, String> collect = inventory.stream()
            .collect(Collectors
                .groupingBy(Apple::getAddress, Collectors.mapping(Apple::getColor, Collectors.joining(",", "[", "]"))));
        System.out.println(collect);
    }

    @Test
    public void test109() {
        Map<String, String> collect = inventory.stream()
            .collect(Collectors.groupingBy(Apple::getAddress,
                Collectors.mapping(a -> a.getColor() + "|" + a.getWeight(), Collectors.joining("-"))));
        System.out.println(collect);
    }

    /**
     * groupingBy 之后，Collectors.mapping 对 List 再进行转换和操作
     */
    @Test
    public void test1091() {
        Map<String, List<AppleVO>> color2VoList = inventory.stream()
            .collect(groupingBy(Apple::getColor, Collectors.mapping(a -> {
                AppleVO vo = new AppleVO();
                vo.setAddress(a.getAddress());
                vo.setWeight(a.getWeight());
                return vo;
            }, Collectors.toList())));
        System.out.println(color2VoList);
    }

    @Test
    public void test110() {
        Map<String, String> collect = inventory.stream()
            .collect(Collectors.groupingBy(Apple::getAddress, Collectors.mapping(a -> {
                return a.getColor() + "|" + a.getWeight();
            }, Collectors.joining("-"))));
        System.out.println(collect);
    }

    //根据 key 进行分组并根据 指定属性进行比较取最大元素
    @Test
    public void test111(){
        Map<String, Optional<Apple>> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress, Collectors.maxBy(Comparator.comparing(Apple::getWeight))));
        System.out.println(collect);
    }

    // 遍历结果集
    @Test
    public void test112(){
       inventory.stream()
               .collect(Collectors.groupingBy(Apple::getAddress))
               .forEach((k,v) ->{
                   System.out.println(k + " ===== " + v);
               });

    }

    // List<T> 转 Map<T#Prop, Map<T#Prop2, T#Prop3>>
    @Test
    public void test113(){
        Map<String, Map<String, Integer>> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress, Collectors.toMap(Apple::getColor, Apple::getWeight)));
        System.out.println(collect);

    }

    @Test
    public void test114(){
        Map<Integer, Map<String, String>> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getWeight, Collectors.toMap(Apple::getColor, Apple::getAddress)));
        System.out.println(collect);
    }

    @Test
    public void test115(){
        Map<Integer, Map<String, Apple>> collect = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getWeight, Collectors.toMap(Apple::getColor, a -> a)));
        System.out.println(collect);
    }


    @Test
    public void test116() {
        Map<Integer, Map<String, Apple>> collect = inventory.stream()
            .collect(Collectors.groupingBy(Apple::getWeight, Collectors.toMap(Apple::getColor, Function.identity())));
        System.out.println(collect);
    }

    /**
     * 使用 TreeMap 收集，key值自动排序
     */
    @Test
    public void test117() {
        TreeMap<String, List<Apple>> colorToAppleListTreeMap = inventory.stream()
            .collect(groupingBy(Apple::getColor, TreeMap::new, mapping(Function.identity(), toList())));
        System.out.println(colorToAppleListTreeMap);
    }

    /**
     * partitioningBy 可以理解为特殊的 groupingBy，key值为true和false，
     *
     * partitioningBy 其实是一种特殊的 groupingBy，它依照条件测试的是否两种结果来构造返回的数据结构，
     * get(true) 和 get(false) 能即为全部的元素对象。
     *
     * partitioningBy方法接收一个Predicate作为分区判断的依据 （用于判断的函数式接口）
     * 满足条件的元素放在key为true的集合中，反之放在key为false的集合中
     */
    @Test
    public void test12(){
        System.out.println("-----partitioningBy-----根据谓词进行分类----------");
        Map<Boolean,List<Dish>> booleanListMap = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(booleanListMap);
        System.out.println(JSON.toJSONString(booleanListMap));
        System.out.println();

        //通过Map中键为true的值，就可以找出所有的素食菜肴了
        List<Dish> vegetarianDish2 = booleanListMap.get(true);
        System.out.println(vegetarianDish2);
        System.out.println();

        //请注意，用同样的分区谓词，对菜单List创建的流作筛选，
        // 然后把结果收集到另外一个List中也可以获得相同的结果
        List<Dish> vegetarianDish3 = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println(vegetarianDish3);
    }

    @Test
    public void test14(){
        System.out.println("-----partitioningBy------素食和非素食中热量最高的菜----------");
        Map<Boolean,Dish> booleanDishMax = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));
        System.out.println(booleanDishMax);
        System.out.println();
    }

    @Test
    public void test15(){
        System.out.println("-----partitioningBy-----根据谓词进行分类,两级----------");
        Map<Boolean,Map<Dish.Type,List<Dish>>> booleanTypeList =
                menu.stream()
                        .collect(Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.groupingBy(Dish::getType)));
        System.out.println(booleanTypeList);
        System.out.println(JSON.toJSONString(booleanTypeList));
        System.out.println();
    }


}
