package com.lyp.learn.guava.collect;

import com.google.common.collect.*;
import com.lyp.learn.guava.bean.Person;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Multimap的特点其实就是可以包含有几个重复Key的value，你可以put进入多个不同value但是相同的key，但是又不是让后面覆盖前面的内容。
 *
 * 它的业务场景：当你需要构造像Map<K, List<V>>或者Map<K, Set<V>>这样比较复杂的集合类型的数据结构，来做相应的业务逻辑处理。那Multimap在合适不过。
 *
 * 每个有经验的Java程序员都在某处实现过Map<K, List<V>>或Map<K, Set<V>>，并且要忍受这个结构的笨拙。
 * 例如，Map<K, Set<V>>通常用来表示非标定有向图。
 *
 * Guava的 Multimap可以很容易地把一个键映射到多个值。换句话说，Multimap是把键映射到任意多个值的一般方式。
 *
 * 可以用两种方式思考Multimap的概念：”键-单个值映射”的集合：
 * a -> 1   a -> 2   a ->4   b -> 3    c -> 5
 * 或者”键-值集合映射”的映射：
 * a -> [1, 2, 4]   b -> 3  c -> 5
 *
 * 一般来说，Multimap接口应该用第一种方式看待，但asMap()视图返回Map<K, Collection<V>>，让你可以按另一种方式看待Multimap。
 * 重要的是，不会有任何键映射到空集合：一个键要么至少到一个值，要么根本就不在Multimap中。
 *
 * 很少会直接使用Multimap接口，更多时候你会用 ListMultimap 或 SetMultimap 接口，它们分别把键映射到List或Set
 *
 *
 * Multimap不是Map
 * Multimap<K, V>不是Map<K,Collection<V>>，虽然某些Multimap实现中可能使用了map。它们之间的显著区别包括：
 *  1、Multimap.get(key)总是返回非null、但是可能空的集合。这并不意味着Multimap为相应的键花费内存创建了集合，而只是提供一个集合视图方便你为键增加映射值——
 *     译者注：
 *          如果有这样的键，返回的集合只是包装了Multimap中已有的集合；
 *          如果没有这样的键，返回的空集合也只是持有Multimap引用的栈对象，让你可以用来操作底层的Multimap。
 *          因此，返回的集合不会占据太多内存，数据实际上还是存放在Multimap中。
 *  2、如果你更喜欢像Map那样，为Multimap中没有的键返回null，请使用asMap()视图获取一个Map<K, Collection<V>>。
 *    （或者用静态方法Multimaps.asMap()为ListMultimap返回一个Map<K, List<V>>。对于SetMultimap和SortedSetMultimap，也有类似的静态方法存在）
 *  3、当且仅当有值映射到键时，Multimap.containsKey(key)才会返回true。尤其需要注意的是，如果键k之前映射过一个或多个值，但它们都被移除后，Multimap.containsKey(key)会返回false。
 *  4、Multimap.entries()返回Multimap中所有”键-单个值映射”——包括重复键。如果你想要得到所有”键-值集合映射”，请使用asMap().entrySet()。
 *  5、Multimap.size()返回所有”键-单个值映射”的个数，而非不同键的个数。要得到不同键的个数，请改用Multimap.keySet().size()。
 *
 *
 * Multimap的各种实现
 * Multimap提供了多种形式的实现。在大多数要使用Map<K, Collection<V>>的地方，你都可以使用它们：
 *
 *     实现	                  键行为类似	          值行为类似
 *   ArrayListMultimap	      HashMap	           ArrayList
 *   HashMultimap	          HashMap	           HashSet
 *  LinkedListMultimap*	      LinkedHashMap*	   LinkedList*
 *  LinkedHashMultimap**	  LinkedHashMap    	   LinkedHashMap
 *  TreeMultimap	          TreeMap              TreeSet
 *  ImmutableListMultimap	  ImmutableMap	       ImmutableList
 *  ImmutableSetMultimap	  ImmutableMap	       ImmutableSet
 *
 *  除了两个不可变形式的实现，其他所有实现都支持null键和null值
 *  *LinkedListMultimap.entries()保留了所有键和值的迭代顺序。详情见doc链接。
 *  **LinkedHashMultimap保留了映射项的插入顺序，包括键插入的顺序，以及键映射的所有值的插入顺序。
 *
 *   请注意，并非所有的Multimap都和上面列出的一样，使用Map<K, Collection<V>>来实现（特别是，一些Multimap实现用了自定义的hashTable，以最小化开销）
 *   
 *   如果你想要更大的定制化，请用Multimaps.newMultimap(Map, Supplier<Collection>)或list和 set版本，使用自定义的Collection、List或Set实现Multimap。
 */
