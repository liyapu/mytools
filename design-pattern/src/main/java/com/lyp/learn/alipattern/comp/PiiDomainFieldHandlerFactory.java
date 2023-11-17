package com.lyp.learn.alipattern.comp;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author liyapu
 * @date 2023-11-17 23:02
 * @description 处理器工厂
 */
public class PiiDomainFieldHandlerFactory {
    /**
     * 创建领域处理器
     *
     * @return
     */
    public static List<PiiDomainFieldHandler> createPiiDomainFieldHandler() {
        List<PiiDomainFieldHandler> piiDomainFieldHandlerList = Lists.newArrayList();


        piiDomainFieldHandlerList.add(new ForTestSupportFieldHandler());
        piiDomainFieldHandlerList.add(new ForTestNotSupportFieldHandler());

        return piiDomainFieldHandlerList;
    }
}
