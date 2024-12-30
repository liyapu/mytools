package com.lyp.learn.others;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liyapu
 * @date 2024-12-30 19:57
 * @description Map 中ConcurrentHashMap是线程安全的，但不是所有操作都是，例如get()之后再put()就不是了，这时使用merge()确保没有更新会丢失。
 *
 * 因为Map.merge()意味着我们可以原子地执行插入或更新操作，它是线程安全的。
 *
 * default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
 * Objects.requireNonNull(remappingFunction);
 * Objects.requireNonNull(value);
 * V oldValue = get(key);
 * V newValue = (oldValue == null) ? value :
 * remappingFunction.apply(oldValue, value);
 * if(newValue == null) {
 * remove(key);
 * } else {
 * put(key, newValue);
 * }
 * return newValue;
 * }
 *
 * 该方法接收三个参数，一个 key 值，一个 value，一个 remappingFunction 。如果给定的key不存在，它就变成了put(key, value)；
 * 但是，如果key已经存在一些值，我们 remappingFunction 可以选择合并的方式:
 *
 * 只返回新值即可覆盖旧值： (old, new) -> new;
 * 只需返回旧值即可保留旧值：(old, new) -> old;
 * 合并两者，例如：(old, new) -> old + new;
 * 删除旧值：(old, new) -> null
 */
@Slf4j
public class MapMergeDemo {
    @Data
    static class StudentEntity {
        /**
         * 学生姓名
         */
        private String studentName;
        /**
         * 学科
         */
        private String subject;
        /**
         * 分数
         */
        private Integer score;
    }

    private List<StudentEntity> buildATestList() {
        List<StudentEntity> studentEntityList = new ArrayList<>();
        StudentEntity studentEntity1 = new StudentEntity() {{
            setStudentName("张三");
            setSubject("语文");
            setScore(60);
        }};
        StudentEntity studentEntity2 = new StudentEntity() {{
            setStudentName("张三");
            setSubject("数学");
            setScore(70);
        }};
        StudentEntity studentEntity3 = new StudentEntity() {{
            setStudentName("张三");
            setSubject("英语");
            setScore(80);
        }};
        StudentEntity studentEntity4 = new StudentEntity() {{
            setStudentName("李四");
            setSubject("语文");
            setScore(85);
        }};
        StudentEntity studentEntity5 = new StudentEntity() {{
            setStudentName("李四");
            setSubject("数学");
            setScore(75);
        }};
        StudentEntity studentEntity6 = new StudentEntity() {{
            setStudentName("李四");
            setSubject("英语");
            setScore(65);
        }};
        StudentEntity studentEntity7 = new StudentEntity() {{
            setStudentName("王五");
            setSubject("语文");
            setScore(80);
        }};
        StudentEntity studentEntity8 = new StudentEntity() {{
            setStudentName("王五");
            setSubject("数学");
            setScore(85);
        }};
        StudentEntity studentEntity9 = new StudentEntity() {{
            setStudentName("王五");
            setSubject("英语");
            setScore(95);
        }};

        studentEntityList.add(studentEntity1);
        studentEntityList.add(studentEntity2);
        studentEntityList.add(studentEntity3);
        studentEntityList.add(studentEntity4);
        studentEntityList.add(studentEntity5);
        studentEntityList.add(studentEntity6);
        studentEntityList.add(studentEntity7);
        studentEntityList.add(studentEntity8);
        studentEntityList.add(studentEntity9);

        return studentEntityList;
    }

    /**
     * 如果 key 对应的 value 不存在，则返回该 value 值，
     * 如果存在，则返回通过 remappingFunction 重新计算后的值。
     */
    @Test
    public void test00001() {
        //创建一个HashMap
        HashMap<String, Integer> prices = new HashMap<>();

        // 往 HashMap 插入映射
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        int returnedValue = prices.merge("Shirt", 100, (oldValue, newValue) -> oldValue + newValue);
        System.out.println("Price of Shirt: " + returnedValue);

        // 输出更新后的 HashMap
        System.out.println("Updated HashMap: " + prices);
    }

    /**
     * 我们使用了 lambda表达式 (oldValue, newValue) -> oldValue + "/" + newValue) 作为重映射函数。
     * 因为键 washington 已经存在于 countries，旧值被重映射函数返回的值替换。因此，Washington 的映射包含了 America/USA
     */
    @Test
    public void test00002() {
        // 创建一个 HashMap
        HashMap<String, String> countries = new HashMap<>();

        // 往HashMap插入映射项
        countries.put("Washington", "America");
        countries.put("Canberra", "Australia");
        countries.put("Madrid", "Spain");
        System.out.println("HashMap: " + countries);

        //合并 key为 Washington的映射
        String returnedValue = countries.merge("Washington", "USA", (oldValue, newValue) -> oldValue + "/" + newValue);
        System.out.println("Washington: " + returnedValue);

        //输出更新后的HashMap
        System.out.println("Updated HashMap: " + countries);
    }

    /**
     * 思路：用Map的一组key/value存储一个学生的总成绩(学生姓名作为key,总成绩为value)
     *
     * Map中不存在指定的key时，将传入的value设置为key的值；
     * 当key存在值时,取出存在的值与当前值相加，然后放入Map中
     */
    @Test
    public void test001() {
        // 造一个学生成绩列表
        List<StudentEntity> studentEntityList = buildATestList();

        Map<String, Integer> studentScore = new HashMap<>();
        studentEntityList.forEach(studentEntity -> {
            String studentName = studentEntity.getStudentName();
            if (studentScore.containsKey(studentName)) {
                studentScore.put(studentName, studentScore.get(studentName) + studentEntity.getScore());
            } else {
                studentScore.put(studentName, studentEntity.getScore());
            }
        });
        System.out.println(studentScore);
    }

    /**
     * 很明显，这里需要采用remappingFunction的合并方式。
     */
    @Test
    public void testMergeMethod001() {
        // 造一个学生成绩列表
        List<StudentEntity> studentEntityList = buildATestList();
        Map<String, Integer> studentScore = new HashMap<>();
        studentEntityList.forEach(
                studentEntity -> studentScore.merge(studentEntity.getStudentName(), studentEntity.getScore(), Integer::sum));

        System.out.println(studentScore);
    }


}


