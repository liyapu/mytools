package com.lyp.learn.streampk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Stream 的distinct()方法
 *     distinct()是Java 8 中 Stream 提供的方法，返回的是由该流中不同元素组成的流。
 *
 *     distinct()使用 hashCode() 和 eqauls() 方法来获取不同的元素。
 *     因此，需要去重的类必须实现 hashCode() 和 equals() 方法。
 *     换句话讲，我们可以通过重写定制的 hashCode() 和 equals() 方法来达到某些特殊需求的去重
 *
 *     distinct（）返回由该流的不同元素组成的流。
 *     distinct（）是Stream接口的方法。distinct（）使用hashCode（）和equals（）方法来获取不同的元素。
 *     因此，我们的类必须实现hashCode（）和equals（）方法。
 *
 *     如果distinct（）正在处理有序流，那么对于重复元素，将保留以遭遇顺序首先出现的元素，并且以这种方式选择不同元素是稳定的。
 *
 *     在无序流的情况下，不同元素的选择不一定是稳定的，是可以改变的。
 *
 *     distinct（）执行有状态的中间操作。在有序流的并行流的情况下，保持distinct（）的稳定性是需要很高的代价的，
 *     因为它需要大量的缓冲开销。
 *
 *     如果我们不需要保持遭遇顺序的一致性，那么我们应该可以使用通过BaseStream.unordered（）方法实现的无序流。
 */
public class StreamDistinctDemo {

    /**
     * 对于 String 列表的去重
     * 因为 String 类已经覆写了 equals() 和 hashCode() 方法，所以可以去重成功
     */
    @Test
    public void test01() {
        List<String> stringList = new ArrayList<String>() {{
            add("A");
            add("A");
            add("B");
            add("B");
            add("A");
            add("A");
            add("C");
        }};

        System.out.println("去重前：");
        for (String s : stringList) {
            System.out.println(s);
        }
        System.out.println();

        stringList = stringList.stream().distinct().collect(Collectors.toList());
        System.out.println("去重后：");
        for (String s : stringList) {
            System.out.println(s);
        }
        System.out.println();
    }

    /**
     * 对于实体类列表的去重
     *  需要重写 equals() 以及 hashCode() 方法
     */
    @Test
    public void test02() {
        List<Student> studentList = getStudentList();
        System.out.println("去重前：");
        System.out.println(studentList);

        List<Student> newList = studentList
                .stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("去重后：");
        System.out.println(newList);
    }

    /**
     *   根据 List<Object> 中 Object 某个属性去重
     *     根据名字去重后
     */
    @Test
    public void test03() {
        List<Student> studentList = getStudentList();
        System.out.println("去重前：");
        System.out.println(studentList);

        //先根据 equals 和 hashCode 去重
        List<Student> newList = studentList
                .stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("去重后：");
        System.out.println(newList);

        System.out.println("利用Collectors.toMap去重:");
        //通过 map 去重
        //利用Collectors.toMap去重
        List<Student> mapList = studentList.stream()
                .collect(Collectors.toMap(Student::getName, Function.identity(), (oldValue, newValue) -> oldValue))
                .values()
                .stream()
                .collect(Collectors.toList());
        System.out.println(mapList);

        // 然后再根据 地址 address 去重
        //通过 TreeSet<> 来达到获取不同元素的效果
        List<Student> nl = newList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getAddress))),
                        ArrayList::new)
        );
        System.out.println("根据地址去重后 :");
        System.out.println(nl);


    }

    /**
     * 根据对象某几个属性去重
     *
     * https://blog.csdn.net/qq_35634181/article/details/108867857
     * JDK8 Stream操作 collectingAndThen ------根据对象的属性进行去重操作
     */
    @Test
    public void test031() {
        List<Student> studentList = getStudentList();
        System.out.println("去重前：");
        printList(studentList);

        //通过 TreeSet<> 来达到获取不同元素的效果
        //根据 年龄和姓名 两个字段去重
        List<Student> newList = studentList.stream().collect(
            Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(s -> s.getAge() + "-" + s.getName()))),
                ArrayList::new)
        );

        System.out.println("根据 年龄和姓名 去重后 :");
        printList(newList);
    }

    @Test
    public void test032() {
        List<Student> studentList = getStudentList();
        System.out.println("去重前：");
        printList(studentList);

        Map<String, Integer> sutMap = studentList.stream()
            .collect(Collectors.groupingBy(Student::getName,
                Collectors.collectingAndThen(
                    Collectors.minBy(Comparator.comparing(Student::getAge)),
                    min -> min.map(Student::getId).orElse(null))
            ));

        System.out.println(sutMap);
    }


    /**
     * 根据 List<Object> 中 Object 某个属性去重
     * 通过 filter() 方法
     *
     * 我们首先创建一个方法作为 Stream.filter() 的参数，其返回类型为 Predicate，
     * 原理就是判断一个元素能否加入到 Set 中去
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Test
    public void test04() {
        List<Student> studentList = getStudentList();
        System.out.println("去重前：");
        System.out.println(studentList);

        //这里我们将 distinctByKey() 方法作为 filter() 的参数，过滤掉那些不能加入到 set 的元素
        List<Student> newList = studentList.stream()
                .filter(distinctByKey(Student::getAddress))
                .collect(Collectors.toList());
        System.out.println("根据地址去重后：");
        System.out.println(newList);
    }

    private List<Student> getStudentList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(5, "关羽", 22, "北京"));
        students.add(new Student(8, "张飞", 11, "天津"));
        students.add(new Student(8, "张飞", 11, "天津"));
        students.add(new Student(2, "曹操", 56, "上海"));
        students.add(new Student(9, "刘备", 32, "北京"));
        students.add(new Student(1, "诸葛亮", 26, "温州"));
        students.add(new Student(9, "刘备", 32, "北京"));
        students.add(new Student(9, "刘备", 32, "北京"));
        students.add(new Student(4, "孙权", 18, "上海"));
        return students;
    }

    public void printList(List<Student> students){
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    class Student {
        private Integer id;
        private String name;
        private Integer age;
        private String address;

        public Student() {
        }

        public Student(Integer id, String name, Integer age, String address) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id.equals(student.id) &&
                    name.equals(student.name) &&
                    age.equals(student.age) &&
                    address.equals(student.address);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, age, address);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}


