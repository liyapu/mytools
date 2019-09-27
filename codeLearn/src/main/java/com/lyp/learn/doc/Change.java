package com.lyp.learn.doc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-27 19:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Change {
    /**
     * 变更类型
     */
    private String reason;

    /**
     * 变更日期
     */
    private Date time;
}
