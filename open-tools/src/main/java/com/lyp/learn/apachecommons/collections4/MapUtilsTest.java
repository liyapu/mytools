package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 19:53
 *
 * 为map和sortedmap实例提供实用程序方法和装饰器
 */
public class MapUtilsTest {

    /**
     * getString(Map<? super K, ?> map, K key)
     * 获取map中key的String类型值
     *
     * getString(final Map<? super K, ?> map, final K key, final String defaultValue)
     * 获取map中key的String类型值，获取值为null或者转换异常时，返回默认值 defaultValue
     */
    @Test
    public void testGetXXX() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("sex", true);
        map.put("age", 34);
        map.put("money", null);

        System.out.println(MapUtils.getString(map, "name"));
        System.out.println(MapUtils.getString(map, "name", "name-default-value"));
        System.out.println(MapUtils.getString(map, "nameNo"));
        System.out.println(MapUtils.getString(map, "nameNo", "nameNo-default-value"));

        System.out.println();
        System.out.println(MapUtils.getBoolean(map, "sex"));
        System.out.println(MapUtils.getBoolean(map, "sex", false));
        System.out.println(MapUtils.getBoolean(map, "sexNo"));
        System.out.println(MapUtils.getBoolean(map, "sexNo", false));

        System.out.println();
        System.out.println(MapUtils.getBooleanValue(map, "sex"));
        System.out.println(MapUtils.getBooleanValue(map, "sex", false));
        System.out.println(MapUtils.getBooleanValue(map, "sexNo"));
        System.out.println(MapUtils.getBooleanValue(map, "sexNo", false));

        System.out.println();
        System.out.println(MapUtils.getIntValue(map, "age"));
        System.out.println(MapUtils.getIntValue(map, "age", 100));
        System.out.println(MapUtils.getIntValue(map, "ageNo"));
        System.out.println(MapUtils.getIntValue(map, "ageNo", 100));

        System.out.println();
        System.out.println(MapUtils.getInteger(map, "age"));
        System.out.println(MapUtils.getInteger(map, "age", 100));
        System.out.println(MapUtils.getInteger(map, "ageNo"));
        System.out.println(MapUtils.getInteger(map, "ageNo", 100));

        System.out.println();
        System.out.println(MapUtils.getString(map, "money"));
        System.out.println(MapUtils.getString(map, "money", "money-default-value"));
        System.out.println(MapUtils.getString(map, "moneyNo"));
        System.out.println(MapUtils.getString(map, "moneyNo", "moneyNo-default-value"));

//        还有很多，就不一一举例了，用法相同
//        MapUtils.getNumber();
//        MapUtils.getMap();
//        MapUtils.getObject();
    }

    @Test
    public void testIsEmpty() {
        Map map1 = null;
        Map<String, String> nullMap = null;
        Map<String, String> emptyMap = new HashMap<>();
        Map<String, String> strMap = new HashMap<>();
        strMap.put("a", "11");
        strMap.put("b", "22");

        System.out.println(MapUtils.isEmpty(map1));
        System.out.println(MapUtils.isEmpty(nullMap));
        System.out.println(MapUtils.isEmpty(emptyMap));
        System.out.println(MapUtils.isEmpty(strMap));

        System.out.println();

        System.out.println(MapUtils.isNotEmpty(map1));
        System.out.println(MapUtils.isNotEmpty(nullMap));
        System.out.println(MapUtils.isNotEmpty(emptyMap));
        System.out.println(MapUtils.isNotEmpty(strMap));
    }

    @Test
    public void testEmptyIfNull() {

        Map<String, String> nullMap = null;
        Map<String, String> emptyMap = new HashMap<>();


        System.out.println(MapUtils.isEmpty(nullMap));
        System.out.println("null == nullMap : " + (null == nullMap));
        if (null == nullMap) {
            System.out.println("Collections.emptyMap() : " + Collections.emptyMap());
        }
        System.out.println("emptyIfNull : " + MapUtils.emptyIfNull(nullMap));

        System.out.println("-------------------");
        System.out.println(MapUtils.isEmpty(emptyMap));
        System.out.println("null == emptyMap : " + (null == emptyMap));
        if (null == emptyMap) {
            System.out.println("Collections.emptyMap() : " + Collections.emptyMap());
        }
        //下面这一行判断，等价于上面的三行
        System.out.println("emptyIfNull : " + MapUtils.emptyIfNull(emptyMap));
    }

    /**
     * 对调key和value的值
     */
    @Test
    public void testInvertMap() {
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");
        System.out.println(map);

        Map<String, String> invertMap = MapUtils.invertMap(map);
        System.out.println(invertMap);
    }

    /**
     * 测试迭代时，删除符合要求的 key
     * 原始方式一，不行
     */
    @Test
    public void testRemoveKey1() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        String deleteKey = "key2";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            if (deleteKey.equals(key)) {
                //报异常，不能通过这种方式删除
                //java.util.ConcurrentModificationException
                map.remove(deleteKey);
            }
        }
    }

    /**
     * 测试迭代时，删除符合要求的 key
     * 原始方式二，通过根据key的迭代器去删除
     * map.entrySet().iterator();
     */
    @Test
    public void testRemoveKey2() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        System.out.println(map);

        String deleteKey = "key2";
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, String>> it = entrySet.iterator();
        while (it.hasNext()) {
//            Map.Entry<String, String> next = it.next();
//            String key = next.getKey();
            String key = it.next().getKey();
            if (deleteKey.equals(key)) {
                it.remove();
            }
        }

        System.out.println(map);
    }

    /**
     * iterableMap
     * 构建一个iterableMap，然后方便遍历、删除等等
     */
    @Test
    public void testIterableMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        System.out.println(map);

        String deleteKey = "key2";

        IterableMap<String, String> iterableMap = MapUtils.iterableMap(map);
        MapIterator<String, String> it = iterableMap.mapIterator();
        while (it.hasNext()) {
            it.next();
            String key = it.getKey();
            if (deleteKey.equals(key)) {
                it.remove();
            }
        }

        System.out.println(iterableMap);
        System.out.println(map);
    }

    /**
     * populateMap
     * 能很方便向Map里面放值，并且支持定制化key和value，还是挺好用的
     */
    @Test
    public void testPopulateMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");

        //序列化 根据提供的values，按照后面规则把key都生成出来然后直接放进去
        MapUtils.populateMap(map, Arrays.asList("a", "b", "c"), e -> "key-" + e);
        System.out.println(map);
        //可以在上面的理论上 对value进行进一步操作  不能采用map.values() 否则由于并发修改异常
        // MapUtils.populateMap(map, map.values(), e -> e, e -> "value-" + e); //java.util.ConcurrentModificationException
        MapUtils.populateMap(map, Arrays.asList("a", "b", "c"), e -> e, e -> "value-" + e);

        System.out.println(map);
    }

    /**
     * toProperties：可以有非常简便的转化
     */
    @Test
    public void tesToProperties(){
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        Properties properties = MapUtils.toProperties(map);
        System.out.println(properties);
    }
}
