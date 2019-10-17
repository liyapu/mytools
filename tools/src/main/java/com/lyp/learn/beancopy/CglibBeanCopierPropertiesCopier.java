package com.lyp.learn.beancopy;

import net.sf.cglib.beans.BeanCopier;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 09:42
 */
public class CglibBeanCopierPropertiesCopier implements PropertiesCopier {

    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        BeanCopier copier = BeanCopier.create(source.getClass(),target.getClass(),false);
        copier.copy(source,target,null);
    }
}
