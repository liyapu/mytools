package com.lyp.learn.streampk;

import com.alibaba.fastjson.JSON;
import com.lyp.learn.bean.*;
import com.lyp.learn.utils.MyBeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamComparatorDemo {
    List<Apple> inventory = Arrays.asList(
            new Apple("green", 80, "黄土高原"),
            new Apple("green", 200, "黄河故道"),
            new Apple("red", 160, "渤海湾"),
            new Apple("yellow", 20, "渤海湾"),
            new Apple("green", 100, "黄土高原")
    );

    List<Student> studentsSource = Arrays.asList(
            new Student(10, 20, "aty", new Address("河南省", "商丘市", 476000)),
            new Student(1, 22, "qun", new Address("山东省", "临沂", 300000)),
            new Student(1, 26, "Zen", new Address("河南省", "郑州市", 400000)),
            new Student(5, 23, "Aty", new Address("河南省", "开封市", 480000))
    );

    /**
     * 排序----按苹果重量升序排序
     */
    @Test
    public void test1() {
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
    public void test2() {
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
    public void test3() {
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(weightList);
    }

    public static List<User> users = new ArrayList<>();

    {
        users.add(new User(1, "张三", 20, "13211112222"));
        users.add(new User(4, "jack", 15, "13266668888"));
        users.add(new User(3, "李四", 60, "13299999999"));
        users.add(new User(2, "jack", 10, "1323333444"));
    }

    @Test
    public void testSort1() {
        List<UserVo> userVoList = users.stream()
                .map(u -> MyBeanUtils.copyProperties(u, UserVo.class))
                .sorted(Comparator.comparingInt(UserVo::getAge))
                .collect(Collectors.toList());

        userVoList.stream()
                .forEach(System.out::println);
    }

    @Test
    public void testSort2() {
        List<UserVo> userVoList = users.stream()
                .map(u -> {
                    UserVo uv = new UserVo();
                    BeanUtils.copyProperties(u, uv);
                    uv.setPhone(u.getTelephone());
                    return uv;
                }).sorted(Comparator.comparingInt(UserVo::getAge))
                .collect(Collectors.toList());

        userVoList.stream()
                .forEach(System.out::println);
    }

    @Test
    public void testSort3() {
        List<UserVo> userVoList = users.stream()
                .map(u -> {
                    UserVo uv = MyBeanUtils.copyProperties(u, UserVo.class);
                    uv.setPhone(u.getTelephone());
                    return uv;
                }).sorted(Comparator.comparingInt(UserVo::getAge))
                .collect(Collectors.toList());

        userVoList.stream()
                .forEach(System.out::println);
    }

    /**
     * comparing
     */
    @Test
    public void test4() {
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparing(apple -> apple.getWeight()));
        System.out.println(weightList);
    }


    @Test
    public void test5() {
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(weightList);
    }

    /**
     * comparingInt
     */
    @Test
    public void test51() {
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparingInt(Apple::getWeight));
        System.out.println(weightList);
    }

    /**
     * 倒序排
     */
    @Test
    public void test6() {
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort((Apple a1, Apple a2) -> a2.getWeight().compareTo(a1.getWeight()));
        System.out.println(weightList);
    }

    @Test
    public void test7() {
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparing(Apple::getWeight, Comparator.reverseOrder()));
        System.out.println(weightList);
    }

    @Test
    public void test71() {
        List<Apple> weightList = new ArrayList<>(inventory);
        System.out.println(weightList);
        weightList.sort(Comparator.comparingInt(Apple::getWeight).reversed());
        System.out.println(weightList);
    }

    /**
     * 按多个字段进行排序
     */
    @Test
    public void test10() {
        List<Apple> colorWeightList = new ArrayList<>(inventory);
        colorWeightList.sort(Apple::compareByColorWeight);
        System.out.println(colorWeightList);
        System.out.println(JSON.toJSONString(colorWeightList));
    }

    /**
     * 按多个字段进行排序
     * 先按颜色排序，颜色相同再按重量排序
     */
    @Test
    public void test100() {
        List<Apple> colorWeightList = new ArrayList<>(inventory);
        Collections.sort(colorWeightList, (a1, a2) -> {
            if (a1.getColor().equals(a2.getColor())) {
                //正排序
//                return Integer.compare(a1.getWeight(),a2.getWeight());
                //倒排序
                return -Integer.compare(a1.getWeight(), a2.getWeight());
            } else {
                return a1.getColor().compareTo(a2.getColor());
            }
        });
        colorWeightList.forEach(System.out::println);
    }

    /**
     * thenComparing
     * thenComparingInt
     * 按多个字段进行排序
     */
    @Test
    public void test11() {
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
    public void test20() {
        List<Integer> numList1 = Arrays.asList(1, 4, 2, 6, 2, 8);
        numList1.sort(Comparator.naturalOrder());
        System.out.println(numList1);

        List<String> strList1 = Arrays.asList("d", "f", "c", "a", "x", "H", "D", "A", "Y");
        strList1.sort(Comparator.naturalOrder());
        System.out.println(strList1);
    }

    /**
     * reverseOrder
     * 按自然顺序的反序排序
     * 要求排序的 Integer,String 必须实现 Comparable 接口，默认已经实现
     */
    @Test
    public void test21() {
        List<Integer> numList1 = Arrays.asList(1, 4, 2, 6, 2, 8);
        numList1.sort(Comparator.reverseOrder());
        System.out.println(numList1);

        List<String> strList1 = Arrays.asList("d", "f", "c", "a", "x", "H", "D", "A", "Y");
        strList1.sort(Comparator.reverseOrder());
        System.out.println(strList1);
    }

    /**
     *  Comparator.comparing(Function keyExtractor)生成1个Comparator对象，要求keyExtractor.apply()返回值一定要实现Comparable接口。
     *  比如下面代码extractIdWay1和extractIdWay2都是等价的，从Student对象中提取id属性，而id是int类型(Integer实现了Comparable)。
     *  流中比较对象
     */
    @Test
    public void test80() {
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
    public void test81() {
        Comparator<Address> cmpPro = Comparator.comparing(Address::getProvince);
        Comparator<Student> byAddress = Comparator.comparing(Student::getAddress, cmpPro);
        List<Student> sortedAddressList = studentsSource.stream()
                .sorted(byAddress)
                .collect(Collectors.toList());
        //这种形式的comparing()接收2个参数，第一个参数提取要排序的key，第二个参数指定排序的Comparator。
        System.out.println(sortedAddressList);
        System.out.println();

        List<Student> sortAddressList2 = studentsSource.stream()
                .sorted(Comparator.comparing(Student::getAddress, Comparator.comparing(Address::getProvince)))
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
    public void test83() {
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
    public void test22() {
        List<String> strList1 = Arrays.asList("d", "a", null, "x", "D", null, "A");
        strList1.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println(strList1);

        List<String> strList2 = Arrays.asList("d", "a", null, "x", "D", null, "A");
        strList2.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(strList2);
    }


    /**
     * list 使用 map 去重，然后排序
     */
    @Test
    public void test23() {
        List<Worker> Workers = getWorkerList();
        //利用Collectors.toMap去重
        //values.stream id排序的功能
        String nameMsg = Workers.stream()
                .filter(s -> s.getAge() <= 100)
                //此步骤可以根据 id  去重
                .collect(Collectors.toMap(Worker::getId, Function.identity(), (oldValue, newValue) -> oldValue))
                .values()
                .stream()
                //根据 id 排序
                .sorted(Comparator.comparing(Worker::getId))
                .map(Worker::getName)
                .collect(Collectors.joining("|"));
        System.out.println(nameMsg);
    }

    /**
     *  list 转 map，value 为 list
     *  然后 value list  转为 string
     */
    @Test
    public void test24(){
        List<Worker> workers = getWorkerList();
        //获取 地址 对应的 工人列表
        Map<String, List<Worker>> address$WorkMap = workers.stream()
                .collect(Collectors.groupingBy(Worker::getAddress));

        //获取 地址 对应的工人列表对应的字符串名称
        Map<String, String> address$namesStr = address$WorkMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                                .stream()
                                .map(c -> c.getName())
                                .collect(Collectors.joining(","))
                        )
                );

        // 拼接为地址:工人名称列表
        StringBuilder msgContentSb = new StringBuilder();
        address$namesStr.entrySet()
                .stream()
                //根据 key 排序
                .sorted(Map.Entry.comparingByKey())
                //有序遍历
                .forEachOrdered((entry) ->{
                    msgContentSb.append("|")
                            .append(entry.getKey())
                            .append(":")
                            .append(entry.getValue());
                });
        String msgContent = msgContentSb.toString();
        System.out.println(msgContent);
    }

    @Test
    public void test25(){
        List<Worker> workers = getWorkerList();
        //获取 地址 对应的 工人列表
        Map<String, List<Worker>> address$WorkMap = workers.stream()
                .collect(Collectors.groupingBy(Worker::getAddress));

        //获取 地址 对应的工人列表对应的字符串名称
        LinkedHashMap<String, String> address$namesStr = address$WorkMap.entrySet()
                .stream()
                //根据 key 排序
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        // value 变为 list 集合
                        e -> e.getValue()
                                .stream()
                                .map(c -> c.getName())
                                .collect(Collectors.joining(",")),
                        // key 重复时，采取的策略，保留 老值
                        (oldValue, newValue) -> oldValue,
                        // 使用 LinkedHashMap 接收，保留顺序
                        LinkedHashMap::new)
                );

        System.out.println("LinkedHashMap  address$namesStr");
        System.out.println(address$namesStr);

        //.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // 拼接为地址:工人名称列表
        StringBuilder msgContentSb = new StringBuilder();
        address$namesStr.entrySet()

                //上面排好序了，此处可以不用排序和按序遍历了
                //.stream()
                //根据 key 排序
                //.sorted(Map.Entry.comparingByKey())
                //有序遍历
                //.forEachOrdered((entry) ->{

                .forEach((entry) ->{
                    msgContentSb.append("|")
                            .append(entry.getKey())
                            .append(":")
                            .append(entry.getValue());
                });
        String msgContent = msgContentSb.toString();
        System.out.println(msgContent);
    }

    private List<Worker> getWorkerList() {
        List<Worker> Workers = new ArrayList<>();
        Workers.add(new Worker(5, "关羽", 22, "北京"));
        Workers.add(new Worker(8, "张飞", 11, "天津"));
        Workers.add(new Worker(8, "张飞", 11, "天津"));
        Workers.add(new Worker(2, "曹操", 56, "上海"));
        Workers.add(new Worker(9, "刘备", 32, "北京"));
        Workers.add(new Worker(1, "诸葛亮", 26, "温州"));
        Workers.add(new Worker(9, "刘备", 32, "北京"));
        Workers.add(new Worker(9, "刘备", 32, "北京"));
        Workers.add(new Worker(4, "孙权", 18, "上海"));
        return Workers;
    }

    public void printList(List<Worker> Workers) {
        for (Worker Worker : Workers) {
            System.out.println(Worker);
        }
        System.out.println();
    }

    class Worker {
        private Integer id;
        private String name;
        private Integer age;
        private String address;

        public Worker() {
        }

        public Worker(Integer id, String name, Integer age, String address) {
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
            Worker Worker = (Worker) o;
            return id.equals(Worker.id) &&
                    name.equals(Worker.name) &&
                    age.equals(Worker.age) &&
                    address.equals(Worker.address);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, age, address);
        }

        @Override
        public String toString() {
            return "Worker{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    '}';
        }
    }


}
