package com.lyp.learn.collectionpk;



import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class DiamondOperatorTest {

    @Test
    public void testDiamondOperator(){
        diamondOperator();
    }

    public void diamondOperator(){
        //创建一个继承于HashSet的匿名子类的对象
//        Set<String> set = new HashSet<>(){};//编译通过
//        Set<String> set = new HashSet<>(){
//            {  // 匿名内部类中的 代码块
//                add("aa");
//                add("bb");
//            }
//        };//编译通过
        Set<String> set = new HashSet<>();//编译通过
        set.add("MM");
        set.add("JJ");
        set.add("GG");
        set.add("DD");

        for(String s : set){
            System.out.println(s);
        }

    }
}
