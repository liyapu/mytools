
package com.lyp.learn.base.demo.pk13;

import sun.misc.SharedSecrets;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 一个线程安全的变体ArrayList ，其中所有可变操作（ add ， set ，等等）通过对底层数组的最新副本实现。
 * 这通常是太昂贵的，但是当遍历操作大大超过突变时，可能比替代方法更有效，并且在不能或不想同步遍历时需要有用，
 * 但需要排除并发线程之间的干扰。
 * “快照”样式迭代器方法在创建迭代器的时候使用对数组状态的引用。
 *该数组在迭代器的生命周期内永远不会改变，所以干扰是不可能的，
 * 迭代器保证不会丢弃ConcurrentModificationException 。
 * 自迭代器创建以来，迭代器将不会反映列表的添加，删除或更改。
 * 元变化的迭代器操作本身（ remove ， set和add ）不被支持。 这些方法抛出UnsupportedOperationException 。
 *
 * 允许所有元素，包括null 。
 *
 * 内存一致性效果：与其他并发集合一样，在将对象放入CopyOnWriteArrayList 之后的线程中的操作，
 * 在另一个线程中从CopyOnWriteArrayList访问或删除该元素之后。
 */
public class CopyOnWriteArrayList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    private static final long serialVersionUID = 8673264195747942595L;

    /** 此锁保护所有的突变体 */
    final transient ReentrantLock lock = new ReentrantLock();

    /**保存数据的数组 */
    private transient volatile Object[] array;

    /**
     * 获取数组
     */
    final Object[] getArray() {
        return array;
    }

    /**
     * 设置数组
     */
    final void setArray(Object[] a) {
        array = a;
    }

    /**
     * 创建一个空list
     */
    public CopyOnWriteArrayList() {
        setArray(new Object[0]);
    }

    /**
     * 按照集合的迭代器返回的顺序创建一个包含指定集合元素的列表。
     * @param c 初始持有元素的集合
     * @throws NullPointerException 如果指定的集合为空
     */
    public CopyOnWriteArrayList(Collection<? extends E> c) {
        Object[] elements;
        if (c.getClass() == CopyOnWriteArrayList.class)
            elements = ((CopyOnWriteArrayList<?>)c).getArray();
        else {
            elements = c.toArray();
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elements.getClass() != Object[].class)
                elements = Arrays.copyOf(elements, elements.length, Object[].class);
        }
        setArray(elements);
    }

    /**
     * 创建一个包含给定数组的副本的列表。
     * @param toCopyIn  数组（该数组的副本用作内部数组）
     * @throws NullPointerException 如果指定的数组为空
     */
    public CopyOnWriteArrayList(E[] toCopyIn) {
        setArray(Arrays.copyOf(toCopyIn, toCopyIn.length, Object[].class));
    }

    /**
     * 返回此列表中的元素数。
     */
    public int size() {
        return getArray().length;
    }

    /**
     *如果此列表不包含元素，则返回 true 。
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 比较两个对象是否相等
     */
    private static boolean eq(Object o1, Object o2) {
        return (o1 == null) ? o2 == null : o1.equals(o2);
    }

    /**
     * indexof的静态版本，
     * 允许重复调用，而不需要每次重新获取数组。
     * @param o 查找的元素
     * @param elements 数组源
     * @param index 开始搜索的位置
     * @param fence 结束搜索的位置
     * @return 返回元素的位置，或者没有找到时返回-1
     */
    private static int indexOf(Object o, Object[] elements,
                               int index, int fence) {
        if (o == null) {
            for (int i = index; i < fence; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = index; i < fence; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    /**
     * lastIndexOf 的静态方法
     * @param o element to search for
     * @param elements the array
     * @param index first index to search
     * @return index of element, or -1 if absent
     */
    private static int lastIndexOf(Object o, Object[] elements, int index) {
        if (o == null) {
            for (int i = index; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = index; i >= 0; i--)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    /**
     * 如果此列表包含指定的元素，则返回true 。
     * 更正式地，返回true当且仅当该列表至少包含一个元素e ，使得(o==null ? e==null : o.equals(e)) 。
     *
     * @param o 要在此列表中存在的元素要进行测试
     * @return {@code true} 如果此列表包含指定的元素
     */
    public boolean contains(Object o) {
        Object[] elements = getArray();
        return indexOf(o, elements, 0, elements.length) >= 0;
    }

    /**
     * 返回此列表中指定元素的第一次出现的索引，如果此列表不包含元素，则返回-1。
     * 更正式地，返回最低指数i ，使(o==null ? get(i)==null : o.equals(get(i))) ，或-1如果没有这样的索引。
     */
    public int indexOf(Object o) {
        Object[] elements = getArray();
        return indexOf(o, elements, 0, elements.length);
    }

    /**
     * 返回此列表中指定元素的第一次出现的索引，从index向前index ，如果未找到元素，则返回-1。
     * 更正式地，返回最低指数i ，使(i >= index && (e==null ? get(i)==null : e.equals(get(i)))) ，或-1如果没有这样的索引。
     *
     * @param e  要搜索的元素
     * @param index 开始搜索的索引
     * @return 列表中该列表中元素第一次出现的索引位于列表中的位置index或更高版本; -1如果没有找到该元素。
     * @throws IndexOutOfBoundsException 如果指定的索引为负数
     */
    public int indexOf(E e, int index) {
        Object[] elements = getArray();
        return indexOf(e, elements, index, elements.length);
    }

    /**
     * 返回此列表中指定元素的最后一次出现的索引，如果此列表不包含元素，则返回-1。
     * 更正式地，返回最高指数i ，使(o==null ? get(i)==null : o.equals(get(i))) ，或-1如果没有这样的索引。
     */
    public int lastIndexOf(Object o) {
        Object[] elements = getArray();
        return lastIndexOf(o, elements, elements.length - 1);
    }

    /**
     * 返回此列表中指定元素的最后一次出现的索引，从index ，如果未找到该元素，则返回-1。
     * 更正式地，返回最高指数i ，使(i <= index && (e==null ? get(i)==null : e.equals(get(i)))) ，或-1如果没有这样的索引。
     *
     * @param e 要搜索的元素
     * @param index 开始向后搜索的索引
     * @return 该列表中位置小于或等于index的元素的最后一次出现的索引; -1如果没有找到元素。
     * @throws IndexOutOfBoundsException 如果指定的索引大于或等于此列表的当前大小
     */
    public int lastIndexOf(E e, int index) {
        Object[] elements = getArray();
        return lastIndexOf(e, elements, index);
    }

    /**
     * 返回此列表的浅层副本。 （元素本身不被复制。）
     */
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            CopyOnWriteArrayList<E> clone =
                (CopyOnWriteArrayList<E>) super.clone();
            clone.resetLock();
            return clone;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    /**
     * 以正确的顺序（从第一个到最后一个元素）返回一个包含此列表中所有元素的数组。
     * 返回的数组将是“安全的”，因为该列表不保留对它的引用。
     * （换句话说，这个方法必须分配一个新的数组）。
     * 因此，调用者可以自由地修改返回的数组。
     *
     * 此方法充当基于阵列和基于集合的API之间的桥梁。
     */
    public Object[] toArray() {
        Object[] elements = getArray();
        return Arrays.copyOf(elements, elements.length);
    }

    /**
     *
     * 以正确的顺序返回一个包含此列表中所有元素的数组（从第一个到最后一个元素）;
     * 返回的数组的运行时类型是指定数组的运行时类型。
     * 如果列表适合指定的数组，则返回其中。 否则，将为指定数组的运行时类型和此列表的大小分配一个新数组。
     *
     * 如果此列表适用于指定的数组，其余空间（即，该数组具有比此列表更多的元素），则紧接在列表结束后的数组中的元素将设置为null 。
     * （这仅在调用者知道此列表不包含任何空元素时才能确定此列表的长度）。
     *
     * 像toArray()方法一样，此方法充当基于数组和基于集合的API之间的桥梁。
     * 此外，该方法允许精确地控制输出阵列的运行时类型，并且在某些情况下可以用于节省分配成本。
     *
     * 假设x是一个已知只包含字符串的列表。 下面的代码可以被用来将该列表转储到一个新分配的阵列String ：
     *    String[] y = x.toArray(new String[0]);
     * 请注意， toArray(new Object[0])的功能与toArray() 。
     *
     * @param a 要存储列表的元素的数组，如果它足够大; 否则，为此目的分配相同运行时类型的新数组。
     * @return 一个包含此列表中所有元素的数组
     * @throws ArrayStoreException 如果指定数组的运行时类型不是此列表中每个元素的运行时类型的超类型
     * @throws NullPointerException 如果指定的数组为空
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T a[]) {
        Object[] elements = getArray();
        int len = elements.length;
        if (a.length < len)
            return (T[]) Arrays.copyOf(elements, len, a.getClass());
        else {
            System.arraycopy(elements, 0, a, 0, len);
            if (a.length > len)
                a[len] = null;
            return a;
        }
    }

    // Positional Access Operations

    @SuppressWarnings("unchecked")
    private E get(Object[] a, int index) {
        return (E) a[index];
    }

    /**
     * 返回此列表中指定位置的元素。
     */
    public E get(int index) {
        return get(getArray(), index);
    }

    /**
     * 用指定的元素替换此列表中指定位置的元素。
     */
    public E set(int index, E element) {
        //获取锁
        final ReentrantLock lock = this.lock;
        //使用锁
        lock.lock();
        try {
            Object[] elements = getArray();
            //获取旧元素
            E oldValue = get(elements, index);
            //新值不同于旧值
            if (oldValue != element) {
                //获取数组长度
                int len = elements.length;
                //赋值旧数组
                Object[] newElements = Arrays.copyOf(elements, len);
                //新数组的index位置上，设置新值
                newElements[index] = element;
                //新数组覆盖掉旧数组
                setArray(newElements);
            } else {
                // Not quite a no-op; ensures volatile write semantics
                setArray(elements);
            }
            return oldValue;
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 将指定的元素追加到此列表的末尾。
     * @param e 要附加到此列表的元素
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        //使用锁
        lock.lock();
        try {
            //获取旧数组
            Object[] elements = getArray();
            //获取旧数组长度
            int len = elements.length;
            //新建数组，长度加1
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            //新数组新增位置(最后一个位置),设置为新数据
            newElements[len] = e;
            //新数组覆盖掉旧数组
            setArray(newElements);
            return true;
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 在此列表中的指定位置插入指定的元素。
     * 将当前位于该位置的元素（如果有）和任何后续元素（向其索引添加一个）移动。
     */
    public void add(int index, E element) {
        final ReentrantLock lock = this.lock;
        //使用锁
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            //校验index参数
            if (index > len || index < 0)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+len);
            Object[] newElements;
            //需要移动的元素个数
            int numMoved = len - index;
            if (numMoved == 0)
                //在末尾位置添加元素
                //新建数组，根据旧数组建，长度加1
                newElements = Arrays.copyOf(elements, len + 1);
            else {
                //新建数组，长度加
                newElements = new Object[len + 1];
                //把index之前的元素迁移到新数组中
                System.arraycopy(elements, 0, newElements, 0, index);
                //把index(包含)之后的元素迁移到新数组的 index+1 位置
                System.arraycopy(elements, index, newElements, index + 1,
                                 numMoved);
            }
            //插入位置index 设置新元素
            newElements[index] = element;
            //新数组覆盖掉旧数组
            setArray(newElements);
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 删除该列表中指定位置的元素。
     * 将任何后续元素移动到左侧（从其索引中减去一个元素）。
     * 返回从列表中删除的元素。
     */
    public E remove(int index) {
        final ReentrantLock lock = this.lock;
        //使用锁
        lock.lock();
        try {
            //获取旧数组
            Object[] elements = getArray();
            //获取旧数组长度
            int len = elements.length;
            //获取index上的旧值
            E oldValue = get(elements, index);
            //计算需要移动的元素个数
            int numMoved = len - index - 1;
            if (numMoved == 0)
                //移除最后一个位置上的元素时
                setArray(Arrays.copyOf(elements, len - 1));
            else {
                //新建长度减1的新数组
                Object[] newElements = new Object[len - 1];
                //把index之前的元素迁移到新数组中
                System.arraycopy(elements, 0, newElements, 0, index);
                //把index+1(包含)之后的元素迁移到新数组的 index 位置
                System.arraycopy(elements, index + 1, newElements, index, numMoved);
                //新数组覆盖掉旧数组
                setArray(newElements);
            }
            //返回index位置上的元素值
            return oldValue;
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 从列表中删除指定元素的第一个出现（如果存在）。 如果此列表不包含该元素，则它将保持不变。
     * 更正式地，删除具有最低索引i的元素，使得(o==null ? get(i)==null : o.equals(get(i))) （如果这样的元素存在）。
     * 如果此列表包含指定的元素（或等效地，如果此列表作为调用的结果而更改），则返回true 。
     * @param o 要从此列表中删除的元素（如果存在）
     * @return {@code true} 如果此列表包含指定的元素
     */
    public boolean remove(Object o) {
        Object[] snapshot = getArray();
        int index = indexOf(o, snapshot, 0, snapshot.length);
        return (index < 0) ? false : remove(o, snapshot, index);
    }

    /**
     * A version of remove(Object) using the strong hint that given
     * recent snapshot contains o at the given index.
     */
    private boolean remove(Object o, Object[] snapshot, int index) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] current = getArray();
            int len = current.length;
            if (snapshot != current) findIndex: {
                int prefix = Math.min(index, len);
                for (int i = 0; i < prefix; i++) {
                    if (current[i] != snapshot[i] && eq(o, current[i])) {
                        index = i;
                        break findIndex;
                    }
                }
                if (index >= len)
                    return false;
                if (current[index] == o)
                    break findIndex;
                index = indexOf(o, current, index, len);
                if (index < 0)
                    return false;
            }
            Object[] newElements = new Object[len - 1];
            System.arraycopy(current, 0, newElements, 0, index);
            System.arraycopy(current, index + 1,
                             newElements, index,
                             len - index - 1);
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 从列表中删除[fromIndex,toIndex) 位置的 (toInfex-fromIndex) 个元素
     * 后边的元素迁移
     * 如果 toIndex 等于 fromIndex, 则此操作没有影响
     *
     * @param fromIndex 删除元素的开始位置
     * @param toIndex 删除元素的结束位置
     * @throws IndexOutOfBoundsException if fromIndex or toIndex out of range
     *         ({@code fromIndex < 0 || toIndex > size() || toIndex < fromIndex})
     */
    void removeRange(int fromIndex, int toIndex) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            //校验参数
            if (fromIndex < 0 || toIndex > len || toIndex < fromIndex)
                throw new IndexOutOfBoundsException();
            //计算新数组的长度
            int newlen = len - (toIndex - fromIndex);
            //需要移动的元素个数
            int numMoved = len - toIndex;
            if (numMoved == 0)
                //删除尾部元素
                setArray(Arrays.copyOf(elements, newlen));
            else {
                //使用新长度新建数组
                Object[] newElements = new Object[newlen];
                //把fromIndex之前的元素迁移到新数组中
                System.arraycopy(elements, 0, newElements, 0, fromIndex);
                //把toIndex(包含)之后的元素迁移到新数组的fromIndex 位置
                System.arraycopy(elements, toIndex, newElements, fromIndex, numMoved);
                //新数组覆盖掉旧数组
                setArray(newElements);
            }
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 如果元素不存在，添加颜色
     *
     * @param e 要添加到此列表的元素，如果不存在
     * @return {@code true} if the element was added
     */
    public boolean addIfAbsent(E e) {
        Object[] snapshot = getArray();
        return indexOf(e, snapshot, 0, snapshot.length) >= 0 ? false : addIfAbsent(e, snapshot);
    }

    /**
     * A version of addIfAbsent using the strong hint that given
     * recent snapshot does not contain e.
     */
    private boolean addIfAbsent(E e, Object[] snapshot) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] current = getArray();
            int len = current.length;
            if (snapshot != current) {
                // Optimize for lost race to another addXXX operation
                int common = Math.min(snapshot.length, len);
                for (int i = 0; i < common; i++)
                    if (current[i] != snapshot[i] && eq(e, current[i]))
                        return false;
                if (indexOf(e, current, common, len) >= 0)
                        return false;
            }
            Object[] newElements = Arrays.copyOf(current, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 如果此列表包含指定 true所有元素，则返回true。
     */
    public boolean containsAll(Collection<?> c) {
        Object[] elements = getArray();
        int len = elements.length;
        for (Object e : c) {
            if (indexOf(e, elements, 0, len) < 0)
                return false;
        }
        return true;
    }

    /**
     * 从此列表中删除指定集合中包含的所有元素。
     * 这是一个特别昂贵的操作，因为需要一个内部的临时数组。
     *
     * @param c 包含要从此列表中删除的元素的集合
     * @return true如果此列表因呼叫而更改
     * @throws ClassCastException if the class of an element of this list
     *         is incompatible with the specified collection
     *         (<a href="../Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this list contains a null element and the
     *         specified collection does not permit null elements
     *         (<a href="../Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    public boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            if (len != 0) {
                // temp array holds those elements we know we want to keep
                int newlen = 0;
                Object[] temp = new Object[len];
                for (int i = 0; i < len; ++i) {
                    Object element = elements[i];
                    if (!c.contains(element))
                        temp[newlen++] = element;
                }
                if (newlen != len) {
                    setArray(Arrays.copyOf(temp, newlen));
                    return true;
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 仅保留此列表中包含在指定集合中的元素。
     * 换句话说，从此列表中删除其中不包含在指定集合中的所有元素。
     *
     * @param c  包含要保留在此列表中的元素的集合
     * @return true 如果此列表因呼叫而更改
     * @throws ClassCastException if the class of an element of this list
     *         is incompatible with the specified collection
     *         (<a href="../Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this list contains a null element and the
     *         specified collection does not permit null elements
     *         (<a href="../Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    public boolean retainAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            if (len != 0) {
                // temp array holds those elements we know we want to keep
                int newlen = 0;
                Object[] temp = new Object[len];
                for (int i = 0; i < len; ++i) {
                    Object element = elements[i];
                    if (c.contains(element))
                        temp[newlen++] = element;
                }
                if (newlen != len) {
                    setArray(Arrays.copyOf(temp, newlen));
                    return true;
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 将指定集合中尚未包含在此列表中的所有元素按指定集合的迭代器返回的顺序附加到此列表的末尾。
     *
     * @param c 包含要添加到此列表的元素的集合
     * @return 添加的元素数量
     * @throws NullPointerException 如果指定的集合为空
     */
    public int addAllAbsent(Collection<? extends E> c) {
        Object[] cs = c.toArray();
        if (cs.length == 0)
            return 0;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            int added = 0;
            // uniquify and compact elements in cs
            for (int i = 0; i < cs.length; ++i) {
                Object e = cs[i];
                if (indexOf(e, elements, 0, len) < 0 &&
                    indexOf(e, cs, 0, added) < 0)
                    cs[added++] = e;
            }
            if (added > 0) {
                Object[] newElements = Arrays.copyOf(elements, len + added);
                System.arraycopy(cs, 0, newElements, len, added);
                setArray(newElements);
            }
            return added;
        } finally {
            lock.unlock();
        }
    }

    /**
     *从列表中删除所有元素。 此呼叫返回后，列表将为空。
     */
    public void clear() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            setArray(new Object[0]);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 按照指定集合的迭代器返回的顺序将指定集合中的所有元素追加到此列表的末尾。
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     * @see #add(Object)
     */
    public boolean addAll(Collection<? extends E> c) {
        Object[] cs = (c.getClass() == CopyOnWriteArrayList.class) ?
            ((CopyOnWriteArrayList<?>)c).getArray() : c.toArray();
        if (cs.length == 0)
            return false;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            if (len == 0 && cs.getClass() == Object[].class)
                setArray(cs);
            else {
                Object[] newElements = Arrays.copyOf(elements, len + cs.length);
                System.arraycopy(cs, 0, newElements, len, cs.length);
                setArray(newElements);
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 将指定集合中的所有元素插入到此列表中，从指定的位置开始。
     * 将当前位于该位置（如果有的话）的元素和随后的任何元素移动到右边（增加其索引）。
     * 新元素将按照指定集合的迭代器返回的顺序显示在此列表中。
     *
     * @param index 从中指定集合插入第一个元素的索引
     * @param c 包含要添加到此列表的元素的集合
     * @return true 如果此列表因呼叫而更改
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     * @see #add(int,Object)
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        Object[] cs = c.toArray();
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            if (index > len || index < 0)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+len);
            if (cs.length == 0)
                return false;
            int numMoved = len - index;
            Object[] newElements;
            if (numMoved == 0)
                newElements = Arrays.copyOf(elements, len + cs.length);
            else {
                newElements = new Object[len + cs.length];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index,
                                 newElements, index + cs.length,
                                 numMoved);
            }
            System.arraycopy(cs, 0, newElements, index, cs.length);
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 对Iterable的每个元素执行给定的操作，直到所有元素都被处理或动作引发异常。
     * 除非实现类另有规定，否则按照迭代的顺序执行操作（如果指定了迭代顺序）。
     * 动作抛出的异常被转发给呼叫者。
     */
    public void forEach(Consumer<? super E> action) {
        if (action == null) throw new NullPointerException();
        Object[] elements = getArray();
        int len = elements.length;
        for (int i = 0; i < len; ++i) {
            @SuppressWarnings("unchecked") E e = (E) elements[i];
            action.accept(e);
        }
    }

    /**
     * 删除满足给定谓词的此集合的所有元素。
     * 在迭代或谓词中抛出的错误或运行时异常被转发给调用者。
     */
    public boolean removeIf(Predicate<? super E> filter) {
        if (filter == null) throw new NullPointerException();
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            if (len != 0) {
                int newlen = 0;
                Object[] temp = new Object[len];
                for (int i = 0; i < len; ++i) {
                    @SuppressWarnings("unchecked") E e = (E) elements[i];
                    if (!filter.test(e))
                        temp[newlen++] = e;
                }
                if (newlen != len) {
                    setArray(Arrays.copyOf(temp, newlen));
                    return true;
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 将该列表的每个元素替换为将该运算符应用于该元素的结果。
     * 运营商抛出的错误或运行时异常被转发给呼叫者。
     */
    public void replaceAll(UnaryOperator<E> operator) {
        if (operator == null) throw new NullPointerException();
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len);
            for (int i = 0; i < len; ++i) {
                @SuppressWarnings("unchecked") E e = (E) elements[i];
                newElements[i] = operator.apply(e);
            }
            setArray(newElements);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用提供的 Comparator对此列表进行排序以比较元素。
     */
    public void sort(Comparator<? super E> c) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            Object[] newElements = Arrays.copyOf(elements, elements.length);
            @SuppressWarnings("unchecked") E[] es = (E[])newElements;
            Arrays.sort(es, c);
            setArray(newElements);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 序列化
     *
     * @param s the stream
     * @throws java.io.IOException if an I/O error occurs
     * @serialData The length of the array backing the list is emitted
     *               (int), followed by all of its elements (each an Object)
     *               in the proper order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {

        s.defaultWriteObject();

        Object[] elements = getArray();
        // Write out array length
        s.writeInt(elements.length);

        // Write out all elements in the proper order.
        for (Object element : elements)
            s.writeObject(element);
    }

    /**
     * 反序列化
     * @param s the stream
     * @throws ClassNotFoundException if the class of a serialized object
     *         could not be found
     * @throws java.io.IOException if an I/O error occurs
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {

        s.defaultReadObject();

        // bind to new lock
        resetLock();

        // Read in array length and allocate array
        int len = s.readInt();
        SharedSecrets.getJavaOISAccess().checkArray(s, Object[].class, len);
        Object[] elements = new Object[len];

        // Read in all elements in the proper order.
        for (int i = 0; i < len; i++)
            elements[i] = s.readObject();
        setArray(elements);
    }

    /**
     * 返回此列表的字符串表示形式。
     * 字符串表示由列表元素的字符串表示形式组成，按照它们的迭代器返回的顺序，括在方括号（ "[]" ）中。
     * 相邻元素由字符", " （逗号和空格）分隔。
     * 元素被转换为字符串由String.valueOf(Object) 。
     */
    public String toString() {
        return Arrays.toString(getArray());
    }

    /**
     * 将指定的对象与此列表进行比较以获得相等性。
     * 如果指定的对象与此对象相同，则返回true ，
     * 或者如果它也是List ，并且iterator在指定列表上返回的元素序列与迭代器在此列表上返回的序列相同。
     * 如果两个序列具有相同的长度，并且序列中相同位置的相应元素相等 ，则认为这两个序列是相同的 。
     * 如果(e1==null ? e2==null : e1.equals(e2))两个元素e1和e2被认为是相等的 。
     *
     * @param o 要与此列表相等的对象进行比较
     * @return true如果指定的对象等于此列表
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        List<?> list = (List<?>)(o);
        Iterator<?> it = list.iterator();
        Object[] elements = getArray();
        int len = elements.length;
        for (int i = 0; i < len; ++i)
            if (!it.hasNext() || !eq(elements[i], it.next()))
                return false;
        if (it.hasNext())
            return false;
        return true;
    }

    /**
     * 返回此列表的哈希码值。
     * 此实现使用List.hashCode()中的定义 。
     */
    public int hashCode() {
        int hashCode = 1;
        Object[] elements = getArray();
        int len = elements.length;
        for (int i = 0; i < len; ++i) {
            Object obj = elements[i];
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * 以正确的顺序返回该列表中的元素的迭代器。
     * 返回的迭代器在构建迭代器时提供列表状态的快照。
     * 遍历迭代器时不需要同步。 该迭代器不支持remove方法。
     */
    public Iterator<E> iterator() {
        return new COWIterator<E>(getArray(), 0);
    }

    /**
     * 返回列表中的列表迭代器（按适当的顺序）。
     * 返回的迭代器在构建迭代器时提供列表状态的快照。
     * 遍历迭代器时不需要同步。 该迭代器不支持remove ， set或add方法。
     */
    public ListIterator<E> listIterator() {
        return new COWIterator<E>(getArray(), 0);
    }

    /**
     *从列表中的指定位置开始，返回列表中的元素（按正确顺序）的列表迭代器。
     * 指定的索引表示初始调用next将返回的第一个元素。 初始调用previous将返回指定索引减1的元素。
     * 返回的迭代器在构建迭代器时提供列表状态的快照。 遍历迭代器时不需要同步。 该迭代器不支持remove ， set或add方法。
     */
    public ListIterator<E> listIterator(int index) {
        Object[] elements = getArray();
        int len = elements.length;
        if (index < 0 || index > len)
            throw new IndexOutOfBoundsException("Index: "+index);

        return new COWIterator<E>(elements, index);
    }

    /**
     * 在此列表中的元素上返回一个Spliterator 。
     * 该Spliterator报告Spliterator.IMMUTABLE ， Spliterator.ORDERED ， Spliterator.SIZED和Spliterator.SUBSIZED 。
     *
     * 拼接器在构建拼接器时提供列表状态的快照。 在拼接器上操作时不需要同步。
     */
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator
            (getArray(), Spliterator.IMMUTABLE | Spliterator.ORDERED);
    }

    static final class COWIterator<E> implements ListIterator<E> {
        /** Snapshot of the array */
        private final Object[] snapshot;
        /** Index of element to be returned by subsequent call to next.  */
        private int cursor;

        private COWIterator(Object[] elements, int initialCursor) {
            cursor = initialCursor;
            snapshot = elements;
        }

        public boolean hasNext() {
            return cursor < snapshot.length;
        }

        public boolean hasPrevious() {
            return cursor > 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (! hasNext())
                throw new NoSuchElementException();
            return (E) snapshot[cursor++];
        }

        @SuppressWarnings("unchecked")
        public E previous() {
            if (! hasPrevious())
                throw new NoSuchElementException();
            return (E) snapshot[--cursor];
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        /**
         * Not supported. Always throws UnsupportedOperationException.
         * @throws UnsupportedOperationException always; {@code remove}
         *         is not supported by this iterator.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Not supported. Always throws UnsupportedOperationException.
         * @throws UnsupportedOperationException always; {@code set}
         *         is not supported by this iterator.
         */
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        /**
         * Not supported. Always throws UnsupportedOperationException.
         * @throws UnsupportedOperationException always; {@code add}
         *         is not supported by this iterator.
         */
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            Object[] elements = snapshot;
            final int size = elements.length;
            for (int i = cursor; i < size; i++) {
                @SuppressWarnings("unchecked") E e = (E) elements[i];
                action.accept(e);
            }
            cursor = size;
        }
    }

    /**
     * 返回此列表之间的部分视图fromIndex ，包容和toIndex ，排斥。
     * 返回的列表由此列表支持，因此返回的列表中的更改将反映在此列表中。
     *
     * 如果支持列表（即，此列表）以除了通过返回的列表之外的任何方式进行修改，则此方法返回的列表的语义将变得未定义。
     *
     * @param fromIndex fromIndex低端点（含）
     * @param toIndex toIndex高端点（独占）
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public List<E> subList(int fromIndex, int toIndex) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            if (fromIndex < 0 || toIndex > len || fromIndex > toIndex)
                throw new IndexOutOfBoundsException();
            return new COWSubList<E>(this, fromIndex, toIndex);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sublist for CopyOnWriteArrayList.
     * This class extends AbstractList merely for convenience, to
     * avoid having to define addAll, etc. This doesn't hurt, but
     * is wasteful.  This class does not need or use modCount
     * mechanics in AbstractList, but does need to check for
     * concurrent modification using similar mechanics.  On each
     * operation, the array that we expect the backing list to use
     * is checked and updated.  Since we do this for all of the
     * base operations invoked by those defined in AbstractList,
     * all is well.  While inefficient, this is not worth
     * improving.  The kinds of list operations inherited from
     * AbstractList are already so slow on COW sublists that
     * adding a bit more space/time doesn't seem even noticeable.
     */
    private static class COWSubList<E>
        extends AbstractList<E>
        implements RandomAccess
    {
        private final CopyOnWriteArrayList<E> l;
        private final int offset;
        private int size;
        private Object[] expectedArray;

        // only call this holding l's lock
        COWSubList(CopyOnWriteArrayList<E> list,
                   int fromIndex, int toIndex) {
            l = list;
            expectedArray = l.getArray();
            offset = fromIndex;
            size = toIndex - fromIndex;
        }

        // only call this holding l's lock
        private void checkForComodification() {
            if (l.getArray() != expectedArray)
                throw new ConcurrentModificationException();
        }

        // only call this holding l's lock
        private void rangeCheck(int index) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ",Size: "+size);
        }

        public E set(int index, E element) {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                rangeCheck(index);
                checkForComodification();
                E x = l.set(index+offset, element);
                expectedArray = l.getArray();
                return x;
            } finally {
                lock.unlock();
            }
        }

        public E get(int index) {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                rangeCheck(index);
                checkForComodification();
                return l.get(index+offset);
            } finally {
                lock.unlock();
            }
        }

        public int size() {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                checkForComodification();
                return size;
            } finally {
                lock.unlock();
            }
        }

        public void add(int index, E element) {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                checkForComodification();
                if (index < 0 || index > size)
                    throw new IndexOutOfBoundsException();
                l.add(index+offset, element);
                expectedArray = l.getArray();
                size++;
            } finally {
                lock.unlock();
            }
        }

        public void clear() {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                checkForComodification();
                l.removeRange(offset, offset+size);
                expectedArray = l.getArray();
                size = 0;
            } finally {
                lock.unlock();
            }
        }

        public E remove(int index) {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                rangeCheck(index);
                checkForComodification();
                E result = l.remove(index+offset);
                expectedArray = l.getArray();
                size--;
                return result;
            } finally {
                lock.unlock();
            }
        }

        public boolean remove(Object o) {
            int index = indexOf(o);
            if (index == -1)
                return false;
            remove(index);
            return true;
        }

        public Iterator<E> iterator() {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                checkForComodification();
                return new COWSubListIterator<E>(l, 0, offset, size);
            } finally {
                lock.unlock();
            }
        }

        public ListIterator<E> listIterator(int index) {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                checkForComodification();
                if (index < 0 || index > size)
                    throw new IndexOutOfBoundsException("Index: "+index+
                                                        ", Size: "+size);
                return new COWSubListIterator<E>(l, index, offset, size);
            } finally {
                lock.unlock();
            }
        }

        public List<E> subList(int fromIndex, int toIndex) {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                checkForComodification();
                if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
                    throw new IndexOutOfBoundsException();
                return new COWSubList<E>(l, fromIndex + offset,
                                         toIndex + offset);
            } finally {
                lock.unlock();
            }
        }

        public void forEach(Consumer<? super E> action) {
            if (action == null) throw new NullPointerException();
            int lo = offset;
            int hi = offset + size;
            Object[] a = expectedArray;
            if (l.getArray() != a)
                throw new ConcurrentModificationException();
            if (lo < 0 || hi > a.length)
                throw new IndexOutOfBoundsException();
            for (int i = lo; i < hi; ++i) {
                @SuppressWarnings("unchecked") E e = (E) a[i];
                action.accept(e);
            }
        }

        public void replaceAll(UnaryOperator<E> operator) {
            if (operator == null) throw new NullPointerException();
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                int lo = offset;
                int hi = offset + size;
                Object[] elements = expectedArray;
                if (l.getArray() != elements)
                    throw new ConcurrentModificationException();
                int len = elements.length;
                if (lo < 0 || hi > len)
                    throw new IndexOutOfBoundsException();
                Object[] newElements = Arrays.copyOf(elements, len);
                for (int i = lo; i < hi; ++i) {
                    @SuppressWarnings("unchecked") E e = (E) elements[i];
                    newElements[i] = operator.apply(e);
                }
                l.setArray(expectedArray = newElements);
            } finally {
                lock.unlock();
            }
        }

        public void sort(Comparator<? super E> c) {
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                int lo = offset;
                int hi = offset + size;
                Object[] elements = expectedArray;
                if (l.getArray() != elements)
                    throw new ConcurrentModificationException();
                int len = elements.length;
                if (lo < 0 || hi > len)
                    throw new IndexOutOfBoundsException();
                Object[] newElements = Arrays.copyOf(elements, len);
                @SuppressWarnings("unchecked") E[] es = (E[])newElements;
                Arrays.sort(es, lo, hi, c);
                l.setArray(expectedArray = newElements);
            } finally {
                lock.unlock();
            }
        }

        public boolean removeAll(Collection<?> c) {
            if (c == null) throw new NullPointerException();
            boolean removed = false;
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                int n = size;
                if (n > 0) {
                    int lo = offset;
                    int hi = offset + n;
                    Object[] elements = expectedArray;
                    if (l.getArray() != elements)
                        throw new ConcurrentModificationException();
                    int len = elements.length;
                    if (lo < 0 || hi > len)
                        throw new IndexOutOfBoundsException();
                    int newSize = 0;
                    Object[] temp = new Object[n];
                    for (int i = lo; i < hi; ++i) {
                        Object element = elements[i];
                        if (!c.contains(element))
                            temp[newSize++] = element;
                    }
                    if (newSize != n) {
                        Object[] newElements = new Object[len - n + newSize];
                        System.arraycopy(elements, 0, newElements, 0, lo);
                        System.arraycopy(temp, 0, newElements, lo, newSize);
                        System.arraycopy(elements, hi, newElements,
                                         lo + newSize, len - hi);
                        size = newSize;
                        removed = true;
                        l.setArray(expectedArray = newElements);
                    }
                }
            } finally {
                lock.unlock();
            }
            return removed;
        }

        public boolean retainAll(Collection<?> c) {
            if (c == null) throw new NullPointerException();
            boolean removed = false;
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                int n = size;
                if (n > 0) {
                    int lo = offset;
                    int hi = offset + n;
                    Object[] elements = expectedArray;
                    if (l.getArray() != elements)
                        throw new ConcurrentModificationException();
                    int len = elements.length;
                    if (lo < 0 || hi > len)
                        throw new IndexOutOfBoundsException();
                    int newSize = 0;
                    Object[] temp = new Object[n];
                    for (int i = lo; i < hi; ++i) {
                        Object element = elements[i];
                        if (c.contains(element))
                            temp[newSize++] = element;
                    }
                    if (newSize != n) {
                        Object[] newElements = new Object[len - n + newSize];
                        System.arraycopy(elements, 0, newElements, 0, lo);
                        System.arraycopy(temp, 0, newElements, lo, newSize);
                        System.arraycopy(elements, hi, newElements,
                                         lo + newSize, len - hi);
                        size = newSize;
                        removed = true;
                        l.setArray(expectedArray = newElements);
                    }
                }
            } finally {
                lock.unlock();
            }
            return removed;
        }

        public boolean removeIf(Predicate<? super E> filter) {
            if (filter == null) throw new NullPointerException();
            boolean removed = false;
            final ReentrantLock lock = l.lock;
            lock.lock();
            try {
                int n = size;
                if (n > 0) {
                    int lo = offset;
                    int hi = offset + n;
                    Object[] elements = expectedArray;
                    if (l.getArray() != elements)
                        throw new ConcurrentModificationException();
                    int len = elements.length;
                    if (lo < 0 || hi > len)
                        throw new IndexOutOfBoundsException();
                    int newSize = 0;
                    Object[] temp = new Object[n];
                    for (int i = lo; i < hi; ++i) {
                        @SuppressWarnings("unchecked") E e = (E) elements[i];
                        if (!filter.test(e))
                            temp[newSize++] = e;
                    }
                    if (newSize != n) {
                        Object[] newElements = new Object[len - n + newSize];
                        System.arraycopy(elements, 0, newElements, 0, lo);
                        System.arraycopy(temp, 0, newElements, lo, newSize);
                        System.arraycopy(elements, hi, newElements,
                                         lo + newSize, len - hi);
                        size = newSize;
                        removed = true;
                        l.setArray(expectedArray = newElements);
                    }
                }
            } finally {
                lock.unlock();
            }
            return removed;
        }

        public Spliterator<E> spliterator() {
            int lo = offset;
            int hi = offset + size;
            Object[] a = expectedArray;
            if (l.getArray() != a)
                throw new ConcurrentModificationException();
            if (lo < 0 || hi > a.length)
                throw new IndexOutOfBoundsException();
            return Spliterators.spliterator
                (a, lo, hi, Spliterator.IMMUTABLE | Spliterator.ORDERED);
        }

    }

    private static class COWSubListIterator<E> implements ListIterator<E> {
        private final ListIterator<E> it;
        private final int offset;
        private final int size;

        COWSubListIterator(List<E> l, int index, int offset, int size) {
            this.offset = offset;
            this.size = size;
            it = l.listIterator(index+offset);
        }

        public boolean hasNext() {
            return nextIndex() < size;
        }

        public E next() {
            if (hasNext())
                return it.next();
            else
                throw new NoSuchElementException();
        }

        public boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        public E previous() {
            if (hasPrevious())
                return it.previous();
            else
                throw new NoSuchElementException();
        }

        public int nextIndex() {
            return it.nextIndex() - offset;
        }

        public int previousIndex() {
            return it.previousIndex() - offset;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            int s = size;
            ListIterator<E> i = it;
            while (nextIndex() < s) {
                action.accept(i.next());
            }
        }
    }

    // Support for resetting lock while deserializing
    private void resetLock() {
        UNSAFE.putObjectVolatile(this, lockOffset, new ReentrantLock());
    }
    private static final sun.misc.Unsafe UNSAFE;
    private static final long lockOffset;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = CopyOnWriteArrayList.class;
            lockOffset = UNSAFE.objectFieldOffset
                (k.getDeclaredField("lock"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
