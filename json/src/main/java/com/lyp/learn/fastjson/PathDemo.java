package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;

import java.util.*;

public class PathDemo {
    public static void main(String[] args) {
     Entity entity =  new Entity(123,new Object());

        System.out.println(JSONPath.eval(entity,"$.value"));
        System.out.println(JSONPath.contains(entity,"$.value"));
        System.out.println(JSONPath.containsValue(entity,"$.id",123));
        System.out.println(JSONPath.containsValue(entity,"$.value",entity.getValue()));
        System.out.println(JSONPath.size(entity,"$"));
        System.out.println(JSONPath.size(new Object[5],"$"));
        System.out.println();


        /**
         * 读取集合多个元素的某个属性
         */
        System.out.println("-----读取集合多个元素的某个属性------");
        List<Entity> entities1 = new ArrayList<>();
        entities1.add(new Entity("wenshao"));
        entities1.add(new Entity("阿里"));
        //返回enties的所有名称
        List<String> names = (List<String>)JSONPath.eval(entities1,"$.name");
        System.out.println(names);
        System.out.println();

        /**
         * 返回集合中多个元素
         */
        System.out.println("-----返回集合中多个元素-----------");
        List<Entity> entities2 = new ArrayList<Entity>();
        entities2.add(new Entity("wenshao"));
        entities2.add(new Entity("ljw2083"));
        entities2.add(new Entity("Yako"));
        // 返回下标为1和2的元素
        List<Entity> result2 = (List<Entity>) JSONPath.eval(entities2,"[1,2]");
        System.out.println(result2.size());
        System.out.println(result2.get(0));
        System.out.println(result2.get(1));
        System.out.println();

        /**
         * 按范围返回集合的子集
         */
        System.out.println("-----按范围返回集合的子集------");
        List<Entity> entities3 = new ArrayList<Entity>();
        entities3.add(new Entity("wenshao"));
        entities3.add(new Entity("ljw2083"));
        entities3.add(new Entity("Yako"));
        // 返回下标从0到2的元素
        List<Entity> result3 = (List<Entity>)JSONPath.eval(entities3, "[0:2]");
        System.out.println(result3.size());
        System.out.println(result3.get(0));
        System.out.println(result3.get(1));
        System.out.println(result3.get(2));
        System.out.println();

        /**
         * 通过条件过滤，返回集合的子集
         */
        System.out.println("------通过条件过滤，返回集合的子集----");
        List<Entity> entities4 = new ArrayList<Entity>();
        entities4.add(new Entity(1001, "ljw2083"));
        entities4.add(new Entity(1002, "wenshao"));
        entities4.add(new Entity(1003, "yakolee"));
        entities4.add(new Entity(1004, null));

        List<Object> result4 = (List<Object>) JSONPath.eval(entities4, "[id in (1001)]");
        System.out.println(result4.size());
        System.out.println(result4.get(0));
        System.out.println();

        /**
         * 根据属性值过滤条件判断是否返回对象，修改对象，数组属性添加元素
         */
        System.out.println("---根据属性值过滤条件判断是否返回对象，修改对象，数组属性添加元素----");
        Entity entity2 = new Entity(1001,"ali");
        System.out.println(JSONPath.eval(entity2,"[id = 1001]"));
        System.out.println(JSONPath.eval(entity2,"[id = 1002]"));

        //将id字段修改为123456
        JSONPath.set(entity2, "id", 123456);
        System.out.println(entity2.getId());
        System.out.println(JSONPath.eval(entity2,"[name = 'ali']"));

        ////将value字段赋值为长度为0的数组
        JSONPath.set(entity2, "value", new int[0]);
        //将value字段的数组添加元素1,2,3
        JSONPath.arrayAdd(entity2, "value", 1, 2, 3);
        System.out.println(entity2);
        System.out.println(Arrays.toString((int [])entity2.getValue()));
        System.out.println();

        /**
         * 获取map中多组数据
         */
        System.out.println("-----获取map中多组数据-----");
        Map root = Collections.singletonMap("company",
                Collections.singletonMap("departs",
                        Arrays.asList(
                                Collections.singletonMap("id",
                                        1001),
                                Collections.singletonMap("id",
                                        1002),
                                Collections.singletonMap("id", 1003)
                        )
                ));
        System.out.println(JSON.toJSON(root));
        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
        System.out.println(ids.size());
        System.out.println(ids.get(0));
        System.out.println(ids.get(1));
        System.out.println(ids.get(2));
        System.out.println();
        /**
         * keySet
         * 使用keySet抽取对象的属性名,null值属性的名字并不包含在keySet结果中，使用时需要注意
         */
        System.out.println("--------使用keySet抽取对象的属性名-----");
        Entity e = new Entity();
        e.setId(null);
        e.setName("hello");
        Map<String, Entity> map = Collections.singletonMap("e", e);
        System.out.println(JSON.toJSONString(map));
        Collection<String> result;

        // id is null, excluded by keySet
        result = (Collection<String>)JSONPath.eval(map, "$.e.keySet()");
        System.out.println(JSON.toJSONString(result));
        System.out.println(result.size());
        System.out.println(result.contains("name"));

        e.setId(1);
        result = (Collection<String>)JSONPath.eval(map, "$.e.keySet()");
        System.out.println(JSON.toJSONString(result));
        System.out.println(result.size());
        System.out.println(result.contains("id"));
        System.out.println(result.contains("name"));

        System.out.println(JSONPath.keySet(map,"$.e"));
        System.out.println( new JSONPath("$.e").keySet(map));

    }
}
