package com.lyp.learn.streampk;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lyp.learn.bean.Apple;
import com.lyp.learn.bean.Dish;
import com.lyp.learn.bean.QuarterEnum;
import com.lyp.learn.bean.QuarterVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 *   Stream到底是什么呢？
 *     是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
 *     “集合讲的是数据，Stream讲的是计算！”
 *
 *    注意：
 *       ①Stream 自己不会存储元素。
 *       ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 *       ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * 一、Stream API 的操作步骤：
 * 1. 创建 Stream
 *     一个数据源（如：集合、数组），获取一个流
 * 2. 中间操作
 *     一个或多个中间操作链，对数据源的数据进行处理
 * 3. 终止操作(终端操作)
 *     一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 */
public class StreamTest {

    // 创建 stream 流的几种方式

    /**
     * 1. 通过集合
     *   Java8 中的 Collection 接口被扩展，提供了两个获取流的方法：
     *   Collection 提供了两个方法  stream() 与 parallelStream()
     *
     *   default Stream<E> stream() : 返回一个顺序流
     *   default Stream<E> parallelStream() : 返回一个并行流
     */
    @Test
    public void testCollectionStream() {
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream(); //获取一个顺序流
        Stream<String> parallelStream = list.parallelStream(); //获取一个并行流
    }

    /**
     *  2. 创建 Stream方式二：由数组创建流
     *   Java8 中的 Arrays 的静态方法 stream() 可以获取数组流：
     *
     *   static <T> Stream<T> stream(T[] array): 返回一个流
     *
     *    重载形式，能够处理对应基本类型的数组：
     *       public static IntStream stream(int[] array)
     *       public static LongStream stream(long[] array)
     *      public static DoubleStream stream(double[] array)
     */
    @Test
    public void testArraysStream() {
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        int[] intArr = {5, 3, 2, 7, 8, 3};
        IntStream intStream = Arrays.stream(intArr);
        int intSum = intStream.sum();
        System.out.println(intSum);

        System.out.println();

        String[] strArr = new String[]{"aa", "bb", "cc"};
        Stream<String> strStream = Stream.of(strArr);
        strStream.forEach(System.out::println);

    }

    /**
     * 3. 通过 Stream 类中静态方法 of()
     *
     *   可以调用Stream类静态方法 of(), 通过显示值创建一个流。它可以接收任意数量的参数。
     *   public static<T> Stream<T> of(T... values) : 返回一个流
     */
    @Test
    public void testStreamOf() {
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<String> strStream = Stream.of("aa", "bb", "ff", "bb", "gg");
        strStream.forEach(s -> System.out.print(s + ", "));
    }


    /**
     * 有 值产生流
     * 对于基本数值型，目前有三种对应的包装类型 Stream：IntStream、LongStream、DoubleStream
     *
     * 当然我们也可以用 Stream<Integer>、Stream<Long> >、Stream<Double>，但是 boxing 和 unboxing 会很耗时，
     * 所以特别为这三种基本数值型提供了对应的 Stream。
     */
    @Test
    public void testNum() {
        IntStream.of(new int[]{1, 3, 9})
                .forEach(System.out::println);
        System.out.println("-------------");

        IntStream.range(1, 3)
                .forEach(System.out::println);
        System.out.println("------------");

        IntStream.rangeClosed(1, 3)
                .forEach(System.out::println);
    }

    /**
     * 创建一个空流
     */
    public void testEmptyStream() {
        Stream<String> strStream2 = Stream.empty();
    }


    /**
     *  创建 Stream方式四：创建无限流
     *   由函数生成流：创建无限流
     *  可以使用静态方法 Stream.iterate() 和 Stream.generate(), 创建无限流
     *
     *    迭代
     *       public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
     *    生成
     *       public static<T> Stream<T> generate(Supplier<T> s)
     */
    @Test
    public void testFunctionStream() {
        List<Integer> intList = Stream.iterate(5, n -> n + 5)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(intList);

        System.out.println("-----------");

        Stream.generate(Math::random)
                .limit(5)
                .forEach(t -> System.out.print(t + " , "));

        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }

