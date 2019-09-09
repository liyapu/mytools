package com.lyp.mt.entity.yapi;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 22:57
 */
@lombok.Data
@EqualsAndHashCode
@ToString
public class Data {
    private String type;
    private String description;
    private Items items;
}
