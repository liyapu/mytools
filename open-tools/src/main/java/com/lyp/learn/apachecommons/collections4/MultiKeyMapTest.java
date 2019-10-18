package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 15:39
 *
 * MultiKeyMap能够解决我们平时可能遇到的一个痛点。
 * 比如我们Map的key，可能是由多个字段的值联合决定的（有点类似联合索引的意思），这个时候我们一般方案为：自己拼接字符串，然后put进去。
 *
 * 但现在有了MultiKeyMap，我们可以非常优雅的解决这个问题
 */
public class MultiKeyMapTest {

    /**
     * MultiKey功能很简单：装载多个key的一个对象
     */
    @Test
    public void testMultiKey(){
        MultiKey<String> multiKey1 = new MultiKey<>("k1","k2","k3");

        System.out.println(multiKey1);
        System.out.println(multiKey1.getKey(2));
        System.out.println("------------------");
        Object[] key1Keys = multiKey1.getKeys();
        for(Object key : key1Keys){
            System.out.println(key);
        }

        System.out.println("------------------");
        String[] keyArr = new String[]{"a","b","c","e","f","g","h","i","j","k"};
        MultiKey<String> multiKey2 = new MultiKey<>(keyArr);
        System.out.println(multiKey2);

    }

    @Test
    public void test01(){

        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap();
        multiKeyMap.put("1","2","12");
        multiKeyMap.put("1","2","3","123");
        multiKeyMap.put("1","2","3","4","1234");
        multiKeyMap.put("1","2","3","4","5","12345");


        MultiKey<String> multiKey = new MultiKey<>("a","b","c");
        multiKeyMap.put(multiKey,"abc");

        System.out.println(multiKeyMap);

        System.out.println(multiKeyMap.get("1","2"));
        System.out.println(multiKeyMap.get("1","2","3"));
        System.out.println(multiKeyMap.get("1","2","3","4"));
        System.out.println(multiKeyMap.get(multiKey));


    }
}
