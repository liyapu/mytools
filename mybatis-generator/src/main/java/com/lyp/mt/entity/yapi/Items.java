package com.lyp.mt.entity.yapi;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 22:56
 */
@Data
@EqualsAndHashCode
@ToString
public class Items {
    private String type;
    private String description;
    private Object properties;
    private List<String> required;
}
