package com.lyp.learn.beancopy;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 09:47
 */
public class CommonsBeanUtilsPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        BeanUtils.copyProperties(target,source);
    }
}
