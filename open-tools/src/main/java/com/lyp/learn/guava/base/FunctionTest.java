package com.lyp.learn.guava.base;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

public class FunctionTest {

    /**
     * 应用两个函数
     * hello.length * 1.0D
     */
    @Test
    public void testCompose(){
        Function<String, Double> compose = Functions.compose(new Function<Integer, Double>() {
            @Nullable
            @Override
            public Double apply(@Nullable Integer input) {
                return input * 1.0D;
            }
        }, new Function<String, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable String input) {
                return input.length();
            }
        });

        System.out.println(compose.apply("hello"));
    }
}
