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
        System.out.println(size);

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
