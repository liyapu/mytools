package com.lyp.learn.guava.base;

import com.google.common.base.Preconditions;
import com.lyp.learn.bean.Person;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-26 11:14
 *
 * 前置条件：让方法调用的前置条件判断更简单。
 *
 * Guava在Preconditions类中提供了若干前置条件判断的实用方法每个方法都有三个变种：
 *
 * 没有额外参数：抛出的异常中没有错误消息；
 * 有一个Object对象作为额外参数：抛出的异常使用Object.toString() 作为错误消息；
 * 有一个String对象作为额外参数，并且有一组任意数量的附加Object对象：这个变种处理异常消息的方式有点类似printf，但考虑GWT的兼容性和效率，只支持%s指示符。例如：
 *
 * 方法声明（不包括额外参数）	              描述                       	检查失败时抛出的异常
 * checkArgument(boolean)	            检查boolean是否为true，用来检查传递给方法的参数。	IllegalArgumentException
 * checkNotNull(T)	                   检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。	NullPointerException
 * checkState(boolean)	                用来检查对象的某些状态。	IllegalStateException
 * checkElementIndex(int index, int size)	检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *	IndexOutOfBoundsException
 * checkPositionIndex(int index, int size)	检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size *	IndexOutOfBoundsException
 * checkPositionIndexes(int start, int end, int size)	检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*	IndexOutOfBoundsException
 *
 * 索引值常用来查找列表、字符串或数组中的元素，如List.get(int), String.charAt(int)
 * 位置值和位置范围常用来截取列表、字符串或数组，如List.subList(int，int), String.substring(int)
 */
public class PreconditionsTest {

    @Test
    public void test01(){
//        methodPerson(null);
        methodPerson(new Person());
    }

    public static void methodPerson(Person person){
        Preconditions.checkNotNull(person,"param person can not is null");
        System.out.println("methodPerson.......");
    }


    @Test
    public void test02(){
//        methodNum(-100);
        methodNum(50);
    }

    public static void methodNum(int i){
        Preconditions.checkArgument(i >= 0,"param i = %s 不能为负值",i);
        System.out.println("methodNum .....");
    }

    @Test
    public void test03(){
//        methodNumber(8,2);
        methodNumber(2,6);
    }

    public static void methodNumber(int i, int j){
        Preconditions.checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
        System.out.println("methodNumber....");
    }


}
