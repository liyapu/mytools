package com.lyp.learn.hamcrest;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author: liyapu
 * @description:
 * @date 2020-03-14 14:16
 *
 * Hamcrest是用于Java的单元测试的框架，它使用matcher匹配器来进行断言
 *
 *   https://www.jianshu.com/p/e7d4c3bdac6e
 *
 *  使用静态导入可以使得所有匹配器都可用,更方便开发人员找到合适的匹配器
 *        import static org.hamcrest.MatcherAssert.assertThat;
 *        import static org.hamcrest.Matchers.*;
 *
 *  下面是一些非常重要且常用的Hamcrest匹配器
 *      allOf - 所有匹配条件都匹配则通过
 *      anyOf - 任何一个匹配条件匹配则通过
 *      not - 与匹配条件违背则通过
 *      equalTo - 使用Object.equals方法测试对象相等
 *      is - 与equalTo相同,仅用来提高代码可读性
 *      hasToString - 测试 Object.toString方法
 *      instanceOf,isCompatibleType - 测试类型
 *      notNullValue,nullValue - 测试null
 *      sameInstance - 测试是否是同一实例
 *      hasEntry,hasKey,hasValue - 测试一个Map包含entry,key或者value
 *      hasItem,hasItems - 测试一个集合包含对应元素
 *      hasItemInArray - 测试一个数组包含某个元素
 *      closeTo - 测试浮点值接近于给定值
 *      greaterThan, greaterThanOrEqualTo, lessThan, lessThanOrEqualTo
 *      equalToIgnoringCase - 测试字符串相等且忽略大小写
 *      equalToIgnoringWhiteSpace - 测试字符串相等且忽略空白符
 *      containsString, endsWith, startsWith - 匹配字符串
 *
 *
 */
public class DemoTest1 {

    @Test
    public void test0(){
        String a = "1";
        String b = "1";
        //下面语句的测试目的是一致的
        assertThat(a, equalTo(b));
        assertThat(a, is(equalTo(b)));
        assertThat(a, is(b));
    }

    /**
     * is
     * 使用is匹配器使得程序更加易懂
     */
    @Test
    public void test01() {
        String str1 = "text";
        String str2 = "    text   ";
        MatcherAssert.assertThat(str1, is(equalToCompressingWhiteSpace(str2)));
    }

    /**
     * 用于简单数据类型的判断
     */
    @Test
    public void test02() {
        String str1 = "text";
        String str2 = "text";
        assertThat(str1, is(str2));
    }

    /**
     * not
     * 和is使用方式一样，只是含义相反
     */
    @Test
    public void test03() {
        String str1 = "text";
        String str2 = " text ";
        assertThat(str1, not(equalToCompressingWhiteSpace(str2)));
    }

    @Test
    public void test04() {
        String str1 = "text";
        String str2 = " text ";
        assertThat(str1, not(str2));
    }

    /**
     * containsString
     * 是否包含子串
     */
    @Test
    public void test05() {
        String str1 = "text123";
        String str2 = "text";
        assertThat(str1, containsString(str2));
    }

    /**
     * 以为某个字段开头/结尾
     */
    @Test
    public void test06() {
        String str1 = "text123";
        assertThat(str1, startsWith("text"));// 以某个字符开头
        assertThat(str1, endsWith("text"));// 以某个字符开头
    }

    /**
     * 判断两个对象是否为同一个实体
     */
    @Test
    public void test07() {
        String s = new String();
        assertThat(s, sameInstance(s));
    }

    /**
     * 类似OR的效果
     */
    @Test
    public void test08() {
        String str = "calligraphy";
        String start = "call";
        String end = "foo";
        assertThat(str, anyOf(startsWith(start), containsString(end)));
    }

    /**
     * 类似AND的效果
     */
    @Test
    public void test09() {
        String str = "calligraphy";
        String start = "call";
        String end = "phy";
        assertThat(str, allOf(startsWith(start), endsWith(end)));
    }

    //==================Number 匹配器===============

