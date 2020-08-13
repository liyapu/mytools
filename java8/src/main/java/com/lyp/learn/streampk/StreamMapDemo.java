package com.lyp.learn.streampk;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.lyp.learn.bean.Apple;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-16 19:00
 */
public class StreamMapDemo {
    List<Apple> inventory = Arrays.asList(
            new Apple("green", 80, "黄土高原"),
            new Apple("green", 200, "黄河故道"),
            new Apple("red", 160, "渤海湾"),
            new Apple("yellow", 20, "渤海湾")
    );

    Map<String, List<String>> map = new HashMap<>();

    {
        map.put("java", Arrays.asList("1.7", "1.8"));
    }

    /**
     * 现在想通过stream流操作,将:
     *    Map<String,List<String>> map ---> Map<String,Java> map
     *
     *  根据题主的描述，我感觉其实就是一个把map转化为map的过程，可以看到变化的只是有value而已，key没有变化，
     *  这里map也提供了一种变相来把map中数据转化为stream的方式，并没有直接map.stream()的方法
     *
     *   以前map循环是用到map的entrySet，而这里的entrySet就是一个集合，也就可以用stream了
     *   map.entrySet().stream()
     *   此时流map.entrySet().stream()里的数据是Map.Entry<String,List<String>>，
     *   现在其实就是要把Map.Entry<String,List<String>>转化为Map<String,Java>，
     *   由于最后是要的结果是用map收集，所以只能collect(Collectors.toMap())了
     *
     *   Collectors.toMap的两个参数的方法，
     *       第一个参数代表如何获取key，
     *       第二个代表如何获取value，
     *       因为key没有变，所以直接取entry的key，
     *       value的话要转化为Java对象，所以写了一个构造函数
     */
    @Test
    public void test01() {
        Map<String, Java> collect = map.entrySet()
                .stream()
                .collect(Collectors.toMap(en -> en.getKey(), en -> new Java(en.getValue())));
        System.out.println(collect);
    }

