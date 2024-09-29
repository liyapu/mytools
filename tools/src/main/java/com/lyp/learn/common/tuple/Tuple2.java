package com.lyp.learn.common.tuple;


import com.lyp.learn.common.StrUtil;
import com.lyp.learn.common.tuple.v.V1;
import com.lyp.learn.common.tuple.v.V2;

import java.util.Objects;

/**
 * Tuple2
 *
 * @author at 2023/12/24 21:55
 */
public final class Tuple2<T1, T2> extends Tuple implements V1<T1>, V2<T2> {

    private final T1 o1;

    private final T2 o2;

    public Tuple2(T1 o1, T2 o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public T1 v1() {
        return o1;
    }

    @Override
    public T2 v2() {
        return o2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(o1, tuple2.o1) && Objects.equals(o2, tuple2.o2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(o1, o2);
    }

    @Override
    public String toString() {
        return StrUtil.format("({},{})", o1, o2);
    }

    public String toAliasString(String alias1, String alias2) {
        return StrUtil.format("({}:{},{}:{})", alias1, o1, alias2, o2);
    }
}
