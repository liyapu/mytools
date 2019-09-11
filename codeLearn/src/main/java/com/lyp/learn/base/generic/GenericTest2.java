package com.lyp.learn.base.generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericTest2 {
        @Test
        public void test2(){
            List<String> list = new ArrayList<String>();
            list.add("aaa");
            list.add("bbb");
            //list.add(100);   // 1  提示编译错误

            for (int i = 0; i < list.size(); i++) {
                String name = list.get(i); // 2
                System.out.println("name:" + name);
            }
        }
}
