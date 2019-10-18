package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 16:09
 *
 * 统计值出现的次数,感觉和 Bag 很类似
 */
public class MultiSetTest {

    @Test
    public void test01(){
            MultiSet<String> multiSet = new HashMultiSet<>();
            multiSet.add("fang");
            multiSet.add("fang");
            multiSet.add("shi");
            multiSet.add("xiang");
            multiSet.add("xiang");
            multiSet.add("xiang");

            //我们发现此set是无序的，但是允许了重复元素的进入 并且记录了总数
            System.out.println(multiSet);
            System.out.println(multiSet.size());

            //批量添加  一些字就添加N个
            multiSet.add("test",5);
            System.out.println(multiSet);

            //移除方法
            System.out.println(multiSet.getCount("fang"));
            multiSet.remove("fang");
            //此移除 一次性只会移除一个
            System.out.println(multiSet.getCount("fang"));
            //一次性全部移除 N个
            multiSet.remove("xiang", multiSet.getCount("xiang"));
            System.out.println(multiSet.getCount("xiang"));

            //removeAll 吧指定的key，全部移除
            multiSet.removeAll(Arrays.asList("shi","xiang"));
            System.out.println(multiSet);
    }
}
