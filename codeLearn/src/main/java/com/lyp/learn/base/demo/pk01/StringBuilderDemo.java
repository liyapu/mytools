package com.lyp.learn.base.demo.pk01;

public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(123456)
                .append("a")
                .append("b")
                .append("c")
                .append("d");
        System.out.println(sb.toString());
        System.out.println(sb.delete(2,5));
        System.out.println(sb.reverse());
        System.out.println(sb.deleteCharAt(2));
        System.out.println(sb.toString());
        System.out.println(sb.replace(2,4,"JAVA"));
        System.out.println(sb.insert(2,222));
        System.out.println(sb.lastIndexOf("2"));

        System.out.println();
        StringBuilder str2=new StringBuilder(30);//创建一个长度为30的字符串
        str2.append("JAVA");
        //length是字符串内容的长度，而capacity是字符串容量（包括缓存区）的长度！
        System.out.println("字符串长度为："+str2.length());//length()，获取字符串长度
        System.out.println("字符串容量为："+str2.capacity());//capacity()，获取字符串的容量
        System.out.println();
        //有关字符串空间的方法
        //setLength(int newSize)，设置字符串缓冲区大小
        str2.setLength(20);
        System.out.println("字符串长度为："+str2.length());
        System.out.println("字符串容量为："+str2.capacity());
        System.out.println();
        //ensureCapacity(int n)，重新设置字符串容量的大小
        //ensureCapacity方法是确保容量至少等于指定的最小值。
        // 如果当前容量不小于该参数，则容量不变。
        // 如果当前容量小于该参数，然后分配一个新的内部数组容量更大（不是你指定的值，系统自动分配一个空间）
        str2.ensureCapacity(20);
        System.out.println("字符串长度为："+str2.length());
        System.out.println("字符串容量为："+str2.capacity());
        str2.ensureCapacity(35);
        System.out.println("字符串长度为："+str2.length());
        System.out.println("字符串容量为："+str2.capacity());
        System.out.println();
        //trimToSize()，存储空间缩小到和字符串长度一样的长度。避免空间的浪费
        str2.trimToSize();
        System.out.println("字符串长度为："+str2.length());
        System.out.println("字符串容量为："+str2.capacity());
    }
}
