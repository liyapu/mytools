package com.lyp.learn.base.demo.pk01;

/**
 * == 与 equals 方法
 * 判断两个变量是否相等有两种方式：一种是利用 == 运算符，另一种是利用equals方法。
 */
public class EqualDemo {
    public static void main(String[] args) {
        /**
         * ① 比较java基本类型：
         * 比较基本类型只能用"=="，不能用"equals"，这里的"=="比较的是两个基本类型的值；
         */
        System.out.println(" ① 比较java基本类型 ");
        int a1 = 10;
        int a2 = 10;
        System.out.println(a1 == a2);

        int a11 = 200;
        int a22 = 200;
        System.out.println(a11 == a22);
        System.out.println();

        /**
         * ② 比较包装类
         * 这里拿Integer，Character 来举例
         */
        System.out.println("② 比较包装类");
        Character a = new Character('A');
        Character b = new Character('A');

        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println();

        Integer i1 = new Integer(10);
        Integer i2 = new Integer(10);

        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));

        Integer i11 = new Integer(200);
        Integer i22 = new Integer(200);
        System.out.println(i11 == i22);
        System.out.println(i11.equals(i22));
        System.out.println();
        /*
         Java中存在整数（Integer 对象，而非基本类型）pool。
         在Java中1字节大小以内的Integer（0-127）都是存在一个常量池中的，（不包含new Integer(xx)初始化），
         所以他们的引用也是相同的。

         所以如果值在-128到127之间，你使用valueof()将获得相同的引用，超过这个范围将返回new Integer(int)。
         因为是相同的引用，在-128到127范围内你的==操作是有用的。

          Java缓存了-128到127范围内的Integer类。所以当你给这个范围的封装类赋值时，装箱操作会调用Integer.valueOf() 函数
          反过来就会给封装类赋上一个缓存池实例的引用。
        */
        Integer b1 = 100;
        Integer b2 = 100;
        System.out.println(b1 ==  b2);
        System.out.println(b1.equals(b2));

        /*
        这样定义的变量不会都被放入常量池，当变量的值在（-128,127）之间（也就是可以用一个字节所能表示的int值）时才会被放入常量池，
        否则会自动装箱生成普通Integer对象

        也就是说此时
        Integer integer5=200;//等同于Integer integer5=new Integer(200);

        搞清这些原理之后是不是对Java有更深入的了解了呢,不过在编写代码时不建议使用“==”来比较引用类型的对象，
        还是使用equals()方法比较好，需要的时候重写equals() 和hashcode() 方法还是也有必要的。
         */
        Integer c1 = 600;
        Integer c2 = 600;
        System.out.println(c1 ==  c2);
        System.out.println(c1.equals(c2));
        System.out.println();

        int c11 = 10;
        Integer c22 = 10;
        System.out.println(c11 == c22);

        int c111 = 200;
        Integer c222 = 200;
        System.out.println(c111 == c222);
        /*
        　这边"=="比较的是对象的内存地址，new了两个不同的对象所存放的地址是不一样的，
        这边的"equals"比较的就是值，这里为什么比较的是值呢，equals里的重写了equals的方法。附上源码：
        public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return value == ((Integer)obj).intValue();
        }
        return false;
        }
         */

        /**
         * ③ 比较String
         * "=="比较的是内存地址，"equals"比较的是值
         */
        System.out.println("③比较String");
        String s1 = "latiny";
        String s2 = "latiny";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        String s3 = new String("latiny");
        String s4 = new String("latiny");

        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
        System.out.println();
        /*
        补充说明：Java String 和 new String()的区别

　　   栈区存引用和基本类型，不能存对象，而堆区存对象。==是比较地址，equals()比较对象内容。

        (1) String str1 = "abcd"的实现过程：
        首先栈区创建str引用，然后在String池（独立于栈和堆而存在，存储不可变量）中寻找其指向的内容为"abcd"的对象，
        如果String池中没有，则创建一个，然后str指向String池中的对象，
        如果有，则直接将str1指向"abcd""；
        如果后来又定义了字符串变量 str2 = "abcd",则直接将str2引用指向String池中已经存在的“abcd”，不再重新创建对象；

        当str1进行了赋值（str1=“abc”），则str1将不再指向"abcd"，而是重新指String池中的"abc"，
        此时如果定义String str3 = "abc",进行str1 == str3操作，返回值为true，因为他们的值一样，地址一样，
        但是如果内容为"abc"的str1进行了字符串的+连接str1 = str1+"d"；此时str1指向的是在堆中新建的内容为"abcd"的对象，
        即此时进行str1==str2，返回值false，因为地址不一样。

        (2) String str3 = new String("abcd")的实现过程：直接在堆中创建对象。
        如果后来又有String str4 = new String("abcd")，str4不会指向之前的对象，而是重新创建一个对象并指向它，
        所以如果此时进行str3==str4返回值是false，因为两个对象的地址不一样，
        如果是str3.equals(str4)，返回true,因为内容相同。
         */

        /**
         * ④ 比较对象
         * 这里"=="比较的是内存地址，"equals"比较的也是地址，没有重写equals方法的类都是调用的Object的equals的方法。
         */
        System.out.println("④ 比较对象");
        Person p1 = new Person(1001, "aa");
        Person p2 = new Person(1001, "aa");

        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));

    }
}

class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}