public class MultimapTest {
    @Test
    public void testMultimap(){
        HashMultimap<String,Integer> hashMultimap = HashMultimap.create();

        hashMultimap.put("a",1);
        hashMultimap.put("a",2);
        hashMultimap.put("a",3);
        hashMultimap.put("a",4);

        hashMultimap.put("b",1);
        hashMultimap.put("b",4);
        hashMultimap.put("b",5);
        hashMultimap.put("b",7);

        hashMultimap.put("c",2);
        hashMultimap.put("c",4);
        hashMultimap.put("c",6);

        Set<Integer> a = hashMultimap.get("a");
        System.out.println(a);

        System.out.println(hashMultimap.get("b"));
        System.out.println(hashMultimap.get("c"));
        System.out.println(hashMultimap);

    }

    @Test
    public void testMultimap2(){
        Multimap<String,String> multimap = ArrayListMultimap.create();

        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");

        multimap.put("upper", "A");
        multimap.put("upper", "B");

        List<String> lowerList = (List<String>)multimap.get("lower");
        //输出key为lower的list集合
        System.out.println("输出key为lower的list集合=========");
        System.out.println(lowerList.toString());
        lowerList.add("f");
        System.out.println(lowerList.toString());
        System.out.println(multimap);


        Map<String, Collection<String>> map = multimap.asMap();
        System.out.println("把Multimap转为一个map============");
        for (Map.Entry<String,  Collection<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Collection<String> value =  multimap.get(key);
            System.out.println(key + ":" + value);
        }

        System.out.println("获得所有Multimap的key值==========");
        Set<String> keys =  multimap.keySet();
        for(String key:keys){
            System.out.println(key);
        }

        System.out.println("输出Multimap所有的value值========");
        Collection<String> values = multimap.values();
        System.out.println(values);
    }

    /**
     * ListMultimap
     * get(key) 返回的是collection,如果希望返回的是list,可以选择ListMultimap来接收create()的返回值
     */
    @Test
    public void testMultimap3(){
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a",1);
        multimap.put("a",2);
        multimap.put("a",3);

        multimap.put("b",2);
        multimap.put("b",4);

        Collection<Integer> aCollection = multimap.get("a");
        System.out.println("aCollection :" + aCollection);

        System.out.println();
        List<Integer> aListInteger = (List<Integer>) multimap.get("a");
        System.out.println("aListInteger :" + aListInteger);

        System.out.println("\n------------------");
        ListMultimap<String, Integer> listMultimap = ArrayListMultimap.create();
        listMultimap.put("a",1);
        listMultimap.put("a",2);
        listMultimap.put("a",3);

        listMultimap.put("b",2);
        listMultimap.put("b",4);

        List<Integer> bListMultimap = listMultimap.get("b");
        System.out.println("bListMultimap :" + bListMultimap);

        System.out.println("\n--------------------");
        ArrayListMultimap<String,Integer> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("a",1);
        arrayListMultimap.put("a",2);
        arrayListMultimap.put("a",3);
        arrayListMultimap.put("a",4);

        arrayListMultimap.put("b",2);
        arrayListMultimap.put("b",4);

        List<Integer> integerList = arrayListMultimap.get("a");
        System.out.println("integerList :" + integerList);
    }

