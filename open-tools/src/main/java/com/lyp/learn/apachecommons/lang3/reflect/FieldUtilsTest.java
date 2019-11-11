package com.lyp.learn.apachecommons.lang3.reflect;

import com.lyp.learn.apachecommons.bean.ReflectBean;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-25 19:08
 */
public class FieldUtilsTest {

    @Test
    public void test01() throws IllegalAccessException {
        ReflectBean rb = new ReflectBean("张三", "Ray", 10);
        Class clazz = ReflectBean.class;

        // 以下两个方法完全一样,都能获取共有或私有变量,因为第三个参数都设置了不检查
        FieldUtils.getDeclaredField(clazz, "name", true);
        FieldUtils.getField(clazz, "name", true);

        // 读取私有或公共变量的值
        FieldUtils.readField(rb, "name", true);

        // 读取静态变量
//        FieldUtils.readStaticField(clazz, "STATIC_FIELD");

        // 写入私有或共有变量
        FieldUtils.writeField(rb, "name", "RayRay", true);

        // 写入静态变量
//        FieldUtils.writeStaticField(clazz, "STATIC_FIELD", "static_value");
        System.out.println(rb);
    }
}
