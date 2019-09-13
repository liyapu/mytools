package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

public class EmptyJson {
    public static void main(String[] args) {
        Item item = new Item();
        item.setSum(100);
        item.setMin(5);
        item.setAddress("河南省");
        item.setHigh(true);
        item.setGirl(true);
        item.setColor11(Color.GREEN);

        User user11L = new User(11L,"张一",111,"北京市");
        User user22L = new User();

        List<User> userList11 = new ArrayList<>();
        List<User> userList22 = new ArrayList<>();
        userList11.add(user11L);
        userList22.add(user22L);
        item.setUserList11(userList11);
        item.setUserList22(userList22);

        User user11S = new User(111L,"张一S",111,"北京市S");
        User user22S = new User();
        Set<User> setUser11 = new HashSet<>();
        Set<User> setUser22 = new HashSet<>();
        setUser11.add(user11S);
        setUser22.add(user22S);
        item.setSetUser11(setUser11);
        item.setSetUser22(setUser22);

        Map<String,String> map11 = new HashMap<>();
        Map<String,String> map22 = new HashMap<>();
        map11.put("key11","value11");
        map11.put("key111","value111");
        map11.put("key1111","");
        map11.put("key11111",null);
        //map11.put("","");
        //map11.put(null,"");
        item.setMap11(map11);
        item.setMap22(map22);

        //默认输出
        String jsonString = JSON.toJSONString(item);
        System.out.println("默认输出 : " + jsonString);
        //将Boolean类型字段的空值输出为false
        jsonString = JSON.toJSONString(item,SerializerFeature.WriteNullBooleanAsFalse);
        System.out.println("将Boolean类型字段的空值输出为false : " + jsonString);

        //将数值类型字段的空值输出为0
        jsonString = JSON.toJSONString(item,SerializerFeature.WriteNullNumberAsZero);
        System.out.println("将数值类型字段的空值输出为0 : " + jsonString);

        //将字符串类型字段的空值输出为空字符串 ""
        jsonString = JSON.toJSONString(item,SerializerFeature.WriteNullStringAsEmpty);
        System.out.println("将字符串类型字段的空值输出为空字符串 \"\" : " + jsonString);

        //用枚举name()输出
        jsonString = JSON.toJSONString(item,SerializerFeature.WriteEnumUsingName);
        System.out.println("用枚举name()输出 : " + jsonString);

        //用枚举toString()值输出
        jsonString = JSON.toJSONString(item,SerializerFeature.WriteEnumUsingToString);
        System.out.println("用枚举toString()值输出 : " + jsonString);

        //指定特性输出
        jsonString = JSON.toJSONString(item,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteEnumUsingName,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNonStringKeyAsString,
                SerializerFeature.WriteNonStringValueAsString
                 );
        System.out.println("指定特性输出 : " + jsonString);

    }
}