    /**
     * SetMultimap
     */
    @Test
    public void testSetMultimap(){
        Multimap<String, Integer> multimap = HashMultimap.create();
        multimap.put("a",1);
        multimap.put("a",2);
        multimap.put("a",3);

        multimap.put("b",2);
        multimap.put("b",4);

        Collection<Integer> aCollection = multimap.get("a");
        System.out.println("aCollection :" + aCollection);

        System.out.println();
        Set<Integer> aSetInteger = (Set<Integer>) multimap.get("a");
        System.out.println("aSetInteger :" + aSetInteger);

        System.out.println("\n------------------");
        SetMultimap<String, Integer> setMultimap = HashMultimap.create();
        setMultimap.put("a",1);
        setMultimap.put("a",2);
        setMultimap.put("a",3);

        setMultimap.put("b",2);
        setMultimap.put("b",4);

        Set<Integer> bSetMultimap = setMultimap.get("b");
        System.out.println("bSetMultimap :" + bSetMultimap);

        System.out.println("\n--------------------");
        HashMultimap<String,Integer>  hashMultimap = HashMultimap.create();
        hashMultimap.put("a",1);
        hashMultimap.put("a",2);
        hashMultimap.put("a",3);
        hashMultimap.put("a",4);
        hashMultimap.put("a",1);

        hashMultimap.put("b",2);
        hashMultimap.put("b",4);

        Set<Integer> aSet = hashMultimap.get("a");
        System.out.println("aSet :" + aSet);
    }

    /**
     * Multimap.get(key)
     * 以集合形式返回键所对应的值视图，即使没有任何对应的值，也会返回空集合
     *
     * ListMultimap.get(key)返回List，
     * SetMultimap.get(key)返回Set。
     */
    @Test
    public void testGet(){
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a",1);
        multimap.put("a",2);
        multimap.put("a",3);

        multimap.put("b",2);
        multimap.put("b",4);

        Collection<Integer> cCollection = multimap.get("c");
        System.out.println(cCollection);
        System.out.println();

        ListMultimap<String, Integer> listMultimap = ArrayListMultimap.create();
        listMultimap.put("a",1);
        listMultimap.put("a",2);
        listMultimap.put("a",3);

        listMultimap.put("b",2);
        listMultimap.put("b",4);

        List<Integer> cList = listMultimap.get("c");
        System.out.println(cList);
    }

    /**
     * 对值视图集合进行的修改最终都会反映到底层的Multimap
     */
    @Test
    public void test001(){
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a",1);
        multimap.put("a",2);
        multimap.put("a",3);

        multimap.put("b",2);
        multimap.put("b",4);
        System.out.println("原 multimap ：" + multimap);

        Collection<Integer> aCollection = multimap.get("a");
        System.out.println("aCollection :" + aCollection);
        aCollection.clear();
        System.out.println("aCollection clear :" + aCollection);
        System.out.println("原 multimap ：" + multimap);


    }

