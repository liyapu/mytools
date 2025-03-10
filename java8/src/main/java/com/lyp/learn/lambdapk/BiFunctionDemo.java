package com.lyp.learn.lambdapk;

import cn.hutool.json.JSONUtil;
import com.lyp.learn.outer.User;
import com.lyp.learn.outer.UserService;
import com.lyp.learn.tools.QueryUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author liyapu
 * @date 2025-03-10 20:18
 * @description
 */
public class BiFunctionDemo {
    private static final Integer PAGE_SIZE = 5;

    @Test
    public void test01() {
        BiFunction<Long, Integer, List<User>> onceQueryFunc = (minId, limit) -> UserService.getUserListByMinId(minId, limit);
        List<User> users = QueryUtil.queryByMinIdAsc(NumberUtils.LONG_ZERO,
                PAGE_SIZE,
                onceQueryFunc,
                User::getId)
                .stream()
                .collect(Collectors.toList());

        System.out.println(JSONUtil.toJsonStr(users));
    }

    @Test
    public void test02() {
        String word = "6";
        BiFunction<Long, Integer, List<User>> onceQueryFunc = (minId, limit) -> UserService.getUserListByMinIdWord(word, minId, limit);
        List<User> users = QueryUtil.queryByMinIdAsc(NumberUtils.LONG_ZERO,
                PAGE_SIZE,
                onceQueryFunc,
                User::getId)
                .stream()
                .collect(Collectors.toList());

        System.out.println(JSONUtil.toJsonStr(users));
    }
}
