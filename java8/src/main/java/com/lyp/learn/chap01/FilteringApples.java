package com.lyp.learn.chap01;


import com.lyp.learn.ppt.Apple;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green",80,"黄土高原"),
                                              new Apple("green",200,"黄河故道"),
                                              new Apple("red",160,"渤海湾"),
                                              new Apple("yellow",20,"渤海湾")
                                );
        System.out.println("--------旧的写法，遍历循环找出 找出 weight >= 100---------");
        System.out.println(FilteringApples.filterWeightMoreThan100(inventory));
        System.out.println();

        System.out.println("--------旧的写法，遍历循环找出 找出 weight >= 150---------");
        System.out.println(FilteringApples.filterWeightMoreThan150(inventory));
        System.out.println();

        System.out.println("--------旧的写法，遍历循环找出 找出 weight >= amount 100---------");
        System.out.println(FilteringApples.filterWeightMoreThanAmount(inventory,100));
        System.out.println();

        System.out.println("--------旧的写法，遍历循环找出 找出 weight >= amount 150---------");
        System.out.println(FilteringApples.filterWeightMoreThanAmount(inventory,150));
        System.out.println();

        System.out.println("--------旧的写法，遍历循环找出 绿苹果---------");
        System.out.println(FilteringApples.filterGreenApples(inventory));
        System.out.println();

        System.out.println("--------旧的写法，遍历循环找出 绿苹果---------");
        System.out.println(FilteringApples.filterApplesByColor(inventory,"green"));
        System.out.println();

        System.out.println("--------旧的写法，遍历循环找出 红苹果---------");
        System.out.println(FilteringApples.filterApplesByColor(inventory,"red"));
        System.out.println();


        System.out.println("--------新的写法，找出 绿苹果-----1----");
        List<Apple> greenList = filterApple(inventory,FilteringApples::isGreenApple);
        System.out.println(greenList);
        System.out.println();

        System.out.println("--------新的写法，找出 绿苹果--匿名类---2----");
        List<Apple> greenList2 = filterApple(inventory, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        System.out.println(greenList2);
        System.out.println();

        /**
         * 把方法作为值来传递显然很有用，但要是为类似于isHeavyApple和isGreenApple这种可
         * 能只用一两次的短方法写一堆定义有点儿烦人。不过Java 8也解决了这个问题，它引入了一套新
         * 记法（匿名函数或Lambda），让你可以写
         * filterApples(inventory, (Apple a) -> "green".equals(a.getColor()) );
         * 或者
         * filterApples(inventory, (Apple a) -> a.getWeight() > 150 );
         * 甚至
         * filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "red".equals(a.getColor()) );
         *
         * 所以，你甚至都不需要为只用一次的方法写定义；代码更干净、更清晰，因为你用不着去找
         * 自己到底传递了什么代码。但要是Lambda的长度多于几行（它的行为也不是一目了然）的话，
         * 那你还是应该用方法引用来指向一个有描述性名称的方法，而不是使用匿名的Lambda。你应该
         * 以代码的清晰度为准绳。
         */

        System.out.println("--------新的写法，找出 绿苹果-----3---------");
        List<Apple> greenList3 = filterApple(inventory,(Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenList3);
        System.out.println();

        System.out.println("--------新的写法，找出 绿苹果-----4---------");
        List<Apple> greenList4 = filterApple(inventory,apple -> "green".equals(apple.getColor()));
        System.out.println(greenList4);
        System.out.println();

        System.out.println("--------新的写法，找出 绿苹果-----5---推荐----");
        List<Apple> greenList5 = inventory.stream()
                                        .filter(apple -> "green".equals(apple.getColor()))
                                        .collect(Collectors.toList());
        System.out.println(greenList5);
        System.out.println();



        System.out.println("--------新的写法，找出 重苹果----------");
        List<Apple> heavyList = filterApple(inventory,FilteringApples::isHeavyApple);
        System.out.println(heavyList);
        System.out.println();



        System.out.println("--------新的写法，找出 重苹果---------");
        List<Apple> heavyList2 = filterApple(inventory,(Apple a) -> a.getWeight() >= 150);
        System.out.println(heavyList2);
        System.out.println();

        System.out.println("--------新的写法，找出 重苹果---------");
        List<Apple> heavyList3 = filterApple(inventory,apple -> apple.getWeight() >= 150);
        System.out.println(heavyList3);
        System.out.println();

        System.out.println("--------新的写法，找出 红苹果 和 黄苹果---------");
        List<Apple> redYellowList = filterApple(inventory,(Apple a) -> "red".equals(a.getColor()) || "yellow".equals(a.getColor()));
        System.out.println(redYellowList);
        System.out.println();

        System.out.println("------旧的写法，按颜色分类苹果---------------");
        Map<String,List<Apple>> applesByColorsOld1 = FilteringApples.getApplesByColors(inventory);
        for(Map.Entry<String,List<Apple>> entry : applesByColorsOld1.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }
        System.out.println();

        System.out.println("------新的写法，按颜色分类苹果---------------");
        Map<String,List<Apple>> applesByColorsNew1 = inventory.stream()
                                                              .collect((groupingBy(Apple::getColor)));
        for(Map.Entry<String,List<Apple>> entry : applesByColorsNew1.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }
        System.out.println();

        System.out.println("------旧的写法，找出重量>=50--------------");
        List<Apple> appleWeightList1 = FilteringApples.getApplesByWeight(inventory,50);
        System.out.println(appleWeightList1);
        System.out.println();

        System.out.println("------新的写法，找出重量>=50--------------");
        List<Apple> appleWeightListNew1 = inventory.stream()
                                                    .filter((Apple a) -> a.getWeight() >= 50)
                                                    .collect(Collectors.toList());
        System.out.println(appleWeightListNew1);
        System.out.println();

        System.out.println("------新的写法，找出重量>=50 ----并行处理-----------");
        List<Apple> appleWeightListNew2 = inventory.parallelStream()
                                                    .filter((Apple a) -> a.getWeight() >= 50)
                                                    .collect(Collectors.toList());
        System.out.println(appleWeightListNew2);
        System.out.println();

        System.out.println("------旧的写法，找出重量>=50,然后按颜色分类苹果---------------");
        Map<String,List<Apple>> applesByColorsOld2 = FilteringApples.getApplesByColors(inventory,50);
        for(Map.Entry<String,List<Apple>> entry : applesByColorsOld2.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }
        System.out.println();

        System.out.println("------新的写法，找出重量>=50,然后按颜色分类苹果---------------");
        Map<String,List<Apple>> applesByColorsNew2 = inventory.stream()
                                                              .filter((Apple a) -> a.getWeight() > 50)
                                                              .collect(groupingBy(Apple::getColor));
        for(Map.Entry<String,List<Apple>> entry : applesByColorsNew2.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }
        System.out.println();

        System.out.println("-----旧的写法，按苹果重量升序排序--------------1------------");
        List<Apple> weightList1 = new ArrayList<>(inventory);
        System.out.println(weightList1);
        Collections.sort(weightList1, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        System.out.println(weightList1);
        System.out.println();

        System.out.println("-----旧的写法，按苹果重量升序排序--------------2------------");
        List<Apple> weightList2 = new ArrayList<>(inventory);
        System.out.println(weightList2);
        weightList2.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        System.out.println(weightList2);
        System.out.println();

        System.out.println("-----新的写法，按苹果重量升序排序--------------------------");
        List<Apple> weightList3 = new ArrayList<>(inventory);
        System.out.println(weightList3);
        weightList3.sort((Apple a1,Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(weightList3);
        System.out.println();

        System.out.println("-----新的写法，按苹果重量倒序排序------倒序------------------");
        List<Apple> weightList4 = new ArrayList<>(inventory);
        System.out.println(weightList4);
        weightList4.sort((Apple a1,Apple a2) -> a2.getWeight().compareTo(a1.getWeight()));
        System.out.println(weightList4);
        System.out.println();

        System.out.println("-----新的写法，按苹果重量升序排序--------推荐------------------");
        List<Apple> weightList5 = new ArrayList<>(inventory);
        System.out.println(weightList5);
        weightList5.sort(Comparator.comparing(apple -> apple.getWeight()));
        System.out.println(weightList5);
        System.out.println();

        System.out.println("-----新的写法，按苹果重量升序排序--------推荐------------------");
        List<Apple> weightList6 = new ArrayList<>(inventory);
        System.out.println(weightList6);
        weightList6.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(weightList6);
        System.out.println();

        System.out.println("-----新的写法，按苹果重量倒序排序--------推荐-----自定义排序--------");
        List<Apple> weightList7 = new ArrayList<>(inventory);
        System.out.println(weightList7);
        Collections.sort(weightList7,Comparator.comparing(Apple::getWeight,( a1, a2) -> a2.compareTo(a1)) );
        System.out.println(weightList7);
        System.out.println();

        System.out.println("-----新的写法，按苹果重量升序排序--------推荐-------------");
        List<Apple> weightList8 = new ArrayList<>(inventory);
        System.out.println(weightList8);
        Collections.sort(weightList8,Comparator.comparing(Apple::getWeight,Comparator.naturalOrder()) );
        System.out.println(weightList8);
        System.out.println();

        System.out.println("-----新的写法，按苹果重量倒序排序--------推荐-------------");
        List<Apple> weightList9 = new ArrayList<>(inventory);
        System.out.println(weightList9);
        Collections.sort(weightList9,Comparator.comparing(Apple::getWeight,Comparator.reverseOrder()) );
        System.out.println(weightList9);
        System.out.println();

        System.out.println("----旧的写法，找出重量大于50，然后按重量排序，输出颜色------------");
        List<Apple> weightList10 = new ArrayList<>(inventory);
        List<Apple> weightList10Temp = new ArrayList<>();
        for(Apple apple : weightList10){
            if(apple.getWeight() > 50){
                weightList10Temp.add(apple);
            }
        }
        weightList10Temp.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        List<String> weightList10Color = new ArrayList<>();
        for(Apple apple : weightList10Temp){
            weightList10Color.add(apple.getColor());
        }
        System.out.println(weightList10Color);
        System.out.println();

        System.out.println("----新的写法，找出重量大于50，然后按重量排序，输出颜色------------");
        List<String> colorList =
                inventory.stream()
                .filter(a -> a.getWeight() > 50)
                .sorted(Comparator.comparing(Apple::getWeight))
                .map(Apple::getColor)
                .collect(Collectors.toList());

        System.out.println(colorList);
        System.out.println();

    }

    /**
     * 找出 weight >= 100
     * 旧的写法，遍历循环找出
     */
    public static List<Apple> filterWeightMoreThan100(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getWeight() >= 100){
                result.add(apple);
            }
        }
        return result;
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

    /**
     * 判断是否是绿苹果
     * @param apple
     * @return
     */
    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    /**
     * 根据颜色判断苹果
     * @param apple
     * @return
     */
    public static boolean isColorApple(Apple apple,String color){
        return color.equals(apple.getColor());
    }

    /**
     * 判断是否是 重苹果 >= 150
     * @param apple
     * @return
     */
   public static boolean isHeavyApple(Apple apple){
        return apple.getWeight() >= 150;
   }

   public static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
   }


    /**
     * 按颜色分类苹果
     */
    public static Map<String,List<Apple>> getApplesByColors(List<Apple> inventory){
        Map<String,List<Apple>> applesByColors = new HashMap<>();
//        for(Apple apple : inventory){
//            String color = apple.getColor();
//            List<Apple> appleList = applesByColors.get(color);
//            if(null == appleList){
//                appleList = new ArrayList<>();
//                applesByColors.put(color,appleList);
//            }
//            appleList.add(apple);
//        }
        return applesByColors;
    }

    /**
     * 根据重量获取苹果
     * @param inventory
     * @param weight
     * @return
     */
    public static List<Apple> getApplesByWeight(List<Apple> inventory,int weight){
        List<Apple> appleWeightList = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getWeight() >= weight){
                appleWeightList.add(apple);
            }
        }
        return appleWeightList;
    }

    /**
     * 找出 苹果重量 >= amount,然后按颜色分类苹果
     */
    public static Map<String,List<Apple>> getApplesByColors(List<Apple> inventory,int amount){
        Map<String,List<Apple>> applesByColors = new HashMap<>();
        for(Apple apple : inventory){
            if(apple.getWeight() >= amount){
                String color = apple.getColor();
                List<Apple> appleList = applesByColors.get(color);
                if(null == appleList){
                    appleList = new ArrayList<>();
                    applesByColors.put(color,appleList);
                }
                appleList.add(apple);
            }
        }
        return applesByColors;
    }



    @AllArgsConstructor
    @Data
    public static class AppleBak{
        private String color;
        private Integer weight;
        private String address;

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}
