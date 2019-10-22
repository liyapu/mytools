package com.lyp.learn.guava.collect;

import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FluentIterableTest {

    public static FluentIterable<String> build(){
        List<String> list = Lists.newArrayList("java", "python", "guava", "js", "css", "scala");
        return FluentIterable.from(list);
    }

    @Test
    public void testFilter(){
        FluentIterable<String> fit = build();
        assertThat(fit.size(),equalTo(6));

        FluentIterable<String> result = fit.filter(e -> e != null && e.length() > 4);
        assertThat(result.size(),equalTo(3));
    }

    @Test
    public void testAppend(){
        FluentIterable<String> fit = build();
        assertThat(fit.size(),equalTo(6));
        System.out.println(fit);

        FluentIterable<String> result1 = fit.append("a", "bbbb", "cccc");
        assertThat(result1.size(),equalTo(9));
        System.out.println(result1);

        ArrayList<String> appendList = Lists.newArrayList("11", "22", "33");
        FluentIterable<String> result2 = result1.append(appendList);
        assertThat(result2.size(),equalTo(12));
        assertThat(result2.contains("22"),is(true));
        System.out.println(result2);
    }

    @Test
    public void testMatch(){
        FluentIterable<String> fit = build();

        boolean b = fit.allMatch(e -> e != null && e.length() >= 4);
        assertThat(b,is(false));

        boolean b1 = fit.anyMatch(e -> e != null && e.length() == 5);
        assertThat(b1,is(true));

        //找到第一个匹配的，并拿出来
        Optional<String> optional = fit.firstMatch(e -> e != null && e.length() == 5);
        assertThat(optional.isPresent(),is(true));
        assertThat(optional.get(),equalTo("guava"));
    }

    @Test
    public void testFirst$Last(){
        FluentIterable<String> fit = build();

        Optional<String> first = fit.first();
        assertThat(first.isPresent(),is(true));
        assertThat(first.get(),equalTo("java"));


        Optional<String> last = fit.last();
        assertThat(last.isPresent(),is(true));
        assertThat(last.get(),equalTo("scala"));

    }

}