    @Test
    public void test02() {
        Map<String, List<Apple>> address$appleMap = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress));
        System.out.println(address$appleMap);
    }

    /**
     * 把 Map 的 value 值集合进行操作
     */
    @Test
    public void test03() {
        Map<String, List<Apple>> address$appleMap = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress));
        System.out.println(address$appleMap);

        Map<String, String> address$cwMap = address$appleMap.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                e -> e.getKey(),
                                e -> e.getValue().stream()
                                        .map(a -> a.getWeight() + "+" + a.getColor())
                                        .collect(Collectors.joining("，"))
                        )
                );

        System.out.println(address$cwMap);
    }

    /**
     * map 拼接为字符串
     */
    @Test
    public void test04() {
        Map<String, List<Apple>> address$appleMap = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress));
        System.out.println(address$appleMap);
        System.out.println();

        Map<String, String> address$cwMap = address$appleMap.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                e -> e.getKey(),
                                e -> e.getValue().stream()
                                        .map(a -> a.getWeight() + "+" + a.getColor())
                                        .collect(Collectors.joining("，"))
                        )
                );

        System.out.println(address$cwMap);
        System.out.println();

        StringBuilder sb = new StringBuilder();
        address$cwMap.forEach((k, v) -> {
            sb.append(k)
                    .append(":")
                    .append(v)
                    .append(";");
        });
        System.out.println(sb.toString());
    }

    //================== 排序 ================

    /**
     *
     * 在讲解Map排序之前，我们先来稍微了解下map，map是键值对的集合接口，
     * 它的实现类主要包括：HashMap, TreeMap, Hashtable以及LinkedHashMap等。
     *
     * 其中这四者的区别如下（简单介绍）：
     *
     *     HashMap：我们最常用的Map，HashMap是无序的，
     *              它根据key的HashCode 值来存储数据，根据key可以直接获取它的Value，同时它具有很快的访问速度。
     *             HashMap最多只允许一条记录的key值为Null(多条会覆盖);允许多条记录的Value为 Null。非同步的。
     *
     *     TreeMap：能够把它保存的记录根据key排序,默认是按升序排序，也可以指定排序的比较器，
     *              当用Iterator 遍历TreeMap时，得到的记录是排过序的。
     *              TreeMap不允许key的值为null。非同步的。
     *
     *     Hashtable：与HashMap类似,不同的是:key和value的值均不允许为null；
     *                它支持线程的同步，即任一时刻只有一个线程能写Hashtable, 因此也导致了Hashtale在写入时会比较慢。
     *
     *     LinkedHashMap：LinkedHashMap是有序的，保存了记录的插入顺序，
     *                在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的.在遍历的时候会比HashMap慢。
     *                key和value均允许为空，非同步的。
     */
    @Test
    public void testMapSort1() {
        Map<String, Integer> map = ImmutableMap.of("0", 3, "1", 8, "0.29", 7, "1.67", 3);
        System.out.println("原始的map：" + map);
        System.out.println("根据map的key降序：" + sortByKey(map, true));
        System.out.println("根据map的key升序：" + sortByKey(map, false));
        System.out.println("根据map的value降序：" + sortByValue(map, true));
        System.out.println("根据map的value升序：" + sortByValue(map, false));
    }

    /**
     * 根据map的key排序
     *
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     *
     *   如果我们需要根据key排序，就需要让key 继承 Comparable ，也就说我们需要对待排序的字段继承 Comparable接口
     *   如果我们需要升序排序的话，只需要将上面的.reversed(）关键字限制去掉即可
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }


    /**
     * 根据map的value排序
     *
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     * @author zero 2019/04/08
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.<K, V>comparingByValue().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.<K, V>comparingByValue())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

    /**
     * map到map排序
     *
     *   请注意使用LinkedHashMap来存储排序的结果以保持顺序。
     *   默认情况下，Collectors.toMap()返回HashMap。HashMap不能保证元素的顺序
     */
    @Test
    public void testMapSort2() {
        Map<String, String> strMap = new HashMap<>();
        strMap.put("20", "aaaaa");
        strMap.put("50", "ccccc");
        strMap.put("30", "ddddd");
        strMap.put("40", "eeeee");
        strMap.put("25", "bbbbb");

        System.out.println("原始:");
        System.out.println(strMap);


        System.out.println("key 正序排:");
        Map<String, String> ascKeyResult = Maps.newLinkedHashMap();
        strMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e -> ascKeyResult.put(e.getKey(), e.getValue()));
        System.out.println(ascKeyResult);

        System.out.println("key 正序排:");
        LinkedHashMap<String, String> ascKeyLinkedHashMap = strMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println(ascKeyLinkedHashMap);


        System.out.println("key 倒序排:");
        Map<String, String> descKeyResult = Maps.newLinkedHashMap();
        strMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, String>comparingByKey().reversed())
                .forEachOrdered(e -> descKeyResult.put(e.getKey(), e.getValue()));
        System.out.println(descKeyResult);


        System.out.println();
        System.out.println("value 正序排:");
        LinkedHashMap<String, String> ascValueLinkedHashMap = strMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println(ascValueLinkedHashMap);


        System.out.println("value 倒序排:");
        LinkedHashMap<String, String> descValueLinkedHashMap = strMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, String>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println(descValueLinkedHashMap);

    }

    /**
     * Map<Object,Object>
     *   Stream 不能直接对 Map<Object,Object>  进行排序，可以现将它转为 Map<String,String>，再操作
     */
    @Test
    public void testMapSort3() {
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();

        LinkedHashMap<String, String> collect = entries.stream()
                .collect(Collectors.toMap(k -> (String) k.getKey(), e -> (String) e.getValue()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        collect.forEach((k, v) -> System.out.println(k + ":" + v));
    }



}


@AllArgsConstructor
@Data
class Java {
    private List<String> versions;
}