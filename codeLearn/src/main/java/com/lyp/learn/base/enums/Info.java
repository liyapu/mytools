package com.lyp.learn.base.enums;

/**
 * enum 的全称为 enumeration， 是 JDK 1.5 中引入的新特性
 *
 * 枚举类型的本质
 * 尽管 enum 看起来像是一种新的数据类型，事实上，enum是一种受限制的类，并且具有自己的方法。
 * 创建enum时，编译器会为你生成一个相关的类，这个类继承自 java.lang.Enum
 *
 * 枚举类的对象是有限个
 * 对象个数::::  一个可以看做单例模式的实现        多个 为枚举类
 * 若枚举类只有一个成员，则可以作为一种单列模式的实现方式
 * 单例模式中 ，通过公共方法调用。  也可以通过类来调用。
 *
 *
 * 枚举的方法
 * 在enum中，提供了一些基本方法：
 * values()：返回 enum 实例的数组，而且该数组中的元素严格保持在 enum 中声明时的顺序。
 * name()：返回实例名。
 * ordinal()：返回实例声明时的次序，从0开始。
 * getDeclaringClass()：返回实例所属的 enum 类型。
 * equals() ：判断是否为同一个对象。可以使用 == 来比较enum实例。
 * 此外，java.lang.Enum实现了Comparable和 Serializable 接口，所以也提供 compareTo() 方法。
 */
public interface Info {
}
