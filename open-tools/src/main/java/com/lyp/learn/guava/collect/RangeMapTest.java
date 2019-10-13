package com.lyp.learn.guava.collect;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * RangeMap 是一种集合类型(collection type)，它将不相交、且不为空的 Range（key）映射给一个值（Value）。
 * 和 RangeSet不一样，RangeMap 不可以将相邻的区间合并，即使这个区间映射的值是一样的。
 *
 * 和 RangeSet 一样，实现 RangeMap 也是一个接口，实现它的也只有两个类，分别为 mmutableRangeMap 和 TreeRangeMap。
 * 用的多的还是 TreeRangeMap，下面主要以 TreeRangeMap 来说明 RangeMap
 */
public class RangeMapTest {

    /**
     * 从结果中可以看出，RangeMap 中的每一个 Range 都对应一个 value。
     * 注意观察运行的结果：(10‥20)=aaa, [20‥20]=aaa 是没有被合并的！这是 RangeMap 和 RangeSet 的一个区别。
     */
    @Test
    public void test1(){
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "aaa");
        System.out.println(rangeMap);

        rangeMap.put(Range.open(3, 6), "bbb");
        System.out.println(rangeMap);

        rangeMap.put(Range.openClosed(10, 20), "aaa");
        System.out.println(rangeMap);

        rangeMap.put(Range.closed(20, 20), "aaa");
        System.out.println(rangeMap);

        rangeMap.remove(Range.closed(5, 11));
        System.out.println(rangeMap);

        System.out.println("------------keySet--------------");
        Set<Range<Integer>> keySet = rangeMap.asMapOfRanges().keySet();
        keySet.forEach(System.out::println);

        System.out.println("-------------正序遍历----------------");
        Map<Range<Integer>, String> rangeStringMap = rangeMap.asMapOfRanges();
        rangeStringMap.forEach((k,v) -> System.out.println(k + ":" + v));

        System.out.println("-------------倒序遍历---------------");
        Map<Range<Integer>, String> descendingMapOfRanges = rangeMap.asDescendingMapOfRanges();
        descendingMapOfRanges.forEach((k,v) -> System.out.println(k + ":" + v));

        System.out.println("-------------entrySet遍历--------------");
        Set<Map.Entry<Range<Integer>, String>> entrySet = rangeStringMap.entrySet();
        for(Map.Entry<Range<Integer>,String> entry : entrySet){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("------------iterator 遍历-----------------");
        Iterator<Map.Entry<Range<Integer>, String>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()){
            Map.Entry<Range<Integer>, String> next = entryIterator.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }
    }
}
