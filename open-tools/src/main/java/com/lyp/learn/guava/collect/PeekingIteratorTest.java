package com.lyp.learn.guava.collect;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有时候普通的Iterator 接口不够。
 *
 * Iterators 支持方法 Iterators.peekingIterator(Iterator),
 * 其包装了一个 Iterator 返回 PeekingIterator, 是一种Iterator 的子类能够让你 peek() 在下一次调用next()时返回的元素。
 *
 * 注意: Iterators 返回 PeekingIterator
 * peekingIterator 不支持 remove() 在调用 peek()之后.
 */
public class PeekingIteratorTest {

    /**
     * 复制一个列表，同时删除连续的重复元素
     * 或者跳过相邻重复元素
     *
     * 传统的实现方法包括跟踪前一个元素，并在特定条件下回退，但这是一项棘手且易出错的业务。
     * PeekingIterator 比较容易理解和使用。
     */
    @Test
    public void test01(){
        List<String> source = new ArrayList<>();
        source.add("a");
        source.add("b");
        source.add("b");
        source.add("c");
        source.add("c");
        source.add("d");

        List<String> result = Lists.newArrayList();
        PeekingIterator<String> peekIter = Iterators.peekingIterator(source.iterator());

        while (peekIter.hasNext()) {
            String current = peekIter.next();
            while (peekIter.hasNext() && peekIter.peek().equals(current)) {
                // skip this duplicate element
                peekIter.next();
            }
            result.add(current);
        }

        System.out.println(source);
        System.out.println(result);
    }
}
