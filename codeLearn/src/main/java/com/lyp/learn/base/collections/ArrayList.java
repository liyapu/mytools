package java.util;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.lyp.learn.base.threads.pk01.Object;
import sun.misc.SharedSecrets;

/**
 *  可调整大小的数组实现 List 实例。实现所有可选的列表操作，并且允许所有元素，包括 null 元素。
 *  此外除了实现List接口外，此类会提供一些操作内部存储列表元素的数组大小的方法
 *  (此类大致上等同于Vector, ArrayList和Vector的区别是：
 *  ArrayList是线程不安全的，当多条线程访问同一个ArrayList集合时，程序需要手动保证该集合的同步性，
 *  而Vector则是线程安全的）
 *
 * ==================常见方法的时间复杂度概述==========
 * size、isEmpty、get、set以及iterator方法的时间复杂度是常数级的；
 * 平摊下来的话，add方法所需要的时间也是常数级的——这意味着，插入n个元素的平均时间是O(n)；
 * 以上这些方法都可以在线性时间里完成，并且常数因子要比LinkedList的小；
 * ---------------------------------------
 *
 *
 * ================动态增长概述=================
 * 每一个ArrayList有一个capacity变量，这个变量是ArrayList内部所使用的数组的长度，它不小于其size变量的值；
 * 当元素被添加到ArrayList的时候，capacity会自动增长；
 *
 * 如果想ArrayList中添加大量元素，可使用ensureCapacity方法一次性增加capacity，可以减少增加重分配的次数提高性能
 * 实际上，ArrayList的自动扩容只发生在add以及ensureCapacity函数中，也就是说在构造ArrayList的时候，并不会做扩容
 *-------------------------------------------
 *
 *
 * ======================非同步性=================
 * 请注意，此实现不同步，
 * 所以如果多线程并发访问同一个ArrayList实例，并且至少有一个线程可能会结构性修改list，那么就必须提供外部的同步机制；
 *
 * 所谓结构性修改ArrayList是指添加或者删除一个或者多个元素，或者明确改变内部数组的大小；仅仅设置元素的值并不算是改变了list的结构；
 *
 * 处理ArrayList并发访问的常见做法是对访问ArrayList的对象做同步处理；
 * 如果没有这样的对象，ArrayList对象应该使用Collections.synchronizedList进行包装，
 *
 * 该动作最好在创建的时候就完成，以避免意外地对该List进行了非同步的访问；
 * List list = Collections.synchronizedList(new ArrayList(...));
 *
 * ArrayList使用modCount这一整数变量来跟踪结构性修改的次数；
 * 结构修改指改变list实例元素长度，或使正在迭代的列表产生不正确的结果的影响
 * -------------------------------------------
 *
 * 通过此类的 iterator() 和 listIterator(int) 返回的iterators 是快速失败的 ：
 * 如果列表在任何时间从结构上修改创建迭代器之后，以任何方式除非通过迭代器自身remove种或add方法，
 * 迭代器都将抛出一个ConcurrentModificationException 。
 * 因此，面对并发修改，迭代器将快速而干净地失败，而不是在未来未确定的时间冒着任意的非确定性行为。
 *
 * 需要注意的是，遍历器快速失效的行为在并发的非同步的修改中不能得到任何硬性保证，遍历器会尽最大的努力抛出异常，
 * 但是应用程序不能依赖该异常去保证程序的正确性，该行为只适合于发现Bug。
 */

