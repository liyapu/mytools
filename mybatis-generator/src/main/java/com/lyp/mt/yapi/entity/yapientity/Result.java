package com.lyp.mt.yapi.entity.yapientity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 21:44
 */
@Data
@EqualsAndHashCode
@ToString
public class Result {
    private String type;
    private String description;
    private Object properties;
    private List<String> required;
}
