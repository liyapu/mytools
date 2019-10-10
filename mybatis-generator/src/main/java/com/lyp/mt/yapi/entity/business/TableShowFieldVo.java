package com.lyp.mt.yapi.entity.business;

import lombok.Data;
import lombok.ToString;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 22:06
 */
@Data
@ToString
public class TableShowFieldVo {
    private String fieldName;
    private String fieldShow;
    private Integer fieldOrder;
}
