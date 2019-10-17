package com.lyp.learn.beancopy;

import org.springframework.beans.BeanUtils;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 09:46
 */
public class SpringBeanUtilsPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        BeanUtils.copyProperties(source,target);
    }
}
