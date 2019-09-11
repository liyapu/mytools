package com.lyp.learn.base.generic.nest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 泛型嵌套
 *
 */
public class DemoTest {
   
        public static void main(String[] args) {
            Student<String> student = new  Student<String>();
            student.setScore("优秀");
            System.out.println(student.getScore());

            //泛型嵌套
            School< Student<String>> school = new School< Student<String>>();
            school.setStu(student);

            String s = school.getStu().getScore(); //从外向里取
            System.out.println(s);
            System.out.println();


            // hashmap 使用了泛型的嵌套
            Map<String, String> map =  new HashMap<String,String>();
            map.put("a", "张三");
            map.put("b", "李四");
            Set<Map.Entry<String, String>> set = map.entrySet();
            for (Map.Entry<String, String> entry : set) {
                System.out.println(entry.getKey()+":"+entry.getValue());
            }

        }

}
