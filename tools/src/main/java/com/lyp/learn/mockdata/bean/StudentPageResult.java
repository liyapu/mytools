package com.lyp.learn.mockdata.bean;

import lombok.Data;

import java.util.List;

/**
 * @author liyapu
 * @date 2024-09-30 20:40
 * @description 学生分页结果对象
 */
@Data
public class StudentPageResult {
    private int code;
    private String msg;
    private StudentPageInfo data;

    @Data
    public static class StudentPageInfo {
        private long total;
        private List<Student> studentList;
    }
}
