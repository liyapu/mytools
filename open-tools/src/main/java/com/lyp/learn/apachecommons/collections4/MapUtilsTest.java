package com.lyp.learn.apachecommons.collections4;

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
     *  getString(Map<? super K, ?> map, K key)
     *  获取map中key的String类型值
     *
     * getString(final Map<? super K, ?> map, final K key, final String defaultValue)
     * 获取map中key的String类型值，获取值为null或者转换异常时，返回默认值 defaultValue
     *
     */
    @Test
    public void testGetXXX(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("sex",true);
        map.put("age",34);
        map.put("money",null);

        System.out.println(MapUtils.getString(map,"name"));
        System.out.println(MapUtils.getString(map,"name","name-default-value"));
        System.out.println(MapUtils.getString(map,"nameNo"));
        System.out.println(MapUtils.getString(map,"nameNo","nameNo-default-value"));

        System.out.println();
        System.out.println(MapUtils.getBoolean(map,"sex"));
        System.out.println(MapUtils.getBoolean(map,"sex",false));
        System.out.println(MapUtils.getBoolean(map,"sexNo"));
        System.out.println(MapUtils.getBoolean(map,"sexNo",false));

        System.out.println();
        System.out.println(MapUtils.getBooleanValue(map,"sex"));
        System.out.println(MapUtils.getBooleanValue(map,"sex",false));
        System.out.println(MapUtils.getBooleanValue(map,"sexNo"));
        System.out.println(MapUtils.getBooleanValue(map,"sexNo",false));

        System.out.println();
        System.out.println(MapUtils.getIntValue(map,"age"));
        System.out.println(MapUtils.getIntValue(map,"age",100));
        System.out.println(MapUtils.getIntValue(map,"ageNo"));
        System.out.println(MapUtils.getIntValue(map,"ageNo",100));

        System.out.println();
        System.out.println(MapUtils.getInteger(map,"age"));
        System.out.println(MapUtils.getInteger(map,"age",100));
        System.out.println(MapUtils.getInteger(map,"ageNo"));
        System.out.println(MapUtils.getInteger(map,"ageNo",100));

        System.out.println();
        System.out.println(MapUtils.getString(map,"money"));
        System.out.println(MapUtils.getString(map,"money","money-default-value"));
        System.out.println(MapUtils.getString(map,"moneyNo"));
        System.out.println(MapUtils.getString(map,"moneyNo","moneyNo-default-value"));

//        还有很多，就不一一举例了，用法相同
//        MapUtils.getNumber();
//        MapUtils.getMap();
//        MapUtils.getObject();
    }

    @Test
    public void testIsEmpty(){
        Map map1 = null;
        Map<String,String> nullMap = null;
        Map<String,String> emptyMap = new HashMap<>();
        Map<String,String> strMap = new HashMap<>();
        strMap.put("a","11");
        strMap.put("b","22");

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
    public void testEmptyIfNull(){

        Map<String,String> nullMap = null;
        Map<String,String> emptyMap = new HashMap<>();


        System.out.println(MapUtils.isEmpty(nullMap));
        System.out.println("null == nullMap : " + (null == nullMap));
        if(null == nullMap){
            System.out.println("Collections.emptyMap() : " + Collections.emptyMap());
        }
        System.out.println("emptyIfNull : " + MapUtils.emptyIfNull(nullMap));

        System.out.println("-------------------");
        System.out.println(MapUtils.isEmpty(emptyMap));
        System.out.println("null == emptyMap : " + (null == emptyMap));
        if(null == emptyMap){
            System.out.println("Collections.emptyMap() : " + Collections.emptyMap());
        }
        //下面这一行判断，等价于上面的三行
        System.out.println("emptyIfNull : " + MapUtils.emptyIfNull(emptyMap));
    }

    /**
     * 对调key和value的值
     */
    @Test
    public void testInvertMap(){
        Map<String,String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");
        map.put("k4","v4");
        System.out.println(map);

        Map<String, String> invertMap = MapUtils.invertMap(map);
        System.out.println(invertMap);


    }
}
