package com.lyp.learn.others;


import com.lyp.learn.bean.Address;
import com.lyp.learn.bean.Student;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Optional<T> 类，是一个容器类，代表一个值存在或不存在，原来用 null 表示一个值不存在，
 * 现在 Optional 可以更好的表达这个概念。并且可以避免空指针异常。
 *
 * Optional是一个容器对象，它可能包含，也可能不包含一个非空的值,
 * 如果这个值存在，isPresent方法将返回true,get方法将会返回它本身。
 *
 * Optional提供一些额外的方法，这些方法依赖于它所包含的对象存在与否，
 * 例如 orElse如果这个包含的对象不存在，将会返回一个默认值；
 * ifPresent方法，如果包含的值存在，则会执行方法块中的内容
 *
 * 这是一个基于值的class类，对于同一性（特性）敏感的操作 （包含引用的相等性如:==）,
 * 同一性的hashcode或者同步等等、对optional实例可能会产生不可预料的结果，这种结果应该被避免。
 *
 * Optioanal通常作为方法的返回值来使用，它可以有效的规避返回null的结果，
 * 如果一个类需要序列化，当Optional作为参数类型或是成员变量类型是有问题的。
 * 因为Optional没有实现序列化，所以Optioanl通常不被建议作为参数或常量使用
 *
 * 这里的Optional构造方法是私有的，也就是说不允许外部通过new的形式创建对象。构造方法是供Optional类内部使用。
 * Optional内部维护这个一个value的变量，无参数构建的时候value为null
 *
 * 使用 Optional 时尽量不直接调用 Optional.get() 方法, Optional.isPresent() 更应该被视为一个私有方法,
 * 应依赖于其他像 Optional.orElse(), Optional.orElseGet(), Optional.map() 等这样的方法
 *------------------------------------------------------------------------------
 * Null的含糊语义让人很不舒服。Null很少可以明确地表示某种语义，
 * 例如，Map.get(key)返回Null时，可能表示map中的值是null，亦或map中没有key对应的值。
 * Null可以表示失败、成功或几乎任何情况。使用Null以外的特定值，会让你的逻辑描述变得更清晰。
 *
 * Guava用Optional<T>表示可能为null的T类型引用。
 * 一个Optional实例可能包含非null的引用（我们称之为引用存在），也可能什么也不包括（称之为引用缺失）。
 * 它从不说包含的是null值，而是用存在或缺失来表示。但Optional从不会包含null值引用。
 *
 * 若T类型数据可以为null，Optional<T>是用来以非空值替代T数据类型的一种方法。
 * 一个Optional对象可以包含一个非空的T引用（这种情况下我们称之为“存在的”）或者不包含任何东西（这种情况下我们称之为“空缺的”）。
 * 但Optional从来不会包含对null值的引用。
 *
 * Optional<T>的最常用价值在于，
 * 例如，假设一个方法返回某一个数据类型，调用这个方法的代码来根据这个方法的返回值来做下一步的动作，
 * 若该方法可以返回一个null值表示成功，或者表示失败，在这里看来都是意义含糊的，所以使用Optional<T>作为返回值，
 * 则后续代码可以通过isPresent()来判断是否返回了期望的值（原本期望返回null或者返回不为null，其意义不清晰），并且可以使用get()来获得实际的返回值。
 *
 * 容器类型与null：
 *   List：允许重复元素，可以加入任意多个null。
 *   Set：不允许重复元素，最多可以加入一个null。
 *   Map：Map的key最多可以加入一个null，value字段没有限制。
 *   数组：基本类型数组，定义后，如果不给定初始值，则java运行时会自动给定值。引用类型数组，不给定初始值，则所有的元素值为null。
 *
 * null的其他作用
 *   1、判断一个引用类型数据是否null。 用==来判断。
 *   2、释放内存，让一个非null的引用类型变量指向null。这样这个对象就不再被任何对象应用了。等待JVM垃圾回收机制去回收。
 *
 * null的使用建议：
 * 　1、在Set或者Map中使用null作为键值指向的value，千万别这么用。很显然，在Set和Map的查询操作中，将null作为特殊的例子可以使查询结果更浅显易懂。
 * 　2、在Map中包含value是null值的键值对，你应该把这种键值对移出map，使用一个独立的Set来包含所有null或者非null的键。
 *     很容易混淆的是，一个Map是不是包含value是　null的key，还是说这个Map中没有这样的键值对。
 *     最好的办法就是把这类key值分立开来，并且好好想想到底一个value是null的键值对对于你的程序来说到底意味着什么。
 * 　3、在列表中使用null，并且这个列表的数据是稀疏的，或许你最好应该使用一个Map<Integer,E>字典来代替这个列表。
 *     因为字典更高效，并且也许更加精确的符合你潜意识里对程序的需求。
 * 　4、想象一下如果有一种自然的“空对象”可以使用，比方说对于枚举类型，添加一个枚举常数实例，这个实例用来表示你想用null值所表示的情形。
 *    比如：Java.math.RoundingMode有一个常数实例UNNECESSARY来表示“不需要四舍五入”，
 *    任何精度计算的方法若传以RoundingMode.UNNECESSARY为参数来计算，必然抛出一个异常来表示不需要舍取精度
 *
 *
 *   Optional实际上是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 *   Optional类的Javadoc描述如下：这是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 *
 *   Optional<T> 类(java.util.Optional) 是一个容器类，代表一个值存在或不存在，原来用 null 表示一个值不存在，
 *   现在 Optional 可以更好的表达这个概念。并且可以避免空指针异常。
 *      常用方法：
 *        Optional.empty() : 创建一个空的 Optional 实例
 *        Optional.of(T t) : 创建一个 Optional 实例
 *        Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 *        isPresent() : 判断是否包含值
 *        T get(): 如果调用对象包含值，返回该值，否则抛异常
 *        orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 *        orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 *        map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 *       flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 *
 */

