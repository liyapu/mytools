
package com.lyp.learn.base.juc.pk01;
import com.lyp.learn.base.threads.pk01.Object;

import java.io.Serializable;
import java.util.function.LongBinaryOperator;

/**
 * 一个或多个变量一起保持使用提供的功能更新的运行的值long 。
 * 当跨线程争用更新（方法accumulate(long) ）时，该变量集可以动态增长以减少争用。
 * 方法get() （或等价于longValue() ）返回维护更新的变量的当前值。
 *
 * 当多线程更新用于收集统计信息的公共值时，此类通常优于AtomicLong，而不是细粒度的同步控制。
 * 在低更新争议下，这两类具有相似的特征。
 * 但是，在高度争议的情况下，这一类的预期吞吐量明显高于牺牲更高的空间消耗。
 *
 * 线程内或跨线程的累积顺序不能得到保证，不能依赖，所以此类仅适用于积累顺序无关的功能。
 * 提供的累加器功能应该是无效的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。
 * 该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。
 * 例如，为了保持最大值，您可以提供Long::max以及Long.MIN_VALUE作为身份。
 *
 * LongAdder课程提供了类别功能的类别，用于维护计数和总和的常见特殊情况。
 * 调用new LongAdder()相当于new LongAccumulator((x, y) -> x + y, 0L 。
 *
 */
public class LongAccumulator extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    private final LongBinaryOperator function;
    private final long identity;

    /**
     * 使用给定的累加器函数和identity元素创建一个新的实例。
     *
     * accumulatorFunction一个双目运算器接口，根据输入的两个参数返回一个计算值，
     * identity则是LongAccumulator累加器的初始值。
     * @param accumulatorFunction 两个参数的无效副作用
     * @param identity 累加器功能的标识（初始值）
     */
    public LongAccumulator(LongBinaryOperator accumulatorFunction,
                           long identity) {
        this.function = accumulatorFunction;
        base = this.identity = identity;
    }

    /**
     * 具有给定值的更新
     *
     * @param x the value
     */
    public void accumulate(long x) {
        Cell[] as; long b, v, r; int m; Cell a;
        //当cells为null,经过给定函数运算，base结果变了，则尝试把新结果更新到base字段上
        if ((as = cells) != null || (r = function.applyAsLong(b = base, x)) != b && !casBase(b, r)) {
            boolean uncontended = true;
            //cells不为null,长度大于0，线程hash在cells中存在，应用函数，与cell上的结果不同，则尝试更新cell上的结果
            if (as == null || (m = as.length - 1) < 0 ||
                (a = as[getProbe() & m]) == null ||
                !(uncontended =
                  (r = function.applyAsLong(v = a.value, x)) == v ||
                  a.cas(v, r)))
                //cells为null,或者长度为0，在cells上不存在，尝试更新失败，都执行下面的函数
            {
                longAccumulate(x, function, uncontended);
            }
        }
    }

    /**
     * 返回当前值。
     * 返回的值不是原子快照;
     * 在没有并发更新的情况下调用返回一个准确的结果，但是在计算该值时发生的并发更新可能未被并入。
     */
    public long get() {
        Cell[] as = cells; Cell a;
        long result = base;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    result = function.applyAsLong(result, a.value);
                }
            }
        }
        return result;
    }

    /**
     * 重置维持更新到标识值的变量。
     * 此方法可能是创建新更新程序的有用替代方法，但仅在没有并发更新时才有效。
     * 因为这个方法本质上是racy，所以只有当已知没有线程同时更新时才应该使用它。
     */
    public void reset() {
        Cell[] as = cells; Cell a;
        base = identity;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    a.value = identity;
                }
            }
        }
    }

    /**
     * 相当于将get()其次是reset() 。
     * 该方法可以例如在多线程计算之间的静态点期间使用。
     * 如果与此方法同时存在更新，则返回值不能保证是重置前发生的最终值。
     */
    public long getThenReset() {
        Cell[] as = cells; Cell a;
        long result = base;
        base = identity;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    long v = a.value;
                    a.value = identity;
                    result = function.applyAsLong(result, v);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Long.toString(get());
    }

    @Override
    public long longValue() {
        return get();
    }

    @Override
    public int intValue() {
        return (int)get();
    }

    @Override
    public float floatValue() {
        return (float)get();
    }

    @Override
    public double doubleValue() {
        return (double)get();
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 7249069246863182397L;

        private final long value;

        private final LongBinaryOperator function;

        private final long identity;

        SerializationProxy(LongAccumulator a) {
            function = a.function;
            identity = a.identity;
            value = a.get();
        }


        private Object readResolve() {
            LongAccumulator a = new LongAccumulator(function, identity);
            a.base = value;
            return a;
        }
    }


    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(java.io.ObjectInputStream s)
        throws java.io.InvalidObjectException {
        throw new java.io.InvalidObjectException("Proxy required");
    }

}
