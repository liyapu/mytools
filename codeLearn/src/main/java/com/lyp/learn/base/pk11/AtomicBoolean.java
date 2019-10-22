
package com.lyp.learn.base.pk11;
import sun.misc.Unsafe;

/**
 * 一个boolean值可以用原子更新。
 * 一个AtomicBoolean用于诸如原子更新标志的应用程序，不能用作替代Boolean 。
 */
public class AtomicBoolean implements java.io.Serializable {
    private static final long serialVersionUID = 4654671469794556979L;
    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (AtomicBoolean.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int value;

    /**
     * 用给定的初始值创建一个新的 AtomicBoolean 。
     */
    public AtomicBoolean(boolean initialValue) {
        value = initialValue ? 1 : 0;
    }

    /**
     * 创建一个新的 AtomicBoolean ，初始值为 false 。
     */
    public AtomicBoolean() {
    }

    /**
     * 返回当前值
     */
    public final boolean get() {
        return value != 0;
    }

    /**
     * 如果当前值为 ==的预期值，则将该值原子设置为给定的更新值。
     *
     * @param expect 期望值
     * @param update 新值
     * @return true如果成功。 False return表示实际值不等于预期值。
     */
    public final boolean compareAndSet(boolean expect, boolean update) {
        int e = expect ? 1 : 0;
        int u = update ? 1 : 0;
        return unsafe.compareAndSwapInt(this, valueOffset, e, u);
    }

    /**
     * 如果当前值为==为预期值，则将该值原子设置为给定的更新值。
     * 可能虚假失败，并且不提供顺序保证,所以只是很少适合替代compareAndSet
     *
     * @param expect 预期值
     * @param update 新的价值
     * @return true如果成功
     */
    public boolean weakCompareAndSet(boolean expect, boolean update) {
        int e = expect ? 1 : 0;
        int u = update ? 1 : 0;
        return unsafe.compareAndSwapInt(this, valueOffset, e, u);
    }

    /**
     * 无条件地设置为给定的值。
     * @param newValue  新的价值
     */
    public final void set(boolean newValue) {
        value = newValue ? 1 : 0;
    }

    /**
     * 最终设定为给定值。
     *
     * @param newValue 新的价值
     */
    public final void lazySet(boolean newValue) {
        int v = newValue ? 1 : 0;
        unsafe.putOrderedInt(this, valueOffset, v);
    }

    /**
     * 将原子设置为给定值并返回上一个值
     * @param newValue 新值
     * @return 旧值
     */
    public final boolean getAndSet(boolean newValue) {
        boolean prev;
        do {
            prev = get();
        } while (!compareAndSet(prev, newValue));
        return prev;
    }

    /**
     * 返回当前值的String表示形式。
     */
    @Override
    public String toString() {
        return Boolean.toString(get());
    }

}
