package com.lyp.learn.guava.base;

import com.google.common.base.Objects;
import com.lyp.learn.bean.Person;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-26 10:21
 */
public class ObjectsTest {

    /**
     * 当一个对象中的字段可以为null时，实现Object.equals方法会很痛苦，因为不得不分别对它们进行null检查。
     * 使用Objects.equal帮助你执行null敏感的equals判断，从而避免抛出NullPointerException
     *
     * guava 推荐使用jdk中类
     * 注意：JDK7引入的Objects类提供了一样的方法Objects.equals。
     */
    @Test
    public void testEqual(){
        System.out.println(Objects.equal("a","a"));
        System.out.println(Objects.equal("a",null));
        System.out.println(Objects.equal(null,"a"));
        System.out.println(Objects.equal(null,null));

        System.out.println("------------------");
        System.out.println(java.util.Objects.equals("a","a"));
        System.out.println(java.util.Objects.equals("a",null));
        System.out.println(java.util.Objects.equals(null,"a"));
        System.out.println(java.util.Objects.equals(null,null));
    }

    /**
     * 用对象的所有字段作散列[hash]运算应当更简单。Guava的Objects.hashCode(Object...)会对传入的字段序列计算出合理的、顺序敏感的散列值。
     * 你可以使用Objects.hashCode(field1, field2, …, fieldn)来代替手动计算散列值。
     *
     * 注意：JDK7引入的Objects类提供了一样的方法Objects.hash(Object...)
     */
    @Test
    public void testHashCode(){
        Person person = new Person("张三","北京",20);
        System.out.println(Objects.hashCode(person));
        System.out.println(Objects.hashCode(person.getName(),person.getAddress(),person.getAge()));

        System.out.println("--------------");
        System.out.println(java.util.Objects.hashCode(person));
        System.out.println(java.util.Objects.hash(person));
        System.out.println(java.util.Objects.hash(person.getName(),person.getAddress(),person.getAge()));
    }
}
