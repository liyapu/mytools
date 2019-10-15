package com.lyp.learn.guava.collect;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import org.junit.jupiter.api.Test;

/**
 * ClassToInstanceMap是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象。
 *
 * 为了扩展Map接口，ClassToInstanceMap额外声明了两个方法：
 * T getInstance(Class<T>) 和T putInstance(Class<T>, T)，从而避免强制类型转换，同时保证了类型安全。
 *
 * 对于ClassToInstanceMap，Guava提供了两种有用的实现：MutableClassToInstanceMap和 ImmutableClassToInstanceMap。
 */
public class ClassToInstanceMapTest {

    /**
     * ClassToInstanceMap有唯一的泛型参数，通常称为B，代表Map支持的所有类型的上界。
     *
     * 从技术上讲，ClassToInstanceMap<B>实现了Map<Class<? extends B>, B>
     * 或者换句话说，是一个映射B的子类型到对应实例的Map。
     * 这让ClassToInstanceMap包含的泛型声明有点令人困惑，但请记住B始终是Map所支持类型的上界——通常B就是Object。
     */
    @Test
    public void test01(){
        ClassToInstanceMap<Number> numberDefaults= MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, Integer.valueOf(10));
        numberDefaults.putInstance(Long.class,Long.valueOf(100L));

        System.out.println(numberDefaults);
        System.out.println(numberDefaults.get(Integer.class));
        System.out.println(numberDefaults.get(Long.class));
    }
}
