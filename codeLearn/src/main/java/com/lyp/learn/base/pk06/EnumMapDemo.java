package com.lyp.learn.base.pk06;

import com.lyp.learn.base.enums.Color;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 08:45
 *
 * 用于枚举类型键的专用Map实现。
 * EnumMap映射中的所有键必须来自创建映射时显式或隐式指定的单个枚举类型。
 * 枚举映射在内部表示为数组， 枚举映射按其键的自然顺序（枚举常量的声明顺序）维护。
 * 这反映在集合视图keySet()，entrySet()和values()返回的迭代器中。
 * 集合视图返回的迭代器非常一致：它们永远不会抛出ConcurrentModificationException，
 * 它们可能会也可能不会显示出迭代进行过程中对映射所做的修改的而对其造成的影响。不允许使用空键，
 * 尝试插入空键将抛出NullPointerException。
 * 但是，测试是否存在空键或删除空键将正常运行，且允许空值。
 * 同大多数集合一样，EnumMap不是线程同步的
 *
 * EnumMap要求其Key必须为Enum类型,更重要的是EnumMap效率更高，因为其内部是通过数组实现的
 *
 * 它只能接收同一枚举类型的实例作为键值且不能为null，由于枚举类型实例的数量相对固定并且有限，
 * 所以EnumMap使用数组来存放与枚举类型对应的值，毕竟数组是一段连续的内存空间，根据程序局部性原理，效率会相当高
 *
 * //创建一个具有指定键类型的空枚举映射。
 * EnumMap(Class<K> keyType)
 * //创建一个其键类型与指定枚举映射相同的枚举映射，最初包含相同的映射关系（如果有的话）。
 * EnumMap(EnumMap<K,? extends V> m)
 * //创建一个枚举映射，从指定映射对其初始化。
 * EnumMap(Map<K,? extends V> m)
 *
 */
public class EnumMapDemo {

    /**
     * 先思考这样一个问题，现在我们有一堆size大小相同而颜色不同的数据，
     * 需要统计出每种颜色的数量是多少以便将数据录入仓库
     *
     * 方案1:使用HashMap
     */
    @Test
    public void testHashMap(){
        Map<String,Integer> map = new HashMap<>();
        //for 遍历list 添加
        map.put(Color.RED.getCode(),10);
        map.put(Color.GREEN.getCode(),5);
        map.put(Color.YELLOW.getCode(),20);
    }

    /**
     * 方案2:使用EnumMap
     *
     *
     */
    @Test
    public void testEnumMap1(){
        Map<Color,Integer> enumMap=new EnumMap<>(Color.class);
        enumMap.put(Color.RED,10); //第一个值为key不能为null，第二个值为values可以为null
        enumMap.put(Color.GREEN,5);
        enumMap.put(Color.YELLOW,20);
        System.out.println(enumMap);
    }

    @Test
    public void testCreat(){
        //使用第一种构造
        Map<Color,Integer> enumMap=new EnumMap<>(Color.class);

        //使用第二种构造
        Map<Color,Integer> enumMap2=new EnumMap<>(enumMap);

        //使用第三种构造
        Map<Color,Integer> hashMap = new HashMap<>();
        hashMap.put(Color.GREEN, 2);
        hashMap.put(Color.GREEN, 3);
        Map<Color, Integer> enumMap3 = new EnumMap<>(hashMap);
    }


}
