package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.bidimap.DualLinkedHashBidiMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 14:28
 *
 * 双向Map
 * 使用双向映射，可以使用值查找键，并且可以使用键轻松查找值。（自然，它可以根据key移除，也可以根据value移除）
 *
 * 该场景使用还是比较多的，比如一对一的映射关系，都可以使用这来存储。如果你使用HashMap，那你得维护两个，还是比较麻烦的
 *
 *
 */
public class BidiMapTest {

    @Test
    public void test01(){
        BidiMap<String,String> bidiMap = new DualHashBidiMap<String, String>();
        bidiMap.put("k1","v1");
        bidiMap.put("k2","v2");
        bidiMap.put("k3","v3");
        bidiMap.put("k4","v4");

        System.out.println(bidiMap);

        System.out.println(bidiMap.get("k2"));
        System.out.println(bidiMap.getKey("v3"));

        Set<String> keySet = bidiMap.keySet();
        System.out.println(keySet);

        Set<String> values = bidiMap.values();
        System.out.println(values);
    }

    /**
     * inverseBidiMap 翻转BidiMap
     * 修改翻转后的map，会影响原始map
     *
     */
    @Test
    public void testInverse(){
        BidiMap<String,String> bidiMap = new DualHashBidiMap<String, String>();
        bidiMap.put("k1","v1");
        bidiMap.put("k2","v2");
        bidiMap.put("k3","v3");
        bidiMap.put("k4","v4");

        System.out.println(bidiMap);

        BidiMap<String, String> inverseBidiMap = bidiMap.inverseBidiMap();
        System.out.println(inverseBidiMap);
        System.out.println();

        inverseBidiMap.put("k1","v11");
        inverseBidiMap.put("v1","k11");
        inverseBidiMap.put("k5","v5");
        inverseBidiMap.put("v6","k6");
        System.out.println(bidiMap);
        System.out.println(inverseBidiMap);
    }

    @Test
    public void testPut(){
        BidiMap<String,String> bidiMap = new DualHashBidiMap<String, String>();
        bidiMap.put("k1","v1");
        bidiMap.put("k2","v2");
        bidiMap.put("k3","v3");
        //相同的key
        bidiMap.put("k1","v11");
        //相同的value
        bidiMap.put("k4","v2");

        System.out.println(bidiMap);
    }

    @Test
    public void testRemoveValue(){
        BidiMap<String,String> bidiMap = new DualHashBidiMap<>();
        bidiMap.put("k1","v1");
        bidiMap.put("k2","v2");
        bidiMap.put("k3","v3");
        bidiMap.put("k4","v4");
        bidiMap.put("k5","v5");
        bidiMap.put("k6","v6");
        bidiMap.put("k7","v7");
        bidiMap.put("k8","v8");

        System.out.println(bidiMap);
        bidiMap.remove("k1");
        bidiMap.remove("k2","v2");
        bidiMap.remove("k3","v33");
        bidiMap.removeValue("v4");

        bidiMap.replace("k5","k555");
        bidiMap.replace("k6","v6","v666");
        bidiMap.replace("k7","v77","v777");
        System.out.println(bidiMap);
    }

    /**
     * 把一个普通的Map转成BidiMap
     */
    @Test
    public void testBidiMap(){
        Map<String,String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");
        map.put("k4","v4");
        System.out.println(map);
        System.out.println();

        BidiMap<String,String> bidiMap = new DualLinkedHashBidiMap<>(map);
        System.out.println(bidiMap);
        System.out.println(bidiMap.inverseBidiMap());
    }
}