public class OptionalDemo {
    /**
     *  我们需要来了解一下Java中null。
     *  因为，只有我们深入的了解了null的相关知识，我们才能更加深入体会领悟到Optional设计和使用上的优雅和简单。
     *
     *  null代表不确定的对象：
     * 　　Java中，null是一个关键字，用来标识一个不确定的对象。因此可以将null赋给引用类型变量，但不可以将null赋给基本类型变量。
     *
     * 　　Java中，变量的使用都遵循一个原则：先定义，并且初始化后，才可以使用。
     *    例如如下代码中，我们不能定义int age后，不给age指定值，就去打印age的值。
     *    这条对对于引用类型变量也是适用的（String name也同样适用），在编译的时候就会提示为初始化。
     */
    @Test
    public void testNull(){
        int age;
//        System.out.println("user age :"+age);

        Integer sum;
//        System.out.println("sum is :" + sum);

        long money;
        money = 10L;
        System.out.println("user money :"+money);

        String name;
//        System.out.println("user name :"+name);
    }

    /**
     * 在Java中，Java默认给变量赋值：在定义变量的时候，如果定义后没有给变量赋值，则Java在运行时会自动给变量赋值。
     * 赋值原则是整数类型int、byte、short、long的自动赋值为0，
     * 带小数点的float、double自动赋值为0.0，
     * boolean的自动赋值为false，
     * 其他各供引用类型变量自动赋值为null
     * 上面代码会变为如下可运行代码：
     */
    @Test
    public  void testNullRun(){
        int age = 0;
        System.out.println("user age :"+age);

        Integer sum = null;
        System.out.println("sum is :" + sum);

        long money;
        money = 10L;
        System.out.println("user money :"+money);

        String name = null;
        System.out.println("user name :"+name);
    }

    /**
     * null本身不是对象，也不是Objcet的实例：
     * 　　null只是一个关键字，用来标识一个不确定的对象，他既不是对象，也不是Objcet对象的实例。
     * 下面我们通过代码确定一下
     */
    @Test
    public static void testNullObject() {
        if (null instanceof java.lang.Object) {
            System.out.println("null属于java.lang.Object类型");
        } else {
            System.out.println("null不属于java.lang.Object类型");
        }
    }

    /**
     * of
     * 方法接收一个T参数，T必需为非null值，返回一个Optional对象。
     *
     * ofNullable
     * 方法接收一个T参数，如果T为null，它会调用empty方法，如果不为null则调用of方法。
     * ofNullable方法接收一个可能为null的参数，将参数的值赋给Optional类中的成员变量value
     */
    @Test
    public void test1(){
        String str1 = "hello";
        String str2 = null;

        Optional<String> optStr1 = Optional.of(str1);
        System.out.println(optStr1.get());
        //下面这句执行报 java.lang.NullPointerException
        //Optional<String> optStr2 = Optional.of(str2);


        Optional<String> optStr2 = Optional.ofNullable(str2);
        System.out.println(optStr2.isPresent() ? optStr2.get() : optStr2.orElse("空对象"));
    }

