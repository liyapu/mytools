package com.lyp.learn.mockdata.bean;

import lombok.Data;

import java.util.List;

/**
 * @author liyapu
 * @date 2024-09-30 20:40
 * @description 用户分页结果对象
 */
@Data
public class UserPageResult {
    private int code;
    private String msg;
    private UserPageInfo data;

    @Data
    public static class UserPageInfo {
        private long total;
        private List<User> userList;
    }
}
