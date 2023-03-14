package com.lyp.learn.dp.pattern.proxypattern2;

/**
 * @author liyapu
 * @date 2023-03-11 21:57
 * @description
 */
public class UserController implements IUserController {
    //...省略其他属性和方法...

    @Override
    public UserVo login(String telephone, String password) {
        //...省略login逻辑...
        //...返回UserVo数据...
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        //...省略register逻辑...
        //...返回UserVo数据...
        return null;
    }

}