package com.lyp.learn.base.pk11;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleBinaryOperator;
import java.util.function.LongBinaryOperator;

/**
 * A package-local class holding common representation and mechanics
 * for classes supporting dynamic striping on 64bit values. The class
 * extends Number so that concrete subclasses must publicly do so.
 */
@SuppressWarnings("serial")
abstract class Striped64 extends Number {
    /*
     * This class maintains a lazily-initialized table of atomically
     * updated variables, plus an extra "base" field. The table size
     * is a power of two. Indexing uses masked per-thread hash codes.
     * Nearly all declarations in this class are package-private,
     * accessed directly by subclasses.
     *
     * Table entries are of class Cell; a variant of AtomicLong padded
     * (via @sun.misc.Contended) to reduce cache contention. Padding
     * is overkill for most Atomics because they are usually
     * irregularly scattered in memory and thus don't interfere much
     * with each other. But Atomic objects residing in arrays will
     * tend to be placed adjacent to each other, and so will most
     * often share cache lines (with a huge negative performance
     * impact) without this precaution.
     *
     * In part because Cells are relatively large, we avoid creating
     * them until they are needed.  When there is no contention, all
     * updates are made to the base field.  Upon first contention (a
     * failed CAS on base update), the table is initialized to size 2.
     * The table size is doubled upon further contention until
     * reaching the nearest power of two greater than or equal to the
     * number of CPUS. Table slots remain empty (null) until they are
     * needed.
     *
     * A single spinlock ("cellsBusy") is used for initializing and
     * resizing the table, as well as populating slots with new Cells.
     * There is no need for a blocking lock; when the lock is not
     * available, threads try other slots (or the base).  During these
     * retries, there is increased contention and reduced locality,
     * which is still better than alternatives.
     *
     * The Thread probe fields maintained via ThreadLocalRandom serve
     * as per-thread hash codes. We let them remain uninitialized as
     * zero (if they come in this way) until they contend at slot
     * 0. They are then initialized to values that typically do not
     * often conflict with others.  Contention and/or table collisions
     * are indicated by failed CASes when performing an update
     * operation. Upon a collision, if the table size is less than
     * the capacity, it is doubled in size unless some other thread
     * holds the lock. If a hashed slot is empty, and lock is
     * available, a new Cell is created. Otherwise, if the slot
     * exists, a CAS is tried.  Retries proceed by "double hashing",
     * using a secondary hash (Marsaglia XorShift) to try to find a
     * free slot.
     *
     * The table size is capped because, when there are more threads
     * than CPUs, supposing that each thread were bound to a CPU,
     * there would exist a perfect hash function mapping threads to
     * slots that eliminates collisions. When we reach capacity, we
     * search for this mapping by randomly varying the hash codes of
     * colliding threads.  Because search is random, and collisions
     * only become known via CAS failures, convergence can be slow,
     * and because threads are typically not bound to CPUS forever,
     * may not occur at all. However, despite these limitations,
     * observed contention rates are typically low in these cases.
     *
     * It is possible for a Cell to become unused when threads that
     * once hashed to it terminate, as well as in the case where
     * doubling the table causes no thread to hash to it under
     * expanded mask.  We do not try to detect or remove such cells,
     * under the assumption that for long-running instances, observed
     * contention levels will recur, so the cells will eventually be
     * needed again; and for short-lived ones, it does not matter.
     */

    /**
     * 注解 @sun.misc.Contended 来自动实现缓存行填充，让Java编译器和JRE运行时来决定如何填充。
     * Cell本质就是一个填充的volatile变量，然后使用CAS进行更新，从而保证线程安全性。
     *
     * Cell内部保存了一个volatile修饰的long型域，同时提供了原子操作，看起来像一个原子量。
     * 这个注解会交个jvm进行合适的填充
     * 注意到Cell类被一个Contended注解修饰，Contended的作用是对Cell做缓存行填充，避免伪共享。
     *
     * Padded variant of AtomicLong supporting only raw accesses plus CAS.
     *
     * JVM intrinsics note: It would be possible to use a release-only
     * form of CAS here, if it were provided.
     */
    @sun.misc.Contended static final class Cell {
        //这个变量是实际承载这个真实值的
        //用来保存要累加的值
        volatile long value;

        Cell(long x) {
            value = x;
        }

        //执行CAS操作
        final boolean cas(long cmp, long val) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
        }