    /**
     * Number 匹配器
     */
    @Test
    public void test10() {
        assertThat(1, greaterThan(0)); // 大于
        assertThat(5, greaterThanOrEqualTo(5)); //大于等于
        assertThat(-1, lessThan(0)); // 小于
        assertThat(-1, lessThanOrEqualTo(5)); // 小于等于
    }

    //===================Text 匹配器================

    /**
     * 匹配空串
     */
    @Test
    public void test11() {
        String str = "";
        assertThat(str, is(emptyString())); // 空字符串
        assertThat(str, is(emptyOrNullString())); // 空字符串或者null
    }

    /**
     * 匹配字符串相等
     */
    @Test
    public void test12() {
        String str1 = "text";
        String str2 = " text ";
        assertThat(str1, equalToCompressingWhiteSpace(str2)); // 忽略左右空白
        assertThat(str1, equalToIgnoringCase(str2)); //忽略大小写
    }

    //==============Collections 匹配器===================

    /**
     * 检查某个元素是否在集合中
     */
    @Test
    public void test13() {
        List<String> collection = Lists.newArrayList("ab", "cd", "ef");
        assertThat(collection, hasItem("cd"));
        assertThat(collection, not(hasItem("zz")));
        assertThat(collection, hasItems("cd", "ab")); // 检查多个元素是否在集合中
    }

    /**
     * 检查所有元素
     */
    @Test
    public void test14() {
        List<String> collection = Lists.newArrayList("ab", "cd", "ef");
        assertThat(collection, hasItems("ab", "cd", "ef"));
        assertThat(collection, hasItems("cd", "ab", "ef"));
        assertThat(collection, hasItems("ab", "cd"));
        assertThat(collection, containsInAnyOrder("cd", "ab", "ef")); //正确,不区分顺序
    }

    // --------------- 为空检查 ---------

    /**
     * 集合为空检查
     */
    @Test
    public void test15() {
        List<String> collection = Lists.newArrayList("ab", "cd", "ef");
        assertThat(collection, empty()); // false, 用于检查集合类型
    }

    /**
     * 数组为空检查
     */
    @Test
    public void test16() {
        String[] array = new String[]{"ab"};
        assertThat(array, emptyArray()); // false
    }

    /**
     * map为空检查
     */
    @Test
    public void test17() {
        Map<String, String> maps = Maps.newHashMap();
        assertThat(maps, equalTo(Collections.EMPTY_MAP));
    }

    /**
     * Iterable为空检查
     */
    @Test
    public void test18() {
        Iterable<String> collection = Lists.newArrayList();
        assertThat(collection, emptyIterable());
    }

    // ----------------- 检查数目 ------------

    /**
     * 检查集合数目
     */
    @Test
    public void test19() {
        List<String> list = Lists.newArrayList("ab", "cd", "ef");
        assertThat(list, hasSize(3));
    }

    /**
     * 检查iterable数目
     */
    @Test
    public void test20() {
        Iterable<String> list = Lists.newArrayList("ab", "cd", "ef");
        assertThat(list, iterableWithSize(3));
    }

    /**
     * 检查每项的条件
     */
    @Test
    public void test21() {
        List<String> list = Lists.newArrayList("ab", "cd", "ef");
        assertThat(list, everyItem(equalTo("ab")));
    }

    // ============== Bean 匹配器 ===========

    /**
     * 测试是否存在某个属性
     */
    @Test
    public void test22() {
        City city = new City("shenzhen", "CA");
        assertThat(city, hasProperty("state"));
        assertThat(city, hasProperty("state", equalTo("CA"))); // 判断是否存在某个属性，并且是否存在某个特性值
    }

    /**
     * 判断两个对象property值是否一样
     */
    @Test
    public void test23() {
        City city = new City("San Francisco", "CA");
        City city2 = new City("San Francisco", "CA");
        assertThat(city, samePropertyValuesAs(city2));
    }

    /**
     *
     */
    @Test
    public void test24() {

    }

    /**
     *
     */
    @Test
    public void test25() {

    }

    /**
     *
     */
    @Test
    public void test26() {

    }



}

