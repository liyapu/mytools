package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.list.SetUniqueList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 17:06
 *
 * SetUniqueList
 * 实现了一个不允许重复元素的列表，有点和Set类似。但是由有List，保证了顺序
 *
 */
public class SetUniqueListTest {

    /**
     * 表示这个List在我们既希望去重，有需要保持原来顺序的时候，特别特别好用。
     * 装饰一下就行，使用也非常方便
     */
    @Test
    public void test01() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("b");
        list.add("b");
        System.out.println(list);

        //完美实现去重 且还完美保证了顺序
        list = SetUniqueList.setUniqueList(list);
        System.out.println(list);

        // 但是需要注意 因为已经是SetUniqueList 类型了  这个时候add相同元素就不再好使了
        list.add("a");
        System.out.println(list);
    }

}
