package com.lyp.learn.guava.collect;

import com.google.common.collect.SortedMultiset;

/**
 * SortedMultiset是Multiset 接口的变种，它支持高效地获取指定范围的子集。
 * 比方说，你可以用 latencies.subMultiset(0,BoundType.CLOSED, 100, BoundType.OPEN).size()来统计你的站点中延迟在100毫秒以内的访问，
 * 然后把这个值和latencies.size()相比，以获取这个延迟水平在总体访问中的比例。
 */
public class SortedMultisetTest {


}
