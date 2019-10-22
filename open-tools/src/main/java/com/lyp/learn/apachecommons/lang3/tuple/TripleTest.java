package com.lyp.learn.apachecommons.lang3.tuple;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 20:31
 */
public class TripleTest {

    @Test
    public void test01(){
        Triple<String,String,String> triple = MutableTriple.of("A","Aa","a");
        System.out.println(triple.getLeft());
        System.out.println(triple.getRight());
        System.out.println(triple.getMiddle());
    }

    /**
     * 方法参数，多个返回值
     */
    @Test
    public void test02(){
        Triple<String, Integer, String> triple = methodReturn();
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());
    }

    public static Triple<String,Integer,String> methodReturn(){
        Triple<String,Integer,String> triple = ImmutableTriple.of("第一行",11,"第一列");
        return triple;
    }
}
