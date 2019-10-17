package com.lyp.learn.beancopy;

import com.lyp.learn.bean.User;
import com.lyp.learn.bean.UserVo;
import net.sf.cglib.beans.BeanCopier;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 09:44
 */
public class StaticCglibBeanCopierPropertiesCopier implements PropertiesCopier{
    //全局静态 BeanCopier, 避免每次都生成新的对象
    private static BeanCopier copier = BeanCopier.create(User.class, UserVo.class,false);
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        copier.copy(source,target,null);
    }
}
