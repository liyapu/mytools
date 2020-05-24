package com.lyp.learn.pk7;

/**
 *  题目:
 *     new String("a") 会创建几个对象？
 *       看字节码，就知道是两个
 *
 *       一个对象是: new 关键字在堆空间创建的
 *       另一个对象是: 字符串常量池中的对象(ab)，字节码指令 ldc 从字符串常量池中 加载的 a
 *
 *        0 new #2 <java/lang/String>
 *        3 dup
 *        4 ldc #3 <ab>
 *        6 invokespecial #4 <java/lang/String.<init>>
 *        9 astore_1
 *       10 return
 *
 *
 *   思考:
 *     new String("a") + new String("b") 呢?
 *
 *     字符串拼接操作，底层会使用 StringBuilder()
 *     对象1: new StringBuilder()
 *     对象2: new String("a")
 *     对象3: 常量池中的"a"
 *     对象4: new String("b")
 *     对象5: 常量池中的"b"
 *
 *     深入剖析: StringBuilder()的toString()方法
 *              对象6: new String("ab")
 *              强调一下: toString() 的调用，在字符串常量池中，没有生成 "ab"
 *
 *
 *     a
 *     StringBuilder().append(a)
 *     b
 *     StringBuilder().append(b)
 *
 *     StringBuilder.toString()
 *
 *  0 new #2 <java/lang/StringBuilder>
 *  3 dup
 *  4 invokespecial #3 <java/lang/StringBuilder.<init>>
 *  7 new #4 <java/lang/String>
 * 10 dup
 * 11 ldc #5 <a>
 * 13 invokespecial #6 <java/lang/String.<init>>
 * 16 invokevirtual #7 <java/lang/StringBuilder.append>
 * 19 new #4 <java/lang/String>
 * 22 dup
 * 23 ldc #8 <b>
 * 25 invokespecial #6 <java/lang/String.<init>>
 * 28 invokevirtual #7 <java/lang/StringBuilder.append>
 * 31 invokevirtual #9 <java/lang/StringBuilder.toString>
 * 34 astore_1
 * 35 getstatic #10 <java/lang/System.out>
 * 38 aload_1
 * 39 invokevirtual #11 <java/io/PrintStream.println>
 * 42 return
 *
 */
public class StringNewTest {
    public static void main(String[] args) {
//        String str = new String("ab");

        String str = new String("a") + new String("b");
        System.out.println(str);
    }
}
