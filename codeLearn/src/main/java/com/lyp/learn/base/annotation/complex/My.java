package com.lyp.learn.base.annotation.complex;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-04 22:47
 */

@MyAnnotation(value = "孤傲苍狼",
              arrayAttr = {4,5,6},
              lamp = TrafficLampEnum.GREEN,
              annotationAttr = @MetaAnnotation("bbbb"))
public class My {

    @MyAnnotation("将myAnnotation 注解到方法上")
    public void testMy(){

    }
}
