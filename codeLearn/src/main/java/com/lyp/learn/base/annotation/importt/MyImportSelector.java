package com.lyp.learn.base.annotation.importt;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: liyapu
 * @Description: 定义一个我自己的ImportSelector
 * @create: 2018-11-08 21:55
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.lyp.learn.annotation.importt.Triangle"};    }
}
