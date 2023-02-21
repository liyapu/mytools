package com.lyp.learn.bean;

import lombok.Data;

/**
 * @author liyapu
 * @desc 人查询
 */
@Data
public class ManQueryParam {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 地址
     */
    private String address;

    /**
     * 每页条数
     */
    private Integer limitNum;

    /**
     * 有效状态
     */
    private Integer valid;

}
