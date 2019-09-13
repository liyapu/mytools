package com.lyp.learn.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class SimplePropertyPreFilterDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setId(100L);
        user.setName("李思");
        user.setAge(68);
        user.setAddress("北京");
        SimplePropertyPreFilter mySimplePropertyPreFilter = new SimplePropertyPreFilter(User.class);
        System.out.println(JSON.toJSONString(user, mySimplePropertyPreFilter));
        System.out.println(mySimplePropertyPreFilter.getClazz().getName());
        System.out.println(mySimplePropertyPreFilter.getMaxLevel());
        System.out.println(mySimplePropertyPreFilter.getExcludes());
        System.out.println(mySimplePropertyPreFilter.getIncludes());
        System.out.println("----------------------");

        User user2 = new User();
        user2.setId(100L);
        user2.setName("李思");
        user2.setAge(68);
        user2.setAddress("北京");
        SimplePropertyPreFilter mySimplePropertyPreFilter2 = new SimplePropertyPreFilter(User.class);
        mySimplePropertyPreFilter2.getExcludes().add("id");
        mySimplePropertyPreFilter2.getExcludes().add("address");
        System.out.println(JSON.toJSONString(user2, mySimplePropertyPreFilter2));
        System.out.println(mySimplePropertyPreFilter2.getClazz().getName());
        System.out.println(mySimplePropertyPreFilter2.getMaxLevel());
        System.out.println(mySimplePropertyPreFilter2.getExcludes());
        System.out.println(mySimplePropertyPreFilter2.getIncludes());
        System.out.println("------------------------");

        SimplePropertyPreFilter mySimplePropertyPreFilter3 = new SimplePropertyPreFilter(User.class);
        mySimplePropertyPreFilter3.getIncludes().add("id");
        mySimplePropertyPreFilter3.getIncludes().add("address");
        System.out.println(JSON.toJSONString(user2, mySimplePropertyPreFilter3));
        System.out.println(mySimplePropertyPreFilter3.getClazz().getName());
        System.out.println(mySimplePropertyPreFilter3.getMaxLevel());
        System.out.println(mySimplePropertyPreFilter3.getExcludes());
        System.out.println(mySimplePropertyPreFilter3.getIncludes());
        System.out.println("------------------------");

        SimplePropertyPreFilter mySimplePropertyPreFilter4 = new SimplePropertyPreFilter(User.class,"id","name","age");
        mySimplePropertyPreFilter4.getExcludes().add("age");
        System.out.println(JSON.toJSONString(user2, mySimplePropertyPreFilter4));
        System.out.println(mySimplePropertyPreFilter4.getClazz().getName());
        System.out.println(mySimplePropertyPreFilter4.getMaxLevel());
        System.out.println(mySimplePropertyPreFilter4.getExcludes());
        System.out.println(mySimplePropertyPreFilter4.getIncludes());
        System.out.println("------------------------");

    }
}
