package com.lyp.learn.ppt;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamComparatorDemo {
    List<Apple> inventory = Arrays.asList(
            new Apple("green",80,"黄土高原"),
            new Apple("green",200,"黄河故道"),
            new Apple("red",160,"渤海湾"),
            new Apple("yellow",20,"渤海湾"),
            new Apple("green",100,"黄土高原")
    );

    List<Student> studentsSource = Arrays.asList(
            new Student(10, 20, "aty", new Address("河南省","商丘市",476000)),
		    new Student(1, 22, "qun", new Address("山东省","临沂",300000)),
		    new Student(1, 26, "Zen", new Address("河南省","郑州市",400000)),
		    new Student(5, 23, "Aty", new Address("河南省","开封市",480000))
    );

    /**
     * 排序----按苹果重量升序排序
     */
    @Test
    public void test1(){
        List<Apple> weightList1 = new ArrayList<>(inventory);
        System.out.println(weightList1);
        Collections.sort(weightList1, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        System.out.println(weightList1);
    }

    /**
     * List 中排序对象
     */
    @Test
    public void test2(){
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        System.out.println(weightList);
    }


    @Test
    public void test3(){
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort((Apple a1,Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(weightList);
    }

    /**
     * comparing
     */
    @Test
    public void test4(){
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparing(apple -> apple.getWeight()));
        System.out.println(weightList);
    }


    @Test
    public void test5(){
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(weightList);
    }

    /**
     * comparingInt
     */
    @Test
    public void test51(){
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparingInt(Apple::getWeight));
        System.out.println(weightList);
    }

    /**
     * 倒序排
     */
    @Test
    public void test6(){
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort((Apple a1,Apple a2) -> a2.getWeight().compareTo(a1.getWeight()));
        System.out.println(weightList);
    }

    @Test
    public void test7(){
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparing(Apple::getWeight,Comparator.reverseOrder()));
        System.out.println(weightList);
    }

    @Test
    public void test71(){
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparingInt(Apple::getWeight).reversed());
        System.out.println(weightList);
    }

    /**
     * 按多个字段进行排序
     */
    @Test
    public void test10(){
        List<Apple> colorWeightList = new ArrayList<>(inventory);
        colorWeightList.sort(Apple::compareByColorWeight);
        System.out.println(colorWeightList);
        System.out.println(JSON.toJSONString(colorWeightList));
    }

    /**
     * thenComparing
     * thenComparingInt
     * 按多个字段进行排序
     */
    @Test
    public void test11(){
        List<Apple> colorWeightList = new ArrayList<>(inventory);
        colorWeightList.sort(Comparator.comparing(Apple::getColor)
                                        .thenComparing(Apple::getWeight)
                            );
        System.out.println(colorWeightList);
        System.out.println(JSON.toJSONString(colorWeightList));
        System.out.println();

        List<Apple> colorWeightList2 = new ArrayList<>(inventory);
        colorWeightList2.sort(Comparator.comparing(Apple::getColor).thenComparingInt(Apple::getWeight));
        System.out.println(colorWeightList2);
        System.out.println(JSON.toJSONString(colorWeightList2));
    }

    /**
     * naturalOrder
     * 按自然顺序排序
     * 要求排序的 Integer,String 必须实现 Comparable 接口，默认已经实现
     */
    @Test
    public void test20(){
        List<Integer> numList1 = Arrays.asList(1, 4, 2, 6, 2, 8);
        numList1.sort(Comparator.naturalOrder());
        System.out.println(numList1);

        List<String> strList1 = Arrays.asList("d","f","c","a","x","H","D","A","Y");
        strList1.sort(Comparator.naturalOrder());
        System.out.println(strList1);
    }

    /**
     * reverseOrder
     * 按自然顺序的反序排序
     * 要求排序的 Integer,String 必须实现 Comparable 接口，默认已经实现
     */
    @Test
    public void test21(){
        List<Integer> numList1 = Arrays.asList(1, 4, 2, 6, 2, 8);
        numList1.sort(Comparator.reverseOrder());
        System.out.println(numList1);

        List<String> strList1 = Arrays.asList("d","f","c","a","x","H","D","A","Y");
        strList1.sort(Comparator.reverseOrder());
        System.out.println(strList1);
    }

    /**
     *  Comparator.comparing(Function keyExtractor)生成1个Comparator对象，要求keyExtractor.apply()返回值一定要实现Comparable接口。
     *  比如下面代码extractIdWay1和extractIdWay2都是等价的，从Student对象中提取id属性，而id是int类型(Integer实现了Comparable)。
     *  流中比较对象
     */
    @Test
    public void test80(){
        // 使用lambda表达式创建Function对象
        Function<Student, Integer> extractIdWay1 = (student) -> student.getId();

        // 使用方法引用简化lambda
        Function<Student, Integer> extractIdWay2 = Student::getId;

        // Comparator.comparing(Function keyExtractor)
        Comparator<Student> byId = Comparator.comparing(extractIdWay2);

        // 升序
        List<Student> ascList = studentsSource.stream()
                                                .sorted(byId)
                                                .collect(Collectors.toList());
        System.out.println(ascList);

        List<Student> ascList2 = studentsSource.stream()
                                                .sorted(Comparator.comparingInt(Student::getId))
                                                .collect(Collectors.toList());
        System.out.println(ascList2);


        // 降序
        List<Student> descList = studentsSource.stream()
                                                .sorted(byId.reversed())
                                                .collect(Collectors.toList());
        System.out.println(descList);
    }

    /**
     * 由于Student.getAddress()返回的对象没有实现Comparable接口，所以不能通过Comparator.comparing()创建一个Comparator对象
     * //下面的代码编译不通过
     *  List<Student> ascList = studentsSource.stream()
    *                                           .sorted(Comparator.comparing(Student::getAddress))
    *                                           .collect(Collectors.toList());
     * 如果我们想按照Address的province字段(没有实现Comparable接口)排序怎么办呢？使用另一种形式的comparing方法：
     *
     *  *@param keyExtractor the function used to extract the sort key
     *  *@param  keyComparator the {@code Comparator} used to compare the sort key , keyComparator 是创建一个自定义的比较器
     */
    @Test
    public void test81(){
        Comparator<Address> cmpPro = Comparator.comparing(Address::getProvince);
        Comparator<Student> byAddress = Comparator.comparing(Student::getAddress, cmpPro);
        List<Student> sortedAddressList = studentsSource.stream()
                                                        .sorted(byAddress)
                                                        .collect(Collectors.toList());
        //这种形式的comparing()接收2个参数，第一个参数提取要排序的key，第二个参数指定排序的Comparator。
        System.out.println(sortedAddressList);
        System.out.println();

        List<Student> sortAddressList2 = studentsSource.stream()
                .sorted(Comparator.comparing(Student::getAddress,Comparator.comparing(Address::getProvince)))
                .collect(Collectors.toList());
        System.out.println(sortAddressList2);
        System.out.println();

        // 自己指定比较器，可以灵活定制比较逻辑。比如，我们想实现字符串不区分大小写比较
        //getName()返回String本身已经实现了Comparable,但是我们可以自己传递一个不区分大小写的比较器

        Comparator<Student> byName = Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER);
        List<Student> sortedNameList = studentsSource.stream()
                                                    .sorted(byName)
                                                    .collect(Collectors.toList());
        System.out.println(studentsSource);
        System.out.println(sortedNameList);
    }

    /**
     * 按多个字段进行排序
     */
    @Test
    public void test83(){
        // id升序
        Comparator<Student> byIdASC = Comparator.comparing(Student::getId);
        // named不分区大小写降序
        Comparator<Student> byNameDESC = Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER)
                                                    .reversed();
        // 联合排序
        Comparator<Student> finalComparator = byIdASC.thenComparing(byNameDESC);

        List<Student> result = studentsSource.stream()
                                                .sorted(finalComparator)
                                                .collect(Collectors.toList());
        System.out.println(result);

    }

    /**
     * nullsFirst
     * null元素小于非空元素，null元素排前面
     *
     * nullsLast
     * null元素大于非空元素，null元素排后面
     */
    @Test
    public void test22(){
        List<String> strList1 = Arrays.asList("d","a",null,"x","D",null,"A");
        strList1.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println(strList1);

        List<String> strList2 = Arrays.asList("d","a",null,"x","D",null,"A");
        strList2.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(strList2);

    }



}
