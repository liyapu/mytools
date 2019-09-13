package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Set;

public class DeserializeDemo {
    public static void main(String[] args) {

        /**
         * 将JSON字符串反序列化为JavaBean
         */
        String guestUserJsonStr = "{\"address\":\"河南省\",\"age\":20,\"id\":2,\"name\":\"guest\"}";
        User guestUser = JSON.parseObject(guestUserJsonStr, User.class);
        System.out.println(guestUser);

        String groupJsonStr = "{\"id\":0,\"name\":\"admin\",\"users\":" +
                "[{\"address\":\"河南省\",\"age\":20,\"id\":2,\"name\":\"guest\"}," +
                "{\"address\":\"北京\",\"age\":30,\"id\":3,\"name\":\"root\"}]}";
        Group group = JSON.parseObject(groupJsonStr,Group.class);
        System.out.println(group);

        System.out.println();

        /**
         * 将JSON字符串反序列化为泛型类型的JavaBean
         */
        String userListJsonStr = "[{\"address\":\"河南省\",\"age\":20,\"id\":2,\"name\":\"guest\"}," +
                "{\"address\":\"北京\",\"age\":30,\"id\":3,\"name\":\"root\"}]";
        List<User> userList = JSON.parseObject(userListJsonStr,new TypeReference<List<User>>(){});
        System.out.println(userList);

        List<User> userList2 = JSON.parseArray(userListJsonStr, User.class);
        System.out.println(userList2);

        String userSetJsonStr = "[{\"address\":\"北京\",\"age\":30,\"id\":3,\"name\":\"root\"}," +
                "{\"address\":\"河南省\",\"age\":20,\"id\":2,\"name\":\"guest\"}]";
        Set<User> userSet = JSON.parseObject(userSetJsonStr,new TypeReference<Set<User>>(){});
        System.out.println(userSet);

    }
}
