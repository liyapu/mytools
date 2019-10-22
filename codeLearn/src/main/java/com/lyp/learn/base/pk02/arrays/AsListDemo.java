package com.lyp.learn.base.pk02.arrays;

import java.util.*;

public class AsListDemo {
    public static void main(String[] args) {
        /**
         * 将数组转成list集合
         * 注意：这个方法返回的 ArrayList 不是我们常用的集合类 java.util.ArrayList。
         * 这里的 ArrayList 是 Arrays 的一个内部类 java.util.Arrays.ArrayList
         *
         * Arrays.asList() 方法使用场景:::::
         * 使用该方法可以将一个变长参数或者数组转换成List,返回的 ArrayList 数组是一个定长列表，
         * 我们只能对其进行查看或者修改（比如，修改某个位置的元素），
         * 但是不能执行影响长度的操作（（如add、remove等操作）,否则会抛出UnsupportedOperationException异常。
         *
         * 通过源码我们发现该类是没有add()或者remove() 这样的方法的，如果对其进行增加或者删除操作，
         * 都会调用其父类 AbstractList 对应的方法，而追溯父类的方法最终会抛出 UnsupportedOperationException 异常
         *
         *  所以 Arrays.asList 比较适合那些已经有数组数据或者一些元素，
         *  而需要快速构建一个List，只用于读取操作,判断是否包含某值等，而不进行添加或删除操作的场景
         */
        System.out.println("-----将数组转成list集合----");
        Integer [] arrayInteger = new Integer[] {1,2,3,4,5};
        List<Integer> integerList = Arrays.asList(arrayInteger);
        System.out.println("integerList :" + integerList);
        System.out.println("integerList size :" + integerList.size());
        System.out.println("integerList contains 3 :" + integerList.contains(3));
        System.out.println("integerList contains 30 :" + integerList.contains(30));

        System.out.println();

        int [] arrayInt = new int [] {1,2,3,4,5,6,7,8,9};
        List<int []> arrayIntList = Arrays.asList(arrayInt);
        System.out.println(arrayIntList);
        System.out.println("arrayIntList size :" + arrayIntList.size());
        System.out.println("arrayIntList contains 3 :" + arrayIntList.contains(3));
        int [] arrayIntTemp = arrayIntList.get(0);
        System.out.println(Arrays.toString(arrayIntTemp));
        System.out.println();

        String [] arrayString = {"a","b","c","d","e"};
        List<String> stringList = Arrays.asList(arrayString);
        System.out.println("stringList :" + stringList);
        System.out.println("stringList size :" + stringList.size());
        System.out.println("stringList contains b :" + stringList.contains("b"));
        System.out.println("stringList contains bb :" + stringList.contains("bb"));

        System.out.println("stringList :" + stringList);

        System.out.println("=====================Java Arrays.asList注意事项========================");

        /**
         * 引用类型的数组和基本类型的数组区别
         */
        System.out.println("-------引用类型的数组和基本类型的数组区别------");
        String[] str = {"a","b","c"};
        List listStr = Arrays.asList(str);
        System.out.println(listStr.size());//3

        int[] i = {1,2,3};
        List listI = Arrays.asList(i);
        System.out.println(listI.size());//1
        /**
         * 上面的结果第一个listStr.size()==3，而第二个 listI.size()==1。这是为什么呢？
         * 我们看源码，在 Arrays.asList 中，方法声明为  <T> List<T> asList(T... a)。
         * 该方法接收一个可变参数，并且这个可变参数类型是作为泛型的参数。
         * 我们知道基本数据类型是不能作为泛型的参数的，
         * 但是数组是引用类型，所以数组是可以泛型化的，所以采用基本类型的数组转化后是将整个数组放入了构造的ArrayList中，填充了list的第一个位置，长度是1
         * 于是 int[] 作为了整个参数类型，而不是 int 作为参数类型。
         *
         *
         * 所以将上面的方法泛型化补全应该是：
         */
        System.out.println("--------------泛型化补全应该是------------");
        String[] str2 = {"a","b","c"};
        List<String> listStr2 = Arrays.asList(str2);
        System.out.println(listStr2.size());//3

        int[] i2 = {1,2,3};
        List<int[]> listI2 = Arrays.asList(i2);//注意这里List参数为 int[]，整个数组放在了list的第一个位置上 ，而不是 int
        System.out.println(listI2.size());//1

        Integer[] in = {1,2,3};
        List<Integer> listIn = Arrays.asList(in);//这里参数为int的包装类Integer，所以集合长度为3
        System.out.println(listIn.size());//3

        /**
         * 返回的列表ArrayList里面的元素都是引用，不是独立出来的对象
         */
        System.out.println("-----------返回的列表ArrayList里面的元素都是引用，不是独立出来的对象------");
        String[] str3 = {"a","b","c","d","e","f"};
        List<String> listStr3 = Arrays.asList(str3);
        //执行更新操作前
        System.out.println(Arrays.toString(str3));//[a, b, c, d, e, f]

        str3[4] = "eeeeeee"; //将数组的第5个元素e改为eeeeeee
        listStr3.set(0, "aaaaa");//将list第1个元素a改为aaaaa

        //执行更新操作后
        System.out.println(Arrays.toString(str3));//[aaaaa, b, c, d, eeeeeee, f]

        /**
         * 从array中创建arraylist
         * 已知数组数据，快速获取一个可进行增删改查的列表List
         */
        String [] arrayStr = null;
        System.out.println("-------从array中创建arraylist--------------");
        arrayStr = new String [] {"关羽","刘备","张飞","诸葛亮","马超"};
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(arrayStr));
        System.out.println("arrayList : " + arrayList);
        System.out.println("arrayList size is: " + arrayList.size());
        System.out.println("arrayList contains  诸葛亮 : " + arrayList.contains("诸葛亮"));
        System.out.println("arrayList contains  曹操 : " + arrayList.contains("曹操"));
        arrayList.add("曹操");
        System.out.println("arrayList : " + arrayList);
        System.out.println("arrayList size is: " + arrayList.size());
        System.out.println("arrayList contains  诸葛亮 : " + arrayList.contains("诸葛亮"));
        System.out.println("arrayList contains  曹操 : " + arrayList.contains("曹操"));

        /**
         * 从数组中创建set集合
         */
        System.out.println("-------将数组转成set集合----------");
        Set<String> arraySet =new HashSet<>(Arrays.asList(arrayStr));
        System.out.println(arraySet);
    }
}
