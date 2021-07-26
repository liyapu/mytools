package com.lyp.learn.mapstruct;

import com.alibaba.fastjson.JSONObject;
import com.lyp.learn.mapstruct.bean.User;
import com.lyp.learn.mapstruct.bean.UserEnum;
import com.lyp.learn.mapstruct.covert.UserCovertBasic;
import com.lyp.learn.mapstruct.enums.UserTypeEnum;
import com.lyp.learn.mapstruct.vo.UserVO1;
import com.lyp.learn.mapstruct.vo.UserVO2;
import com.lyp.learn.mapstruct.vo.UserVO3;
import com.lyp.learn.mapstruct.vo.UserVO4;
import com.lyp.learn.mapstruct.vo.UserVO5;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2021-07-26 15:32
 * @desc
 */
@Slf4j
public class UserCovertBasicTest {


    public static User getUser(){
        User user = User.builder()
                .id(1)
                .name("张三")
                .createTime("2020-04-01 11:05:07")
                .updateTime(LocalDateTime.now())
                .build();
        return user;
    }

    public static UserEnum getUserEnum(){
        UserEnum userEnum = UserEnum.builder()
                .id(100)
                .name("李四")
                .userTypeEnum(UserTypeEnum.DB)
                .build();
        return userEnum;
    }

    @Test
    public void testToConvertVO1(){
        User user = getUser();
        UserVO1 userVO1 = UserCovertBasic.INSTANCE.toConvertVO1(user);
        System.out.printf("userVO1====");

        log.info("userVO1========{}", JSONObject.toJSONString(userVO1));
    }

    @Test
    public void testFromConvertEntity1(){
        User user = getUser();
        UserVO1 userVO1 = UserCovertBasic.INSTANCE.toConvertVO1(user);
        User userCopy = UserCovertBasic.INSTANCE.fromConvertEntity1(userVO1);
        log.info("userCopy========={}",JSONObject.toJSONString(userCopy));
    }


    @Test
    public void testToConvertVO2(){
        User user = getUser();
        UserVO2 userVO2 = UserCovertBasic.INSTANCE.toConvertVO2(user);
        log.info("userVO2========={}",JSONObject.toJSONString(userVO2));
    }

    @Test
    public void testToConvertVO3(){
        User user = getUser();
        UserVO3 userVO3 = UserCovertBasic.INSTANCE.toConvertVO3(user);
        log.info("userVO3========={}",JSONObject.toJSONString(userVO3));
    }

    @Test
    public void testFromConvertEntity3(){
        User user = getUser();
        UserVO3 userVO3 = UserCovertBasic.INSTANCE.toConvertVO3(user);
        User userCopy = UserCovertBasic.INSTANCE.fromConvertEntity3(userVO3);
        log.info("userCopy========={}",JSONObject.toJSONString(userCopy));
    }


    @Test
    public void testToConvertVO4(){
        User user = getUser();
        UserVO4 userVO4 = UserCovertBasic.INSTANCE.toConvertVO4(user);
        log.info("userVO4========={}",JSONObject.toJSONString(userVO4));
    }

    @Test
    public void testToConvertVO5(){
        UserEnum userEnum = getUserEnum();
        UserVO5 userVO5 = UserCovertBasic.INSTANCE.toConvertVO5(userEnum);
        log.info("userVO5========={}",JSONObject.toJSONString(userVO5));
    }

    @Test
    public void testFromConvertEntity5(){
        UserEnum userEnum = getUserEnum();
        UserVO5 userVO5 = UserCovertBasic.INSTANCE.toConvertVO5(userEnum);
        UserEnum userEnumCopy = UserCovertBasic.INSTANCE.fromConvertEntity5(userVO5);
        log.info("userEnumCopy========={}",JSONObject.toJSONString(userEnumCopy));

    }

}