    /**
     * 由文件生成流
     */
    @Test
    public void testFileStream() {
        List<String> uniqueWords = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("").getPath(), "data.txt"), Charset.forName("utf-8"))) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        uniqueWords.forEach(System.out::println);
    }

    /**
     * 斐波纳契
     */
    @Test
    public void testFeiBoNaQie() {
        //斐波纳契数列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(t -> System.out.print(t + " "));

        System.out.println();
        //斐波纳契元组序列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.print("(" + t[0] + "," + t[1] + ")  "));
    }


    /**
     *   2. 中间操作
     *     多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！
     *     而在终止操作时一次性全部处理，称为“惰性求值”
     */
    List<Apple> inventory = Arrays.asList(new Apple("green", 80, "黄土高原"),
            new Apple("green", 200, "黄河故道"),
            new Apple("red", 160, "渤海湾"),
            new Apple("yellow", 20, "渤海湾")
    );

    /**
     * 筛选与切片
     * filter——接收 Lambda ， 从流中排除某些元素。
     * limit——截断流，使其元素不超过给定数量。
     * skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */

    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void test200() {
        //所有的中间操作不会做任何的处理
        Stream<Apple> stream = inventory.stream()
                .filter((a) -> {
                    System.out.println("测试中间操作");
                    return a.getWeight() >= 100;
                });

        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test201() {
        Iterator<Apple> it = inventory.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void test202() {
        inventory.stream()
                .filter((a) -> {
                    System.out.println("短路！"); // &&  ||
                    return a.getWeight() >= 50;
                }).limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void test203() {
        inventory.parallelStream()
                .filter((a) -> a.getWeight() >= 50)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test204() {
        inventory.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 流中流
     * map 返回外层的流
     * filterCharacter 的返回值是一个流
     *
     * 映射
     * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     *
     *     map(Function f)	接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *     mapToDouble(ToDoubleFunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream。
     *     mapToInt(ToIntFunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 IntStream。
     *     mapToLong(ToLongFunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 LongStream。
     *    flatMap(Function f)	接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    List<String> words = Arrays.asList("aa", "bbb", "cc");

    @Test
    public void testStreamStream() {
        Stream<Stream<Character>> streamStream = words.stream()
                .map(StreamTest::filterCharacter);

        streamStream.forEach(stream -> {
            stream.forEach(System.out::println);
        });
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    @Test
    public void testFlapMap() {
        Stream<Character> cs = words.stream()
                .flatMap(StreamTest::filterCharacter);

        cs.forEach(System.out::println);
    }

    /**
     * List<List> 应用 flatMap 函数
     */
    @Test
    public void testFlapMapList() {
        List<List<String>> numsList = new ArrayList<>();
        numsList.add(Lists.newArrayList("aa", "bb", "cc"));
        numsList.add(Lists.newArrayList("c", "b", "a"));
        numsList.add(Lists.newArrayList("aaa", "bbb", "ccc"));
        numsList.add(Lists.newArrayList("bb", "cc", "aa"));

        numsList.stream()
                .forEach(System.out::println);

        System.out.println();

        numsList.stream()
                .forEachOrdered(System.out::println);
        System.out.println();

        List<String> numStrs = numsList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        numStrs.forEach(x -> System.out.println(x));
    }

    /**
     * map 查询BD
     */
    @Test
    public void testFlapMapQueryDb() {
        List<Integer> numList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<String> strList = ListUtils.partition(numList, 3)
                .stream()
                .map(partList -> queryDb(partList))
                //这里使用 List::stream 转换，不要使用Collection::stream
                .flatMap(List::stream)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(strList)) {
            System.out.println("strList 为空：" + strList);
            return;
        }
        System.out.println("strList ：" + strList);
    }

    private List<String> queryDb(List<Integer> nums) {
        return ListUtils.emptyIfNull(nums)
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * long dateSellCount = dateSells.stream()
     * .flatMap(dto -> dto.getSellPoiList().stream())
     * .mapToLong(SellPoi::getSellCount)
     * .sum();
     */

    @Test
    public void testList() {
        List numList = new ArrayList<>();
        numList.add("11");
        numList.add("22");
        numList.add(words);
        numList.add(33);
        numList.add(44);
        numList.addAll(words);

        System.out.println(numList);
    }

    /**
     *	sorted()——自然排序,产生一个新流，其中按自然顺序排序
     *	sorted(Comparator com)——定制排序,产生一个新流，其中按比较器顺序排序
     */


    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));


    /**
     * 创建流的几种方式
     */
    @Test
    public void test1() {
        //第一种 通过Stream接口的of静态方法创建一个流
        Stream<String> stream = Stream.of("hello", "world", "helloworld");

        //第二种 通过Arrays类的stream方法，实际上第一种of方法底层也是调用的Arrays.stream(values);
        String[] array = new String[]{"hello", "world", "helloworld"};
        Stream<String> stream2 = Arrays.stream(array);

        List<QuarterVo> quarterVoList = Arrays.stream(QuarterEnum.values())
                .map(qe -> {
                    QuarterVo qv = new QuarterVo();
                    BeanUtils.copyProperties(qe, qv);
                    qv.setName(qe.getValue());
                    return qv;
                }).collect(Collectors.toList());
        System.out.println("quarterVoList :" + quarterVoList);

        //第三种 通过集合的stream方法，该方法是Collection接口的默认方法，所有集合都继承了该方法
        //通过Collection得Stream（）方法（串行流）
        //或者 parallelStream（）方法（并行流）创建Stream。
        Stream<Dish> stream3 = menu.stream();
        Stream<Dish> stream33 = menu.parallelStream();

        //第四种 创建无限流(迭代、生成)
        //迭代（需要传入一个种子，也就是起始值，然后传入一个一元操作）
        Stream<Integer> stream4 = Stream.iterate(2, (x) -> x * 2).limit(5);
        stream4.forEach(System.out::println);
        System.out.println();

        //生成(无限产生对象)
        Stream<Double> stream44 = Stream.generate(() -> Math.random()).limit(3);
        stream44.forEach(System.out::println);

        //第五种  通过文件创建流
    }

    /**
     * filter
     * 谓词筛选
     */
    @Test
    public void test2() {
        System.out.println("-----------谓词筛选，选出所有素菜---------------------");
        List<Dish> vegetarianDish = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println(vegetarianDish);
        System.out.println();
    }


    /**
     * map
     * 方法接收一个Function参数，最后会返回一个Stream对象.
     * 记住，这里是一个中间操作。
     * map方法中的Function函数会传入一个参数，然后返回把这个参数经过处理后的结果
     */
    @Test
    public void test3() {
        System.out.println("---------------map 映射，取出所有菜名------");
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNames);
        System.out.println();
    }

    @Test
    public void test4() {
        System.out.println("-------------菜名 长度---------------------");
        List<Integer> dishNames3 = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNames3);
    }

    /**
     * 获取 3个高热量食物的名字
     */
    @Test
    public void test5() {
        System.out.println("---------获取 3个高热量食物的名字------------------");
        List<String> threeHighCaloriesDishName = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(threeHighCaloriesDishName);
        System.out.println();
    }

    /**
     * forEach 输出
     * Java8操作集合的时候可以直接foreach的原因也是在Iterable接口中也新增了一个默认方法：
     * forEach，该方法功能和 for 循环类似，但是允许 用户使用一个Lambda表达式作为循环体。
     *
     *   内部迭代
     *    使用 Collection 接口需要用户去做迭代，称为外部迭代
     *    相反，Stream API 使用内部迭代——它帮你把迭代做了
     */
    @Test
    public void test6() {
        System.out.println("-------------forEach 输出---------");
        Stream<Dish> dishStream = menu.stream();
        dishStream.forEach(System.out::println);
        System.out.println();

        Optional<Integer> optionalInteger = menu.parallelStream()
                .map(dish -> dish.getCalories())
                .findFirst();
        System.out.println(optionalInteger.isPresent() ? optionalInteger.get() : "kkkk");
    }

    /**
     * foreach 输出map
     */
    @Test
    public void testForeachMap() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "11");
        map.put("B", "22");
        map.put("C", "33");

        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    /**
     * foreEachOrdered
     * 表示严格按照顺序取数据
     *
     * 串行流stream, 与 foreEach 没有区别
     *
     * 并行流parallelStream，
     * forEach 在并行中，随机排列了,不保证顺序，效率更高；这个也可以看出来，
     * 在并行的程序中，如果对处理之后的数据，没有顺序的要求，使用forEach的效率，肯定是要更好的
     *
     * foreEachOrdered 输出的顺序与元素的顺序严格一致
     *
     */
    @Test
    public void test90() {
        List<Integer> numList1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        numList1.stream().forEach(System.out::print);
        System.out.println();
        numList1.stream().forEachOrdered(System.out::print);
        System.out.println();

        numList1.parallelStream().forEach(System.out::print);
        System.out.println();
        numList1.parallelStream().forEachOrdered(System.out::print);
        System.out.println();
    }

    /**
     * 流有串行和并行两种，
     * 串行流上的操作是在一个线程中依次完成，
     * 而并行流则是在多个线程上同时执行。
     *
     * 并行与串行的流可以相互切换：
     * 通过 stream.sequential() 返回串行的流，
     * 通过 stream.parallel() 返回并行的流。
     * 相比较串行的流，并行的流可以很大程度上提高程序的执行效率。
     *
     *  并行流就是把一个内容(数组或集合)分成多个数据块，并用不同的线程分别处理每个数据块的流，
     *  这样一来，你就可以自动把给定操作的工作负荷分配给多核处理器的所有内核，让它们都忙起来，
     *  整个过程无需程序员显示实现优化
     */
    @Test
    public void test93() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            double d = Math.random() * 1000;
            list.add(d + "");
        }

        //串行排序
        long start = System.nanoTime();//获取系统开始排序的时间点
        int count = (int) ((Stream) list.stream().sequential()).sorted().count();
        long end = System.nanoTime();//获取系统结束排序的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end - start);//得到串行排序所用的时间
        System.out.println("串行" + ms + "ms");

        //并行排序
        long start2 = System.nanoTime();//获取系统开始排序的时间点
        int count2 = (int) ((Stream) list.stream().parallel()).sorted().count();
        long end2 = System.nanoTime();//获取系统结束排序的时间点
        long ms2 = TimeUnit.NANOSECONDS.toMillis(end2 - start2);//得到并行排序所用的时间
        System.out.println("并行 " + ms2 + "ms");
    }

    /**
     *  Collections中包含parallelStream()方法，通过这个方法能够为Collections中的元素创建并行流。
     *  另外也可以调用stream的parallel()方法将一个顺序流转变为一个并行流的拷贝。
     *
     * 为了了解并行流的执行动作，下面的例子会打印当前线程的执行信息。
     */
    @Test
    public void test94() {
        Arrays.asList("b", "x", "a", "g", "p")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
    }


    /**
     * 大写输出菜名长度大于 6的菜名
     */
    @Test
    public void test7() {
        menu.stream()
                .map(Dish::getName)
                .filter(d -> d.length() > 6)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    /**
     * 通过分析调试输出，我们可以更好地了解哪一个线程执行了哪些stream操作。
     *从上面的输出中我们可以看到parallel stream使用了ForkJoinPool提供的所有可用的线程来执行流的各种操作。
     * 由于不能确定哪个线程会执行并行流的哪个操作，因此反复执行上面的代码，打印的结果会不同
     *
     * 扩充上面的例子，添加sort操作
     */

    @Test
    public void test96() {
        Arrays.asList("b", "x", "a", "g", "p")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n",
                            s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
    }
    /**
     * 这个执行结果看起来比较奇怪，看起来sort操作只是在main线程中顺序执行的。实际上，parallel stream中的sort操作使用了JAVA 8的一个新方法：Arrays.parallelSort()。JAVA doc中是这样描述Arrays.parallelSort()的：待排序数组的长度决定了排序操作是顺序执行还是并行执行。java doc 描述如下：
     *
     * If the length of the specified array is less than the minimum granularity, then it is sorted using the appropriate Arrays.sort method.
     */


    /**
     * concat
     * 可以把两个stream合并成一个stream（合并的stream类型必须相同）
     *  只能两两合
     */
    @Test
    public void test82() {
        Stream<Integer> numStream1 = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> numStream2 = Stream.of(2, 4, 6, 8);

        Stream.concat(numStream1, numStream2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * sort
     * 返回按自然顺序排序元素的流
     */
    @Test
    public void test20() {
        List<Integer> numList1 = Arrays.asList(1, 4, 2, 6, 2, 8);
        System.out.println("原始列表：numList1 ：" + numList1);
        List<Integer> numList11 = numList1.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("排序后 : numList11 : " + numList11);
        System.out.println("原始列表：numList1 ：" + numList1);
        System.out.println();


        List<String> strList1 = Arrays.asList("d", "f", "c", "a", "x", "H", "D", "A", "Y");
        System.out.println("原始列表：strList1 ：" + strList1);
        List<String> strList11 = strList1.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("排序后: strList11 : " + strList11);
        System.out.println("原始列表：strList1 ：" + strList1);
    }

    /**
     * sort
     * 指定比较器
     */
    @Test
    public void test21() {
        List<Integer> numList1 = Arrays.asList(1, 4, 2, 6, 2, 8);
        System.out.println("原始列表：numList1 ：" + numList1);
        List<Integer> numList11 = numList1.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("排序后 : numList11 : " + numList11);
        System.out.println("原始列表：numList1 ：" + numList1);
        System.out.println();


        List<String> strList1 = Arrays.asList("d", "f", "c", "a", "x", "H", "D", "A", "Y");
        System.out.println("原始列表：strList1 ：" + strList1);
        List<String> strList11 = strList1.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("排序后: strList11 : " + strList11);
        System.out.println("原始列表：strList1 ：" + strList1);
    }


    /**
     * peek
     * 为每个元素，执行给定的操作，更多的是debug调试时使用
     */
    @Test
    public void test71() {
        menu.stream()
                .map(Dish::getName)
                .peek(s -> System.out.println("过滤前:" + s))
                .filter(d -> d.length() > 6)
                .peek(s -> System.out.println("过滤后剩余元素--------------->" + s))
                .forEach(System.out::println);
    }

    /**
     * flatMap
     * 把多个流，汇集成一个流
     *
     * flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，
     * 最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
     *
     * flatMap方法。使用流时，flatMap方法接受一个函数作为参数，这个函数的返回值是另一个流。
     * 这个方法会应用到流中的每一个元素，最终形成一个新的流的流。
     * 但是flagMap会用流的内容替 换每个新生成的流。
     * 换句话说，由方法生成的各个流会被合并或者扁平化为一个单一的流
     */
    @Test
    public void test8() {
        Stream<List<Integer>> inputStream1 = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        String strList = inputStream1.collect(Collectors.toList()).toString();
        System.out.println(strList);


        Stream<List<Integer>> inputStream2 = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        List<Integer> integerList = inputStream2.flatMap(childList -> childList.stream())
                .map(i -> i * i)
                .collect(Collectors.toList());
        //1.首先我们创建了一个Stream对象，Stream中的每个元素都是容器List<Integer>类型，
        // 并使用三个容器list初始化这个Stream对象，
        //2.然后使用flatMap方法将每个容器中的元素映射到一个容器中，
        // 这时flatMap接收的参数Funciton的泛型T就是List<Integer>类型，返回类型就是T对应的Stream。
        // 3.最后再对这个容器使用map方法求出买个元素的平方
        System.out.println(integerList);
    }

    @Test
    public void test9() {
        System.out.println("------------单词单个不重复字符---------");
        String[] arrayOfWords1 = {"Hello", "World"};
        List<String[]> wordList1 = Arrays.stream(arrayOfWords1)
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(wordList1);
        System.out.println(JSON.toJSONString(wordList1));

        String[] arrayOfWords = {"Hello", "World"};
        List<String> wordLists = Arrays.stream(arrayOfWords)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(wordLists);
        System.out.println();
    }

    /**
     * 将二个集合中的元素做笛卡尔集，交叉拼接
     */
    @Test
    public void test91() {
        List<String> list1 = Arrays.asList("hello", "world", "today");
        List<String> list2 = Arrays.asList("a", "b", "c");

        list1.stream()
                .flatMap(
                        item -> list2.stream()
                                .map(item2 -> item + " " + item2)
                )
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * reduce
     *  这个方法的主要作用是把 Stream 元素组合起来。
     *  它提供一个起始值（种子），
     *  然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。
     *  从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。
     *  例如 Stream 的 sum 就相当于
     * Integer sum = integers.reduce(0, (a, b) -> a+b); 或
     * Integer sum = integers.reduce(0, Integer::sum);
     * 也有没有起始值的情况，这时会把 Stream 的前面两个元素组合起来，返回的是 Optional
     *
     * reduce方法是一个终止操作.
     * T reduce(T identity, BinaryOperator<T> accumulator);
     * reduce方法接收2个参数：第一个参数可以认为是初始值，第二个参数是计算方式的函数
     *
     *
     * reduce（BinaryOperator）此方法相对于上面方法来说，没有起始值，则有可能结果为空，
     * 所以返回的值会被封装到Optional中
     */
    @Test
    public void test10() {
        // 字符串连接，concat1 = "ABCD"
        String concat1 = Stream.of("A", "B", "C", "D")
                .reduce("", String::concat);
        System.out.println(concat1);

        // 过滤，字符串连接，concat = "ace"
        String concat2 = Stream.of("a", "B", "c", "D", "e", "F")
                .filter(x -> x.compareTo("Z") > 0)
                .reduce("", String::concat);
        System.out.println(concat2);

        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0)
                .reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);

        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4)
                .reduce(0, Integer::sum);
        System.out.println(sumValue);

        int sumValue2 = Stream.of(10, 20, 30)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumValue2);

        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4)
                .reduce(Integer::sum)
                .get();
        System.out.println(sumValue);

        Optional<Integer> sumValue5 = Stream.of(10, 25, 30)
                .reduce(Integer::sum);
        System.out.println(sumValue5.orElse(Integer.MIN_VALUE));
    }

    /**
     * iterate
     * iterate方法有两个参数，第一个是seed也可以称作种子，第二个是一个UnaryOperator.
     * 第一次生成的元素是UnaryOperator对seed执行apply后的返回值，之后所有生成的元素都是UnaryOperator对上一个apply的返回值再执行apply，不断循环。
     */
    @Test
    public void test11() {
        //从1开始，每个元素比前一个元素大2，最多生成10个元素
        Stream.iterate(1, item -> item + 2).limit(10).forEach(System.out::println);

    }

    @Test
    public void test12() {
        System.out.println("-----Collectors------joining-------------");
        String dishName = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining());
        System.out.println(dishName);

        String dishNam2 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        System.out.println(dishNam2);

        String dishName3 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(",", "starttt ----", "----enddd"));
        System.out.println(dishName3);
        System.out.println();
    }


    @Test
    public void test13() {
        System.out.println("----------将数字按质数和非质数分区-----------------------");
        Map<Boolean, List<Integer>> booleanPrime = IntStream.rangeClosed(1, 20)
                .boxed()
                .collect(Collectors.partitioningBy(condidate -> isPrime(condidate)));
        System.out.println(booleanPrime);
    }


    /**
     * 判断一个数是否是质数
     */
    private static boolean isPrime(int condidate) {
        int sqar = (int) Math.sqrt((double) condidate);
        return IntStream.rangeClosed(2, sqar)
                .noneMatch(i -> condidate % i == 0);
    }

    /**
     * intStream 生成 1到40连续的 List列表
     */
    @Test
    public void test30() {
        /**
         *  这段代码使用了IntStream.rangeClosed(1, 40)来生成一个从1到40的整数流，
         *  然后使用boxed()将IntStream转换为Stream<Integer>，
         *  最后使用collect(Collectors.toList())收集结果到一个List中。
         */
        List<Integer> intList = IntStream.rangeClosed(1, 40).boxed().collect(Collectors.toList());
        System.out.println(intList);
    }

}