        // Unsafe mechanics
        //就像UnSafe字面意义一样，减少使用这个类，毕竟它等同于使用了c的指针
        private static final sun.misc.Unsafe UNSAFE;
        //value在Cell类中存储位置的偏移量；
        private static final long valueOffset;
        //静态初始化块，用于初始化UNSAFE和valueOffset
        //这个静态方法用于获取偏移量
        static {
            try {
                //Unsafe使用了单例模式
                //所以用getUnsafe获取实例。
                //Unsafe做了限制，
                //如果是一个普通的调用getUnsafe会报SecurityException异常
                //只有主类加载器才能调用这个方法。
                UNSAFE = sun.misc.Unsafe.getUnsafe();
                Class<?> ak = Cell.class;
                //计算value元素的偏移量是多少
                valueOffset = UNSAFE.objectFieldOffset(ak.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /**
     * CPU的核心数，和table的大小有关
     * Number of CPUS, to place bound on table size
     */
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    /**
     * 用来存储Cell的table，在非空情况下一定是2的幂长度
     * Table of cells. When non-null, size is a power of 2.
     */
    transient volatile Cell[] cells;

    /**
     * base字段, 主要在非竞争情况下使用,
     * 也同时充当table在初始化期间的竞争回退，通过CAS操作进行更新.
     *
     * 基础值，没有竞争时会使用(更新)这个值，同时做为初始化竞争失败的回退方案。
     * 原子更新
     *
     * 基础值，
     * 1. 在没有竞争时会更新这个值；
     * 2. 在cells初始化的过程中，cells处于不可用的状态，这时候也会尝试将通过cas操作值累加到base。
     * Base value, used mainly when there is no contention, but also as
     * a fallback during table initialization races. Updated via CAS.
     */
    transient volatile long base;

    /**
     * 自旋锁 (通过CAS操作加锁)用于在对Cells做创建或者调整大小的时候进行加锁
     * Spinlock (locked via CAS) used when resizing and/or creating Cells.
     */
    transient volatile int cellsBusy;

    /**
     *  默认函数使用default修饰，也就是包内有效
     * Package-private default constructor
     */
    Striped64() {
    }

    /**
     *  通过CAS操作，修改Base字段
     * CASes the base field.
     */
    final boolean casBase(long cmp, long val) {
        return UNSAFE.compareAndSwapLong(this, BASE, cmp, val);
    }

    /**
     * 通过CAS操作修改cellsBusy字段，从0修改为1，表示获得锁
     *
     * 对cellsBusy(自旋锁)成员变量进行更新,
     * 如果cellsBusy为0(自旋锁未被占用状态)则将cellsBusy置为1(占用状态)并返回true.
     * 如果cellsBusy为1(占用状态),获取自旋锁失败,不进行任何操作,返回false.
     * CASes the cellsBusy field from 0 to 1 to acquire lock.
     */
    final boolean casCellsBusy() {
        return UNSAFE.compareAndSwapInt(this, CELLSBUSY, 0, 1);
    }

    /**
     * 返回当前线程的probe值(hash值)
     *
     * 返回当前线程的标示.
     * 由于包限制，这段代码是从ThreadLocalRandom拷贝过来的
     * Returns the probe value for the current thread.
     * Duplicated from ThreadLocalRandom because of packaging restrictions.
     */
    static final int getProbe() {
        return UNSAFE.getInt(Thread.currentThread(), PROBE);
    }

    /**
     * 通过移位和异或运算更新当前线程的probe值(hash值)
     *
     * 计算下一个随机值作为hash值，使用xorshift算法。
     * 利用伪随机算法加强标识后，将为当前线程记录这个标识。
     * 由于包限制，这段代码是从ThreadLocalRandom拷贝过来的
     * Pseudo-randomly advances and records the given probe value for the given thread.
     * Duplicated from ThreadLocalRandom because of packaging restrictions.
     */
    static final int advanceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        //设置到当前线程的threadLocalRandomProbe域。
        UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
        return probe;
    }

    /**
     * 将给定的值x累加到当前值(Striped64本身)上，x值为正就是加、为负就是减。
     *
     * 这个方法处理初始化、调整大小、创建新Cells和争用等情况。
     * 这个方法由于有比较乐观的重试机制，所以存在常见的非模块化问题，依赖于重试
     *
     *
     * Handles cases of updates involving initialization, resizing,
     * creating new Cells, and/or contention. See above for
     * explanation. This method suffers the usual non-modularity
     * problems of optimistic retry code, relying on rechecked sets of
     * reads.
     *
     * @param x the value  元素
     * @param fn the update function, or null for add (this convention
     * avoids the need for an extra field or function in LongAdder).
     *           更新函数，如果是add可以为null，
     *           这个约定避免了longadder中定义额外的变量或者函数
     * @param wasUncontended false if CAS failed before call
     *                       以前是否竞争过
     *                       如果CAS在调用之前失败了，这个值为false
     */
    final void longAccumulate(long x, LongBinaryOperator fn, boolean wasUncontended) {
        int h;
        //获取当前线程的probe值，如果probe值为0，强制初始化当前线程的probe值，这次初始化的probe值不会为0。
        if ((h = getProbe()) == 0) {
            // 强制初始化
            ThreadLocalRandom.current();
            //再次获取probe值作为hash值。
            h = getProbe();
            //这次相当于再次计算了hash，所以设置未竞争标记为true。
            wasUncontended = true;
        }
        //如果上一个slot不为空置为true
        //碰撞标记
        boolean collide = false;
        for (;;) {
            Cell[] as; Cell a; int n; long v;
            //Cells不为空，进行操作
            if ((as = cells) != null && (n = as.length) > 0) {
                //通过（hashCode & (length - 1)）这种算法来实现取模
                //如果当前位置为null说明需要初始化
                if ((a = as[(n - 1) & h]) == null) {
                    //判断锁状态
                    //尝试添加新的cell
                    if (cellsBusy == 0) {       // Try to attach new Cell
                        Cell r = new Cell(x);   // Optimistically create
                        //再次判断锁状态，同时获取锁
                        if (cellsBusy == 0 && casCellsBusy()) {
                            boolean created = false;
                            //利用try确保处于获取锁的状态下
                            try {
                                // Recheck under lock
                                Cell[] rs; int m, j;
                                if ((rs = cells) != null && (m = rs.length) > 0 && rs[j = (m - 1) & h] == null) {
                                    //Cell添加
                                    rs[j] = r;
                                    //标示创建
                                    created = true;
                                }
                            } finally {
                                //释放锁
                                cellsBusy = 0;
                            }
                            //创建成功跳出，否则重试
                            if (created) {
                                break;
                            }
                            //说明上面指定的cell的位置上有cell了，继续尝试。
                            continue;           // Slot is now non-empty
                        }
                    }
                    //走到这里说明获取cellsBusy锁失败。
                    collide = false;
                }
                //运行到此说明cell的对应位置上已经有相应的Cell了，
                //不需要初始化了
                //CAS操作已经失败了，出现了竞争
                else if (!wasUncontended)       // CAS already known to fail
                    //如果之前的CAS失败，说明已经发生竞争，
                    //这里会设置未竞争标志位true，然后再次算一个probe值，然后重试。
                {
                    wasUncontended = true;      // Continue after rehash
                }
                    //尝试去修改a上的计数，
                    //a为Cell数组中index位置上的cell
                    //这里尝试将x值加到a的value上。
                else if (a.cas(v = a.value, ((fn == null) ? v + x : fn.applyAsLong(v, x))))
                    //如果尝试成功，跳出循环，方法退出。
                {
                    break;
                }
                    //cell数组最大为cpu的数量，
                    //cells != as表明cells数组已经被更新了
                    //标记为最大状态或者说是过期状态
                else if (n >= NCPU || cells != as)
                    //如果cell表的size已经最大，或者cell表已经发生变化(as是一个过时的)。
                {
                    collide = false;            // At max size or stale
                } else if (!collide)
                    //设置冲突标志，表示发生了冲突，重试。
                {
                    collide = true;
                }
                    //尝试获取cellsBusy锁。
                    //尝试获取锁之后扩大Cells
                else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        //检测as是否过时。
                        if (cells == as) {      // Expand table unless stale
                            //Cell数组扩容，每次扩容为原来的两倍
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i) {
                                rs[i] = as[i];
                            }
                            cells = rs;
                        }
                    } finally {
                        //释放cellsBusy锁。
                        cellsBusy = 0;
                    }
                    collide = false;
                    //扩容cell表后，再次重试。
                    continue;                   // Retry with expanded table
                }
                //算出下一个hash值
                h = advanceProbe(h);
            }
            //此分支表明Cells是空的，还未创建，所以要获取锁，然后初始化Cells
            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    if (cells == as) {
                        //初始化cell表，初始容量为2。
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(x);
                        cells = rs;
                        init = true;
                    }
                } finally {
                    //释放cellsBusy锁。
                    cellsBusy = 0;
                }
                //初始化cell表成功后，退出方法。
                if (init) {
                    break;
                }
            }
            //如果创建cell表由于竞争导致失败，尝试将x累加到base上。
            //此处表明Cells为空，并且初始化的时候获取锁失败，直接在base上进行CAS
            else if (casBase(v = base, ((fn == null) ? v + x : fn.applyAsLong(v, x)))) {
                break;                          // Fall back on using base
            }
        }
    }

    /**
     * Same as longAccumulate, but injecting long/double conversions
     * in too many places to sensibly merge with long version, given
     * the low-overhead requirements of this class. So must instead be
     * maintained by copy/paste/adapt.
     */
    final void doubleAccumulate(double x, DoubleBinaryOperator fn, boolean wasUncontended) {
        int h;
        if ((h = getProbe()) == 0) {
            ThreadLocalRandom.current(); // force initialization
            h = getProbe();
            wasUncontended = true;
        }
        boolean collide = false;                // True if last slot nonempty
        for (;;) {
            Cell[] as; Cell a; int n; long v;
            if ((as = cells) != null && (n = as.length) > 0) {
                if ((a = as[(n - 1) & h]) == null) {
                    if (cellsBusy == 0) {       // Try to attach new Cell
                        Cell r = new Cell(Double.doubleToRawLongBits(x));
                        if (cellsBusy == 0 && casCellsBusy()) {
                            boolean created = false;
                            try {               // Recheck under lock
                                Cell[] rs; int m, j;
                                if ((rs = cells) != null && (m = rs.length) > 0 && rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                cellsBusy = 0;
                            }
                            if (created) {
                                break;
                            }
                            continue;           // Slot is now non-empty
                        }
                    }
                    collide = false;
                }
                else if (!wasUncontended)       // CAS already known to fail
                {
                    wasUncontended = true;      // Continue after rehash
                } else if (a.cas(v = a.value, ((fn == null) ?
                        Double.doubleToRawLongBits(Double.longBitsToDouble(v) + x) :
                                Double.doubleToRawLongBits(fn.applyAsDouble(Double.longBitsToDouble(v), x))
                ))) {
                    break;
                } else if (n >= NCPU || cells != as) {
                    collide = false;            // At max size or stale
                } else if (!collide) {
                    collide = true;
                } else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        if (cells == as) {      // Expand table unless stale
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i) {
                                rs[i] = as[i];
                            }
                            cells = rs;
                        }
                    } finally {
                        cellsBusy = 0;
                    }
                    collide = false;
                    continue;                   // Retry with expanded table
                }
                h = advanceProbe(h);
            }
            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    if (cells == as) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(Double.doubleToRawLongBits(x));
                        cells = rs;
                        init = true;
                    }
                } finally {
                    cellsBusy = 0;
                }
                if (init) {
                    break;
                }
            }
            else if (casBase(v = base,
                             ((fn == null) ?
                              Double.doubleToRawLongBits
                              (Double.longBitsToDouble(v) + x) :
                              Double.doubleToRawLongBits
                              (fn.applyAsDouble
                               (Double.longBitsToDouble(v), x))))) {
                break;                          // Fall back on using base
            }
        }
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    //代表的base成员变量相对于Striped64对象头的偏移地址
    private static final long BASE;
    //代表cellsBusy这个自旋锁相对于Striped64对象头的偏移地址
    private static final long CELLSBUSY;
    //代表threadLocalRandomProbe相对于Thread类对象头的偏移地址
    private static final long PROBE;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> sk = Striped64.class;
            BASE = UNSAFE.objectFieldOffset
                (sk.getDeclaredField("base"));
            CELLSBUSY = UNSAFE.objectFieldOffset
                (sk.getDeclaredField("cellsBusy"));
            Class<?> tk = Thread.class;
            PROBE = UNSAFE.objectFieldOffset
                (tk.getDeclaredField("threadLocalRandomProbe"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
