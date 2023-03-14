package com.lyp.learn.dp.pattern.proxypattern2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liyapu
 * @date 2023-03-11 21:51
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfo {

    private String apiName;
    private Long responseTime;
    private Long startTimestamp;
}
