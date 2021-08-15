package com.lyp.learn.streampk;

import com.google.common.base.Strings;
import com.lyp.learn.bean.Apple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *@author: liyapu
 *@description:
 *@date 2021-02-03 10:49
 */
public class Utils {

    List<Apple> inventory = Arrays.asList(
            new Apple("green",80,"黄土高原"),
            new Apple("green",200,"黄河故道"),
            new Apple("green",200,"黄河故道"),
            new Apple("red",160,"渤海湾"),
            new Apple("yellow",20,"渤海湾"),
            new Apple("yellow",20,"渤海湾")
    );

    /**
     * list 中找出重复项
     *
     * 当 key 相同时，指定 value 的处理规则 (a, b) -> a + b
     */
    @Test
    public void test01(){
        List<String> dupList = inventory.stream()
                .collect(Collectors.toMap(e -> e.getColor(), e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        System.out.println(dupList);

    }

    public  <E> List<E> getDuplicateElements(List<E> list) {
        return list.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 999; i++) {
            String num = Strings.padStart(i+"",3,'0');
            System.out.println("![](..\\img\\law\\economic"+num+".png)");

        }
    }

}
