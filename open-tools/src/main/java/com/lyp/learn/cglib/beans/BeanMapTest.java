package com.lyp.learn.cglib.beans;

import com.lyp.learn.cglib.bean.User;
import net.sf.cglib.beans.BeanMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-21 16:17
 */
public class BeanMapTest {

    /**
     * 实体类到Map
     */
    @Test
    public void test01(){
        User user = new User();
        user.setId(100);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("河南省 商丘市");
        user.setTelephone("13566668888");
        user.setHeight(170);
        user.setWeight(62);

        BeanMap beanMap = BeanMap.create(user);
        System.out.println(beanMap);
        Object address = beanMap.get("address");
        System.out.println(address);
        Object age = beanMap.get("age");
        System.out.println(age);
    }

    /**
     * Map赋值到Bean
     */
    @Test
    public void test02(){
        User user1 = new User();
        BeanMap beanMap = BeanMap.create(user1);

        Map<String,Object> userMap = new HashMap<>();
        userMap.put("id",1);
        userMap.put("name","张三");
        userMap.put("age",68);
        userMap.put("address","河南省 商丘市");
        userMap.put("telephone","13566668888");
        userMap.put("height",175);
        userMap.put("weight",62);
        beanMap.putAll(userMap);

        System.out.println(user1);

    }
}
