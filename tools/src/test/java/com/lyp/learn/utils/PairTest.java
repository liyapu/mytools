package com.lyp.learn.utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: liyapu
 * @description:
 * @date 2019-11-27 19:45
 */
class PairTest {

    @Test
    public void test1(){
        Pair<Integer, String> aa = Pair.of(2, "aa");
        System.out.println(aa);
    }

    @Test
    public void test2(){
        List<Pair<Integer,String>> list = new ArrayList<>();
        list.add(Pair.of(1,"aa"));
        list.add(Pair.of(2,"bb"));
        list.add(Pair.of(3,"cc"));
        System.out.println(list);
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void test3(){
        List<Pair<String,String>> list = new ArrayList<>();
        list.add(Pair.of("1","aa"));
        list.add(Pair.of("2","bb"));
        list.add(Pair.of("3","cc"));
        System.out.println(list);
        System.out.println(JSONObject.toJSONString(list));
    }


    @Test
    public void testThree(){
        Three<Integer, String, String> of = Three.of(8, "八百里", "很长的一段路");
        System.out.println(of);
    }

}