package com.lyp.learn.guava.collect;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-08 16:01
 *
 * Range 表示一个间隔或一个序列。它被用于获取一组数字/串在一个特定范围之内。可比较类型的区间API，包括连续和离散类型。
 * Range 定义了连续跨度的范围边界，这个连续跨度是一个可以比较的类型(Comparable type)。比如1到100之间的整型数据。
 *
 * 在数学里面的范围是有边界和无边界之分的；
 * 同样，在Guava中也有这个说法。如果这个范围是有边界的，那么这个范围又可以分为包括 开集（不包括端点）和 闭集（包括端点）；
 * 如果是无解的可以用 +∞ 表示。
 *
 *
 */
public class RangeTest {

    /**
     * range 范围
     */
    @Test
    public void testOpenClosed() {
        Range<Integer> range = Range.closed(20, 30);
        print("closed",range, ContiguousSet.create(range, DiscreteDomain.integers()));

        range = Range.open(20, 30);
        print("open",range, ContiguousSet.create(range, DiscreteDomain.integers()));

        range = Range.openClosed(20, 30);
        print("openClosed",range, ContiguousSet.create(range, DiscreteDomain.integers()));

        range = Range.closedOpen(20, 30);
        print("closedOpen",range, ContiguousSet.create(range, DiscreteDomain.integers()));

        range = Range.greaterThan(20);
        System.out.println("greaterThan: " + ContiguousSet.create(range, DiscreteDomain.integers()).toString());

        range = Range.atLeast(20);
        System.out.println("atLeast: " + ContiguousSet.create(range, DiscreteDomain.integers()).toString());

        range = Range.lessThan(20);
        System.out.println("lessThan: " + ContiguousSet.create(range, DiscreteDomain.integers()).toString());

        range = Range.atMost(20);
        System.out.println("atMost: " + ContiguousSet.create(range, DiscreteDomain.integers()).toString());

        range = Range.all();
        System.out.println("all: " + ContiguousSet.create(range, DiscreteDomain.integers()).toString());
    }

    public static void print(String type, Range range,Set<Integer> ranges) {
        System.out.println(type + "：" + range + "      list：" + Lists.newArrayList(ranges));
    }

    /**
     * 也可以明确地指定边界类型来构造区间
     * 这里的 BoundType 是一个枚举类型，包含 CLOSED 和 OPEN 两个值。
     */
    @Test
    public void testBoundType(){
        Range.downTo(3, BoundType.OPEN);    // (3..+∞)
        Range.upTo(3, BoundType.CLOSED);    // (-∞..3]
        Range.range(1, BoundType.CLOSED, 6, BoundType.OPEN);  // [1..6) 等同于 Range.closedOpen(1, 6)
    }



    /**
     * 是否包含在区间内
     * contains
     */
    @Test
    public void testContains(){
        Range<Integer> range = Range.closed(20, 30);
        System.out.println(range.contains(1));
        System.out.println(range.contains(25));
        System.out.println(range.contains(60));

        System.out.println();
        System.out.println(range.containsAll(Ints.asList(1, 2, 3)));

    }

    /**
     * 两个区间，是否可以连续上
     */
    @Test
    public void testIsConnected(){
        Range<Integer> range = Range.closedOpen(2, 4);

        Range<Integer> other1 = Range.closed(5, 7);
        System.out.println(range.isConnected(other1));

        Range<Integer> other2 = Range.closed(3, 5);
        System.out.println(range.isConnected(other2));

        Range<Integer> other3 = Range.closed(4, 6);
        System.out.println(range.isConnected(other3));
    }

    /**
     * Range 类提供了以下方法来 查看区间的端点：
     *
     * 方法	描述
     * hasLowerBound() 和 hasUpperBound()	判断区间是否有特定边界，或是无限的
     * lowerBoundType() 和 upperBoundType()	返回区间边界类型，CLOSED 或 OPEN；如果区间没有对应的边界，抛出 IllegalStateException。
     * lowerEndpoint() 和 upperEndpoint()	返回区间的端点值；如果区间没有对应的边界，抛出 IllegalStateException。
     * isEmpty()	判断是否为空区间。
     */
    @Test
    public void testOperator(){
        Range<Integer> closed = Range.closed(20, 30);
        System.out.println(closed.hasUpperBound());
        System.out.println(closed.upperBoundType());
        System.out.println(closed.upperEndpoint());
        System.out.println(closed.isEmpty());
    }

    /**
     * 包含 [enclose]
     * 区间之间的最基本关系就是包含[encloses(Range)]：
     * 如果内区间的边界没有超出外区间的边界，则外区间包含内区间。包含判断的结果完全取决于区间端点的比较！
     *
     * 包含：是一种偏序关系。基于包含关系的概念。
     */
    @Test
    public void testEnclose(){
        Range<Integer> closed = Range.closed(3, 6);
        System.out.println(closed.encloses(Range.closed(4,5)));
        System.out.println(closed.encloses(Range.closed(2,5)));
    }

    /**
     * 交集 [intersection]
     * Range.intersection(Range) 返回两个区间的交集：既包含于第一个区间，又包含于另一个区间的最大区间。当且仅当两个区间是相连的，它们才有交集。
     * 如果两个区间没有交集，该方法将抛出 IllegalArgumentException。
     *
     * 交集：是可互换的、关联的运算。
     */
    @Test
    public void testIntersection(){
        Range<Integer> range = Range.closed(20, 30);
        System.out.println(range.intersection(Range.closed(25,27)));
        System.out.println(range.intersection(Range.closed(25,40)));

        Range<Integer> other = Range.closed(1, 6);
        if(range.isConnected(other)){
            //先判断是否连续，防止抛出异常
            System.out.println("连续，可能有交集");
        }else{
            System.out.println("不连续，不可能有交集");
        }
    }

    /**
     * 跨区间 [span]
     * Range.span(Range) 返回”同时包括两个区间的最小区间”，如果两个区间相连，那就是它们的并集(并不是数学意义上的并集)
     *
     * 跨区间：是可互换的、关联的、闭合的运算。
     */
    @Test
    public void testSpan(){
        Range<Integer> range = Range.closed(20, 30);

        Range<Integer> other1 = Range.closed(10, 20);
        System.out.println(range.span(other1));


        //两个集合都没有的 7，19 也包含进来了
        Range<Integer> other2 = Range.closed(1, 6);
        System.out.println(range.span(other2));

    }



}
