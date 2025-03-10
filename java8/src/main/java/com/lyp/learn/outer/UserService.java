package com.lyp.learn.outer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author liyapu
 * @date 2025-03-10 19:58
 * @description
 */
public class UserService {

    static Set<User> userSet = new HashSet<>();
    static List<User> userList = new ArrayList<>();

    static {
        LongStream.rangeClosed(1L, 100L)
                .forEach(i -> {
                    User user = new User();
                    user.setId(i);
                    user.setName("user" + i);
                    userSet.add(user);
                    userList.add(user);
                });

    }


    public static List<User> getUserListByMinId(Long minId, Integer limit) {
        return userList.stream()
                .filter(user -> user.getId() > minId)
                .limit(limit)
                .collect(Collectors.toList());

    }

    public static List<User> getUserListByMinIdWord(String word, Long minId, Integer limit) {
        return userList.stream()
                .filter(user -> user.getName().contains(word))
                .filter(user -> user.getId() > minId)
                .limit(limit)
                .collect(Collectors.toList());
    }

}
