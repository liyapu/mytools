package com.lyp.learn.guava.base;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Joiner
 * 警告：joiner实例总是不可变的。用来定义joiner目标语义的配置方法总会返回一个新的joiner实例。这使得joiner实例都是线程安全的，
 * 你可以将其定义为static final常量。
 */

public class JoinerTest {

    public static final List<String> strList = Arrays.asList( "aa", "bb", "11", "22", "33");
    public static final List<String> strListWithNullValue = Arrays.asList( "aa", "bb",null, "11", "22", null,"33");
    public static final String [] strArr = new String [] {"aa", "bb", "11", "22", "33"};
    public static final String [] strArrWithNullValue = new String [] {"aa", "bb", null, "11", "22", null, "33"};
    public static final Map<String,String> strMap = ImmutableMap.of("aa","11","bb","22","cc","33");

    /**
     * 用分隔符把字符串序列连接起来也可能会遇上不必要的麻烦。
     * 如果字符串序列中含有null，那连接操作会更难
     */
    @Test
    public void test1(){
        Joiner joiner = Joiner.on(";").skipNulls();
        String join = joiner.join("aa", "bb", null, 11, 22, null, 33);
        System.out.println(join);
    }

    /**
     * useForNull(String)方法可以给定某个字符串来替换null，而不像skipNulls()方法是直接忽略null
     */
    @Test
    public void test2(){
        Joiner joiner = Joiner.on(";").useForNull("nullStr");
        String join = joiner.join("aa", "bb", null, 11, 22, null, 33);
        System.out.println(join);
    }

    /**
     * Joiner
     * 若没有指定skipNulls 或者 useForNull，拼接遇到null时，会抛出 NullPointerException
     */
    @Test
    public void test3(){
        Joiner joiner = Joiner.on(",");
        String join = joiner.join("aa", "bb", null, 11, 22, null, 33);
        System.out.println(join);
    }

    /**
     * append StringBuilder
     * @throws IOException
     */
    @Test
    public void test31() {
        Joiner joiner = Joiner.on(",").skipNulls();
        StringBuilder sb = new StringBuilder();
        sb.append("start").append(",");
        StringBuilder stringBuilder = joiner.appendTo(sb, "aa", "bb", null, 11, 22, null, 33);
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void test32() {
        Joiner joiner = Joiner.on(",").skipNulls();
        StringBuilder sb = new StringBuilder();
        sb.append("start").append(",");
        StringBuilder stringBuilder = joiner.appendTo(sb, "aa", "bb", null, 11, 22, null, 33);
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void test33() {
        Joiner joiner = Joiner.on(",").skipNulls();
        StringBuilder sb = new StringBuilder();
        sb.append("start").append(",");
        String [] arrs = new String [] {"aa", "bb", null, "11", "22", null, "33"};

        StringBuilder stringBuilder = joiner.appendTo(sb, arrs);
        System.out.println(stringBuilder.toString());

        System.out.println(sb == stringBuilder);
    }


    /**
     * 拼接数组,列表
     */
    @Test
    public void test4(){
        Joiner joiner = Joiner.on(";").skipNulls();
        String [] arrs = new String [] {"aa", "bb", null, "11", "22", null, "33"};
        String join = joiner.join(arrs);
        System.out.println(join);

        System.out.println("------------");
        List<Integer> integerList = Arrays.asList(11,22,null,33,44,null,55);
        System.out.println(joiner.join(integerList));
    }

    /**
     * 拼接 Set
     */
    @Test
    public void test5(){
        Joiner joiner = Joiner.on(";").skipNulls();
        Set<String> sets = new HashSet<>();
        sets.add("aa");
        sets.add(null);
        sets.add("bb");
        sets.add(null);
        sets.add("cc");

        String join = joiner.join(sets);
        System.out.println(join);
    }

    /**
     * 自己实现 skipNulls
     */
    @Test
    public void testSkipNullsImplement(){
        String str1 = strListWithNullValue.stream()
                .filter(w -> w != null && !w.isEmpty())
                .collect(Collectors.joining());

        System.out.println(str1);

        String str2 = strListWithNullValue.stream()
                .filter(w -> StringUtils.isNotBlank(w))
                .collect(Collectors.joining(";"));
        System.out.println(str2);

    }

    /**
     * 自己实现 useForNull
     */
    @Test
    public void testUseForNUllImplement(){
        String str1 = strListWithNullValue.stream()
                .map(w -> w == null || w.isEmpty() ? "null" : w)
                .collect(Collectors.joining());

        System.out.println(str1);

        String str2 = strListWithNullValue.stream()
                .map(w -> StringUtils.isBlank(w) ? "null" : w)
                .collect(Collectors.joining(";"));
        System.out.println(str2);

    }

    /**
     * 写进文件
     * appendTo
     */
    @Test
    public void testJoinerFileWriter1() throws Exception {
        String targetFileName = JoinerTest.class.getResource("/").getPath() + "aa.txt";
        try(FileWriter fileWriter = new FileWriter(new File(targetFileName))) {
            Joiner joiner = Joiner.on(";").skipNulls();

            joiner.appendTo(fileWriter,strList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * MapJoiner
     */
    @Test
    public void testMapJoiner(){
        String str1 = Joiner.on('#').withKeyValueSeparator("=").join(strMap);
        System.out.println(str1);
    }

}
