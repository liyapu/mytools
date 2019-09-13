package com.lyp.learn.base.demo.pk02;

import java.util.*;

public class ArraysDemoCopy {
    public static void main(String[] args) {
        //定义一个整型数组
        int [] arrayInt = null;

        /**
         * 将数组转成list集合
         * 注意：这个方法返回的 ArrayList 不是我们常用的集合类 java.util.ArrayList。
         * 这里的 ArrayList 是 Arrays 的一个内部类 java.util.Arrays.ArrayList
         *
         * 返回的 ArrayList 数组是一个定长列表，我们只能对其进行查看或者修改，但是不能进行添加或者删除操作。
         *
         * 通过源码我们发现该类是没有add()或者remove() 这样的方法的，如果对其进行增加或者删除操作，
         * 都会调用其父类 AbstractList 对应的方法，而追溯父类的方法最终会抛出 UnsupportedOperationException 异常
         */
        System.out.println("-----将数组转成list集合----");
        List<Integer> intList =  Arrays.asList(1,2,3,4,5);
        System.out.println(intList);
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
         *  http://www.cnblogs.com/ysocean/p/8616122.html
         * 我们看源码，在 Arrays.asList 中，方法声明为  <T> List<T> asList(T... a)。
         * 该方法接收一个可变参数，并且这个可变参数类型是作为泛型的参数。
         * 我们知道基本数据类型是不能作为泛型的参数的，但是数组是引用类型，所以数组是可以泛型化的，
         * 于是 int[] 作为了整个参数类型，而不是 int 作为参数类型。
         *
         * 所以将上面的方法泛型化补全应该是：
         */
        System.out.println("--------------泛型化补全应该是------------");
        String[] str2 = {"a","b","c"};
        List<String> listStr2 = Arrays.asList(str2);
        System.out.println(listStr2.size());//3

        int[] i2 = {1,2,3};
        List<int[]> listI2 = Arrays.asList(i2);//注意这里List参数为 int[] ，而不是 int
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
         * ④已知数组数据，快速获取一个可进行增删改查的列表List
         */
        System.out.println("--------已知数组数据，快速获取一个可进行增删改查的列表List---");
        String[] str4 = {"a","b","c"};
        List<String> listStr4 = new ArrayList<>(Arrays.asList(str4));
        listStr4.add("d");
        System.out.println(listStr4);
        System.out.println(listStr4.size());//4
        /**
         * ⑤、Arrays.asList() 方法使用场景
         *
         * Arrays工具类提供了一个方法asList, 使用该方法可以将一个变长参数或者数组转换成List 。
         * 但是，生成的List的长度是固定的；能够进行修改操作（比如，修改某个位置的元素）；
         * 不能执行影响长度的操作（如add、remove等操作），否则会抛出UnsupportedOperationException异常。
         *
         * 　　所以 Arrays.asList 比较适合那些已经有数组数据或者一些元素，
         * 而需要快速构建一个List，只用于读取操作，而不进行添加或删除操作的场景
         */
        /**
         * 声明 int 类型的数组
         */
        int[] arrayInt5 = new int[]{1,2,3,4,5};
        List listInt = Arrays.asList(arrayInt5);
        System.out.println("listInt size is :" + listInt.size());
        //运行结果是1。一个长度为5的数组，转化为List后，长度却成了1。

        /**
         * 声明 包装类型Integer 类型的数组
         */
        Integer[] arrayInteger2 = new Integer[]{1,2,3,4,5};
        List listInteger = Arrays.asList(arrayInteger2);
        System.out.println("listInteger2 size is : " + listInteger.size());
        //只是将数组的类型由原来的基本类型改变为包装类型，转化后的List却不一样
        /**
         * asList接受的是一个泛型类型的参数，再构造了一个ArrayList。
         * 然而基本类型是不支持泛型化的，
         * 但是数组支持，所以采用基本类型的数组转化后是将数组放入了构造的ArrayList中，长度是1。
         * 再接着看，这个坑过去了，兴高采烈的想要往转化后的List中添加些元素：
         * Integer[] datas = new Integer[]{1,2,3,4,5};
         * List list = Arrays.asList(datas);
         * list.add(6);
         * 然后运行后却抛出了java.lang.UnsupportedOperationException异常，这是怎么回事？
         * 原来asList返回的ArrayList并不是我们熟悉的java.util.ArrayList，而是另一个类：
         * private static class ArrayList<E> extends AbstractList<E>
         *         implements RandomAccess, java.io.Serializable
         *
         *  抽象类AbstractList 定义了一系列比如add()、remove()修改list的方法，然而在这个ArrayList中并没有实现它们，
         *  所以返回的ArrayList是不支持修改操作的。
         * 那么如果想要对其进行add或者remove应该怎么做呢？
         * Integer[] datas = new Integer[]{1,2,3,4,5};
         * ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(datas));
         */
        System.out.println("======================================================================");


        /**
         * 输出一维数组
         */
        System.out.println("----输出一维数组----");
        arrayInt = new int [] {10,20,30,40,50};
        System.out.println(Arrays.toString(arrayInt));
        /**
         * 输出二维数组
         * deepEquals,deepToString 能够进行比较多维数组，而且是任意层次的嵌套数组。
         */
        System.out.println("----输出二维数组----");
        int [] [] arrayIntTwo = new int [][] {{1,2,3},{10,20,30},{100,200,300}};
        System.out.println(Arrays.toString(arrayIntTwo));
        System.out.println(Arrays.deepToString(arrayIntTwo));

        /**
         * 输出三维数组
         */
        int [][][] arrayIntThree = new int [][][]
                {
                    { { 1, 2, 3 }, { 4, 5, 6 } },
                    { { 7, 8, 9 }, { 10, 11, 12 } },
                    { { 13, 14, 15 }, { 16, 17, 18 } }
                 };
        System.out.println("-------输出三维数组----");
        System.out.println(Arrays.deepToString(arrayIntThree));
        /**
         * 填充整个数组
         */
        System.out.println("----填充整个数组------");
        arrayInt = new int[5];
        System.out.println(Arrays.toString(arrayInt));
        Arrays.fill(arrayInt,2);
        System.out.println(Arrays.toString(arrayInt));
        /**
         * 填充指定位置之间的数组
         */
        System.out.println("----填充指定位置之间的数组------");
        arrayInt = new int[5];
        Arrays.fill(arrayInt,1,4,8);
        System.out.println(Arrays.toString(arrayInt));

        /**
         * 排序整个数组(升序)
         * 这将改变原数组
         */
        System.out.println("----排序整个数组(升序)-----");
        arrayInt = new int [] {4,7,2,5,8,3};
        Arrays.sort(arrayInt);
        System.out.println(Arrays.toString(arrayInt));
        /**
         * 排序指定位置之间的数组(升序)
         */
        System.out.println("----排序指定位置之间的数组(升序)-----");
        arrayInt = new int [] {4,7,2,5,8,3};
        Arrays.sort(arrayInt,1,4);
        System.out.println(Arrays.toString(arrayInt));
        //使用比较器全部降序排序
        /**
         * 使用比较器全部排序整个数组(降序)
         */
        System.out.println("-----使用比较器全部排序整个数组(降序)---");
        Integer [] arrayInteger = new Integer[]{4,7,2,5,8,3};
        Arrays.sort(arrayInteger,(x,y)->y.compareTo(x));
        System.out.println(Arrays.toString(arrayInteger));
        /**
         * 比较一维数组内容是否相等
         * 如果是arr1.equals(arr2),则返回false，因为equals比较的是两个对象的地址，不是里面的数，
         * 而Arrays.equals重写了equals，所以，这里能比较元素是否相等
         */
        int [] arr1 = {1,2,3};
        int [] arr2 = {1,2,3};
        System.out.println("----比较一维数组内容是否相等-----");
        System.out.println(arr1 == arr2);
        System.out.println(arr1.equals(arr2));
        System.out.println(Arrays.equals(arr1,arr1));
        System.out.println(Arrays.equals(arr1,arr2));

        /**
         * 比较二维数组内容是否相等
         */
        System.out.println("------比较二维数组内容是否相等----");
        int [] [] arrayIntTwo1 = new int [][] {{1,2,3},{10,20,30},{100,200,300}};
        int [] [] arrayIntTwo2 = new int [][] {{1,2,3},{10,20,30},{100,200,300}};
        System.out.println(Arrays.deepEquals(arrayIntTwo1,arrayIntTwo2));

        /**
         * 二分查找法找指定元素的索引值（下标）
         * binarySearch()方法的返回值为：
         * 1、如果找到关键字，则返回值为关键字在数组中的位置索引，且索引从0开始
         * 2、如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，而且这个位置索引从1开始。
         *
         * 注意：
         * 1、调用binarySearch()方法前要先调用sort方法对数组进行排序，否则得出的返回值不定，这时二分搜索算法决定的。
         * 2、排序后的返回结果是负数就一定说明没找到
         *
         */
        arrayInt = new int[] {10,20,30,40,50,60,70,80,90,100};
        System.out.println("-----二分查找法找指定元素的索引值（下标）-----");
        System.out.println(Arrays.toString(arrayInt));
        System.out.println(Arrays.binarySearch(arrayInt,30));
        System.out.println(Arrays.binarySearch(arrayInt,31));
        System.out.println(Arrays.binarySearch(arrayInt,39));
        System.out.println(Arrays.binarySearch(arrayInt,41));
        System.out.println(Arrays.binarySearch(arrayInt,49));
        System.out.println(Arrays.binarySearch(arrayInt,101));
        System.out.println(Arrays.toString(Arrays.copyOfRange(arrayInt,3,5)));
        System.out.println(Arrays.binarySearch(arrayInt,3,5,30));
        System.out.println(Arrays.binarySearch(arrayInt,3,5,101));

        /**
         * 复制指定长度的数组
         * 长度可大于被复制数组的长度
         */
        arrayInt = new int [] {10,20,30,40,50};
        System.out.println("-----复制指定长度的数组------");
        int [] arrayInt2 = Arrays.copyOf(arrayInt,4);
        System.out.println(Arrays.toString(arrayInt2));
        System.out.println(Arrays.toString(Arrays.copyOf(arrayInt,arrayInt.length)));
        System.out.println(Arrays.toString(Arrays.copyOf(arrayInt,arrayInt.length * 2 + 3)));

        /**
         * 复制指定范围的数组
         */
        arrayInt = new int [] {10,20,30,40,50};
        System.out.println("-----复制指定范围的数组------");
        System.out.println(Arrays.toString(Arrays.copyOfRange(arrayInt,1,3)));

        /**
         * 从array中创建arraylist
         */
        String [] arrayStr = null;
        System.out.println("-------从array中创建arraylist--------------");
        arrayStr = new String [] {"关羽","刘备","张飞","诸葛亮","马超"};
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(arrayStr));
        System.out.println(arrayList);
        /**
         * 数组中是否包含某一个值
         */
        System.out.println("------数组中是否包含某一个值----------");
        System.out.println(Arrays.asList(arrayStr).contains("诸葛亮"));
        System.out.println(Arrays.asList(arrayStr).contains("曹操"));
        /**
         * 从数组中创建set集合
         */
        System.out.println("-------将数组转成set集合----------");
        Set<String> arraySet =new HashSet<>(Arrays.asList(arrayStr));
        System.out.println(arraySet);
        /**
         * 多线程算法，处理数组(大多以parallel做为开头的方法，可以充分利用现代CPU多核，处理大规模庞大的数组很有效)
         * https://www.cnblogs.com/HeDante/p/7464874.html
         *
         */



        /**
         * 数组的流式处理  Stream
         * https://www.cnblogs.com/HeDante/p/7464874.html
         */






    }
}
