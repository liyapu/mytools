package java.util;


import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Vector类实现了可扩展的对象数组。 像数组一样，它包含可以使用整数索引访问的组件。
 * 但是， Vector的大小可以根据需要增长或缩小，以适应在创建Vector之后添加和删除项目。
 *
 * 每个向量尝试通过维护capacity和capacityIncrement优化存储capacityIncrement 。
 * capacity总是至少与矢量大小一样大;通常较大，因为当向量中添加组分时，向量的存储空间增大capacityIncrement 。
 * 应用程序可以在插入大量组件之前增加向量的容量; 这减少了增量重新分配的数量。
 *
 * 通过个iterator和listIterator方法创建的迭代器是快速失败的 ：
 * 如果向量在任何时间从结构上修改创建迭代器之后，以任何方式除非通过迭代器自身remove种或add方法，
 * 迭代器都将抛出一个ConcurrentModificationException
 * 因此，面对并发修改，迭代器将快速而干净地失败，而不是在未来未确定的时间冒着任意的非确定性行为。
 * 由elements返回的Enumerations 不是故障快速的。
 *
 * 请注意，迭代器的故障快速行为无法保证，因为一般来说，在不同步并发修改的情况下，无法做出任何硬性保证。
 * 失败快速迭代器尽力投入ConcurrentModificationException 。
 * 因此，编写依赖于此异常的程序的正确性将是错误的：迭代器的故障快速行为应仅用于检测错误。
 *
 * 从Java 2平台v1.2，这个类被改造为实现List接口，使其成为成员Java Collections Framework 。
 * 与新集合实现不同， Vector是同步的。
 * 如果不需要线程安全的实现，建议使用ArrayList代替Vector 。
 */