    /**
     * empty
     * 创建一个空的 Optional 实例
     */
    @Test
    public void test201(){
        Optional<String> strOpt = Optional.empty();
        System.out.println(strOpt);

        Optional<Person> personOpt = Optional.empty();
        System.out.println(personOpt);
    }

    /**
     * get
     * 如果这个值存在，则返回这个值，如果这个值为null，则抛出异常。
     *
     * get()是这些方法中最简单但又最不安全的方法。
     * 如果变量存在，它直接返回封装的变量值，否则就抛出一个NoSuchElementException异常。
     * 所以，除非你非常确定Optional变量一定包含值，否则使用这个方法是个相当糟糕的主意。
     * 此外，这种方式即便相对于嵌套式的null检查，也并未体现出多大的改进。
     */
    @Test
    public void test222(){
        String str1 = "hello";
        String str2 = null;

        Optional<String> optStr1 = Optional.of(str1);
        System.out.println(optStr1.get());

        Optional<String> optStr2 = Optional.ofNullable(str2);
        //下面这句，执行报错 java.util.NoSuchElementException: No value present
        // System.out.println(optStr2.get());
    }

    /**
     * orElse
     * 存在即返回, 无则提供默认值
     * 接收一个参数，如果存在，返回这个值本身，否则返回返回这个参数。
     *
     * 不管Optional 对象中是否有值，orElse 中指定的操作 都执行了
     *
     * return user.orElse(null);  //而不是 return user.isPresent() ? user.get() : null;
     * return user.orElse(UNKNOWN_USER);
     */
    @Test
    public void test2(){
        String str1 = "hello";
        String str2 = null;

        Optional<String> optStr1 = Optional.of(str1);
        System.out.println(optStr1.orElse("空对象"));
        System.out.println(optStr1.orElse(createStr()));
        System.out.println();

        Optional<String> optStr2 = Optional.ofNullable(str2);
        System.out.println(optStr2.orElse("空对象"));
        System.out.println(optStr2.orElse(createStr()));

    }

    public String createStr(){
        String str = new String("create str");
        System.out.println("createStr method execute....");
        return str;
    }

    /**
     * orElseGet
     * 存在即返回, 无则由函数来产生
     * 接收一个Supplier，如果Optional对象存在值时,返回这个值; 否则返回Supplier对象。
     *
     * 当Optional对象中的值不存在时，才会执行 orElseGet中指定的操作，
     *
     * orElseGet(Supplier<? extends T> other)是orElse方法的延迟调用版，
     * Supplier方法只有在Optional对象不含值时才执行调用。
     * 如果创建默认值是件耗时费力的工作，你应该考虑采用这种方式（借此提升程序的性能），
     * 或者你需要非常确定某个方法仅在Optional为空时才进行调用，也可以考虑该方式（这种情况有严格的限制条件)
     *
     *
     * return user.orElseGet(() -> fetchAUserFromDatabase()); //而不要 return user.isPresent() ? user: fetchAUserFromDatabase();
     */
    @Test
    public void test22(){
        String str1 = "hello";
        String str2 = null;

        Optional<String> optStr1 = Optional.of(str1);
        System.out.println(optStr1.orElseGet(() -> "空对象"));
        System.out.println(optStr1.orElseGet(() -> createStr()));
        System.out.println("--------");

        Optional<String> optStr2 = Optional.ofNullable(str2);
        System.out.println(optStr2.orElseGet(() -> "空对象"));
        System.out.println(optStr2.orElseGet(() -> createStr()));
        System.out.println(optStr2.orElseGet(this::createStr));
    }

    /**
     * orElseThrow(Supplier<? extends X> exceptionSupplier)和get方法非常类似，
     * 它们遭遇Optional对象为空时都会抛出一个异常，但是使用orElseThrow你可以定制希望抛出的异常类型
     */
    @Test
    public void test220() throws Exception{
        String str1 = "hello";
        String str2 = null;

        Optional.ofNullable(str1).orElseThrow(() -> new Exception("对象是空 str1"));

        Optional.ofNullable(str2).orElseThrow(() -> new Exception("对象是空 str2"));
    }

    /**
     * isPresent
     * 如果这个对象的值不为null返回true,否则返回false。
     */
    @Test
    public void test3(){
        String str1 = "hello";
        String str2 = null;

        Optional<String> optStr1 = Optional.of(str1);
        System.out.println(optStr1.isPresent());
        System.out.println(optStr1.isPresent() ? optStr1.get() : "无值");
        System.out.println();

        Optional<String> optStr2 = Optional.ofNullable(str2);
        System.out.println(optStr2.isPresent());
        System.out.println(optStr2.isPresent() ? optStr2.get() : "无值啊");
    }

