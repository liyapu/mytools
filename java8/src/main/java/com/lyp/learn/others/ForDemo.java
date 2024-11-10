package com.lyp.learn.others;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author liyapu
 * @date 2024-11-07 20:56
 * @description
 */
public class ForDemo {

    /**
     * 在 Java 中，forEach 方法使用的是 Lambda 表达式，不能直接使用 continue 和 break 关键字。可以通过以下方式实现类似的功能：
     * continue: 使用 return 跳过当前元素。
     * break: 使用异常来中断循环
     */
    @Test
    public void testForEach() {
        List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5);

        ints.forEach(i -> {
            System.out.println(i);
        });
        System.out.println("-------------");
        ints.forEach(i -> {
            if (i == 3) {
                //只是没有输出3，其他的4，5都正常输出，类似于continue
                return;
            }
            System.out.println(i);
        });

    }

    @Test
    public void testForEach02() {
        List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5);

        // continue 示例
        ints.forEach(i -> {
            if (i == 3) {
                return; // 跳过当前元素
            }
            System.out.println(i);
        });

        System.out.println("-------------");

        // break 示例
        try {
            ints.forEach(i -> {
                if (i == 3) {
                    throw new RuntimeException("Break loop");
                }
                System.out.println(i);
            });
        } catch (RuntimeException e) {
            // 捕获异常以中断循环
        }
    }

}
