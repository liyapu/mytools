package com.lyp.learn.base.reflect;

import com.lyp.learn.base.threads.pk01.Object;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassDemo4 {
    public static void main(String[] args) throws Exception {
        ArrayList list1 =new ArrayList();
        ArrayList<String> list2 =new ArrayList<String>();

        list2.add("aaaa");
        list2.add("bbb");
        //编译时报错
        //'add(java.lang.String)' in 'java.util.ArrayList' cannot be applied to '(int)'
       // list2.add(10);

        Class c1 = list1.getClass();
        Class c2 = list2.getClass();

        System.out.println(c1==c2);
        System.out.println();

        Class listClazz = list2.getClass();
        Method method = listClazz.getMethod("add", Object.class);
        method.invoke(list2,100);
        System.out.println(list2);


    }
}
