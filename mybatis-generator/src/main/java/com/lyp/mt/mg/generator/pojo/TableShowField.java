package com.lyp.mt.mg.generator.pojo;

import lombok.ToString;

/**
 * 表格显示字段
 *
 * @author liyapu
 * @date 2019-10-10 04:17:38
 */
@Data
@EqualsAndHashCode
@ToString
public class TableShowField {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 表格名字
     */
    private String tableName;

    /**
     * 字段名字
     */
    private String fieldName;

    /**
     * 字段在前端展示的名字
     */
    private String fieldShow;

    /**
     * 字段顺序
     */
    private Integer fieldOrder;
}