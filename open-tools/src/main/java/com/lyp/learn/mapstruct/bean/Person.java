package com.lyp.learn.mapstruct.bean;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {
    private Long id;
    private String name;
    private String email;
    private Date birthday;
    private Dog dog;
}
