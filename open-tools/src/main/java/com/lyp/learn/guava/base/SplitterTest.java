package com.lyp.learn.guava.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-26 14:25
 *
 * =====================拆分器工厂====================================
 *     方法	                描述	           范例
 * Splitter.on(char)	按单个字符拆分	Splitter.on(‘;’)
 * Splitter.on(CharMatcher)	按字符匹配器拆分	Splitter.on(CharMatcher.BREAKING_WHITESPACE)
 * Splitter.on(String)	按字符串拆分	Splitter.on(“,   “)
 * Splitter.on(Pattern) Splitter.onPattern(String)	按正则表达式拆分	Splitter.onPattern(“\r?\n”)
 * Splitter.fixedLength(int)	按固定长度拆分；最后一段可能比给定长度短，但不会为空。	Splitter.fixedLength(3)
 *
 * ====================拆分器修饰符===============================
 *     方法	                 描述
 * omitEmptyStrings()	从结果中自动忽略空字符串
 * trimResults()	移除结果字符串的前导空白和尾部空白
 * trimResults(CharMatcher)	给定匹配器，移除结果字符串的前导匹配字符和尾部匹配字符
 * limit(int)	限制拆分出的字符串数量
 *
 * 如果你想要拆分器返回List，只要使用Lists.newArrayList(splitter.split(string))或类似方法。
 *
 * 警告：splitter实例总是不可变的。用来定义splitter目标语义的配置方法总会返回一个新的splitter实例。
 * 这使得splitter实例都是线程安全的，你可以将其定义为static final常量。
 */
public class SplitterTest {

    /**
     * 原生结果，不做处理
     */
    @Test
    public void test1(){
        Iterable<String> split = Splitter.on(",")
                                        .split("a,   b  , c,,,d , 1 1 , 2 ");
        Iterator<String> iterator = split.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * 对分割结果做 tirm, 忽略空字符串
     */
    @Test
    public void test2(){
        Iterable<String> split = Splitter.on(",")
                                        .trimResults()
                                        .omitEmptyStrings()
                                        .split("a,   b  , c,,,d , 1 1 , 2 ");
        Iterator<String> iterator = split.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * 返回List 结果
     */
    @Test
    public void test3(){
        Iterable<String> split = Splitter.on(",")
                                        .trimResults()
                                        .omitEmptyStrings()
                                        .split("a,   b  , c,,,d , 1 1 , 2 ");

        List<String> list = Lists.newArrayList(split);
        for(String str : list){
            System.out.println(str);
        }
    }

    /**
     * 固定长度拆分
     */
    @Test
    public void testFixLength(){
        List<String> list = Splitter.fixedLength(2).splitToList("1234567     89");
        for(String str : list){
            System.out.println(str);
        }
    }

    /**
     * 多个分隔符拆分
     */
    @Test
    public void testMoreChar(){
        Iterable<String> split = Splitter.on(CharMatcher.anyOf(";,."))
                                        .trimResults()
                                        .omitEmptyStrings()
                                        .split("foo,;bar,quux.aa.  bb ;");
        split.forEach(System.out::println);
    }
}