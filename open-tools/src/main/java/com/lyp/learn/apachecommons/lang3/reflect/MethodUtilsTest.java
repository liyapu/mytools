package com.lyp.learn.apachecommons.lang3.reflect;

import com.lyp.learn.apachecommons.bean.ReflectBean;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 20:55
 */
public class MethodUtilsTest {

    @Test
    public void test01() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ReflectBean rb = new ReflectBean();

        // 调用无参方法
        MethodUtils.invokeMethod(rb, "getAge", null);

        // 调用一参方法
        Object getInfo1 = MethodUtils.invokeMethod(rb, "getInfo", "Hello");
        System.out.println("getInfo1 : " + getInfo1);

        // 调用多参方法
        Object getInfo2 = MethodUtils.invokeMethod(rb, "getInfo", new Object[]{"特大消息", new Integer(10)});
        System.out.println("getInfo2 : " + getInfo2);

        // 调用静态方法
        Object staticHello = MethodUtils.invokeStaticMethod(ReflectBean.class, "staticHello", null);
        System.out.println("staticHello : " + staticHello);

    }
}
