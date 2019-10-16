package com.lyp.learn.guava.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lists 类主要提供了对List类的子类构造以及操作的静态方法。
 * 在Lists类中支持构造 ArrayList、LinkedList 以及 newCopyOnWriteArrayList 对象的方法。
 *
 * 其中提供了以下构造ArrayList的函数：
 * 下面四个构造一个 ArrayList 对象，但是不显式的给出申请空间的大小：
 * newArrayList()   构造一个可变的、空的ArrayList实例.
 * newArrayList(E... elements)  构造一个可变的包含传入元素elements的ArrayList实例.
 * newArrayList(Iterable<? extends E> elements)   构造一个可变的包含传入元素elements的ArrayList实例.
 * newArrayList(Iterator<? extends E> elements)   构造一个可变的包含传入元素elements的ArrayList实例.
 *
 * 以下两个函数在构造 ArrayList 对象的时候给出了需要分配空间的大小:
 * newArrayListWithCapacity(int initialArraySize)   构造一个分配指定空间大小的ArrayList实例。.
 * newArrayListWithExpectedSize(int estimatedSize)  构造一个期望长度为estimatedSize的ArrayList实例.
 *
 * 如果你事先知道元素的个数，可以用 newArrayListWithCapacity 函数；
 * 如果你不能确定元素的个数，可以用 newArrayListWithExpectedSize 函数，
 * 在 newArrayListWithExpectedSize 函数里面调用了 computeArrayListCapacity(int arraySize) 函数
 *
 * 其中computeArrayListCapacity方法，计算容量时
 * 返回的容量大小为 5L + arraySize + (arraySize / 10)，当 arraySize 比较大的时候，给定大小和真正分配的容量之比为 10/11
 *
 * Lists 类还支持构造 LinkedList、newCopyOnWriteArrayList 对象
 * newLinkedList()
 * newLinkedList(Iterable<? extends E> elements)　　　
 * newCopyOnWriteArrayList()
 * newCopyOnWriteArrayList(Iterable<? extends E> elements)
 *
 * 我们还可以将两个（或三个）类型相同的数据存放在一个list中，这样可以传入到只有一个参数的函数或者需要减少参数的函数中，这些函数如下：
 * asList(E first, E[] rest)    返回一个不可变的List，其中包含指定的第一个元素和附加的元素数组组成，修改这个数组将反映到返回的List上.
 * asList(E first, E second, E[] rest)  返回一个不可变的List，其中包含指定的第一个元素、第二个元素和附加的元素数组组成，修改这个数组将反映到返回的List上.
 *
 */
public class ListsTest {
    /**
     * 可能有人疑问我用Lists.newArrayList(xxx)和直接new ArrayList<?>()有什么区别，其实就是省点打字的功夫。
     * 比如你创建一个List<Map<String,Map<String,Object>> 这样子的list，如果你要用new的话得还得费很大劲去敲键盘，
     * 其实用guava去声明只需要使用重载方法就行了：
     * List<Map<String,Map<String,Object>>> list = Lists.newArrayList();
     *
     *  List<String> test = new ArrayList<String>();
     *  List<String> test2 = Lists.newArrayList();
     *  List<String> test3 = Lists.newArrayList("1","2","3","4");
     *  List<String> test4 = Lists.newArrayList(test);
     */
    @Test
    public void test1(){
        List<String> list1 = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list1.add(i + "");
        }
        System.out.println("list1: " + list1);

        //2、传入多参数
        List<String> list2 = Lists.newArrayList("1", "2", "3");
        System.out.println("list2: " + list2);

        //3、传入数组
        List<String> list3 = Lists.newArrayList(new String[]{"aa", "bb"});
        System.out.println("list3: " + list3);

        //4、传入集合
        List<String> list4 = Lists.newArrayList(list1);
        System.out.println("list4: " + list4);

        ArrayList<List<String>> list5 = Lists.newArrayList(list2, list3);
        System.out.println("list5: " + list5);

        Iterable<String> concat = Iterables.concat(list2, list3);
        System.out.println("concat:" + concat);

        //5、使用条件：你确定你的容器会装多少个，不确定就用一般形式的
        //说明：这个容器超过10个还是会自动扩容的。不用担心容量不够用。默认是分配一个容量为10的数组，不够将扩容
        //整个来说的优点有：节约内存，节约时间，节约性能。代码质量提高。
        List<String> list = Lists.newArrayListWithExpectedSize(10);

