package com.lyp.learn.base.compare;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("张",20,"北京"));
        studentList.add(new Student("李",18,"天津"));
        studentList.add(new Student("赵",15,"山东"));

        System.out.println(studentList);
        System.out.println();
        /**
         *  按年龄升序排，声明比较器
         */
        System.out.println("---旧的写法----按年龄升序排，声明比较器---------");
        StudentAgeAscComparator studentAgeAscComparator = new StudentAgeAscComparator();
        Collections.sort(studentList,studentAgeAscComparator);
        System.out.println(studentList);
        System.out.println();

        /**
         * 按年龄降序排，使用匿名类
         */
        System.out.println("----旧的写法---按年龄的 降序排，使用匿名类---------");
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        System.out.println(studentList);
        System.out.println();

        /**
         * lambda表达式
         */
        System.out.println("----新的写法---按年龄的 升序排，使用lambda表达式---------");
        studentList.sort((Student s1,Student s2) -> s1.getAge() - s2.getAge());
        System.out.println(studentList);
        System.out.println();

        /**
         *  精简版的lambda表达式
         * 我们通过不指定类型定义来进一步简化表达式，因为编译器自己可以进行类型判断
         */
        System.out.println("----新的写法---按年龄的 升序排，使用--精简版的lambda表达式---------");
        studentList.sort((s1,s2) -> s1.getAge() - s2.getAge());
        System.out.println(studentList);
        System.out.println();

        /**
         * 使用Comparator.comparing的方式
         */
        System.out.println("----新的写法---按年龄的 升序排，使用--Comparator.comparing---------");
        studentList.sort(Comparator.comparing(student -> student.getAge()));
        System.out.println(studentList);
        System.out.println();

        /**
         * 使用静态方法的引用
         * java中的双冒号就是方法引用。::是JDK8里引入lambda后的一种用法，表示引用，
         * 比如静态方法的引用String::valueOf，
         * 比如构造器的引用，ArrayList::new
         */
        System.out.println("----新的写法---按年龄的 升序排，使用静态方法的引用---------");
        studentList.sort(Comparator.comparing(Student::getAge));
        System.out.println(studentList);
        System.out.println();

        /**
         * 排序反转
         * 很多时候，想对排序进行反转，或者说逆序：
         */
        System.out.println("----新的写法---按年龄的 降序排，使用 排序反转---------");
        studentList.sort(Comparator.comparing(Student::getAge).reversed());
        System.out.println(studentList);
        System.out.println();

        /**
         * 许多条件组合排序
         */
        System.out.println("----旧的写法------许多条件组合排序---------");
        studentList.sort((s1,s2) ->{
            if(!s1.getAge().equals(s2.getAge())){
                return s1.getAge() - s2.getAge();
            }else{
                return s1.getName().compareTo(s2.getName());
            }
        });
        System.out.println(studentList);
        System.out.println();
        /**
         * 从JDK 8开始，我们现在可以把多个Comparator链在一起（chain together）去建造更复杂的比较逻辑
         */
        System.out.println("----新的写法------thenComparing---------");
        studentList.sort(Comparator.comparing(Student::getAge).thenComparing(Student::getName));
        System.out.println(studentList);
        System.out.println();

        studentList.forEach(System.out::println);

    }
}
