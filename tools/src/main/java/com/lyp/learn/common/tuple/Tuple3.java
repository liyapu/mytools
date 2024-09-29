package com.lyp.learn.common.tuple;


import com.lyp.learn.common.StrUtil;
import com.lyp.learn.common.tuple.v.V1;
import com.lyp.learn.common.tuple.v.V2;
import com.lyp.learn.common.tuple.v.V3;

import java.util.Objects;

/**
 * Tuple2
 *
 * @author at 2023/12/24 21:55
 */
public final class Tuple3<T1, T2, T3> extends Tuple implements V1<T1>, V2<T2>, V3<T3> {

    private final T1 o1;

    private final T2 o2;

    private final T3 o3;

    public Tuple3(T1 o1, T2 o2, T3 o3) {
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
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
    public T3 v3() {
        return o3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
        return Objects.equals(o1, tuple3.o1) &&
                Objects.equals(o2, tuple3.o2) &&
                Objects.equals(o3, tuple3.o3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(o1, o2, o3);
    }

    @Override
    public String toString() {
        return StrUtil.format("({},{},{})", o1, o2, o3);
    }

    public String toAliasString(String alias1, String alias2, String alias3) {
        return StrUtil.format("({}:{},{}:{},{}:{})", alias1, o1, alias2, o2, alias3, o3);
    }
}