    /**
     * ifPresent
     * 方法接收一个consumer函数式接口
     * 如果对象存在，就执行给定的操作;反之，什么也不做
     *
     * ifPresent(Consumer<? super T>)
     * 让你能在变量值存在时执行一个作为参数传入的方法，否则就不进行任何操作
     *
     * user.ifPresent(System.out::println);
     *
     * //而不要下边那样
     * if (user.isPresent()) {
     *   System.out.println(user.get());
     * }
     */
    @Test
    public void test4(){
        String str1 = "hello";
        String str2 = null;

        Optional<String> optStr1 = Optional.of(str1);
        optStr1.ifPresent(s -> System.out.println(s));

        Optional<String> optStr2 = Optional.ofNullable(str2);
        optStr2.ifPresent(s -> System.out.println(s));

        System.out.println("--------");
    }

    /**
     * orElse(T other) 不论容器是否为空,只要调用该方法, 则对象other一定存在
     * orElseGet(Supplier<? extends T> supplier) 只有当容器为空时,才调用supplier.get()方法产生对象
     *
     * 请想象以下场景:
     *    在分布式开发中,有一个方法需要接收前台数据,但该数据可能为空,
     *    当该数据为空时,需要远程调用一个方法为其设置默认值
     *
     * 结果为orElse调用方法,orElseGet没有调用方法
     *      可以看到,虽然opt不为空,但是orElse()依然调用了远程方法,并产生了一个String对象
     *      orElseGet并没有调用方法,也没有产生任何对象
     *
     *  所以可见orElseGet()更优, 但代价就是需要传入一个Supplier<T>类型的参数,相对会麻烦一些.
     */
    @Test
    public void testOrElse() {
        Optional<String> opt = Optional.of("前端数据");

        System.out.println("---orElse 调用---");
        String x = opt.orElse(getDefaultValue());
        System.out.println("x = " + x);

        System.out.println();
        System.out.println("---orElseGet 调用---");
        String y = opt.orElseGet(() -> getDefaultValue());
        System.out.println("y = " + y);

    }

    public String getDefaultValue(){  //远程方法调用
        System.out.println("我被调用了!");
        return "我是默认值";
    }

    /**
     * filter
     * filter方法在Optional中value不为空的情况下对Optional中的值进行过滤
     *
     * 如果值存在并且满足提供的谓词，就返回包含该值的 Optional 对象；否则返回一个空的Optional 对象
     *
     * filter方法接受一个谓词作为参数。
     * 如果Optional对象的值存在，并且它符合谓词的条件，filter方法就返回其值；
     * 否则它就返回一个空的Optional对象
     */
    @Test
    public void test5(){
        String str1 = "hello";
        String str2 = null;

        if(null != str1 && "hello".equals(str1)){
            System.out.println("旧的写法，str1 显式判断null");
        }

         Optional.ofNullable(str1)
                .filter(s -> "hello".equals(s))
                .ifPresent(x -> System.out.println("新的写法，str1 自动检测null"));

        System.out.println("---------------");

        if(null != str2 && "hello".equals(str2)){
            System.out.println("旧的写法，str2 显式判断null");
        }

        Optional.ofNullable(str2)
                .filter(s -> "hello".equals(s))
                .ifPresent(x -> System.out.println("新的写法，str2 自动检测null"));

        System.out.println("---------------");

        String str1Out = Optional.of(str1)
                                    .filter(s -> s.length() > 3)
                                    .orElse(null);
        System.out.println(str1Out);

        String str1OutOut = Optional.of(str1)
                                    .filter(s -> s.length() > 10)
                                    .orElse(null);
        System.out.println(str1OutOut);
        System.out.println("-------------");



    }


