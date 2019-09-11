package com.lyp.learn.base.annotation.inherited;

import java.lang.annotation.*;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-07 17:23
 *
 *
 * @Inherited：
 *     @Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
 * 如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 *
 * 注意：@Inherited annotation类型是被标注过的class的子类所继承。类并不从它所实现的接口继承annotation，
 *       方法并不从它所重载的方法继承annotation。
 *
 *       @Inherited 可以让注解被继承，但这并不是真的继承，只是通过使用@Inherited，可以让子类Class对象使用getAnnotations()获取父类被@Inherited修饰的注解
 *
 * 　　当@Inherited annotation类型标注的annotation的Retention是RetentionPolicy.RUNTIME，则反射API增强了这种继承性。
 *    如果我们使用java.lang.reflect去查询一个@Inherited annotation类型的annotation时，反射代码检查将展开工作：
 *    检查class和其父类，直到发现指定的annotation类型被发现，或者到达类继承结构的顶层。

 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InheritedAnnotation {
    String value();
}