        //这个方法就是直接返回一个10的数组。
        List<String> list_ = Lists.newArrayListWithCapacity(10);
    }

    /**
     * newArrayListWithExpectedSize(int estimatedSize)  构造一个期望长度为estimatedSize的ArrayList实例.
     * newArrayListWithCapacity(int initialArraySize)   构造一个分配指定空间大小的ArrayList实例。
     *
     * 下面的实验说明，都可以自动扩容
     */
    @Test
    public void test2(){
        ArrayList<Object> expectedList = Lists.newArrayListWithExpectedSize(2);
        expectedList.add("a");
        expectedList.add("b");
        expectedList.add("c");
        expectedList.add("d");
        System.out.println(expectedList);

        System.out.println();
        ArrayList<Object> capacityList = Lists.newArrayListWithCapacity(2);
        capacityList.add(1);
        capacityList.add(2);
        capacityList.add(3);
        capacityList.add(4);
        System.out.println(capacityList);
    }

    /**
     * asList(E first, E[] rest)
     * 返回一个不可变的List，其中包含指定的第一个元素和附加的元素数组组成，修改这个数组将反映到返回的List上.
     *
     * asList(E first, E second, E[] rest)
     * 返回一个不可变的List，其中包含指定的第一个元素、第二个元素和附加的元素数组组成，修改这个数组将反映到返回的List上.
     */
    @Test
    public void testAsLists() {
        String str = "a";
        String[] strs = {"b","c"};

        List<String> list = Lists.asList(str, strs);
        System.out.println(list);

        strs[1] = "ccc";
        System.out.println(list);
    }

    /**
     * charactersOf：将传进来的String或者CharSequence分割为单个的字符
     * 并存入到一个ImmutableList对象中返回
     * ImmutableList：一个高性能、不可变的、随机访问列表的实现
     */
    @Test
    public void testCharactersOf(){
        ImmutableList<Character> strList = Lists.charactersOf("abcdefg");
        strList.stream()
                .forEach(System.out::println);
    }

    /**
     * partition(List<T> list, int size)
     * 根据size传入的List进行切割，切割成符合要求的小的List，并将这些小的List存入一个新的List对象中返回.
     *
     *
     * 当你需要请求别人的API传入参数时对方的入参数量有限制，或者分批操作时，可以先拆分然后顺序请求获得结果。
     * RandomAccess意思是加快访问速度，不保证顺序但是可以让所有元素都常量时间拿到
     */
    @Test
    public void testPartition(){
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>>  partitionList = Lists.partition(integers, 3);

        System.out.println(partitionList);
        System.out.println();

        partitionList.stream()
                .forEach(System.out::println);

    }
    /**
     * reverse(List<T> list)
     * 返回一个传入List内元素倒序后的List.
     */
    @Test
    public void testReverse(){
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");
        List<String> reverseList = Lists.reverse(list);
        System.out.println(list);
        System.out.println(reverseList);
    }

    /**
     * transform：根据传进来的function对fromList进行相应的处理
     * 并将处理得到的结果存入到新的list对象中返回
     * 这样有利于我们进行分析，函数接口如下：
     * transform(List<F> fromList, Function<? super F,? extends T> function)
     */
    @Test
    public void testTransform(){
        ArrayList<String> list = Lists.newArrayList("aaa", "Bb", "c","dd");

        List<Object> transformList = Lists.transform(list, new Function<String, Object>() {
            @Override
            public Object apply(@Nullable String input) {
                return input.toUpperCase();
            }
        });
        System.out.println(transformList);
        System.out.println();

        Function<String, Integer> strlen = new Function<String, Integer>() {
            public Integer apply(String from) {
                Preconditions.checkNotNull(from);
                return from.length();
            }
        };

        List<Integer> to = Lists.transform(list, strlen);
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%s has length %d\n", list.get(i), to.get(i));
        }
    }

    /**
     * cartesianProduct(List<? extends B>... lists)
     * 返回通过从各给定集中选择一个元素所形成每一个可能的集合.
     *
     * cartesianProduct(List<? extends List<? extends B>> lists)
     * 返回通过从各给定集中选择一个元素所形成每一个可能的集合.
     */
    @Test
    public void testCartesianProduct(){
        List<String> list1 = Lists.newArrayList("1", "2", "3");
        List<String> list2 = Lists.newArrayList(new String[]{"aa", "bb"});

        List<List<String>> cartesianProduct = Lists.cartesianProduct(list1, list2);
        cartesianProduct.stream()
                .forEach(System.out::println);
    }

}
