package com.lyp.learn.mapstruct.covert;

import com.lyp.learn.mapstruct.bean.Person;
import com.lyp.learn.mapstruct.dto.PersonDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonConverter {
    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    /**
     * 写一个 Mapper 接口 PersonConverter，其中两个方法，一个是单实体映射，另一个是List映射
     *
     * 若源对象属性与目标对象属性名字一致，会自动映射对应属性，
     * 不一样的需要指定，也可以用 format 转成自己想要的类型，也支持表达式的方式，
     * 可以看到像 id、name、email这些名词一致的我并没有指定 source-target，
     * 而birthday-birth指定了，转换格式的birthDateFormat 加了dateFormat 或者 birthExpressionFormat 加了 expression，
     * 如果某个属性你不想映射，可以加个 ignore=true
     *
     * @return
     */
    @Mappings({
                      @Mapping(source = "birthday", target = "birth"),
                      @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
                      @Mapping(target = "birthExpressionFormat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
                      @Mapping(source = "dog.age", target = "age"),
                      @Mapping(target = "email", ignore = true)
              })
    PersonDTO domain2dto(Person person);

    List<PersonDTO> domain2dto(List<Person> people);
}
