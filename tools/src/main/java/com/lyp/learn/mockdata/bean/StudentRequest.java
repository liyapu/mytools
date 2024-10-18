package com.lyp.learn.mockdata.bean;

import lombok.Data;

/**
 * @author liyapu
 * @date 2024-10-11 11:09
 * @description
 */
@Data
public class StudentRequest {
    private String name;
    private Integer age;
    private Integer offset;
    private Integer limit;
}
