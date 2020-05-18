package com.lyp.learn.pk7;

import org.junit.jupiter.api.Test;

/**
 * String 的基本使用: 体现String 的不可变性
 */
public class StringTest1 {

    @Test
    public void test1() {
        // 字面量定义的方式，"abc" 存储在字符串常量池中
        String s1 = "abc";
        String s2 = "abc";

        System.out.println(s1 == s2); // 判断地址
        System.out.println(s1);
        System.out.println(s2);
    }

    //字符串拼接操作
    // 可以查看 target 下的 编译后的文件
    @Test
    public void test2() {
        String s1 = "a" + "b" + "c"; // 编译期优化:等同于 "abc"
        String s2 = "abc"; // "abc"一定是放在字符串常量池中，将此地址赋给s2
        /**
         * 最终 .java 编译成 .class,再执行 .class
         * String s1 = "abc"
         * String s2 = "abc"
         */
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }

    @Test
    public void test3() {
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop"; //  编译期优化

        //如果拼接符号的前后出现了变量，则相当于在堆空间中 new String(),具体的内容为拼接的的结果: javaEEhadoop
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);
        System.out.println(s3 == s7);
        System.out.println();

        System.out.println(s5 == s6);
        System.out.println(s5 == s7);
        System.out.println(s6 == s7);
        System.out.println();

        //intern(): 判断字符串常量池中是否存在javaEEhadoop值
        //          如果存在，则返回常量池中 javaEEhadoop 的地址
        //          如果字符串常量池中不存在javaEEhadoop，则在常量池中加载一份javaEEhadoop，并返回此对象的地址
        String s8 = s6.intern();
        System.out.println(s3 == s8);
    }

    /**
     * 如下的 s1 + s2 的执行细节:
     * ① StringBuilder s = new StringBuilder();
     * ② s.append("a);
     * ③ s.append("b);
     * ④ s.toString() ---> 约等于 new String("ab)
     *
     * 一个 Sting 对象，底层new 了一个 StringBuilder 和 一个 String 对象
     * <p>
     * 具体字节码
     * 0 ldc #16 <a>
     * 2 astore_1
     * 3 ldc #17 <b>
     * 5 astore_2
     * 6 ldc #18 <ab>
     * 8 astore_3
     * 9 new #10 <java/lang/StringBuilder>
     * 12 dup
     * 13 invokespecial #11 <java/lang/StringBuilder.<init>>
     * 16 aload_1
     * 17 invokevirtual #12 <java/lang/StringBuilder.append>
     * 20 aload_2
     * 21 invokevirtual #12 <java/lang/StringBuilder.append>
     * 24 invokevirtual #13 <java/lang/StringBuilder.toString>
     * 27 astore 4
     * 29 getstatic #3 <java/lang/System.out>
     * 32 aload_3
     * 33 aload 4
     * 35 if_acmpne 42 (+7)
     * 38 iconst_1
     * 39 goto 43 (+4)
     * 42 iconst_0
     * 43 invokevirtual #4 <java/io/PrintStream.println>
     * 46 return
     */
    @Test
    public void test4() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;

        System.out.println(s3 == s4);
    }

    /**
     * 字符串拼接操作不一定使用的是 StringBuilder
     * 如果拼接符号左右两边都是字符串常量或常量引用，则任然使用编译期优化，即非StringBuilder的方式
     * <p>
     * 针对于 final修饰类、方法、基本数据类型、引用数据类型的量的结构时，能使用上final的时候建议使用上
     */
    @Test
    public void test5() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;

        System.out.println(s3 == s4);
    }

    /**
     *  体会执行效率: 通过 StringBuilder的append()的方式添加字符串的效率要远高于使用String的字符串拼接方式
     *  详情:
     *      ① StringBuilder的append()的方式：自始至终只创建了一个StringBuilder的对象
     *         使用 String的字符串拼接方式: 创建了多个StringBuilder和String对象，
     *                                   比如执行了 10万次，创建了 10万个StringBuilder和10万个String,并且刚创建完，使用一下就立即不用了
     *
     *      ② 使用String的字符串拼接方式: 内存中由于创建了较多的 StringBuilder和String 对象，内存占用更大，如果进行了GC,需要花费额外的时间
     *
     *  改进的空间: 在实际开发中，如果基本确定要前前后后添加的字符串长度不高于某个限定值 highLevel的情况下，建议使用指定大小的构造函数实例化，省去了每次扩容，赋值元素的浪费
     *             StringBuilder s = new StringBuilder(highLevel); // new char[highLevel];
     *
     *
     */
    @Test
    public void test6() {
        String s = "";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            s += "a";
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间 ： " + (end - start) + " ms "); // 4222 ms
    }

    @Test
    public void test7() {
        StringBuilder s = new StringBuilder();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            s.append("a");
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间 ： " + (end - start) + " ms "); // 3 ms
    }

}
















