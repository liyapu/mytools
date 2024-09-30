package com.lyp.learn.mockdata;

import com.lyp.learn.mockdata.bean.User;
import com.lyp.learn.mockdata.bean.UserPageResult;
import com.lyp.learn.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyapu
 * @date 2024-09-30 19:53
 * @description
 */
@Slf4j
public class UserService {

    private static List<User> userAllList = new ArrayList<>();

    static {
        for (int i = 1; i <= 50; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("user" + i);
            userAllList.add(user);
        }
    }


    public static List<User> getUserList(String name, Integer offset, Integer limit) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("getUserList InterruptedException");
        }
        List<User> tempResult = userAllList.stream()
//                .filter(s -> s.getName().contains(name))
                .collect(Collectors.toList());
        if (tempResult.size() > offset) {
            if (tempResult.size() > offset + limit) {
                tempResult = tempResult.subList(offset, offset + limit);
            } else {
                tempResult = tempResult.subList(offset, tempResult.size());
            }
        } else {
            tempResult = new ArrayList<>();
        }
        System.out.println("getUserList " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " name:" + name + " offset:" + offset + " limit:" + limit + " tempResult:" + JsonUtil.writeToString(tempResult));
        return tempResult;
    }

    public static List<User> getUserListThrowException(String name, Integer offset, Integer limit) throws Exception {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("getUserList InterruptedException");
        }
        List<User> tempResult = userAllList.stream()
//                .filter(s -> s.getName().contains(name))
                .collect(Collectors.toList());
        if (tempResult.size() > offset) {
            if (tempResult.size() > offset + limit) {
                tempResult = tempResult.subList(offset, offset + limit);
            } else {
                tempResult = tempResult.subList(offset, tempResult.size());
            }
        } else {
            tempResult = new ArrayList<>();
        }
        System.out.println("getUserListThrowException " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " name:" + name + " offset:" + offset + " limit:" + limit + " tempResult:" + JsonUtil.writeToString(tempResult));
        return tempResult;
    }

    public static UserPageResult getUserPageResultList(String name, Integer offset, Integer limit) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("getUserList InterruptedException");
        }
        List<User> tempResult = userAllList.stream()
//                .filter(s -> s.getName().contains(name))
                .collect(Collectors.toList());
        //总条数
        long total = tempResult.size();

        if (tempResult.size() > offset) {
            if (tempResult.size() > offset + limit) {
                tempResult = tempResult.subList(offset, offset + limit);
            } else {
                tempResult = tempResult.subList(offset, tempResult.size());
            }
        } else {
            tempResult = new ArrayList<>();
        }
        UserPageResult result = new UserPageResult();
        result.setCode(0);
        result.setMsg("success");

        UserPageResult.UserPageInfo userPageInfo = new UserPageResult.UserPageInfo();
        userPageInfo.setTotal(total);
        userPageInfo.setUserList(tempResult);

        result.setData(userPageInfo);
        System.out.println("getUserPageResultList " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " name:" + name + " offset:" + offset + " limit:" + limit + " currentThread:" + Thread.currentThread().getName() + " result:" + JsonUtil.writeToString(result));
        return result;
    }


}
