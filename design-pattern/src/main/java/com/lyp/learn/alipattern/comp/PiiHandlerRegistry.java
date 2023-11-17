package com.lyp.learn.alipattern.comp;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author liyapu
 * @date 2023-11-17 23:01
 * @description 处理器注册器
 */
@Slf4j
public class PiiHandlerRegistry {

    private static Map<String, PiiDomainFieldHandler> piiDomainFieldHandlerMap = Maps.newHashMap();

    public static void putHandler(String piiDomainFieldName, PiiDomainFieldHandler piiDomainFieldHandler) {
        if (StringUtils.isEmpty(piiDomainFieldName)) {
            log.warn(" piiDomainFieldName is null,continue");
            return;
        }

        if (piiDomainFieldHandler == null) {
            log.warn(piiDomainFieldName + " piiDomainFieldHandler is null,continue");
            return;
        }

        if (!piiDomainFieldHandlerMap.containsKey(piiDomainFieldName)) {
            piiDomainFieldHandlerMap.put(piiDomainFieldName, piiDomainFieldHandler);
        }
    }

    public static <T extends Object> int handlerRead(T domain, Field domainField, PiiContent piiContent) {
        int num = 0;
        for (Map.Entry<String, PiiDomainFieldHandler> piiDomainFieldHandlerEntry : piiDomainFieldHandlerMap.entrySet()) {
            if (piiDomainFieldHandlerEntry.getValue().isSupport(domain, domainField)) {
                piiDomainFieldHandlerEntry.getValue().handlerRead(domain, domainField, piiContent);
            }
        }
        return num;
    }

    public static <T extends Object> int handlerWrite(T domain, Field domainField, PiiContent piiContent) {
        int num = 0;
        for (Map.Entry<String, PiiDomainFieldHandler> piiDomainFieldHandlerEntry : piiDomainFieldHandlerMap.entrySet()) {
            if (piiDomainFieldHandlerEntry.getValue().isSupport(domain, domainField)) {
                piiDomainFieldHandlerEntry.getValue().handlerWrite(domain, domainField, piiContent);
            }
        }
        return num;
    }

    public static Map<String, PiiDomainFieldHandler> getPiiDomainFieldHandlerMap() {
        return piiDomainFieldHandlerMap;
    }

    public static void init() {
        List<PiiDomainFieldHandler> piiDomainFieldHandlerList = PiiDomainFieldHandlerFactory.createPiiDomainFieldHandler();
        if (CollectionUtils.isNotEmpty(piiDomainFieldHandlerList)) {
            for (PiiDomainFieldHandler piiDomainFieldHandler : piiDomainFieldHandlerList) {
                putHandler(piiDomainFieldHandler.getPiiDomainMeta(), piiDomainFieldHandler);
            }
        }
    }
}