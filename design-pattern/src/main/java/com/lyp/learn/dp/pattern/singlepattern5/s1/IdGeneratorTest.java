package com.lyp.learn.dp.pattern.singlepattern5.s1;

/**
 * @author liyapu
 * @date 2023-03-08 11:51
 * @description
 */
public class IdGeneratorTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final long id = IdGenerator.getInstance().getId();
            System.out.println("id = " + id);
        }
    }
}
