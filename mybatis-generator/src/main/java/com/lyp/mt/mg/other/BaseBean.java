package com.lyp.mt.mg.other;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author zanxus
 * @date 2019-06-04 11:26
 * @description
 */
@Data
public class BaseBean {

    private long id;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 记录状态标识 0无效 1有效 default:1
     */
    @JSONField(serialize = false)
    private Integer valid;

}
