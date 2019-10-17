package com.lyp.learn.guava.base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.lyp.learn.guava.bean.Person;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

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


    static class Guava implements Comparable<Guava> {
        private String manufactory;
        private String version;
        private Calendar releaseDate;

        public Guava(String manufactory, String version, Calendar releaseDate) {
            this.manufactory = manufactory;
            this.version = version;
            this.releaseDate = releaseDate;
        }

        @Override
        public int compareTo(Guava o) {
            return ComparisonChain.start()
                    .compare(this.manufactory,o.manufactory)
                    .compare(this.version,o.version)
                    .compare(this.releaseDate,o.releaseDate)
                    .result();
        }

        /**
         * idea 给生成的 equals,hashCode,toString 方法
         */
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Guava guava = (Guava) o;
//            return manufactory.equals(guava.manufactory) &&
//                    version.equals(guava.version) &&
//                    releaseDate.equals(guava.releaseDate);
//        }
//
//        @Override
//        public int hashCode() {
//            return java.util.Objects.hash(manufactory, version, releaseDate);
//        }
//
//        @Override
//        public String toString() {
//            return "Guava{" +
//                    "manufactory='" + manufactory + '\'' +
//                    ", version='" + version + '\'' +
//                    ", releaseDate=" + releaseDate +
//                    '}';
//        }

        /**
         * 也可以自己写，或者用第三方的写
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(manufactory, version, releaseDate);
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) {
                return true;
            }
            if(o == null || getClass() != o.getClass()) {
                return false;
            }
            Guava guava = (Guava) o;
            return Objects.equal(this.manufactory,guava.manufactory)
                    && Objects.equal(this.version,guava.version)
                    && Objects.equal(this.releaseDate,guava.releaseDate);
         }
        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("manufactory",this.manufactory)
                    .add("version",this.version)
                    .add("releaseDate",this.releaseDate)
                    .toString();
        }
    }

    @Test
    public void testGuava(){
        Calendar calendar1 = Calendar.getInstance();
        Guava guava1 = new Guava("google","27",calendar1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.YEAR,1);
        Guava guava2 = new Guava("google","27",calendar2);

        System.out.println("-----guava1-----");
        System.out.println(guava1.toString());
        System.out.println(guava1.hashCode());

        System.out.println("----guava2-----");
        System.out.println(guava2.toString());
        System.out.println(guava2.hashCode());

        System.out.println("-----------------");
        System.out.println(guava1.compareTo(guava2));
    }
}