    /**
     * Multimap的视图
     * Multimap还支持若干强大的视图：
     *
     * asMap为Multimap<K, V>提供Map<K,Collection<V>>形式的视图。
     * 返回的Map支持remove操作，并且会反映到底层的Multimap，但它不支持put或putAll操作。
     * 更重要的是，如果你想为Multimap中没有的键返回null，而不是一个新的、可写的空集合，你就可以使用asMap().get(key)
     * 你可以并且应当把asMap.get(key)返回的结果转化为适当的集合类型——如SetMultimap.asMap.get(key)的结果转为Set，
     *                                                        ListMultimap.asMap.get(key)的结果转为List
     *                                        Java类型系统不允许ListMultimap直接为asMap.get(key)返回List
     *                                        译者注：也可以用Multimaps中的asMap静态方法帮你完成类型转换
     *
     * entries用Collection<Map.Entry<K, V>>返回Multimap中所有”键-单个值映射”——包括重复键。（对SetMultimap，返回的是Set）
     *
     * keySet用Set表示Multimap中所有不同的键。
     *
     * keys用Multiset表示Multimap中的所有键，每个键重复出现的次数等于它映射的值的个数。
     * 可以从这个Multiset中移除元素，但不能做添加操作；移除操作会反映到底层的Multimap。
     *
     * values()用一个”扁平”的Collection<V>包含Multimap中的所有值。
     * 这有一点类似于Iterables.concat(multimap.asMap().values())，
     * 但它直接返回了单个Collection，而不像multimap.asMap().values()那样是按键区分开的Collection。
     */
    @Test
    public void testMultimap4(){
        Multimap<String,Integer> arrayListMultimap = ArrayListMultimap.create();

        arrayListMultimap.put("a",1);
        arrayListMultimap.put("a",2);
        arrayListMultimap.put("a",3);
        arrayListMultimap.put("a",4);

        arrayListMultimap.put("b",1);
        arrayListMultimap.put("b",3);
        arrayListMultimap.put("b",5);
        arrayListMultimap.put("b",7);

        arrayListMultimap.put("c",2);
        arrayListMultimap.put("c",4);
        arrayListMultimap.put("c",6);

        Collection<Integer> aList = arrayListMultimap.get("a");
        System.out.println(aList);
        System.out.println(arrayListMultimap.get("b"));
        System.out.println(arrayListMultimap);

        System.out.println("\n----------asMap---------");
        Map<String, Collection<Integer>> stringCollectionMap = arrayListMultimap.asMap();
        System.out.println(stringCollectionMap);

        System.out.println("\n----------keys---------");
        Multiset<String> keys = arrayListMultimap.keys();
        System.out.println(keys);

        System.out.println("\n----------keySet---------");
        Set<String> keySet = arrayListMultimap.keySet();
        System.out.println(keySet);

        System.out.println("\n----------values---------");
        Collection<Integer> values = arrayListMultimap.values();
        System.out.println(values);

        System.out.println("\n----------entries---------");
        Collection<Map.Entry<String, Integer>> entries = arrayListMultimap.entries();
        for(Map.Entry<String, Integer> entry : entries){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

    @Test
    public void testAsMap(){
        Multimap<String,Integer> arrayListMultimap = ArrayListMultimap.create();

        arrayListMultimap.put("a",1);
        arrayListMultimap.put("a",2);
        arrayListMultimap.put("a",3);
        arrayListMultimap.put("a",4);

        arrayListMultimap.put("b",1);
        arrayListMultimap.put("b",3);
        arrayListMultimap.put("b",5);
        arrayListMultimap.put("b",7);

        System.out.println("arrayListMultimap :" + arrayListMultimap);
        Map<String, Collection<Integer>> stringCollectionMap = arrayListMultimap.asMap();
        System.out.println("stringCollectionMap :" + stringCollectionMap);

        System.out.println();
        Collection<Integer> xCollection = stringCollectionMap.get("x");
        System.out.println("xCollection :" + xCollection);
        System.out.println("xCollection is null :" + (null == xCollection));

        System.out.println();
        SetMultimap<String, Integer> setMultimap = HashMultimap.create();
        setMultimap.put("a",1);
        setMultimap.put("a",2);
        setMultimap.put("a",3);

        setMultimap.put("b",2);
        setMultimap.put("b",4);

        System.out.println("setMultimap :" + setMultimap);
        Set<Integer> aSet = setMultimap.get("a");
        System.out.println("aSet :" + aSet);
        Collection<Integer> aCollection = setMultimap.asMap().get("a");
        System.out.println("aCollection :" + aCollection);

        System.out.println();
        ListMultimap<String, Integer> listMultimap = ArrayListMultimap.create();
        listMultimap.put("a",1);
        listMultimap.put("a",2);
        listMultimap.put("a",3);

        listMultimap.put("b",2);
        listMultimap.put("b",4);
        System.out.println("listMultimap :" + listMultimap);
        List<Integer> aList = listMultimap.get("a");
        System.out.println("aList :" + aList);
        Collection<Integer> aCollection2 = listMultimap.asMap().get("a");
        System.out.println("aCollection2 :" + aCollection2);

//        List<Integer> bList = listMultimap.asMap().get("b");
        //译者注：也可以用Multimaps中的asMap静态方法帮你完成类型转换
        List<Integer> aList2 = Multimaps.asMap(listMultimap).get("a");
        System.out.println("aList2 :" + aList2);

        List<Integer> yList = listMultimap.get("y");
        System.out.println("yList :" + yList);

        Collection<Integer> zCollection = listMultimap.asMap().get("z");
        System.out.println("zCollection :" + zCollection);

    }


    @Test
    public void testMultimap5(){
        Multimap<String,Integer> arrayListMultimap = ArrayListMultimap.create();

        arrayListMultimap.put("a",1);
        arrayListMultimap.put("a",2);
        arrayListMultimap.put("a",3);
        arrayListMultimap.put("a",4);

        arrayListMultimap.put("b",1);
        arrayListMultimap.put("b",3);
        arrayListMultimap.put("b",5);
        arrayListMultimap.put("b",7);

        arrayListMultimap.put("c",2);
        arrayListMultimap.put("c",4);
        arrayListMultimap.put("c",6);

        int size = arrayListMultimap.size();
        System.out.println("size :" + size);

        int keySetSize = arrayListMultimap.keySet().size();
        System.out.println("keySetSize :" + keySetSize);

        Collection<Integer> replaceValues = arrayListMultimap.replaceValues("c", Arrays.asList(100, 200));
        System.out.println(replaceValues);
        System.out.println(arrayListMultimap);

        System.out.println();
        Collection<Integer> replaceValues1 = arrayListMultimap.replaceValues("d", Arrays.asList(10, 20));
        System.out.println(replaceValues1);
        System.out.println(arrayListMultimap);
    }

    @Test
    public void testMultimap6(){
        Multimap<String,Integer> arrayListMultimap = ArrayListMultimap.create();

        arrayListMultimap.put("a",1);
        arrayListMultimap.put("a",2);
        arrayListMultimap.put("a",3);
        arrayListMultimap.put("a",3);
        arrayListMultimap.put("a",4);
        arrayListMultimap.put("a",5);

        arrayListMultimap.put("b",1);
        arrayListMultimap.put("b",3);
        arrayListMultimap.put("b",5);
        arrayListMultimap.put("b",7);

        arrayListMultimap.put("c",2);
        arrayListMultimap.put("c",4);
        arrayListMultimap.put("c",6);

        System.out.println(arrayListMultimap);

        System.out.println(arrayListMultimap.remove("a", 1));
        //注意:这里只remove了一个3,因此还有一个3
        System.out.println(arrayListMultimap.remove("a", 3));
        System.out.println(arrayListMultimap);

        System.out.println(arrayListMultimap.removeAll("c"));
        System.out.println(arrayListMultimap);
    }


    /**
     * 根据业务需要对下面的list数据根据name字段来进行分组
     */
    public static List<Person> personList = new ArrayList<>();
    {
        personList.add(new Person("tom",15));
        personList.add(new Person("jack",28));
        personList.add(new Person("lisa",30));
        personList.add(new Person("kang",18));
        personList.add(new Person("tom",25));
        personList.add(new Person("tom",15));
        personList.add(new Person("lisa",20));
    }

    //传统做法: 很简单， 但是代码量有点多，特别是需要判断List为null并初始化。
    @Test
    public void testNamePersonList1(){
        Map<String,List<Person>> map = new HashMap<>();
        for(Person person : personList){
            List<Person> pList = map.get(person.getName());
            if(pList == null){
                pList = new ArrayList<>();
                map.put(person.getName(),pList);
            }
            pList.add(person);
        }

        System.out.println(map);
    }

    //再用guava实现上述的功能：
    @Test
    public void testNamePersonList2(){
        Multimap<String,Person> multimap = ArrayListMultimap.create();
        for(Person person : personList){
            multimap.put(person.getName(),person);
        }
        System.out.println(multimap);
    }

    //java8 实现
    @Test
    public void testNamePersonList3(){
        Map<String, List<Person>> map = personList.stream()
                                             .collect(Collectors.groupingBy(Person::getName));
        System.out.println(map);
    }
}
