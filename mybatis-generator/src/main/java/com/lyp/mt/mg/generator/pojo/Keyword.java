package com.lyp.mt.mg.generator.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author liyapu
 * @date 2019-10-10 04:17:38
 */
@Data
@EqualsAndHashCode
@ToString
public class Keyword {
    /**
     * 自增id
     */
    private Integer id;

    

    private String word;

    /**
     * 类型 1：地名；2：公司相关
     */
    private Byte type;
}