public class Vector<E>
        extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    /**
     * 存储向量的组件的阵列缓冲区。
     * 向量的容量是此数组缓冲区的长度，并且至少足够大以包含所有向量的元素。
     */
    protected Object[] elementData;

    /**
     * 该Vector对象中有效元素的数量。 元素elementData[0]至elementData[elementCount-1]是实际条目。
     */
    protected int elementCount;

    /**
     * 当矢量的大小大于其容量时，矢量的容量自动增加的量。
     * 如果容量增量小于或等于零，则每次需要增长时，向量的容量将加倍。
     */
    protected int capacityIncrement;

    /** 序列化id */
    private static final long serialVersionUID = -2767605614048989439L;

    /**
     * 构造具有指定的初始容量和容量增量的空向量。
     * @param   initialCapacity     矢量的初始容量
     * @param   capacityIncrement   当矢量溢出时容量增加的量
     * @throws IllegalArgumentException 如果指定的初始容量为负
     */
    public Vector(int initialCapacity, int capacityIncrement) {
        super();
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }

    /**
     * 构造具有指定初始容量并且其容量增量等于零的空向量。
     * @param   initialCapacity   矢量的初始容量
     * @throws IllegalArgumentException  如果指定的初始容量为负
     */
    public Vector(int initialCapacity) {
        this(initialCapacity, 0);
    }

    /**
     * 构造一个空的向量，使其内部数据数组的大小为 10 ，其标准容量增量为零。
     */
    public Vector() {
        this(10);
    }

    /**
     * 构造一个包含指定集合元素的向量，按照集合的迭代器返回的顺序。
     * @param c 要将元素放入此向量的集合
     * @throws NullPointerException   如果指定的集合为空
     */
    public Vector(Collection<? extends E> c) {
        elementData = c.toArray();
        elementCount = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class) {
            elementData = Arrays.copyOf(elementData, elementCount, Object[].class);
        }
    }

    /**
     * 将此向量的元素复制到指定的数组中。 此向量中索引k处的元素被复制到anArray的k索引处 。
     *
     * @param  anArray 组件被复制到的阵列
     * @throws NullPointerException 如果给定的数组为空
     * @throws IndexOutOfBoundsException 如果指定的数组不够大以容纳该向量的所有元素
     * @throws ArrayStoreException 如果此向量的元素不是可以存储在指定数组中的运行时类型
     * @see #toArray(Object[])
     */
    public synchronized void copyInto(Object[] anArray) {
        System.arraycopy(elementData, 0, anArray, 0, elementCount);
    }

    /**
     * 修改该向量的容量成为向量的当前大小。
     * 如果该向量的容量大于其当前大小，
     * 则通过用保留在字段elementData中的较小的内部数据阵列替换其内部数据阵列，将容量更改为等于大小。
     * 应用程序可以使用此操作来最小化向量的存储。
     */
    public synchronized void trimToSize() {
        modCount++;
        int oldCapacity = elementData.length;
        if (elementCount < oldCapacity) {
            elementData = Arrays.copyOf(elementData, elementCount);
        }
    }

    /**
     * 如果需要，增加此向量的容量，以确保它可以至少保存最小容量参数指定的元素数。
     */
    public synchronized void ensureCapacity(int minCapacity) {
        if (minCapacity > 0) {
            modCount++;
            ensureCapacityHelper(minCapacity);
        }
    }

    /**
     * 这个扩容实现是没有同步的。
     * 此类中的同步方法可以在内部调用此方法以确保容量，而不会引起额外的同步成本。
     */
    private void ensureCapacityHelper(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 数组最大容量
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 计算新的newCapacity容量 (oldCapacity + capacityIncrement  或者 oldCapacity * 2)
     * newCapacity 与 minCapacity ，两者之间取大值
     * 容量最大值校验
     * 实际扩容
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ? capacityIncrement : oldCapacity);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
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
     * 设置此向量的大小。
     * 如果新尺寸大于当前大小，则新的null项目将添加到向量的末尾。
     * 如果新尺寸小于当前尺寸，则丢弃索引newSize及更高版本的所有组件。
     */
    public synchronized void setSize(int newSize) {
        modCount++;
        if (newSize > elementCount) {
            ensureCapacityHelper(newSize);
        } else {
            for (int i = newSize ; i < elementCount ; i++) {
                elementData[i] = null;
            }
        }
        elementCount = newSize;
    }

    /**
     * 返回此向量的当前容量。
     * @return 当前容量（其内部数据数组的长度，保存在该向量的字段 elementData ）
     */
    public synchronized int capacity() {
        return elementData.length;
    }

    /**
     * 返回此向量中的元素数。
     * 即实际保存的元素个数
     * @return 该向量中的元素数量
     */
    @Override
    public synchronized int size() {
        return elementCount;
    }

    /**
     * 测试此矢量是否没有元素。
     *
     * @return true当且仅当该向量没有组件时，即其大小为零;
     *         false否则。
     */
    @Override
    public synchronized boolean isEmpty() {
        return elementCount == 0;
    }

    /**
     * 返回此向量的组件的枚举。
     * 返回的Enumeration对象将生成此向量中的所有项。 产生的第一项是在索引的项0 ，则在索引项1 ，依此类推。
     */
    public Enumeration<E> elements() {
        return new Enumeration<E>() {
            int count = 0;

            @Override
            public boolean hasMoreElements() {
                return count < elementCount;
            }

            @Override
            public E nextElement() {
                synchronized (Vector.this) {
                    if (count < elementCount) {
                        return elementData(count++);
                    }
                }
                throw new NoSuchElementException("Vector Enumeration");
            }
        };
    }

    /**
     * 如果此向量包含指定的元素，则返回true 。
     * 更正式地，返回true当且仅当该向量包含至少一个元素e使得(o==null ? e==null : o.equals(e)) 。
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o, 0) >= 0;
    }

    /**
     * 返回此向量中指定元素的第一次出现的索引，如果此向量不包含元素，则返回-1。
     *
     * 更正式地，返回最低指数i ，使(o==null ? get(i)==null : o.equals(get(i))) ，
     * 或-1如果没有这样的指数。
     * Returns the index of the first occurrence of the specified element
     * in this vector, or -1 if this vector does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o 要搜索的元素
     * @return 该向量中指定元素的第一次出现的索引，如果此向量不包含元素，则为-1
     */
    @Override
    public int indexOf(Object o) {
        return indexOf(o, 0);
    }

    /**
     * 返回此向量中指定元素的第一次出现的索引，从index向前index ，如果未找到该元素，则返回-1。
     *
     * 更正式地，返回最低指数i ，使(i >= index && (o==null ? get(i)==null : o.equals(get(i)))) ，
     * 或-1如果没有这样的索引。
     * @param o  要搜索的元素
     * @param index 开始搜索的索引
     * @return 该向量中元素第一次出现的位置在向量中的位置index或更高的位置的index ; -1如果没有找到该元素。
     * @throws IndexOutOfBoundsException 如果指定的索引为负数
     *
     * null 使用 == 判断
     * 其它 使用equals判断
     */
    public synchronized int indexOf(Object o, int index) {
        if (o == null) {
            for (int i = index ; i < elementCount ; i++) {
                if (elementData[i]==null) {
                    return i;
                }
            }
        } else {
            for (int i = index ; i < elementCount ; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回此向量中指定元素的最后一次出现的索引，如果此向量不包含元素，则返回-1。
     *
     * 更正式地，返回最高指数i ，使(o==null ? get(i)==null : o.equals(get(i))) ，
     * 或-1如果没有这样的索引。
     *
     * @param o 要搜索的元素
     * @return 该向量中指定元素的最后一次出现的索引，如果此向量不包含元素，则为-1
     */
    @Override
    public synchronized int lastIndexOf(Object o) {
        return lastIndexOf(o, elementCount-1);
    }

    /**
     * 返回此向量中指定元素的最后一次出现的索引，从index ，如果未找到该元素，则返回-1。
     *
     * 更正式地，返回最高指数i ，使(i <= index && (o==null ? get(i)==null : o.equals(get(i)))) ，
     * 或-1如果没有这样的索引。
     *
     * @param o 要搜索的元素
     * @param index 开始向后搜索的索引
     * @return 该向量中位置小于或等于index的元素的最后一次出现的索引; -1如果没有找到元素。
     * @throws IndexOutOfBoundsException 如果指定的索引大于或等于此向量的当前大小
     */
    public synchronized int lastIndexOf(Object o, int index) {
        if (index >= elementCount) {
            throw new IndexOutOfBoundsException(index + " >= "+ elementCount);
        }

        if (o == null) {
            for (int i = index; i >= 0; i--) {
                if (elementData[i]==null) {
                    return i;
                }
            }
        } else {
            for (int i = index; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 返回指定索引处的组件。
     *
     * 该方法的功能与get(int)方法相同（它是List接口的一部分）。
     *
     * @param index  这个向量的索引
     * @return 指定索引处的组件
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围（ index < 0 || index >= size() ）
     */
    public synchronized E elementAt(int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
        }

        return elementData(index);
    }

    /**
     * 返回此向量的第一个组件（索引号为 0的元素）。
     */
    public synchronized E firstElement() {
        if (elementCount == 0) {
            throw new NoSuchElementException();
        }
        return elementData(0);
    }

    /**
     * 返回向量的最后一个组件
     *
     * @return 向量的最后一个组件，即索引为 size() - 1
     * @throws NoSuchElementException 如果此向量为空
     */
    public synchronized E lastElement() {
        if (elementCount == 0) {
            throw new NoSuchElementException();
        }
        return elementData(elementCount - 1);
    }

    /**
     * 将该向量的指定index的组件设置为指定的对象。 该位置的上一个组件被丢弃。
     * 索引必须大于或等于0 ，小于当前的向量大小。
     *
     * 该方法的功能与set(int, E)方法相同（它是List接口的一部分）。
     * 请注意， set方法会反转参数的顺序，以更紧密地匹配阵列使用。
     * 另请注意， set方法返回存储在指定位置的旧值。
     *
     */
    public synchronized void setElementAt(E obj, int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                    elementCount);
        }
        elementData[index] = obj;
    }

    /**
     * 删除指定索引处的组件。
     * 在该矢量与索引大于或等于指定的每个组件index向下移动到具有比其先前所具有的值越小的指标之一。 此向量的大小减少1 。
     * 索引必须大于或等于0 ，小于当前的向量大小。
     *
     * 该方法的功能与remove(int)方法相同（它是List接口的一部分）。
     * 请注意， remove方法返回存储在指定位置的旧值。
     *
     * 校验index是否合法
     * j 表示要移动的元素个数
     * 元素数减1，最后一个元素变为null
     */
    public synchronized void removeElementAt(int index) {
        modCount++;
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                    elementCount);
        }
        else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount - index - 1;
        if (j > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, j);
        }
        elementCount--;
        elementData[elementCount] = null; /* to let gc do its work */
    }

    /**
     * 在指定的index插入指定对象作为此向量中的index 。
     * 该向量中具有大于或等于指定值index的索引中的每个index向上移动以使索引一大于先前的值。
     * 索引必须大于或等于0 ，小于或等于向量的当前大小。 （如果索引等于向量的当前大小，则新元素将附加到向量。）
     *
     * 该方法的功能与add(int, E)方法相同（它是List接口的一部分）。
     * 请注意， add方法可以反转参数的顺序，以更紧密地匹配阵列使用。
     *
     * @param      obj     要插入的组件
     * @param      index   在哪里插入新组件
     * @throws ArrayIndexOutOfBoundsException  如果索引超出范围（ index < 0 || index > size() ）
     *
     * 校验index
     * 扩容
     * 在 index 位置以及之后的元素后移一位
     * 插入新元素
     * 有效元素数量加1
     */
    public synchronized void insertElementAt(E obj, int index) {
        modCount++;
        if (index > elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " > " + elementCount);
        }
        ensureCapacityHelper(elementCount + 1);
        System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
        elementData[index] = obj;
        elementCount++;
    }

    /**
     * 将指定的组件添加到此向量的末尾，将其大小增加1。
     * 如果该载体的大小大于其容量，则该载体的容量增加。
     *
     * 该方法的功能与add(E)方法相同（它是List接口的一部分）。
     */
    public synchronized void addElement(E obj) {
        modCount++;
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = obj;
    }

    /**
     * 从此向量中删除参数的第一个（最低索引）出现次数。
     * 如果在该向量中找到对象，则向量中具有大于或等于对象索引的索引的向量中的每个组件向下移动，以使索引小于之前的值。
     *
     * 该方法的功能与remove(Object)方法相同（它是List接口的一部分）。
     */
    public synchronized boolean removeElement(Object obj) {
        modCount++;
        int i = indexOf(obj);
        if (i >= 0) {
            removeElementAt(i);
            return true;
        }
        return false;
    }

    /**
     * 从该向量中删除所有组件，并将其大小设置为零。
     * 该方法的功能与clear()方法相同（它是List接口的一部分）。
     */
    public synchronized void removeAllElements() {
        modCount++;
        // Let gc do its work
        for (int i = 0; i < elementCount; i++) {
            elementData[i] = null;
        }

        elementCount = 0;
    }

    /**
     * 返回此向量的克隆。
     * 该副本将包含对内部数据数组的克隆的引用，而不是对该Vector对象的原始内部数据数组的引用。
     */
    @Override
    public synchronized Object clone() {
        try {
            @SuppressWarnings("unchecked")
            Vector<E> v = (Vector<E>) super.clone();
            v.elementData = Arrays.copyOf(elementData, elementCount);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * 以正确的顺序返回一个包含此Vector中所有元素的数组。
     */
    @Override
    public synchronized Object[] toArray() {
        return Arrays.copyOf(elementData, elementCount);
    }

    /**
     * 以正确的顺序返回一个包含此Vector中所有元素的数组; 返回的数组的运行时类型是指定数组的运行时类型。
     * 如果Vector适合指定的数组，则返回其中。 否则，将使用指定数组的运行时类型和此Vector的大小分配一个新数组。
     *
     * 如果向量适合指定的数组，其余空间（即，该数组具有比Vector更多的元素），则紧接在Vector结束之后的数组中的元素将设置为null。
     * （ 仅当调用者知道Vector不包含任何空元素时，这才有助于确定Vector的长度。）
     *
     * @param a 要存储Vector的元素的数组，如果它足够大; 否则，为此目的分配相同运行时类型的新数组
     * @return 一个包含Vector元素的数组
     * @throws ArrayStoreException  如果a的运行时类型不是此向量中每个元素的运行时类型的超类型
     * @throws NullPointerException 如果给定的数组为空
     * @since 1.2
     */
    @Override
    @SuppressWarnings("unchecked")
    public synchronized <T> T[] toArray(T[] a) {
        if (a.length < elementCount) {
            return (T[]) Arrays.copyOf(elementData, elementCount, a.getClass());
        }

        System.arraycopy(elementData, 0, a, 0, elementCount);

        if (a.length > elementCount) {
            a[elementCount] = null;
        }

        return a;
    }

    // Positional Access Operations

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 返回此向量中指定位置的元素。
     */
    @Override
    public synchronized E get(int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return elementData(index);
    }

    /**
     * 用指定的元素替换此Vector中指定位置的元素。
     *
     * @param index 要替换的元素的索引
     * @param element 要存储在指定位置的元素
     * @return  在指定位置上的以前的元素值
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围（ index < 0 || index >= size() ）
     */
    @Override
    public synchronized E set(int index, E element) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 将指定的元素追加到此Vector的末尾。
     */
    @Override
    public synchronized boolean add(E e) {
        modCount++;
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = e;
        return true;
    }

    /**
     * 删除此向量中指定元素的第一个出现,如果Vector不包含元素，则它不会更改。
     * 更正式地，删除具有最低索引i的元素，使得(o==null ? get(i)==null : o.equals(get(i))) （如果这样的元素存在）。
     */
    @Override
    public boolean remove(Object o) {
        return removeElement(o);
    }

    /**
     * 在此Vector中的指定位置插入指定的元素。
     * 将当前位于该位置的元素（如果有）和任何后续元素（向其索引添加一个）移动。
     */
    @Override
    public void add(int index, E element) {
        insertElementAt(element, index);
    }

    /**
     * 删除此向量中指定位置的元素。
     * 将任何后续元素移动到左侧（从其索引中减去一个元素）。
     * 返回从Vector中删除的元素。
     */
    @Override
    public synchronized E remove(int index) {
        modCount++;
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldValue = elementData(index);

        int numMoved = elementCount - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--elementCount] = null; // Let gc do its work

        return oldValue;
    }

    /**
     * 从此Vector中删除所有元素。 此调用返回后，Vector将为空（除非引发异常）。
     */
    @Override
    public void clear() {
        removeAllElements();
    }

    // Bulk Operations

    /**
     * 如果此向量包含指定集合中的所有元素，则返回true。
     * @param   c  一个集合，其元素将在此向量中进行遏制测试
     * @return true 如果此向量包含指定集合中的所有元素，则为true
     * @throws NullPointerException  如果指定的集合为空
     */
    @Override
    public synchronized boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    /**
     * 将指定集合中的所有元素追加到该向量的末尾，按照它们由指定集合的迭代器返回的顺序。
     * 如果在操作进行中修改了指定的集合，则此操作的行为是未定义的。
     * （这意味着如果指定的集合是此Vector，则此调用的行为是未定义的，并且此向量是非空的。）
     *
     * @param c  要插入此向量的元素
     * @return {@code true} true如果此向量由于调用而更改
     * @throws NullPointerException 如果指定的集合为空
     */
    @Override
    public synchronized boolean addAll(Collection<? extends E> c) {
        modCount++;
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityHelper(elementCount + numNew);
        System.arraycopy(a, 0, elementData, elementCount, numNew);
        elementCount += numNew;
        return numNew != 0;
    }

    /**
     * 从此Vector中删除指定集合中包含的所有元素。
     */
    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    /**
     * 仅保留此向量中包含在指定集合中的元素。
     * 换句话说，从此Vector中删除所有不包含在指定集合中的元素。
     */
    @Override
    public synchronized boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    /**
     * 将指定集合中的所有元素插入到此向量中的指定位置。
     * 将当前位于该位置（如果有的话）的元素和随后的任何元素移动到右边（增加其索引）。
     * 新元素将按照它们由指定的集合的迭代器返回的顺序显示在Vector中。
     *
     * 校验index是否合法
     * 扩容
     * 计算index位置以及之后需要移动的元素个数
     * 根据numMoved 判断是否需要移动元素
     * 在指定位置插入指定数量的元素
     */
    @Override
    public synchronized boolean addAll(int index, Collection<? extends E> c) {
        modCount++;
        if (index < 0 || index > elementCount) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityHelper(elementCount + numNew);

        int numMoved = elementCount - index;
        if (numMoved > 0) {
            System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
        }

        System.arraycopy(a, 0, elementData, index, numNew);
        elementCount += numNew;
        return numNew != 0;
    }

    /**
     * 将指定的对象与此向量进行比较以获得相等性。
     * 如果且仅当指定的对象也是列表时，则返回true，两个列表的大小相同，并且两个列表中所有相应的元素对相等 。
     * （如果(e1==null ? e2==null : e1.equals(e2))两个元素e1和e2 相等 ）。
     *
     * 换句话说，如果两个列表以相同的顺序包含相同的元素，则它们被定义为相等。
     */
    @Override
    public synchronized boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * 返回此Vector的哈希码值。
     */
    @Override
    public synchronized int hashCode() {
        return super.hashCode();
    }

    /**
     * 返回此Vector的字符串表示形式，其中包含每个元素的String表示形式。
     */
    @Override
    public synchronized String toString() {
        return super.toString();
    }

    /**
     * 返回此列表之间的fromIndex（包括）和toIndex之间的独占视图。 （如果fromIndex和toIndex相等，则返回的列表为空。）
     * 返回的列表由此列表支持，因此返回的列表中的更改将反映在此列表中，反之亦然。
     * 返回的列表支持此列表支持的所有可选列表操作。
     *
     * 该方法消除了对显式范围操作（对于数组通常存在的排序）的需要。
     * 任何期望列表的操作都可以通过在子列表视图而不是整个列表中进行操作来用作范围操作。
     * 例如，以下惯用语法从列表中删除了一系列元素：
     * list.subList(from, to).clear();
     * 可以为indexOf和lastIndexOf构造类似的语法，并且Collections类中的所有算法都可以应用于子列表。
     *
     * 如果支持列表（即，此列表）以除了通过返回的列表之外的任何方式进行结构修改 ，则此方法返回的列表的语义将变得未定义。
     * （结构修改是那些改变List的大小，或以其他方式扰乱它，使得正在进行的迭代可能产生不正确的结果）。
     *
     * @param fromIndex fromIndex低端点（含）
     * @param toIndex toIndex高端点（排他性）
     * @return 该列表中指定范围的视图
     * @throws IndexOutOfBoundsException 如果端点索引值超出范围 (fromIndex < 0 || toIndex > size)
     * @throws IllegalArgumentException 如果端点索引 (fromIndex > toIndex)
     */
    @Override
    public synchronized List<E> subList(int fromIndex, int toIndex) {
        return Collections.synchronizedList(super.subList(fromIndex, toIndex), this);
    }

    /**
     * 从此列表中删除索引为fromIndex(含)和toIndex之间的所有元素。
     * 将任何后续元素移动到左侧（减少其索引）。
     * 此调用会通过(toIndex - fromIndex)元素(toIndex - fromIndex)列表。
     * （如果toIndex==fromIndex ，此操作无效）
     */
    @Override
    protected synchronized void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = elementCount - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex, numMoved);

        // Let gc do its work
        int newElementCount = elementCount - (toIndex-fromIndex);
        while (elementCount != newElementCount) {
            elementData[--elementCount] = null;
        }
    }

    /**
     * 序列化
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        final java.io.ObjectOutputStream.PutField fields = s.putFields();
        final Object[] data;
        synchronized (this) {
            fields.put("capacityIncrement", capacityIncrement);
            fields.put("elementCount", elementCount);
            data = elementData.clone();
        }
        fields.put("elementData", data);
        s.writeFields();
    }

    /**
     * 从列表中的指定位置开始，返回列表中的元素（按正确顺序）的列表迭代器。
     * 指定的索引表示初始调用next将返回的第一个元素。 对previous的初始调用将返回指定索引减1的元素。
     * 返回的列表迭代器是fail-fast 。
     */
    @Override
    public synchronized ListIterator<E> listIterator(int index) {
        if (index < 0 || index > elementCount) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
        return new ListItr(index);
    }

    /**
     * 返回列表中的列表迭代器（按适当的顺序）。
     * 返回的列表迭代器是fail-fast 。
     */
    @Override
    public synchronized ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    /**
     * 以正确的顺序返回该列表中的元素的迭代器。
     * 返回的迭代器是fail-fast 。
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * 最小化版本实现 AbstractList.Itr
     */
    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            // Racy but within spec, since modifications are checked
            // within or after synchronization in next/previous
            return cursor != elementCount;
        }

        @Override
        public E next() {
            synchronized (Vector.this) {
                checkForComodification();
                int i = cursor;
                if (i >= elementCount) {
                    throw new NoSuchElementException();
                }
                cursor = i + 1;
                return elementData(lastRet = i);
            }
        }

        @Override
        public void remove() {
            if (lastRet == -1) {
                throw new IllegalStateException();
            }
            synchronized (Vector.this) {
                checkForComodification();
                Vector.this.remove(lastRet);
                expectedModCount = modCount;
            }
            cursor = lastRet;
            lastRet = -1;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            synchronized (Vector.this) {
                final int size = elementCount;
                int i = cursor;
                if (i >= size) {
                    return;
                }
                @SuppressWarnings("unchecked")
                final E[] elementData = (E[]) Vector.this.elementData;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                }
                while (i != size && modCount == expectedModCount) {
                    action.accept(elementData[i++]);
                }
                // update once at end of iteration to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * 最小化版本实现 AbstractList.ListItr
     */
    final class ListItr extends Itr implements ListIterator<E> {
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

        @Override
        public E previous() {
            synchronized (Vector.this) {
                checkForComodification();
                int i = cursor - 1;
                if (i < 0) {
                    throw new NoSuchElementException();
                }
                cursor = i;
                return elementData(lastRet = i);
            }
        }

        @Override
        public void set(E e) {
            if (lastRet == -1) {
                throw new IllegalStateException();
            }
            synchronized (Vector.this) {
                checkForComodification();
                Vector.this.set(lastRet, e);
            }
        }

        @Override
        public void add(E e) {
            int i = cursor;
            synchronized (Vector.this) {
                checkForComodification();
                Vector.this.add(i, e);
                expectedModCount = modCount;
            }
            cursor = i + 1;
            lastRet = -1;
        }
    }

    @Override
    public synchronized void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        @SuppressWarnings("unchecked")
        final E[] elementData = (E[]) this.elementData;
        final int elementCount = this.elementCount;
        for (int i=0; modCount == expectedModCount && i < elementCount; i++) {
            action.accept(elementData[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        int removeCount = 0;
        final int size = elementCount;
        final BitSet removeSet = new BitSet(size);
        final int expectedModCount = modCount;
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
            elementCount = newSize;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            modCount++;
        }

        return anyToRemove;
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final int size = elementCount;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            elementData[i] = operator.apply((E) elementData[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void sort(Comparator<? super E> c) {
        final int expectedModCount = modCount;
        Arrays.sort((E[]) elementData, 0, elementCount, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

    /**
     */
    @Override
    public Spliterator<E> spliterator() {
        return new VectorSpliterator<>(this, null, 0, -1, 0);
    }

    /** Similar to ArrayList Spliterator */
    static final class VectorSpliterator<E> implements Spliterator<E> {
        private final Vector<E> list;
        private Object[] array;
        private int index; // current index, modified on advance/split
        private int fence; // -1 until used; then one past last index
        private int expectedModCount; // initialized when fence set

        /** Create new spliterator covering the given  range */
        VectorSpliterator(Vector<E> list, Object[] array, int origin, int fence,
                          int expectedModCount) {
            this.list = list;
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence() { // initialize on first use
            int hi;
            if ((hi = fence) < 0) {
                synchronized(list) {
                    array = list.elementData;
                    expectedModCount = list.modCount;
                    hi = fence = list.elementCount;
                }
            }
            return hi;
        }

        @Override
        public Spliterator<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                    new VectorSpliterator<E>(list, array, lo, index = mid,
                            expectedModCount);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean tryAdvance(Consumer<? super E> action) {
            int i;
            if (action == null) {
                throw new NullPointerException();
            }
            if (getFence() > (i = index)) {
                index = i + 1;
                action.accept((E)array[i]);
                if (list.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> action) {
            int i, hi; // hoist accesses and checks from loop
            Vector<E> lst; Object[] a;
            if (action == null) {
                throw new NullPointerException();
            }
            if ((lst = list) != null) {
                if ((hi = fence) < 0) {
                    synchronized(lst) {
                        expectedModCount = lst.modCount;
                        a = array = lst.elementData;
                        hi = fence = lst.elementCount;
                    }
                }
                else {
                    a = array;
                }
                if (a != null && (i = index) >= 0 && (index = hi) <= a.length) {
                    while (i < hi) {
                        action.accept((E) a[i++]);
                    }
                    if (lst.modCount == expectedModCount) {
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
}
