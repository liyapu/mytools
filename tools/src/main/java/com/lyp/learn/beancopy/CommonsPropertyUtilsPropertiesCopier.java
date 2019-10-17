package com.lyp.learn.beancopy;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 09:48
 */
public class CommonsPropertyUtilsPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        PropertyUtils.copyProperties(target,source);
    }
}
