package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class SerializeDemo {
    public static void main(String[] args) {
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");
        guestUser.setAge(20);
        guestUser.setAddress("河南省");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");
        rootUser.setAge(30);
        rootUser.setAddress("北京");

        List<User> userList = new ArrayList<>();
        userList.add(guestUser);
        userList.add(rootUser);

        Set<User> userSet = new HashSet<>();
        userSet.add(guestUser);
        userSet.add(rootUser);

        group.setUsers(userList);

        /**
         * 将Java对象序列化为JSON字符串，支持各种各种Java基本类型和JavaBean
         */
        String guestUserJsonStr = JSON.toJSONString(guestUser);
        System.out.println("guestUserJsonStr is :  " + guestUserJsonStr);
        System.out.println();

        String userListJsonStr = JSON.toJSONString(userList);
        System.out.println("userListJsonStr is :  " + userListJsonStr);
        System.out.println();

        String userSetJsonStr = JSON.toJSONString(userSet);
        System.out.println("userSetJsonStr is : " + userSetJsonStr);
        System.out.println();

        String groupJsonStr = JSON.toJSONString(group);
        System.out.println("groupJsonStr is ： " + groupJsonStr);
        System.out.println();

        //格式化JSON缩进
        String groupJsonStr2 = JSON.toJSONString(group,true);
        System.out.println("groupJsonStr2 is ： " + groupJsonStr2);
        System.out.println();

        User user2 = new User();
        user2.setId(100L);
        user2.setName("张三");

        System.out.println("--------java Bean 与 JSONObject 转换，null 值过滤");
        JSONObject userJson2 =  JSON.parseObject(JSON.toJSONString(user2));
        Map<String,Object> result2 = new HashMap<>();
        System.out.println(userJson2.size());
        System.out.println(userJson2);
        Set<String> keys = userJson2.keySet();
        for(String s : keys){
            result2.put(s,userJson2.get(s));
        }
        System.out.println(result2);
        System.out.println();

        System.out.println("--------java Bean 与 JSONObject 转换，null 值不过滤");
        JSONObject userJson22 = (JSONObject) JSON.toJSON(user2);
        Map<String,Object> result22 = new HashMap<>();
        System.out.println(userJson22.size());
        System.out.println(userJson22);
        Set<String> keys2 = userJson22.keySet();
        for(String s : keys2){
            result22.put(s,userJson22.get(s));
        }
        System.out.println(result22);
        System.out.println();

    }

}
