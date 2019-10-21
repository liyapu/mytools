package com.lyp.learn.cglib.beans;

import com.lyp.learn.cglib.bean.User;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-21 16:13
 */
public class BeanCopierTest {

    @Test
    public void test01(){
        // 动态生成用于复制的类,false为不使用Converter类
        BeanCopier copier = BeanCopier.create(User.class, User.class, false);

        User source = new User();
        source.setAge(18);
        User target = new User();

        // 执行source到target的属性复制
        copier.copy(source, target, null);

        assertThat(target.getAge(),equalTo(18));
        System.out.println(source);
        System.out.println(target);
    }

    /**
     * 使用Convert
     */
    @Test
    public void testConvert() {
        // 动态生成用于复制的类,并使用Converter类
        BeanCopier copier = BeanCopier.create(User.class, User.class, true);

        User source = new User();
        source.setAge(18);
        User target = new User();

        // 执行source到target的属性复制
        copier.copy(source, target, new Converter() {

            /**
             * @param sourceValue source对象属性值
             * @param targetClass target对象对应类
             * @param methodName targetClass里属性对应set方法名,eg.setId
             * @return
             */
            public Object convert(Object sourceValue, Class targetClass, Object methodName) {
                if (targetClass.equals(Integer.TYPE)) {
                    return new Integer(((Number)sourceValue).intValue() + 1);
                }
                return sourceValue;
            }
        });

        assertThat(target.getAge(),equalTo(19));
    }
}
