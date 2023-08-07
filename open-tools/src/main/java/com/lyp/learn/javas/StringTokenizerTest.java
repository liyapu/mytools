package com.lyp.learn.javas;

import org.junit.jupiter.api.Test;

import java.util.StringTokenizer;

/**
 * @author liyapu
 * @date 2023-08-07 19:43
 * @description StringTokenizer的用法
 *
 * Java StringTokenizer 属于 java.util 包，用于分隔字符串。
 * StringTokenizer 构造方法：
 * StringTokenizer(String str) ：构造一个用来解析 str 的 StringTokenizer 对象。java 默认的分隔符是空格("")、制表符(\t)、换行符(\n)、回车符(\r)。
 * StringTokenizer(String str, String delim) ：构造一个用来解析 str 的 StringTokenizer 对象，并提供一个指定的分隔符。
 * StringTokenizer(String str, String delim, boolean returnDelims) ：构造一个用来解析 str 的 StringTokenizer 对象，并提供一个指定的分隔符，同时，指定是否返回分隔符。
 *
 *
 * StringTokenizer 常用方法：
 * int countTokens()：返回nextToken方法被调用的次数。
 * boolean hasMoreTokens()：返回是否还有分隔符。
 * boolean hasMoreElements()：判断枚举 （Enumeration） 对象中是否还有数据。
 * String nextToken()：返回从当前位置到下一个分隔符的字符串。
 * Object nextElement()：返回枚举 （Enumeration） 对象的下一个元素。
 * String nextToken(String delim)：与 4 类似，以指定的分隔符返回结果。
 */
public class StringTokenizerTest {

    @Test
    public void test01() {
        StringTokenizer st = new StringTokenizer("a   b     c\td\te\nf\ng\rh\ri\r\nj\n\rk  lmn.");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

    @Test
    public void test02() {
        StringTokenizer st = new StringTokenizer("a   b     c\td\te\nf\ng\rh\ri\r\nj\n\rk  lmn.", "");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

    @Test
    public void test03() {
        StringTokenizer st = new StringTokenizer("a   b     c\td\te\nf\ng\rh\ri\r\nj\n\rk  lmn.", "", true);
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

    @Test
    public void test04() {
        String s = new String("The=Java=platform=is=the=ideal=platform=for=network=computing");
        // 分词器构造函数三个参数，
        // 第一个是待分隔的字符串，
        // 第二个为分隔字符串，以字符为分隔单位（比如the，可能匹配到e，就会分隔），
        // 第三个参数说明是否要把分割字符串作为标记返回
        StringTokenizer st = new StringTokenizer(s, "the", true);
        System.out.println("Token Total:" + st.countTokens());
        while (st.hasMoreElements()) {
            System.out.println(st.nextToken());
        }
    }

    /**
     * 注意，StringTokenizer的分隔符不需要使用转义字符
     *
     * 与split的区别
     *
     * String.Split（）使用正则表达式，而StringTokenizer的只是使用逐字分裂的字符。
     * 如果不用正则表达式（StringTokenizer也不能使用正则表达式），StringTokenizer在截取字符串中的效率最高。
     */
    @Test
    public void test05() {
        String str = "100|66,55:200||||567,90:102|43,54";

        StringTokenizer strToke = new StringTokenizer(str, ":,|");// 默认不打印分隔符
//        StringTokenizer strToke = new StringTokenizer(str, ":,|", true);//打印分隔符
//        StringTokenizer strToke = new StringTokenizer(str, ":,|", false);//不打印分隔符
        while (strToke.hasMoreTokens()) {
            System.out.println(strToke.nextToken());
        }
    }
}
