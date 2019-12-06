package com.lyp.learn.base.juc.pk01;
import java.util.function.LongUnaryOperator;
import java.util.function.LongBinaryOperator;
import sun.misc.Unsafe;

/**
 * 一个long值可以用原子更新。
 * 有关原子变量属性的描述，请参阅java.util.concurrent.atomic包规范。
 * 一个AtomicLong用于诸如原子增量的序列号的应用中，不能用作Long的替代物 。
 * 但是，该类确实扩展了Number ，以允许使用基于数字类的工具和实用程序的统一访问。
 */
public class AtomicLong extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 1927816293512124184L;

    // setup to use Unsafe.compareAndSwapLong for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    //对应value在内存中的地址
    private static final long valueOffset;

    /**
     * 虚拟机是否支持CAS操作
     */
    static final boolean VM_SUPPORTS_LONG_CAS = VMSupportsCS8();

    /**
     * 本地方法，看JVM对long值是否支持 CompareAndSet操作
     */
    private static native boolean VMSupportsCS8();

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(AtomicLong.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    /**
     * volatile 修饰value使value在多线程下可见。
     */
    private volatile long value;

    /**
     * 用给定的初始值创建一个新的AtomicLong
     */
    public AtomicLong(long initialValue) {
        value = initialValue;
    }

    /**
     * 创建一个新的AtomicLong，初始值为 0
     */
    public AtomicLong() {
    }

    /**
     * 获取当前值
     */
    public final long get() {
        return value;
    }

    /**
     * 以原子方式设置当前值为newValue
     */
    public final void set(long newValue) {
        value = newValue;
    }

    /**
     * 最终设定为给定值
     * 延时设置变量值，这个等价于set()方法，
     * 但是由于字段是volatile类型的，因此次字段的修改会比普通字段（非volatile字段）有稍微的性能延时（尽管可以忽略），
     * 所以如果不是想立即读取设置的新值，允许在“后台”修改值，那么此方法就很有用。
     *
     * 如果还是难以理解，这里就类似于启动一个后台线程如执行修改新值的任务，原线程就不等待修改结果立即返回
     * （这种解释其实是不正确的，但是可以这么理解）。
     */
    public final void lazySet(long newValue) {
        unsafe.putOrderedLong(this, valueOffset, newValue);
    }

    /**
     * 将原子设置为给定值并返回旧值
     *
     * @param newValue 新的价值
     * @return 以前的值
     */
    public final long getAndSet(long newValue) {
        return unsafe.getAndSetLong(this, valueOffset, newValue);
    }

    /**
     * 如果当前值为 ==为预期值，则将该值原子设置为给定的更新值
     *
     * 设置前比较前值是否一致，一致则设置，不一致则设置失败。
     * 设置成功返回true，设置失败返回false
     *
     * @param expect 预期值
     * @param update 新的价值
     * @return true如果成功。 False return表示实际值不等于预期值
     */
    public final boolean compareAndSet(long expect, long update) {
        return unsafe.compareAndSwapLong(this, valueOffset, expect, update);
    }

    /**
     * 如果当前值为== ，则将原值设置为给定的更新值。
     * 可能会虚假失败,不提供顺序保证，所以很少适合替换 compareAndSet
     *
     * 如果当前值 == 预期值，则以原子方式将该设置为给定的更新值。
     * JSR规范中说：以原子方式读取和有条件地写入变量但不创建任何 happen-before 排序，
     * 因此不提供与除 weakCompareAndSet 目标外任何变量以前或后续读取或写入操作有关的任何保证。
     * 大意就是说调用weakCompareAndSet时并不能保证不存在happen-before的发生（也就是可能存在指令重排序导致此操作失败）。
     * 但是从Java源码来看，其实此方法并没有实现JSR规范的要求，
     * 最后效果和compareAndSet是等效的，都调用了unsafe.compareAndSwapInt()完成操作。
     *
     * @param expect 预期值
     * @param update 新值
     * @return true 如果成功
     */
    public final boolean weakCompareAndSet(long expect, long update) {
        return unsafe.compareAndSwapLong(this, valueOffset, expect, update);
    }

    /**
     *  以原子方式将当前值加 1，并返回加1前的值。等价于“num++”
     *
     * @return 以前的值
     */
    public final long getAndIncrement() {
        return unsafe.getAndAddLong(this, valueOffset, 1L);
    }

    /**
     * 以原子方式将当前值减 1，并返回减1前的值。等价于“num--”
     * @return 以前的值
     */
    public final long getAndDecrement() {
        return unsafe.getAndAddLong(this, valueOffset, -1L);
    }

    /**
     * 以原子方式将delta添加到当前值，并返回相加前的值
     *
     * @param delta 添加的值
     * @return 以前的值
     */
    public final long getAndAdd(long delta) {
        return unsafe.getAndAddLong(this, valueOffset, delta);
    }

    /**
     * 以原子方式将当前值加 1，并返回加1后的值。等价于“++num”
     *
     * @return 新值
     */
    public final long incrementAndGet() {
        return unsafe.getAndAddLong(this, valueOffset, 1L) + 1L;
    }

    /**
     * 以原子方式将当前值减 1，并返回减1后的值。等价于“--num”
     * @return 新值
     */
    public final long decrementAndGet() {
        return unsafe.getAndAddLong(this, valueOffset, -1L) - 1L;
    }

    /**
     * 以原子方式将delta与当前值相加，并返回相加后的值
     *
     * @param delta 添加的值
     * @return 新值
     */
    public final long addAndGet(long delta) {
        return unsafe.getAndAddLong(this, valueOffset, delta) + delta;
    }

    /**
     * 用应用给定函数的结果原子更新当前值，返回上一个值。
     * 该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。
     *
     * @param updateFunction 无副作用的功能
     * @return 以前的值
     */
    public final long getAndUpdate(LongUnaryOperator updateFunction) {
        long prev, next;
        do {
            prev = get();
            next = updateFunction.applyAsLong(prev);
        } while (!compareAndSet(prev, next));
        return prev;
    }

    /**
     * 使用给定函数的结果原子更新当前值，返回更新的值。
     * 该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。
     *
     * @param updateFunction 无副作用的功能
     * @return 更新的值
     */
    public final long updateAndGet(LongUnaryOperator updateFunction) {
        long prev, next;
        do {
            prev = get();
            next = updateFunction.applyAsLong(prev);
        } while (!compareAndSet(prev, next));
        return next;
    }

    /**
     * 使用给定函数应用给当前值和给定值的结果原子更新当前值，返回上一个值。
     * 该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。
     * 该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。
     *
     * @param x 更新值
     * @param accumulatorFunction 两个参数的无效副作用
     * @return 以前的值
     */
    public final long getAndAccumulate(long x,
                                       LongBinaryOperator accumulatorFunction) {
        long prev, next;
        do {
            prev = get();
            next = accumulatorFunction.applyAsLong(prev, x);
        } while (!compareAndSet(prev, next));
        return prev;
    }

    /**
     * 使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值。
     * 该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。
     * 该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。
     *
     * @param x  更新值
     * @param accumulatorFunction 两个参数的无效副作用
     * @return 更新的值
     */
    public final long accumulateAndGet(long x,
                                       LongBinaryOperator accumulatorFunction) {
        long prev, next;
        do {
            prev = get();
            next = accumulatorFunction.applyAsLong(prev, x);
        } while (!compareAndSet(prev, next));
        return next;
    }

    /**
     * 返回当前值的String表示形式
     */
    @Override
    public String toString() {
        return Long.toString(get());
    }

    /**
     * 返回当前值对应的int值
     */
    @Override
    public int intValue() {
        return (int)get();
    }

    /**
     * 获取当前值对应的long值
     */
    @Override
    public long longValue() {
        return get();
    }

    /**
     * 以 float 形式返回当前值
     */
    @Override
    public float floatValue() {
        return (float)get();
    }

    /**
     *  以 double 形式返回当前值
     */
    @Override
    public double doubleValue() {
        return (double)get();
    }

}
