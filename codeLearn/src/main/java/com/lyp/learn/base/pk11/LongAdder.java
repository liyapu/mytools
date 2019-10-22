
package com.lyp.learn.base.pk11;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.LongBinaryOperator;

/**
 * 一个或多个变量一起维持初始为零long总和。
 * 当更新（方法add(long) ）跨线程竞争时，变量集可以动态增长以减少争用。
 * 方法sum() （或等效地， longValue() ）返回保持总和的整个变量组合的当前总和。
 *
 * 这个类是通常优选AtomicLong当多个线程更新时使用，用于诸如收集统计信息，不用于细粒度同步控制的共同总和。
 * 在低更新争议下，这两类具有相似的特征。 但是，在高度争议的情况下，这一类的预期吞吐量明显高于牺牲更高的空间消耗。
 *
 * LongAdders可以使用ConcurrentHashMap来维护可扩展的频率映射（一种直方图或多集）。
 * 例如，要向ConcurrentHashMap<String,LongAdder> freqs添加一个计数，
 * 如果尚未存在，则可以使用freqs.computeIfAbsent(k -> new LongAdder()).increment();
 *
 * 该类扩展Number ，但不定义诸如方法equals ， hashCode和compareTo ，因为实例预计将发生突变，所以不如收集集合有用。
 *
 */
