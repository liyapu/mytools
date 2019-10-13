package com.lyp.learn.guava.collect;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import java.util.*;

public class MapsTest {

    /**
     * newHashMap
     * newHashMapWithExpectedSize
     * newLinkedHashMap
     * 4 种方式遍历
     */
    @Test
    public void test() {
        Map<Integer, Integer> map0 = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            map0.put(i, i);
        }
        System.out.println("map0：" + map0);     // map0：{0=0, 1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9}

        Map<Integer, Integer> map1 = Maps.newHashMap(map0);
        map1.put(10, 10);
        System.out.println("map1：" + map1);     // map1：{0=0, 1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=10}

        Map<Integer, Integer> map2 = Maps.newHashMapWithExpectedSize(3);
        map2.put(1, 1);
        map2.put(2, 2);
        map2.put(3, 3);
        System.out.println("map2：" + map2);     // map2：{1=1, 2=2, 3=3}

        Map<Integer, Integer> map3 = Maps.newLinkedHashMap(map1);
        map3.put(11, 11);
        System.out.println("map3：" + map3);     // map3：{0=0, 1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=10, 11=11}

        outMapKeyValue(map3);
    }

    /**
     * 遍历Map的四种方法
     */
    private static void outMapKeyValue(Map<Integer, Integer> map3) {
        System.out.println();
        System.out.println("\n1.通过Map.entrySet遍历key和value");
        for (Map.Entry<Integer, Integer> integerEntry : map3.entrySet()) {
            System.out.println("key：" + integerEntry.getKey() + " value：" + integerEntry.getValue());
        }

        System.out.println("\n2.通过Map.entrySet使用iterator遍历key和value");
        Iterator<Map.Entry<Integer, Integer>> it = map3.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            System.out.println("key：" + entry.getKey() + " value：" + entry.getValue());
        }

        System.out.println("\n3.通过Map.keySet遍历key；根据key得到value");
        for (Integer integer : map3.keySet()) {
            System.out.println("key：" + integer + " value：" + map3.get(integer));
        }

        System.out.println("\n4.通过Map.values()遍历所有的value，但不能遍历key");
        for (Integer integer : map3.values()) {
            System.out.println("value：" + integer);
        }
    }

    /**
     * difference：返回两个给定map之间的差异。
     */
    @Test
    public void test00(){
        HashMap<String, String> map1 = Maps.newHashMap();
        map1.put("a","1");
        map1.put("b","2");
        map1.put("c","3");
        map1.put("d","4");

        HashMap<String, String> map2 = Maps.newHashMap();
        map2.put("a","1");
        map2.put("b","2");
        map2.put("c","3");
        map2.put("d","4");

        differencePrint(map1, map2);
    }

    private void differencePrint(Map<String, String> map1, Map<String, String> map4) {
        MapDifference<String, String> difference = Maps.difference(map1, map4);
        System.out.println("areEqual :" + difference.areEqual());
        System.out.println("entriesInCommon :" + difference.entriesInCommon());
        System.out.println("entriesOnlyOnLeft :" + difference.entriesOnlyOnLeft());
        System.out.println("entriesOnlyOnRight ：" + difference.entriesOnlyOnRight());
        System.out.println("difference :" + difference);
        Map<String, MapDifference.ValueDifference<String>> stringValueDifferenceMap = difference.entriesDiffering();
        for(Map.Entry<String, MapDifference.ValueDifference<String>> entry : stringValueDifferenceMap.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("entriesDiffering :" + difference.entriesDiffering());
    }



    @Test
    public void test10(){
        HashMap<String, String> map1 = Maps.newHashMap();
        map1.put("a","1");
        map1.put("b","2");
        map1.put("c","3");
        map1.put("d","4");

        HashMap<String, String> map2 = Maps.newHashMap();
        map2.put("c","3");
        map2.put("d","4");
        map2.put("e","5");
        map2.put("f","6");

        differencePrint(map1, map2);
    }


    @Test
    public void test20(){
        HashMap<String, String> map1 = Maps.newHashMap();
        map1.put("a","1");
        map1.put("b","2");
        map1.put("c","3");
        map1.put("d","4");

        HashMap<String, String> map2 = Maps.newHashMap();
        map2.put("c","3");
        map2.put("d","444");
        map2.put("e","5");
        map2.put("f","6");

        differencePrint(map1, map2);
    }

    /**
     * transformValues：返回一个map映射，其键值为给定fromMap的键值，其value为给定formMap中value通过Function转换后的值
     * transformEntries：返回一个map映射， 其Entry为给定fromMap.Entry通过给定EntryTransformer转换后的值
     */
    @Test
    public void test_transformValues_transformEntries() {
        Map<String, Boolean> fromMap1 = Maps.newHashMap();
        fromMap1.put("first", true);
        fromMap1.put("second", false);
        // 对传入的元素取反
        System.out.println(Maps.transformValues(fromMap1, (Function<Boolean, Object>) input -> !input));
        System.out.println(Maps.transformValues(fromMap1,value -> !value));
        System.out.println(fromMap1);
        System.out.println();

        // value为假，则key变大写
        Maps.EntryTransformer<String, Boolean, String> entryTransformer = (key, value) -> value ? key : key.toUpperCase();
        System.out.println(Maps.transformEntries(fromMap1, entryTransformer));
        System.out.println(Maps.transformEntries(fromMap1,(key,value) -> key.length()));
        System.out.println(Maps.transformEntries(fromMap1,(key,value) -> value? key.length() : key.toUpperCase()));
    }

    /**
     * filterKeys：返回给定unfilteredMap中的键值通过给定keyPredicate过滤后的map映射
     * filterValues：返回给定unfilteredMap中的value值通过给定keyPredicate过滤后的map映射
     * filterEntries：返回给定unfilteredMap.Entry中的Entry值通过给定entryPredicate过滤后的map映射
     */
    @Test
    public void test_filterKeys_filterValues_filterEntries(){
        ImmutableMap<String, Integer> imuMap = ImmutableMap.of("tom", 15, "jack", 36, "jimi", 60, "li", 28);

        //过滤key中含有i的键
        Map<String, Integer> filterKeys = Maps.filterKeys(imuMap, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                return input.contains("i");
            }
        });
        System.out.println(filterKeys);
        System.out.println(Maps.filterKeys(imuMap,key -> key.length() >= 4));
        System.out.println(Maps.filterKeys(imuMap,key -> "li".equals(key)));

        System.out.println();
        Map<String, Integer> filterValues = Maps.filterValues(imuMap, new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer input) {
                return input >= 20;
            }
        });
        System.out.println(filterValues);
        System.out.println(Maps.filterValues(imuMap,value -> value <= 20));


        System.out.println();
        Map<String, Integer> filterEntries = Maps.filterEntries(imuMap, new Predicate<Map.Entry<String, Integer>>() {
            @Override
            public boolean apply(@Nullable Map.Entry<String, Integer> input) {
                String key = input.getKey();
                Integer value = input.getValue();
                return key.contains("i") && value <= 30;
            }
        });
        System.out.println(filterEntries);
        System.out.println(Maps.filterEntries(imuMap,entity -> entity.getKey().length() >= 3 && entity.getValue() <= 50));
    }

    /**
     * asMap 视图
     * toMap：返回一个不可变的ImmutableMap实例，其键值为给定keys中去除重复值后的值，其值为键Key被计算了valueFunction后的值
     * uniqueIndex：返回一个不可变的ImmutableMap实例，其value值为按照给定顺序的给定的values值，键值Key为相应的值经过给定Function计算后的值
     */
    @Test
    public void testMaps() {
        Set<String> set = Sets.newHashSet("a", "b", "c");
        // Function：大写转换
        Function<String, String> function = input -> input.toUpperCase();
        System.out.println(Maps.asMap(set, function));          // {b=B, c=C, a=A}

        List<String> keys = Lists.newArrayList("a", "b", "c", "a");
        System.out.println(Maps.toMap(keys, function));         // {a=A, b=B, c=C}

        //注意下面是：function 的值做 Key
        List<String> values = Lists.newArrayList("a", "b", "c", "d");
        System.out.println(Maps.uniqueIndex(values, function));     // {A=a, B=b, C=c, D=d}
    }
}