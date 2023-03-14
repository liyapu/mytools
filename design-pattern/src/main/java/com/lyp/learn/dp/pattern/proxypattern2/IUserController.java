package com.lyp.learn.dp.pattern.proxypattern2;

/**
 * @author liyapu
 * @date 2023-03-11 21:50
 * @description
 */
public interface IUserController {

    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);
}
