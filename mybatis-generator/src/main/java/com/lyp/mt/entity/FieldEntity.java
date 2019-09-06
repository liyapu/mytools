package com.lyp.mt.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author: liyapu
 * @description: 表字段的实体类
 * @date 2019-09-06 18:37
 */
public class FieldEntity {
    private String field;
    private String type;
    private String comment;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