    /**
     * map
     * 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     *
     * 接收一个Function,
     * 如果Optioanal为null则抛出异常（所以这里创建Optional对象时建议用Optional.ofNullable()），
     * 如果为空值则返回空，如果不为空则返回Function的返回值。
     *
     * 首先从无需显式检查的Optional值的使用入手,
     * 使用 map 从 Optional 对象中提取和转换值
     *
     * map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中。这就使对返回值进行链试调用的操作成为可能
     */
    @Test
    public void test10(){
        //例如：一个对象Obj，有一个List属性，如果List有值，返回List,否则返回空集合，可以这么写
        Parent parent1 = new Parent();
        Optional<Parent> parentValue1 = Optional.ofNullable(parent1);
        System.out.println(parentValue1.map(m -> m.getList()).orElse(Collections.emptyList()));
        System.out.println();

        Parent parent2 = new Parent();
        List<Object> list2 = Arrays.asList("张三","李四");
        parent2.setList(list2);

        Optional<Parent> parentValue2 = Optional.ofNullable(parent2);
        System.out.println(parentValue2.map(m -> m.getList()).orElse(Collections.emptyList()));
        System.out.println();

        Optional<String> opt = Optional.ofNullable("nihao");
        System.out.println(opt.map(m -> m + "  123")
                                .orElseGet(() -> "world"));

        //map  是可能无限级联的, 比如再深一层, 获得大写形式
        Optional<String> opt2 = Optional.ofNullable("bb aa");
        String strOpt2 = opt2.map(m -> m + " 123 aa bb")
                             .map(m -> m.toUpperCase())
                             .orElse(null);
        System.out.println(strOpt2);
    }

    @Test
    public void test11(){
        Student student1 = new Student(1,22,"张三", new Address("河南省","商丘市",476000));
        oldGetProvince(student1);
        newGetProvince(student1);
        System.out.println("-----------");

        Student student2 = new Student(1,22,"张三", new Address());
        oldGetProvince(student2);
        newGetProvince(student2);
        System.out.println("-----------");

        Student student3 = new Student();
        oldGetProvince(student3);
        newGetProvince(student3);
        System.out.println("-----------");


    }

    private void oldGetProvince(Student student) {
        if(null != student){
            Address address = student.getAddress();
            if(null != address){
                String province = address.getProvince();
                if(null != province && !"".equals(province)){
                    System.out.println("old 省 is " + province);
                }else{
                    System.out.println("old province is null");
                }
            }else{
                System.out.println("old address is null");
            }
        }
    }

    private String oldGetReturnProvince(Student student) {
        String str = "unknown";
        if(null != student){
            Address address = student.getAddress();
            if(null != address){
                String province = address.getProvince();
                if(null != province && !"".equals(province)){
                    str = province;
                    System.out.println("old 省 is " + province);
                }else{
                    System.out.println("old province is null");
                }
            }else{
                System.out.println("old address is null");
            }
        }
        return str;
    }

    private void newGetProvince(Student student){
        Optional.ofNullable(student)
                .map(s -> s.getAddress())
                .map(Address::getProvince)
                .filter(p -> !"".equals(p))
                .ifPresent(s -> System.out.println("new province is :" + s));
    }

    /**
     * flatMap
     * map和flatMap对Optional中的对象进行转换值的操作，这两个方法唯一的区别就是接受的参数不同
     * flatMap处理的参数为Optional类型；
     *
     * flatMap方法会将这种两层的Optional对象转换为包含单层Optional对象
     *
     * flatMap() 也需要函数作为参数，并对值调用这个函数，然后直接返回结果。
     */
    @Test
    public void test12(){
        Student student1 = new Student(1,22,"张三", new Address("河南省","商丘市",476000));
        Address address1 = Optional.ofNullable(student1)
                                    .map(Student::getAddress)
                                    .orElse(null);
        System.out.println(address1);

        Address address11 = Optional.ofNullable(student1)
                                    .flatMap(s -> Optional.ofNullable(s.getAddress()))
                                    .orElse(null);
        System.out.println(address11);
    }

    @Test
    public void test13(){
        Insurance insurance = new Insurance("安保保险");
        Car car = new Car(Optional.ofNullable(insurance));
        Person person = new Person(Optional.ofNullable(car));

        Optional<Person>  optionalPerson = Optional.ofNullable(person);

        String insuranceName = getCarInsuranceName(optionalPerson);
        System.out.println(insuranceName);

        Car car2 = new Car(Optional.ofNullable(null));
        Person person2 = new Person(Optional.ofNullable(car2));

        Optional<Person>  optionalPerson2 = Optional.ofNullable(person2);

        String insuranceName2 = getCarInsuranceName(optionalPerson2);
        System.out.println(insuranceName2);

    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown"); //如果Optional的结果值为空，设置默认值
    }



}


class Parent{
    private List<Object> list;

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}


class Person {
    private Optional<Car> car;

    public Person() {
    }

    public Person(Optional<Car> car) {
        this.car = car;
    }

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}

class Car {
    private Optional<Insurance> insurance;

    public Car() {
    }

    public Car(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}

class Insurance {
    private String name;

    public Insurance() {
    }

    public Insurance(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }
}


