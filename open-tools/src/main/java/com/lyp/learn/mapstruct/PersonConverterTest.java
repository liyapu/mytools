package com.lyp.learn.mapstruct;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lyp.learn.mapstruct.bean.Dog;
import com.lyp.learn.mapstruct.bean.Person;
import com.lyp.learn.mapstruct.covert.PersonConverter;
import com.lyp.learn.mapstruct.dto.PersonDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2021-07-26 16:29
 * @desc
 */
@Slf4j
public class PersonConverterTest {

    public static Person getPerson(){
        Person person = Person.builder()
                .id(88L)
                .name("王五")
                .email("234@qq.com")
                .birthday(new Date())
                .dog(new Dog(10))
                .build();
        return person;
    }

    public static Person getPerson2(){
        Person person = Person.builder()
                .id(66L)
                .name("赵六")
                .email("666@qq.com")
                .birthday(new Date())
                .dog(new Dog(18))
                .build();
        return person;
    }


    @Test
    public void testDomain2dto(){
        Person person = getPerson();
        PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
        log.info("personDTO========={}", JSONObject.toJSONString(personDTO));
    }

    @Test
    public void testDomain2dtoList(){
        List<Person> personList = new ArrayList<>();
        personList.add(getPerson());
        personList.add(getPerson2());

        List<PersonDTO> personDTOList = PersonConverter.INSTANCE.domain2dto(personList);
        log.info("personDTOList========={}", JSONObject.toJSONString(personDTOList));

    }

}
