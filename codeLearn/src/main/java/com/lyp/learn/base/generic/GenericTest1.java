package com.lyp.learn.base.generic;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericTest1 {

    @Test
    public void test1() {
        List list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add(100);

        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); // 1
            System.out.println("name:" + name);
        }
    }




}
