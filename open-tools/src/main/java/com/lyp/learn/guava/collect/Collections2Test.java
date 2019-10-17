package com.lyp.learn.guava.collect;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Collection 集合的帮助类
 *
 * 推荐使用java8,迁移到 Stream,Streams
 */
public class Collections2Test {

    @Test
    public void testSafeMethod(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("bbbb");
        list.add("cc");
        list.add("d");

        Collection<Object> transformList = Collections2.transform(list, new Function<String, Object>() {
            @Nullable
            @Override
            public Object apply(@Nullable String input) {
                return input.length();
            }
        });

        System.out.println(transformList);

    }
}
