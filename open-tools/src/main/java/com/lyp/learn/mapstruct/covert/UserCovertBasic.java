package com.lyp.learn.mapstruct.covert;

import com.lyp.learn.mapstruct.bean.User;
import com.lyp.learn.mapstruct.bean.UserEnum;
import com.lyp.learn.mapstruct.vo.UserVO1;
import com.lyp.learn.mapstruct.vo.UserVO2;
import com.lyp.learn.mapstruct.vo.UserVO3;
import com.lyp.learn.mapstruct.vo.UserVO4;
import com.lyp.learn.mapstruct.vo.UserVO5;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserCovertBasic {
    /**
     * 接口中声明了一个成员变量INSTANCE，目的是让客户端可以访问 Mapper 接口的实现。
     */
    UserCovertBasic INSTANCE = Mappers.getMapper(UserCovertBasic.class);

    /**
     * 字段数量类型数量相同，利用工具BeanUtils也可以实现类似效果
     * @param source
     * @return
     */
    UserVO1 toConvertVO1(User source);

    User fromConvertEntity1(UserVO1 userVO1);


    /**
     * 字段数量类型相同,数量少：仅能让多的转换成少的，故没有fromConvertEntity2
     * @param source
     * @return
     */
    UserVO2 toConvertVO2(User source);

    /**
     * target 中的反编译代码可以看出，编译时使用了expression中定义的表达式对目标字段 createTime 进行了转换；然后你还会发现 updateTime 字段也被自动从 LocalDateTime 类型转换成了 String 类型。
     *
     * 阿淼小结：
     *    当字段类型不一致时，以下的类型之间是 mapstruct 自动进行类型转换的:
     *       1、基本类型及其他们对应的包装类型。
     *             此时 mapstruct 会自动进行拆装箱。不需要人为的处理
     *       2、基本类型的包装类型和string类型之间
     * @param source
     * @return
     */
    @Mappings({
                      @Mapping(target = "createTime", expression = "java(com.lyp.learn.mapstruct.util.DateTransform.strToDate(source.getCreateTime()))"),
              })
    UserVO3 toConvertVO3(User source);

    User fromConvertEntity3(UserVO3 userVO3);

    /**
     * 通过定义表达式来进行指定转换
     * @param source
     * @return
     */
    @Mappings({
                      @Mapping(source = "id", target = "userId"),
                      @Mapping(source = "name", target = "userName")
              })
    UserVO4 toConvertVO4(User source);

    User fromConvertEntity4(UserVO4 userVO4);

    /**
     * 我们定义的接口还是照常定义，不会受到它是枚举就有所变化：
     * @param source
     * @return
     */
    @Mapping(source = "userTypeEnum", target = "type")
    UserVO5 toConvertVO5(UserEnum source);

    UserEnum fromConvertEntity5(UserVO5 userVO5);
}