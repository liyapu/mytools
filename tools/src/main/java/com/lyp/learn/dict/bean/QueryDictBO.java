package com.lyp.learn.dict.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryDictBO {


    /**
     * 终端渠道id
     */
    private Integer clientChannel;

    /**
     *
     */
    private Integer poiType;

    /**
     *
     */
    private Integer poiBizBranchMark;

    /**
     * 创建单据类型 默认值为0， 1:取回；2:销毁
     */
    private Integer createBillType;
    /**
     * operationType，1-查询，2-创建，0-默认展示
     */
    private int operationType;

    /**
     * poiId
     */
    private long poiId;

    /**
     * 场景类别
     */
    private Integer bizScenarioType;


}
