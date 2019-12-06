package com.lyp.learn.base.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-12-13 12:42
 */
public class IdeaTest {
    private int id;
    private String name;


    public static void main(String[] args) {
        System.out.println();

        List<Person> personList = new ArrayList<>();
        String notice = "%sKKKK";
        System.out.println(String.format(notice,"aa",100));
    }
}
