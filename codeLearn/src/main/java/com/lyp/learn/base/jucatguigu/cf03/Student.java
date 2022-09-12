package com.lyp.learn.base.jucatguigu.cf03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author liyapu
 * @date 2022-09-12 13:42
 * @description
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer id;
    private String studentName;
    /**
     * 专业
     */
    private String major;

}
