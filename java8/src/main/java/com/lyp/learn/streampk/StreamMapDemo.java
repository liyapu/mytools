package com.lyp.learn.streampk;

import com.lyp.learn.bean.Apple;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-16 19:00
 */
public class StreamMapDemo {
    List<Apple> inventory = Arrays.asList(
            new Apple("green",80,"黄土高原"),
            new Apple("green",200,"黄河故道"),
            new Apple("red",160,"渤海湾"),
            new Apple("yellow",20,"渤海湾")
    );

    Map<String,List<String>> map = new HashMap<>();
    {
        map.put("java", Arrays.asList("1.7", "1.8"));
    }

    /**
     * 现在想通过stream流操作,将:
     *    Map<String,List<String>> map ---> Map<String,Java> map
     *
     *  根据题主的描述，我感觉其实就是一个把map转化为map的过程，可以看到变化的只是有value而已，key没有变化，
     *  这里map也提供了一种变相来把map中数据转化为stream的方式，并没有直接map.stream()的方法
     *
     *   以前map循环是用到map的entrySet，而这里的entrySet就是一个集合，也就可以用stream了
     *   map.entrySet().stream()
     *   此时流map.entrySet().stream()里的数据是Map.Entry<String,List<String>>，
     *   现在其实就是要把Map.Entry<String,List<String>>转化为Map<String,Java>，
     *   由于最后是要的结果是用map收集，所以只能collect(Collectors.toMap())了
     *
     *   Collectors.toMap的两个参数的方法，
     *       第一个参数代表如何获取key，
     *       第二个代表如何获取value，
     *       因为key没有变，所以直接取entry的key，
     *       value的话要转化为Java对象，所以写了一个构造函数
     */
    @Test
    public void test01(){
        Map<String, Java> collect = map.entrySet()
                .stream()
                .collect(Collectors.toMap(en -> en.getKey(), en -> new Java(en.getValue())));
        System.out.println(collect);
    }

    @Test
    public void test02(){
        Map<String, List<Apple>> address$appleMap = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress));
        System.out.println(address$appleMap);
    }

    /**
     * 把 Map 的 value 值集合进行操作
     */
    @Test
    public void test03(){
        Map<String, List<Apple>> address$appleMap = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress));
        System.out.println(address$appleMap);

        Map<String, String> address$cwMap = address$appleMap.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                e -> e.getKey(),
                                e -> e.getValue().stream()
                                        .map(a -> a.getWeight() + "+" +  a.getColor())
                                        .collect(Collectors.joining("，"))
                        )
                );

        System.out.println(address$cwMap);
    }

    /**
     * map 拼接为字符串
     */
    @Test
    public void test04(){
        Map<String, List<Apple>> address$appleMap = inventory.stream()
                .collect(Collectors.groupingBy(Apple::getAddress));
        System.out.println(address$appleMap);
        System.out.println();

        Map<String, String> address$cwMap = address$appleMap.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                e -> e.getKey(),
                                e -> e.getValue().stream()
                                        .map(a -> a.getWeight() + "+" +  a.getColor())
                                        .collect(Collectors.joining("，"))
                        )
                );

        System.out.println(address$cwMap);
        System.out.println();

        StringBuilder sb = new StringBuilder();
        address$cwMap.forEach((k,v) ->{
            sb.append(k)
                    .append(":")
                    .append(v)
                    .append(";");
        });
        System.out.println(sb.toString());
    }




}


@AllArgsConstructor
@Data
class Java{
    private List<String> versions;
}