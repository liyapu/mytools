//package com.lyp.learn.utils;
//
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
///**
// * @author: liyapu
// * @description: 参数化的单元测试
// * @date 2019-10-16 20:53
// */
//
//@RunWith(Parameterized.class)
//public class MyBeanUtilsTest {
//
//    @Parameterized.Parameter(0)
//    public MyBeanUtils myBeanUtils;
//
//    //测试次数
//    private static List<Integer> testTimes = Arrays.asList(100,1000,10_000,100_000,1_000_000);
//    //测试结果 以 markdown 表格的形式输出
//    private static StringBuilder resultBuilder = new StringBuilder("|实现|100|1,000|10,000|100,000|1,000,000|\n")
//            .append("|-----|-----|-----|-----|-----|-----|\n");
//    //https://mp.weixin.qq.com/s/K0wbLpi4pLixxJR7gRNvew
//
//    @Parameterized.Parameters
//    public static Collection<Object[]> data(){
//        Collection<Object[]> params = new ArrayList<>();
////        params.add(new Object[])
//    }
//}
