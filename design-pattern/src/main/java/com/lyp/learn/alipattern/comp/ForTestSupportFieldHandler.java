package com.lyp.learn.alipattern.comp;

import java.lang.reflect.Field;

/**
 * @author liyapu
 * @date 2023-11-17 23:04
 * @description
 */
public class ForTestSupportFieldHandler extends PiiDomainFieldHandlerBase {
    @Override
    public <T> boolean isSupport(T domain, Field domainField) {

        if (this.getClass().getSimpleName().equalsIgnoreCase(domain.getClass().getSimpleName())) {

            // to do business

            System.out.println(this.getClass().getSimpleName() + " is support, to do some business");

            return true;
        }

        return false;
    }

    @Override
    public String getPiiDomainMeta() {
        return this.getClass().getSimpleName();
    }
}