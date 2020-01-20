package com.lyp.learn.easyexcel.read;

import lombok.Data;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-20 17:35
 */
@Data
public class User {
    //用户姓名	手机号	登录密码	用户标签	医院名称
    private String name;
    private String phone;
    private Integer password;
    private String userTag;
    private String hospitalName;
}