public class LongAdder extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    /**
     * 创建一个新的加法器，初始和为零。
     * Creates a new adder with initial sum of zero.
     */
    public LongAdder() {
    }

    /**
     *   add方法逻辑很简单，
     *   先尝试将x累加到base上，失败的话再看看能不能从cell表中找到cell，找到的话再尝试将x累加到这个cell里面，
     *   还失败的话就调用longAccumulate方法，
     *   这个方法上篇分析Striped64的时候分析过。
     * @param x
     */
    public void add0(long x) {
        Cell[] as; long b, v; int m; Cell a;
        //如果cell表为null，会尝试将x累加到base上。
        if ((as = cells) != null || !casBase(b = base, b + x)) {
            /*
             * 如果cell表不为null或者尝试将x累加到base上失败，执行以下操作。
             * 如果cell表不为null且通过当前线程的probe值定位到的cell表中的Cell不为null。
             * 那么尝试累加x到对应的Cell上。
             */
            boolean uncontended = true;
            if (as == null || (m = as.length - 1) < 0 ||
                    (a = as[getProbe() & m]) == null ||
                    !(uncontended = a.cas(v = a.value, v + x)))
                //或者cell表为null，或者定位到的cell为null，或者尝试失败，
                // 都会调用下面的Striped64中定义的longAccumulate方法。
            {
                longAccumulate(x, null, uncontended);
            }
        }
    }

    /**
     * 添加给定值。
     * Adds the given value.
     *
     * @param x the value to add 要添加的值
     */


    public void add(long x) {
        Cell[] as; long b, v; int m; Cell a;
        /**
         * 如果一下两种条件则继续执行if内的语句
         * 1. cells数组不为null
         *      （不存在争用的时候，cells数组一定为null，一旦对base的cas操作失败，才会初始化cells数组）
         * 2. 如果cells数组为null，如果casBase执行成功，则直接返回，
         *    如果casBase方法执行失败（casBase失败，说明第一次争用冲突产生，需要对cells数组初始化）进入if内；
         *
         *      casBase方法很简单，就是通过UNSAFE类的cas设置成员变量base的值为base+要累加的值
         *      casBase执行成功的前提是无竞争，
         *      这时候cells数组还没有用到为null，可见在无竞争的情况下是类似于AtomticInteger处理方式，使用cas做累加。
         */
        if ((as = cells) != null || !casBase(b = base, b + x)) {
            //uncontended判断cells数组中，当前线程要做cas累加操作的某个元素是否不存在争用，
            // 如果cas失败则存在争用；
            // uncontended=false代表存在争用，
            // uncontended=true代表不存在争用。

            boolean uncontended = true;
            /**
             *1. as == null ： cells数组未被初始化，成立则直接进入if执行cell初始化
             *2. (m = as.length - 1) < 0： cells数组的长度为0
             *   条件1与2都代表cells数组没有被初始化成功，初始化成功的cells数组长度为2；
             *3. (a = as[getProbe() & m]) == null ：
             *      如果cells被初始化，且它的长度不为0，
             *      则通过getProbe方法获取当前线程Thread的threadLocalRandomProbe变量的值，初始为0，
             *      然后执行threadLocalRandomProbe&(cells.length-1 ),
             *      相当于m%cells.length;
             *      如果cells[threadLocalRandomProbe%cells.length]的位置为null，
             *      这说明这个位置从来没有线程做过累加，需要进入if继续执行，在这个位置创建一个新的Cell对象；
             *
             *4. !(uncontended = a.cas(v = a.value, v + x))：
             *    尝试对cells[threadLocalRandomProbe%cells.length]位置的Cell对象中的value值做累加操作,
             *    并返回操作结果,如果失败了则进入if，重新计算一个threadLocalRandomProbe；

             如果进入if语句执行longAccumulate方法,有三种情况
             1. 前两个条件代表cells没有初始化，
             2. 第三个条件指当前线程hash到的cells数组中的位置还没有其它线程做过累加操作，
             3. 第四个条件代表产生了冲突,uncontended=false
             **/
            if (as == null || (m = as.length - 1) < 0 ||
                    (a = as[getProbe() & m]) == null ||
                    !(uncontended = a.cas(v = a.value, v + x))) {
                longAccumulate(x, null, uncontended);
            }
        }
    }


    final void longAccumulatee(long x, LongBinaryOperator fn, boolean wasUncontended) {
        //获取当前线程的threadLocalRandomProbe值作为hash值,
        // 如果当前线程的threadLocalRandomProbe为0，说明当前线程是第一次进入该方法，
        // 则强制设置线程的threadLocalRandomProbe为ThreadLocalRandom类的成员静态私有变量probeGenerator的值，
        // 后面会详细将hash值的生成;

        //另外需要注意，如果threadLocalRandomProbe=0，代表新的线程开始参与cell争用的情况
        //1.当前线程之前还没有参与过cells争用（也许cells数组还没初始化，进到当前方法来就是为了初始化cells数组后争用的）,
        // 是第一次执行base的cas累加操作失败；
        //2.或者是在执行add方法时，对cells某个位置的Cell的cas操作第一次失败，则将wasUncontended设置为false，
        // 那么这里会将其重新置为true；第一次执行操作失败；
        //凡是参与了cell争用操作的线程threadLocalRandomProbe都不为0；
        int h;
        if ((h = getProbe()) == 0) {
            //初始化ThreadLocalRandom;
            ThreadLocalRandom.current(); // force initialization
            //将h设置为0x9e3779b9
            h = getProbe();
            //设置未竞争标记为true
            wasUncontended = true;
        }
        //cas冲突标志，表示当前线程hash到的Cells数组的位置，做cas累加操作时与其它线程发生了冲突，cas失败；
        // collide=true代表有冲突，collide=false代表无冲突
        boolean collide = false;
        for (;;) {
            Cell[] as; Cell a; int n; long v;
            //这个主干if有三个分支
            //1.主分支一：处理cells数组已经正常初始化了的情况（这个if分支处理add方法的四个条件中的3和4）
            //2.主分支二：处理cells数组没有初始化或者长度为0的情况；（这个分支处理add方法的四个条件中的1和2）
            //3.主分支三：处理如果cell数组没有初始化，并且其它线程正在执行对cells数组初始化的操作，及cellbusy=1；
            //            则尝试将累加值通过cas累加到base上
            //先看主分支一
            if ((as = cells) != null && (n = as.length) > 0) {
                /**
                 *内部小分支一：这个是处理add方法内部if分支的条件3：
                 *              如果被hash到的位置为null，说明没有线程在这个位置设置过值，没有竞争，可以直接使用，
                 *              则用x值作为初始值创建一个新的Cell对象，对cells数组使用cellsBusy加锁，
                 *              然后将这个Cell对象放到cells[m%cells.length]位置上
                 */
                if ((a = as[(n - 1) & h]) == null) {
                    //cellsBusy == 0 代表当前没有线程cells数组做修改
                    if (cellsBusy == 0) {
                        //将要累加的x值作为初始值创建一个新的Cell对象，
                        Cell r = new Cell(x);
                        //如果cellsBusy=0无锁，则通过cas将cellsBusy设置为1加锁
                        if (cellsBusy == 0 && casCellsBusy()) {
                            //标记Cell是否创建成功并放入到cells数组被hash的位置上
                            boolean created = false;
                            try {
                                Cell[] rs; int m, j;
                                //再次检查cells数组不为null，且长度不为空，且hash到的位置的Cell为null
                                if ((rs = cells) != null && (m = rs.length) > 0 && rs[j = (m - 1) & h] == null) {
                                    //将新的cell设置到该位置
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                //去掉锁
                                cellsBusy = 0;
                            }
                            //生成成功，跳出循环
                            if (created) {
                                break;
                            }
                            //如果created为false，说明上面指定的cells数组的位置
                            // cells[m%cells.length]已经有其它线程设置了cell了，继续执行循环。
                            continue;
                        }
                    }
                    //如果执行的当前行，代表cellsBusy=1，有线程正在更改cells数组，代表产生了冲突，将collide设置为false
                    collide = false;

                    /**
                     *内部小分支二：如果add方法中条件4的通过cas设置cells[m%cells.length]位置的Cell对象中的value值设置为v+x失败,
                     *            说明已经发生竞争，将wasUncontended设置为true，跳出内部的if判断，
                     *            最后重新计算一个新的probe，然后重新执行循环;
                     */
                } else if (!wasUncontended)
                    //设置未竞争标志位true，继续执行，后面会算一个新的probe值，然后重新执行循环。
                {
                    wasUncontended = true;
                }
                /**
                 *内部小分支三：新的争用线程参与争用的情况：处理刚进入当前方法时threadLocalRandomProbe=0的情况，
                 *            也就是当前线程第一次参与cell争用的cas失败，这里会尝试将x值
                 *            加到cells[m%cells.length]的value ，如果成功直接退出
                 */
                else if (a.cas(v = a.value, ((fn == null) ? v + x : fn.applyAsLong(v, x)))) {
                    break;
                }
                /**
                 *内部小分支四：分支3处理新的线程争用执行失败了，这时如果cells数组的长度已经到了最大值（大于等于cup数量），
                 *               或者是当前cells已经做了扩容，则将collide设置为false，后面重新计算prob的值
                 */
                else if (n >= NCPU || cells != as) {
                    collide = false;
                }
                 /**
                 *内部小分支五：如果发生了冲突collide=false，则设置其为true；会在最后重新计算hash值后，进入下一次for循环
                 */
                else if (!collide)
                    //设置冲突标志，表示发生了冲突，需要再次生成hash，重试。
                    // 如果下次重试任然走到了改分支此时collide=true，!collide条件不成立，则走后一个分支
                {
                    collide = true;
                }
                /**
                 *内部小分支六：扩容cells数组，新参与cell争用的线程两次均失败，且符合扩容条件，会执行该分支
                 */
                else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        //检查cells是否已经被扩容
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
                //为当前线程重新计算hash值
                h = advanceProbe(h);

                //这个大的分支处理add方法中的条件1与条件2成立的情况，如果cell表还未初始化或者长度为0，先尝试获取cellsBusy锁。
            }else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    //初始化cells数组，初始容量为2,并将x值通过hash&1，放到0个或第1个位置上
                    if (cells == as) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(x);
                        cells = rs;
                        init = true;
                    }
                } finally {
                    //解锁
                    cellsBusy = 0;
                }
                //如果init为true说明初始化成功，跳出循环
                if (init) {
                    break;
                }
            }
            /**
             *如果以上操作都失败了，则尝试将值累加到base上；
             */
            else if (casBase(v = base, ((fn == null) ? v + x :
                    fn.applyAsLong(v, x)))) {
                break;                          // Fall back on using base
            }
        }
    }

    /**
     * 相当于 add(1)
     * Equivalent to {@code add(1)}.
     */
    public void increment() {
        add(1L);
    }

    /**
     * 相当于 add(-1)
     * Equivalent to {@code add(-1)}.
     */
    public void decrement() {
        add(-1L);
    }

    /**
     * 返回当前总和。
     * 返回的值不是原子快照;
     * 在没有并发更新的情况下调用将返回一个准确的结果，但是在计算总和时发生的并发更新可能不会被并入。
     *
     * 返回当前的值，
     * 内部操作是累加所有 Cell 内部的 value 的值后累加 base，
     * 如下代码，由于计算总和时候没有对 Cell 数组进行加锁，所以在累加过程中可能有其它线程对 Cell 中的值进行了修改，
     * 也有可能数组进行了扩容，所以 sum 返回的值并不是非常精确的，
     * @return the sum
     */
    public long sum() {
        Cell[] as = cells; Cell a;
        long sum = base;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    sum += a.value;
                }
            }
        }
        return sum;
    }

    /**
     * 将保持总和的变量重置为零。
     * 该方法可能是创建新加法器的有用替代方法，但仅在没有并发更新时才有效。
     * 因为这个方法本质上是racy，所以只有当已知没有线程同时更新时才应该使用它。
     *
     * 重置操作，如下代码把 base 置为 0，如果 Cell 数组有元素，则元素值重置为 0
     */
    public void reset() {
        Cell[] as = cells; Cell a;
        base = 0L;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    a.value = 0L;
                }
            }
        }
    }

    /**
     * 相当于sum()后跟reset()
     * 该方法可以例如在多线程计算之间的静态点期间使用。
     * 如果与此方法同时存在更新，则返回值不能保证是重置前发生的最终值。
     *
     * 是sum 的改造版本，
     * 如下代码，在计算 sum 累加对应的 cell 值后，把当前 cell 的值重置为 0，base 重置为 0。
     * 当多线程调用该方法时候会有问题，比如考虑第一个调用线程会清空 Cell 的值，后一个线程调用时候累加时候累加的都是 0 值。
     * @return the sum
     */
    public long sumThenReset() {
        Cell[] as = cells; Cell a;
        long sum = base;
        base = 0L;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    sum += a.value;
                    a.value = 0L;
                }
            }
        }
        return sum;
    }

    /**
     * 返回 sum()的String表示 形式 。
     * @return the String representation of the {@link #sum}
     */
    @Override
    public String toString() {
        return Long.toString(sum());
    }

    /**
     *相当于 sum() 。
     * @return the sum
     */
    @Override
    public long longValue() {
        return sum();
    }

    /**
     * 在 缩小原始 int后返回 sum()作为int。
     */
    @Override
    public int intValue() {
        return (int)sum();
    }

    /**
     * 返回 sum()为 float一个宽元转换后。
     */
    @Override
    public float floatValue() {
        return (float)sum();
    }

    /**
     * 返回 sum()为 double一个宽元转换后。
     */
    @Override
    public double doubleValue() {
        return (double)sum();
    }

    /**
     * Serialization proxy, used to avoid reference to the non-public
     * Striped64 superclass in serialized forms.
     * @serial include
     */
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 7249069246863182397L;

        /**
         * The current value returned by sum().
         * @serial
         */
        private final long value;

        SerializationProxy(LongAdder a) {
            value = a.sum();
        }

        /**
         * Return a {@code LongAdder} object with initial state
         * held by this proxy.
         *
         * @return a {@code LongAdder} object with initial state
         * held by this proxy.
         */
        private Object readResolve() {
            LongAdder a = new LongAdder();
            a.base = value;
            return a;
        }
    }

    /**
     * Returns a
     * <a href="../../../../serialized-form.html#java.util.concurrent.atomic.LongAdder.SerializationProxy">
     * SerializationProxy</a>
     * representing the state of this instance.
     *
     * @return a {@link SerializationProxy}
     * representing the state of this instance
     */
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    /**
     * @param s the stream
     * @throws java.io.InvalidObjectException always
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.InvalidObjectException {
        throw new java.io.InvalidObjectException("Proxy required");
    }

}
