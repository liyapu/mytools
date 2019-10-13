package com.lyp.learn.guava.collect;

import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * RangeSet 类是一个接口，需要用它的子类来声明一个 RangeSet 型的对象，
 * 实现 RangeSet 接口的类有 ImmutableRangeSet和 TreeRangeSet，
 * ImmutableRangeSet 是一个不可修改的 RangeSet，而 TreeRangeSet 是利用树的形式来实现
 */
public class RangeSetTest {

    /**
     * complement()
     * 返回RangeSet的补集视图。complement也是RangeSet类型,包含了不相连的、非空的区间。
     */
    @Test
    public void testRangeSet(){
        RangeSet rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1,10));
        System.out.println(rangeSet);

        rangeSet.add(Range.closedOpen(11,15));
        System.out.println(rangeSet);

        rangeSet.add(Range.open(15,20));
        System.out.println(rangeSet);

        rangeSet.add(Range.openClosed(0,0));
        System.out.println(rangeSet);

        rangeSet.remove(Range.open(5,10));
        System.out.println(rangeSet);

        System.out.println("--------forEach 遍历--------------");
        // rangeSet.asDescendingSetOfRanges().forEach(System.out::println);  // 倒序
        rangeSet.asRanges().forEach(System.out::println);

        System.out.println("--------iterator 遍历------------");
        Iterator<Range> iterator = rangeSet.asRanges().iterator();
        while (iterator.hasNext()){
            Range next = iterator.next();
            System.out.println(next);
        }


        System.out.println("-------complement 补集-------");
        RangeSet complement = rangeSet.complement();
        System.out.println(complement);

        System.out.println("-------contains-------");
        System.out.println(rangeSet.contains(2));
        System.out.println(rangeSet.contains(100));

        System.out.println("------rangeContaining 包含范围------");
        System.out.println(rangeSet.rangeContaining(17));
        System.out.println(rangeSet.rangeContaining(100));

        System.out.println("-----enclose 包含范围-------");
        System.out.println(rangeSet.encloses(Range.closed(2,4)));
        System.out.println(rangeSet.encloses(Range.closed(2,10)));
    }

    /**
     * RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。例如：
     * RangeSet<Integer> rangeSet = TreeRangeSet.create();
     * rangeSet.add(Range.closed(1, 10));      // {[1,10]}
     * rangeSet.add(Range.closedOpen(11, 15)); // 不相连区间:{[1,10], [11,15)}
     * rangeSet.add(Range.closedOpen(15, 20)); // 相连区间; {[1,10], [11,20)}
     * rangeSet.add(Range.openClosed(0, 0));   // 空区间; {[1,10], [11,20)}
     * rangeSet.remove(Range.open(5, 10));     // 分割[1, 10]; {[1,5], [10,10], [11,20)}
     * 请注意，要合并 Range.closed(1, 10) 和 Range.closedOpen(11, 15) 这样的区间，
     * 你需要首先用 Range.canonical(DiscreteDomain) 对区间进行预处理，例如 DiscreteDomain.integers()。
     */
    @Test
    public void test1(){
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));      // {[1,10]}
        System.out.println(rangeSet);
        rangeSet.add(Range.closedOpen(11, 15)); // 不相连区间:{[1,10], [11,15)}
        System.out.println(rangeSet);
        rangeSet.add(Range.closedOpen(15, 20)); // 相连区间; {[1,10], [11,20)}
        System.out.println(rangeSet);
    }


}

