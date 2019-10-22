package com.lyp.learn.base.pk06;

import com.lyp.learn.base.enums.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 11:41
 * <p>
 * 创建一个具有指定元素类型的空EnumSet。
 * EnumSet<E>  noneOf(Class<E> elementType)
 * //创建一个指定元素类型并包含所有枚举值的EnumSet
 * <E extends Enum<E>> EnumSet<E> allOf(Class<E> elementType)
 * // 创建一个包括枚举值中指定范围元素的EnumSet
 * <E extends Enum<E>> EnumSet<E> range(E from, E to)
 * // 初始集合包括指定集合的补集
 * <E extends Enum<E>> EnumSet<E> complementOf(EnumSet<E> s)
 * // 创建一个包括参数中所有元素的EnumSet
 * <E extends Enum<E>> EnumSet<E> of(E e)
 * <E extends Enum<E>> EnumSet<E> of(E e1, E e2)
 * <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3)
 * <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4)
 * <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4, E e5)
 * <E extends Enum<E>> EnumSet<E> of(E first, E... rest)
 * //创建一个包含参数容器中的所有元素的EnumSet
 * <E extends Enum<E>> EnumSet<E> copyOf(EnumSet<E> s)
 * <E extends Enum<E>> EnumSet<E> copyOf(Collection<E> c)
 */
public class EnumSetDemo {

    @Test
    public void test01() {
        //空集合
        EnumSet<Color> enumSet = EnumSet.noneOf(Color.class);
        System.out.println("添加前：" + enumSet.toString());
        enumSet.add(Color.GREEN);
        enumSet.add(Color.YELLOW);
        System.out.println("添加后：" + enumSet.toString());

        System.out.println("-----------------------------------");

        //使用allOf创建包含所有枚举类型的enumSet，其内部根据Class对象初始化了所有枚举实例
        EnumSet<Color> enumSet1 = EnumSet.allOf(Color.class);
        System.out.println("allOf直接填充：" + enumSet1.toString());

        System.out.println("-----------------------------------");

        //初始集合包括枚举值中指定范围的元素
        EnumSet<Color> enumSet2 = EnumSet.range(Color.RED , Color.YELLOW);
        System.out.println("指定初始化范围：" + enumSet2.toString());

        System.out.println("-----------------------------------");

        //指定补集，也就是从全部枚举类型中去除参数集合中的元素，如下去掉上述enumSet2的元素
        EnumSet<Color> enumSet3 = EnumSet.complementOf(enumSet2);
        System.out.println("指定补集：" + enumSet3.toString());

        System.out.println("-----------------------------------");

        //初始化时直接指定元素
        EnumSet<Color> enumSet4 = EnumSet.of(Color.RED);
        System.out.println("指定Color.RED元素：" + enumSet4.toString());
        EnumSet<Color> enumSet5 = EnumSet.of(Color.RED, Color.GREEN);
        System.out.println("指定Color.RED和Color.GREEN元素：" + enumSet5.toString());

        System.out.println("-----------------------------------");

        //复制enumSet5容器的数据作为初始化数据
        EnumSet<Color> enumSet6 = EnumSet.copyOf(enumSet5);
        System.out.println("enumSet6：" + enumSet6.toString());

        System.out.println("-----------------------------------");

        //通过结果可以看出ArrayList内放置的元素可以重复，而EnumSet内放置的元素不重复，毕竟是枚举列嘛
        List<Color> list = new ArrayList<Color>();
        list.add(Color.RED);
        list.add(Color.RED);//重复元素
        list.add(Color.BLUE);
        System.out.println("list:" + list.toString());

        //使用copyOf(Collection<E> c)
        EnumSet enumSet7 = EnumSet.copyOf(list);
        System.out.println("enumSet7:" + enumSet7.toString());

    }
}