public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 默认容量
     * ArrayList内部定义了一个DEFAULT_CAPACITY静态变量，其值为10，
     * 该变量在ensureCapacity、newCapacity等方法中被使用，它们是和ArrayList动态扩容相关的两个方法
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 默认的共享的空的数组对象
     * 是所有空的ArrayList实例对象共享的存储数组
     * 底层封装一个 Object 类型的数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 默认的共享的数组对象
     * 是所有使用默认大小作为数组容量的实例对象共享的存储数组，
     * ArrayList通过它来和EMPTY_ELEMENTDATA做区别，以便知道当第一个元素被添加到List中的时候，该如何扩展；
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * elementData则是ArrayList实际存储元素的数组，
     * 如果elementData==DEFAULTCAPACITY_EMPTY_ELEMENTDATA的话，
     * 第一个元素被插入ArrayList的时候，其容量（capacity）将被扩展为默认值，10
     * elementData被赋值为ArrayList返回结果
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * size变量指示ArrayList中存储的元素数量；
     * capacity变量指示内部数组elemenData的大小，使用数组elementData.length 可以查看其值；
     */
    private int size;

    /**
     *  如果初始化容量大于0，那么elementData被创建，然后什么都没有干，没有扩容
     *  如果初始化容量等于0，那么elementData被赋值为EMPTY_ELEMENTDATA，也就是主动申明容量为0。
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 如果不提供初始化容量或者初始化容量为0，elementData实际上都是空的，并没有申请内存空间
     * 构造一个空的初始化容量为10的list
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 过实现Collection接口的类来创造一个ArrayList
     * 如果对象数组的长度为0，elementData则被赋值为EMPTY_ELEMENTDATA
     * 如果size不为0，并且不是Object类型的数组，重新赋值与构造elementData数组
     *
     */
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class) {
                elementData = Arrays.copyOf(elementData, size, Object[].class);
            }
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * trimToSize方法以将其内部数组容量缩减为所包含元素的数量
     * 修改这个ArrayList实例的容量是列表的当前大小。
     * 应用程序可以使用此操作来最小化ArrayList实例的存储。
     * elementData.length 获取的是 数组elementData 的容量capacity
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * 扩充ArrayList实例容量，
     * 确保可以存储minCapacity指定个数的元素
     * 如果需要，增加此 ArrayList实例的容量，以确保它至少可以容纳最小容量参数指定的元素数。
     *
     * @param   minCapacity  要求的最小容量
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // any size if not default element table
                ? 0
                // larger than default for default empty table. It's already
                // supposed to be at default size.
                : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    /**
     *计算容量
     * elementData 为空，则取 DEFAULT_CAPACITY 和 minCapacity 的最大值
     * 否则 ，取 minCapacity
     */
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    /**
     * 内部扩充容量
     */
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    /**
     * 扩充明确的容量
     * minCapacity 大于 elementData数组长度则扩充
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 最大容量
     * ArrayList中最多能存储Integer.MaxValue-8个元素，因为一些虚拟机会在数组中保存一些头字段。试图分配更大的数组将导致抛出异常。
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 扩充容量，确保可以容纳minCapacity指定的个数元素
     * 获取旧的容量
     * 计算新的容量：旧容量 * 1.5倍
     * 新容量小于minCapacity: 则 新容量等于minCapacity
     * 新容量大于MAX_ARRAY_SIZE，则 新容量重新赋值为合理值
     * 使用原有elementData扩充至minCapacity容量
     * @param minCapacity 指定的最小的容量
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
        {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * List中存储的元素个数
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 是否为空
     * 如果此列表不包含元素，则返回 true 。
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 如果此列表包含指定的元素，则返回true 。
     * 更正式地说，返回true当且仅当此列表包含至少一个元素e这样(o==null ? e==null : o.equals(e))。
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * indexOf系列函数
     * 返回此列表中指定元素的第一次出现的索引，如果此列表不包含元素，则返回-1。
     * 更正式地，返回最低指数i ，使(o==null ? get(i)==null : o.equals(get(i))) ，或-1如果没有这样的索引。
     *
     * 用以判断某个对象是否在list里面，基本实现是for循环，
     * 因为ArrayList中的元素可以为空，
     * 而针对空对象，使用==判断，
     * 其他则使用equals判断
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i]==null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回此列表中指定元素的最后一次出现的索引，如果此列表不包含元素，则返回-1。
     * 更正式地，返回最高指数i ，使得(o==null ? get(i)==null : o.equals(get(i))) ，或-1如果没有这样的索引。
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--) {
                if (elementData[i]==null) {
                    return i;
                }
            }
        } else {
            for (int i = size-1; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回此ArrayList实例的浅拷贝。 （元素本身不被复制。）
     */
    @Override
    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * 以正确的顺序（从第一个到最后一个元素）返回一个包含此列表中所有元素的数组。
     * 返回的数组将是“安全的”，因为该列表不保留对它的引用。 （换句话说，这个方法必须分配一个新的数组）。
     * 因此，调用者可以自由地修改返回的数组。
     *
     * 此方法充当基于阵列和基于集合的API之间的桥梁
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 以正确的顺序返回一个包含此列表中所有元素的数组（从第一个到最后一个元素）;
     * 返回的数组的运行时类型是指定数组的运行时类型。
     * 如果列表适合指定的数组，则返回其中。 否则，将为指定数组的运行时类型和此列表的大小分配一个新数组。
     * 会将list中的元素输出到给定的数组中，如果a有足够的空间容纳元素，否则会创建新的a类型的数组；
     *
     * 如果列表适用于指定的数组，其余空间（即数组的列表数量多于此元素），则紧跟在集合结束后的数组中的元素设置为null 。
     * （这仅在调用者知道列表不包含任何空元素的情况下才能确定列表的长度。）
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
        {
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * 返回指定位置的元素
     * @param index
     * @return
     */

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 返回list中指定位置的元素
     */
    @Override
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    /**
     * 用指定的元素替换此列表中指定位置的元素。
     *
     * @param index 要替换的元素的索引
     * @param element 要存储在指定位置的元素
     * @return 指定位置替换之前的老元素
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 将指定的元素追加到此列表的末尾。
     * @param e 要附加到此列表的元素
     */
    @Override
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    /**
     * 在此列表中的指定位置插入指定的元素。
     * 将当前位于该位置的元素（如果有）和任何后续元素向后移动。
     *
     * @param index 要插入指定元素的索引
     * @param element 要插入的元素
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * 删除该列表中指定位置的元素。 将任何后续元素移动到左侧（从其索引中减去一个元素）。
     *
     * @param index 要删除的元素的索引
     * @return 从列表中删除的元素
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    /**
     * 从列表中删除指定元素的第一个出现（如果存在）。
     * 如果列表不包含该元素，则它不会更改。
     * 更正式地，删除具有最低索引i的元素，使得(o==null ? get(i)==null : o.equals(get(i))) （如果存在这样的元素）。
     * 如果此列表包含指定的元素（或等效地，如果此列表作为调用的结果而更改），则返回true 。
     *
     * @param o 要从此列表中删除的元素（如果存在）
     * @return true如果此列表包含指定的元素
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 私有删除方法
     * 跳过了下标检查
     * 不返回删除元素
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null; // clear to let GC do its work
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }

        size = 0;
    }

    /**
     * 按指定集合的Iterator返回的顺序将指定集合中的所有元素追加到此列表的末尾。
     * 如果在操作进行中修改了指定的集合，则此操作的行为是未定义的。
     * （这意味着如果指定的集合是此列表，则此调用的行为是未定义的，并且此列表是非空的。）
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 将指定集合中的所有元素插入到此列表中，从指定的位置开始。
     * 将当前位于该位置（如果有的话）的元素和随后的任何元素移动到右边（增加其索引）。
     * 新元素将按照指定集合的迭代器返回的顺序显示在列表中。
     * Inserts all of the elements in the sp
     *
     * @param index 从中指定集合插入第一个元素的索引
     * @param c  包含要添加到此列表的元素的集合
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
        }

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 从此列表中删除所有索引为 [fromIndex,toIndex) 之间的元素。
     * 将任何后续元素移动到左侧（减少其索引）。 此调用缩短(toIndex - fromIndex)个元素列表。
     * （如果是toIndex==fromIndex ，这个操作没有效果）
     */
    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                numMoved);

        // clear to let GC do its work
        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    /**
     * 检查索引是否在合理范围内
     * 如果不在，抛出异常
     * 如果是负数，抛出异常
     */
    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 校验索引是否在list的有效范围内
     * 在 add 和 addAll 方法时用
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 构造IndexOutOfBoundsException 详情信息
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * 从此列表中删除指定集合中包含的所有元素。
     * 新建 elementData 数组
     * 在新建数组中包含指定集合元素的位置上放置下一个不在指定集合中的元素
     * 新建数组中删除尾部无用元素
     * {a,b,c,d,e,f,g,h}.removeAll({c,f})
     * {a,b,d,d,e,f,g,h}
     * {a,b,d,e,e,f,g,h}
     * {a,b,d,e,g,f,g,h}
     * {a,b,d,e,g,h,g,h}
     * {a,b,d,e,g,h,null,null}
     * {a,b,d,e,g,h}
     *
     * @param c 包含要从此列表中删除的元素的集合
     * @return true:如果列表结果改变
     * @throws ClassCastException  如果此列表的元素的类与指定的集合不兼容
     * @throws NullPointerException 如果此列表包含空元素，并且指定的集合不允许空元素，或者如果指定的集合为空
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    /**
     * 仅保留此列表中包含在指定集合中的元素。
     * 换句话说，从此列表中删除其中不包含在指定集合中的所有元素。
     *
     * 新建 elementData 数组
     * 在新建数组中不包含指定集合元素的位置上放置一个在指定集合中的元素
     * 新建数组中删除尾部无用元素
     * {a,b,c,d,e,f,g}.retainAll({c,f})
     * {c,f,d,d,e,f,g}
     * {c,f,null,null,null,null,null}
     * {c,f}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        //r用来控制循环，w是记录有多少个交集
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++) {
                if (c.contains(elementData[r]) == complement) {
                    elementData[w++] = elementData[r];
                }
            }
        } finally {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            if (r != size) {
                System.arraycopy(elementData, r, elementData, w, size - r);
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++) {
                    elementData[i] = null;
                }
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 序列化
     * 以流的形式保存ArrayList实例的状态信息
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 反序列化
     * 从流中恢复ArrayList实例对象
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // be like clone(), allocate array based upon size not capacity
            int capacity = calculateCapacity(elementData, size);
            SharedSecrets.getJavaOISAccess().checkArray(s, Object[].class, capacity);
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // Read in all elements in the proper order.
            for (int i=0; i<size; i++) {
                a[i] = s.readObject();
            }
        }
    }

    /**
     * 从列表中的指定位置开始，返回列表中的元素（按正确顺序）的列表迭代器。
     * 指定的索引表示初始调用将返回的第一个元素为next 。
     * 初始调用previous将返回指定索引减1的元素。
     * 返回的列表迭代器是快速失败的
     *
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
        return new ListItr(index);
    }

    /**
     * 返回列表中的列表迭代器（按适当的顺序）。
     * 返回的列表迭代器是快速失败的
     */
    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    /**
     * 以正确的顺序返回该列表中的元素的迭代器。
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * 最小化版本的 AbstractList.Itr
     */
    private class Itr implements Iterator<E> {
        //下个被返回元素的索引
        int cursor;
        //返回最后一个元素的索引；如果没有，则为-1
        int lastRet = -1;
        int expectedModCount = modCount;

        Itr() {}

        //不等于size,即不到最后一个就有下一个
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        /**
         * 返回下个元素
         * 校验修改次数
         * 校验游标cursor的合法性
         * 返回当前元素，游标加1，指向后一个元素
         * @return
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        /**
         * 删除刚刚访问过的元素
         * 重置标记元素值
         */
        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            final int size = ArrayList.this.size;
            int i = cursor;
            if (i >= size) {
                return;
            }
            final Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            while (i != size && modCount == expectedModCount) {
                consumer.accept((E) elementData[i++]);
            }
            // update once at end of iteration to reduce heap write traffic
            cursor = i;
            lastRet = i - 1;
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * 最小化版本的 AbstractList.ListItr
     */
    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * 返回前一个元素
         * 校验游标cursor
         * 重置变量指示值
         * @return
         */
        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        @Override
        public void set(E e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                ArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                ArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * 返回指定的fromIndex （含）和toIndex之间的列表部分的视图。
     * （如果fromIndex和toIndex相等，返回的列表为空。）
     * 返回的列表由此列表支持，因此返回列表中的非结构性更改将反映在此列表中，反之亦然。
     * 返回的列表支持所有可选列表操作。
     *
     *
     * 该方法消除了对显式范围操作（对于数组通常存在的排序）的需要。
     * 任何期望列表的操作都可以通过传递一个子列表视图而不是整个列表来用作范围操作。
     * 例如，以下习惯用法从列表中移除了一系列元素：
     * list.subList(from, to).clear();
     *
     * 可以为indexOf(Object)和lastIndexOf(Object)构造类似的习惯用法，
     * 并且Collections类中的所有算法都可以应用于子列表 。
     *
     * 如果支持列表（即，此列表）以除了通过返回的列表之外的任何方式进行结构修改 ，
     * 则此方法返回的列表的语义将变得未定义。
     * （结构修改是那些改变此列表的大小，或以其他方式扰乱它，使得正在进行的迭代可能产生不正确的结果）。
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubList(this, 0, fromIndex, toIndex);
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        }
    }

    private class SubList extends AbstractList<E> implements RandomAccess {
        private final AbstractList<E> parent;
        private final int parentOffset;
        private final int offset;
        int size;

        SubList(AbstractList<E> parent,
                int offset, int fromIndex, int toIndex) {
            this.parent = parent;
            this.parentOffset = fromIndex;
            this.offset = offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = ArrayList.this.modCount;
        }

        @Override
        public E set(int index, E e) {
            rangeCheck(index);
            checkForComodification();
            E oldValue = ArrayList.this.elementData(offset + index);
            ArrayList.this.elementData[offset + index] = e;
            return oldValue;
        }

        @Override
        public E get(int index) {
            rangeCheck(index);
            checkForComodification();
            return ArrayList.this.elementData(offset + index);
        }

        @Override
        public int size() {
            checkForComodification();
            return this.size;
        }

        @Override
        public void add(int index, E e) {
            rangeCheckForAdd(index);
            checkForComodification();
            parent.add(parentOffset + index, e);
            this.modCount = parent.modCount;
            this.size++;
        }

        @Override
        public E remove(int index) {
            rangeCheck(index);
            checkForComodification();
            E result = parent.remove(parentOffset + index);
            this.modCount = parent.modCount;
            this.size--;
            return result;
        }

        @Override
        protected void removeRange(int fromIndex, int toIndex) {
            checkForComodification();
            parent.removeRange(parentOffset + fromIndex,
                    parentOffset + toIndex);
            this.modCount = parent.modCount;
            this.size -= toIndex - fromIndex;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return addAll(this.size, c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            rangeCheckForAdd(index);
            int cSize = c.size();
            if (cSize==0) {
                return false;
            }

            checkForComodification();
            parent.addAll(parentOffset + index, c);
            this.modCount = parent.modCount;
            this.size += cSize;
            return true;
        }

        @Override
        public Iterator<E> iterator() {
            return listIterator();
        }

        @Override
        public ListIterator<E> listIterator(final int index) {
            checkForComodification();
            rangeCheckForAdd(index);
            final int offset = this.offset;

            return new ListIterator<E>() {
                int cursor = index;
                int lastRet = -1;
                int expectedModCount = ArrayList.this.modCount;

                @Override
                public boolean hasNext() {
                    return cursor != SubList.this.size;
                }

                @Override
                @SuppressWarnings("unchecked")
                public E next() {
                    checkForComodification();
                    int i = cursor;
                    if (i >= SubList.this.size) {
                        throw new NoSuchElementException();
                    }
                    Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    cursor = i + 1;
                    return (E) elementData[offset + (lastRet = i)];
                }

                @Override
                public boolean hasPrevious() {
                    return cursor != 0;
                }

                @Override
                @SuppressWarnings("unchecked")
                public E previous() {
                    checkForComodification();
                    int i = cursor - 1;
                    if (i < 0) {
                        throw new NoSuchElementException();
                    }
                    Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    cursor = i;
                    return (E) elementData[offset + (lastRet = i)];
                }

                @Override
                @SuppressWarnings("unchecked")
                public void forEachRemaining(Consumer<? super E> consumer) {
                    Objects.requireNonNull(consumer);
                    final int size = SubList.this.size;
                    int i = cursor;
                    if (i >= size) {
                        return;
                    }
                    final Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    while (i != size && modCount == expectedModCount) {
                        consumer.accept((E) elementData[offset + (i++)]);
                    }
                    // update once at end of iteration to reduce heap write traffic
                    lastRet = cursor = i;
                    checkForComodification();
                }

                @Override
                public int nextIndex() {
                    return cursor;
                }

                @Override
                public int previousIndex() {
                    return cursor - 1;
                }

                @Override
                public void remove() {
                    if (lastRet < 0) {
                        throw new IllegalStateException();
                    }
                    checkForComodification();

                    try {
                        SubList.this.remove(lastRet);
                        cursor = lastRet;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public void set(E e) {
                    if (lastRet < 0) {
                        throw new IllegalStateException();
                    }
                    checkForComodification();

                    try {
                        ArrayList.this.set(offset + lastRet, e);
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override
                public void add(E e) {
                    checkForComodification();

                    try {
                        int i = cursor;
                        SubList.this.add(i, e);
                        cursor = i + 1;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                final void checkForComodification() {
                    if (expectedModCount != ArrayList.this.modCount) {
                        throw new ConcurrentModificationException();
                    }
                }
            };
        }


        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new SubList(this, offset, fromIndex, toIndex);
        }

        private void rangeCheck(int index) {
            if (index < 0 || index >= this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            }
        }

        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            }
        }

        private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+this.size;
        }

        private void checkForComodification() {
            if (ArrayList.this.modCount != this.modCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public Spliterator<E> spliterator() {
            checkForComodification();
            return new ArrayListSpliterator<E>(ArrayList.this, offset,
                    offset + this.size, this.modCount);
        }
    }

    /**
     * 对Iterable的每个元素执行给定的操作，直到所有元素都被处理或动作引发异常。
     * 除非实现类另有规定，否则按照迭代的顺序执行操作（如果指定了迭代顺序）。 动作抛出的异常被转发给调用者。
     * @param action  要对每个元素执行的操作
     */
    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        @SuppressWarnings("unchecked")
        final E[] elementData = (E[]) this.elementData;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            action.accept(elementData[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 在此列表中的元素上创建late-binding和fail-fast Spliterator 。
     * 该Spliterator报告Spliterator.SIZED ， Spliterator.SUBSIZED和Spliterator.ORDERED 。 覆盖实现应记录其他特征值的报告
     */
    @Override
    public Spliterator<E> spliterator() {
        return new ArrayListSpliterator<>(this, 0, -1, 0);
    }

    /** Index-based split-by-two, lazily initialized Spliterator */
    static final class ArrayListSpliterator<E> implements Spliterator<E> {

        /*
         * If ArrayLists were immutable, or structurally immutable (no
         * adds, removes, etc), we could implement their spliterators
         * with Arrays.spliterator. Instead we detect as much
         * interference during traversal as practical without
         * sacrificing much performance. We rely primarily on
         * modCounts. These are not guaranteed to detect concurrency
         * violations, and are sometimes overly conservative about
         * within-thread interference, but detect enough problems to
         * be worthwhile in practice. To carry this out, we (1) lazily
         * initialize fence and expectedModCount until the latest
         * point that we need to commit to the state we are checking
         * against; thus improving precision.  (This doesn't apply to
         * SubLists, that create spliterators with current non-lazy
         * values).  (2) We perform only a single
         * ConcurrentModificationException check at the end of forEach
         * (the most performance-sensitive method). When using forEach
         * (as opposed to iterators), we can normally only detect
         * interference after actions, not before. Further
         * CME-triggering checks apply to all other possible
         * violations of assumptions for example null or too-small
         * elementData array given its size(), that could only have
         * occurred due to interference.  This allows the inner loop
         * of forEach to run without any further checks, and
         * simplifies lambda-resolution. While this does entail a
         * number of checks, note that in the common case of
         * list.stream().forEach(a), no checks or other computation
         * occur anywhere other than inside forEach itself.  The other
         * less-often-used methods cannot take advantage of most of
         * these streamlinings.
         */

        private final ArrayList<E> list;
        private int index; // current index, modified on advance/split
        private int fence; // -1 until used; then one past last index
        private int expectedModCount; // initialized when fence set

        /** Create new spliterator covering the given  range */
        ArrayListSpliterator(ArrayList<E> list, int origin, int fence,
                             int expectedModCount) {
            this.list = list; // OK if null unless traversed
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence() { // initialize fence to size on first use
            int hi; // (a specialized variant appears in method forEach)
            ArrayList<E> lst;
            if ((hi = fence) < 0) {
                if ((lst = list) == null) {
                    hi = fence = 0;
                } else {
                    expectedModCount = lst.modCount;
                    hi = fence = lst.size;
                }
            }
            return hi;
        }

        @Override
        public ArrayListSpliterator<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : // divide range in half unless too small
                    new ArrayListSpliterator<E>(list, lo, index = mid,
                            expectedModCount);
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action == null) {
                throw new NullPointerException();
            }
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                @SuppressWarnings("unchecked") E e = (E)list.elementData[i];
                action.accept(e);
                if (list.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            int i, hi, mc; // hoist accesses and checks from loop
            ArrayList<E> lst; Object[] a;
            if (action == null) {
                throw new NullPointerException();
            }
            if ((lst = list) != null && (a = lst.elementData) != null) {
                if ((hi = fence) < 0) {
                    mc = lst.modCount;
                    hi = lst.size;
                }
                else {
                    mc = expectedModCount;
                }
                if ((i = index) >= 0 && (index = hi) <= a.length) {
                    for (; i < hi; ++i) {
                        @SuppressWarnings("unchecked") E e = (E) a[i];
                        action.accept(e);
                    }
                    if (lst.modCount == mc) {
                        return;
                    }
                }
            }
            throw new ConcurrentModificationException();
        }

        @Override
        public long estimateSize() {
            return (long) (getFence() - index);
        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }

    /**
     * 删除满足给定谓词的此集合的所有元素。 在迭代或谓词中抛出的错误或运行时异常被转发给调用者。
     * @param filter
     * @return
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        // figure out which elements are to be removed
        // any exception thrown from the filter predicate at this stage
        // will leave the collection unmodified
        int removeCount = 0;
        final BitSet removeSet = new BitSet(size);
        final int expectedModCount = modCount;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            @SuppressWarnings("unchecked")
            final E element = (E) elementData[i];
            if (filter.test(element)) {
                removeSet.set(i);
                removeCount++;
            }
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }

        // shift surviving elements left over the spaces left by removed elements
        final boolean anyToRemove = removeCount > 0;
        if (anyToRemove) {
            final int newSize = size - removeCount;
            for (int i=0, j=0; (i < size) && (j < newSize); i++, j++) {
                i = removeSet.nextClearBit(i);
                elementData[j] = elementData[i];
            }
            for (int k=newSize; k < size; k++) {
                elementData[k] = null;  // Let gc do its work
            }
            this.size = newSize;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            modCount++;
        }

        return anyToRemove;
    }

    /**
     * 将该列表的每个元素替换为将该运算符应用于该元素的结果。
     * 操作者抛出的错误或运行时异常被转发给调用者。
     * @param operator
     */
    @Override
    @SuppressWarnings("unchecked")
    public void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            elementData[i] = operator.apply((E) elementData[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

    /**
     * 使用提供的 Comparator对此列表进行排序，以比较元素。
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        final int expectedModCount = modCount;
        Arrays.sort((E[]) elementData, 0, size, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }
}
