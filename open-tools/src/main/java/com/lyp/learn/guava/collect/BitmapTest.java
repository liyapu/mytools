package com.lyp.learn.guava.collect;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 09:35
 *
 * 在 Java 集合类库中有个 Map，它的特点是存放的键（Key）是唯一的，而值（Value）可以不唯一，
 * 如果我们需要键（Key）和值（Value）都唯一，该怎么实现？
 * 这就是今天要谈的BiMap结构，它允许我们可以通过特定的 value 获取 key 值。
 */
public class BitmapTest {

    /**
     * 在过去，如果需要将Map结构中的键值对反转（也就是 key->value 转变成 value->key），这时候我们需要定义两个Map数据结构来存储。
     * 但如果 Map 中存在多个 value 相同的元素会发生什么情况呢？
     * 这时候添加进去的 key 将会覆盖先前加进去的 key。如下所示：
     * k1->v1，k2->v2，k3->v3，k4->v1
     * 如果我们需要反转 key 和 Value 将会变成 v1->k4，v2->k2，v3->k3，这是因为Map的特点 v1->k4 将覆盖先前的 v1->k1。
     */
    @Test
    public void testMap(){
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("k1","v1");
        biMap.put("k2","v2");
        biMap.put("k3","v3");
        biMap.put("k4","v1");
        System.out.println(biMap.get("k2"));
        System.out.println(biMap);
    }
}
