package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.list.GrowthList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 16:59
 *
 * GrowthList LazyList ：list自增长效果
 *
 * GrowthList修饰另一个列表，可以使其在因set或add操作造成索引超出异常时无缝的增加列表长度，
 * 可以避免大多数的IndexOutOfBoundsException。
 *
 * 备注：LazyList修饰另一个列表，当调用get方法时，如果索引超出列表长度，列表会自动增长，我们可以通过一个工厂获得超出索引位置的值。
 * LazyList和GrowthList都可以实现对修饰的列表进行增长，
 * 但是LazyList发生在get时候，
 * 而GrowthList发生在set和add时候，
 * 我们也可以混合使用这两种列
 *
 */
public class GrowthListTest {

    @Test
    public  void testOriginArrayList() {
        List<String> src = new ArrayList<>();
        src.add("11");
        src.add("22");
        System.out.println(src);

        // 索引超出设置,抛出异常
        //java.lang.IndexOutOfBoundsException: Index: 4, Size: 2
//        src.set(4, "44");
        System.out.println(src);
    }

    @Test
    public  void testGrowthList() {
        List<String> src = new ArrayList<>();
        src.add("11");
        src.add("22");
        src = GrowthList.growthList(src);
        System.out.println(src);

        //经过GrowthList.growthList一修饰后  这个list能够最大程度的避免空数组越界问题  有时候还是挺有用的
        // 索引超出，自动增长
        src.set(4, "44");
        System.out.println(src);

    }
}
