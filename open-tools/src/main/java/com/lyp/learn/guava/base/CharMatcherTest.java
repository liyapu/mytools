package com.lyp.learn.guava.base;

import com.google.common.base.CharMatcher;
import org.junit.jupiter.api.Test;

/**
 * CharMatcher像java版的正则表达式
 *
 * 两种操作:
 *一、找到需要的CharMatcher
 *二、用CharMatcher进行操作
 *
 * 一.获得CharMatcher
 * 内置的的对象:
 * ANY: 匹配任何字符
 * ASCII: 匹配是否是ASCII字符
 * BREAKING_WHITESPACE: 匹配所有可换行的空白字符(不包括非换行空白字符,例如"\u00a0")
 * DIGIT: 匹配ASCII数字 
 * INVISIBLE: 匹配所有看不见的字符
 * JAVA_DIGIT: 匹配UNICODE数字, 使用 Character.isDigit() 实现
 * JAVA_ISO_CONTROL: 匹配ISO控制字符, 使用 Charater.isISOControl() 实现
 * JAVA_LETTER: 匹配字母, 使用 Charater.isLetter() 实现
 * JAVA_LETTER_OR_DIGET: 匹配数字或字母
 * JAVA_LOWER_CASE: 匹配小写
 * JAVA_UPPER_CASE: 匹配大写
 * NONE: 不匹配所有字符
 * SINGLE_WIDTH: 匹配单字宽字符, 如中文字就是双字宽
 * WHITESPACE: 匹配所有空白字符
 *
 * 自己构造对象:
 * CharMatcher is(char match): 返回匹配指定字符的Matcher
 * CharMatcher isNot(char match): 返回不匹配指定字符的Matcher
 * CharMatcher anyOf(CharSequence sequence): 返回匹配sequence中任意字符的Matcher
 * CharMatcher noneOf(CharSequence sequence): 返回不匹配sequence中任何一个字符的Matcher
 * CharMatcher inRange(char startInclusive, char endIncludesive): 返回匹配范围内任意字符的Matcher
 * CharMatcher forPredicate(Predicate<? super Charater> predicate): 返回使用predicate的apply()判断匹配的Matcher
 * CharMatcher negate(): 返回以当前Matcher判断规则相反的Matcher
 * CharMatcher and(CharMatcher other): 返回与other匹配条件组合做与来判断的Matcher
 * CharMatcher or(CharMatcher other): 返回与other匹配条件组合做或来判断的Matcher
 *
 *
 * 二.用CharMatcher进行匹配或操作
 *
 * --进行匹配
 * boolean matchesAnyOf(CharSequence sequence): 只要sequence中有任意字符能匹配Matcher,返回true
 * boolean matchesAllOf(CharSequence sequence): sequence中所有字符都能匹配Matcher,返回true
 * boolean matchesNoneOf(CharSequence sequence): sequence中所有字符都不能匹配Matcher,返回true
 *
 * int indexIn(CharSequence sequence): 返回sequence中匹配到的第一个字符的坐标
 * int indexIn(CharSequence sequence, int start): 返回从start开始,在sequence中匹配到的第一个字符的坐标
 * int lastIndexIn(CharSequence sequence): 返回sequence中最后一次匹配到的字符的坐标
 * int countIn(CharSequence sequence): 返回sequence中匹配到的字符计数
 *
 * --对字符串进行操作
 * String removeFrom(CharSequence sequence): 删除sequence中匹配到到的字符并返回
 * String retainFrom(CharSequence sequence): 保留sequence中匹配到的字符并返回
 * String replaceFrom(CharSequence sequence, char replacement): 替换sequence中匹配到的字符并返回
 * String trimFrom(CharSequence sequence): 删除首尾匹配到的字符并返回
 * String trimLeadingFrom(CharSequence sequence): 删除首部匹配到的字符
 * String trimTrailingFrom(CharSequence sequence): 删除尾部匹配到的字符
 * String collapseFrom(CharSequence sequence, char replacement): 将匹配到的组(连续匹配的字符)替换成replacement 
 * String trimAndCollapseFrom(CharSequence sequence, char replacement): 先trim在replace
 */
public class CharMatcherTest {

    @Test
    public void testMatch(){
        System.out.println(CharMatcher.is('a').matches('a'));
        System.out.println(CharMatcher.is('a').matches('b'));
        System.out.println();
        System.out.println(CharMatcher.is('a').matchesAllOf("aaa"));
        System.out.println(CharMatcher.is('a').matchesAllOf("aaa "));
        System.out.println(CharMatcher.is('a').matchesAllOf("abc"));
        System.out.println();
        System.out.println(CharMatcher.is('a').matchesAnyOf("abc"));
        System.out.println(CharMatcher.is('a').matchesAnyOf("abc "));
        System.out.println(CharMatcher.is('a').matchesAnyOf("bc"));
        System.out.println();
        System.out.println(CharMatcher.is('a').matchesNoneOf("aaa"));
        System.out.println(CharMatcher.is('a').matchesNoneOf("abc"));
        System.out.println(CharMatcher.is('a').matchesNoneOf("bc"));
    }

    @Test
    public void testIndex(){
        System.out.println(CharMatcher.is('a').indexIn("hello we are good!"));
        System.out.println(CharMatcher.is('a').lastIndexIn("hello we are good!"));
        System.out.println(CharMatcher.is('o').countIn("hello we are good!"));
    }

    @Test
    public void testReplace(){
        System.out.println(CharMatcher.is('a').removeFrom("abc abc aabbcc"));
        System.out.println(CharMatcher.is('a').retainFrom("abc abc aabbcc"));
        System.out.println(CharMatcher.is('a').replaceFrom("abc abc aabbcc",'$'));
        System.out.println(CharMatcher.is('a').replaceFrom("abc abc aabbcc","$%"));

    }

    @Test
    public void testTrim(){
        System.out.println(CharMatcher.whitespace().trimFrom(" aa   bb   cc  "));
        System.out.println(CharMatcher.whitespace().trimLeadingFrom(" aa   bb   cc  "));
        System.out.println(CharMatcher.whitespace().trimTrailingFrom(" aa   bb   cc  "));
        System.out.println(CharMatcher.whitespace().trimAndCollapseFrom(" aa   bb   cc  ",'$'));
        System.out.println(CharMatcher.whitespace().collapseFrom(" aa   bb   cc  ",'$'));
    }

    @Test
    public void testAnyOf(){
        String str = CharMatcher.anyOf("aa").or(CharMatcher.anyOf("abc")).or(CharMatcher.anyOf("bb"))
                .removeFrom("we aabmn abcccd bbac xyz bc w");
        System.out.println(str);
    }

}
