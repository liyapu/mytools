package com.lyp.learn.dp.pattern.proxypattern2;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liyapu
 * @date 2023-03-11 21:53
 * @description
 */
public class MetricsCollector {

    private MetricsStorage metricsStorage;//基于接口而非实现编程

    public MetricsCollector() {
        
    }

    //依赖注入
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    //用一个函数代替了最小原型中的两个函数
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        //metricsStorage.saveRequestInfo(requestInfo);
    }
}
