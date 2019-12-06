
package java.util;

import java.io.InvalidObjectException;

import com.lyp.learn.base.threads.pk01.Object;
import sun.misc.SharedSecrets;

/**
 * 此类实现Set接口，由哈希表（实际为HashMap实例）支持。 对集合的迭代次序不作任何保证;
 * 特别是，它不能保证订单在一段时间内保持不变。 这个类允许null元素。
 *
 * 这个类提供了基本操作（add，remove，contains和size）固定的时间性能，假定哈希函数将分散的桶中正确的元素。
 * 迭代此集合需要与HashSet实例的大小（元素数量）和后台HashMap实例（桶数）的“容量”的总和成比例的时间。
 * 因此，如果迭代性能很重要，不要将初始容量设置得太高（或负载因子太低）是非常重要的。
 *
 * 请注意，此实现不同步。 如果多个线程并发访问哈希集，并且至少有一个线程修改该集合，那么它必须在外部进行同步。
 * 这通常通过在自然地封装集合的一些对象上进行同步来实现。
 *
 * 如果没有这样的对象存在，那么该集合应该使用Collections.synchronizedSet方法“包装”。
 * 这最好在创建时完成，以防止对该集合的意外不同步访问：
 * Set s = Collections.synchronizedSet(new HashSet(...));
 *
 * 该类iterator方法返回的迭代器是故障快速的 ：
 * 如果集合在迭代器创建之后的任何时间被修改，除了通过迭代器自己的remove方法之外，迭代器会抛出一个ConcurrentModificationException 。
 * 因此，面对并发修改，迭代器将快速而干净地失败，而不是在未来未确定的时间冒着任意的非确定性行为。
 *
 * 请注意，迭代器的故障快速行为无法保证，因为一般来说，在不同步并发修改的情况下，无法做出任何硬性保证。
 * 失败快速迭代器尽力投入ConcurrentModificationException 。
 * 因此，编写依赖于此异常的程序的正确性将是错误的：迭代器的故障快速行为应仅用于检测错误。
 */

public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable {
    static final long serialVersionUID = -5024744406713321676L;

    // 底层使用HashMap来保存HashSet中所有元素。
    private transient java.util.HashMap<E, Object> map;

    // 定义一个虚拟的Object对象作为HashMap的value，将此对象定义为static final。
    // PRESENT是向map中插入key-value对应的value
    // 因为HashSet中只需要用到key，而HashMap是key-value键值对；
    // 所以，向map中添加键值对时，键值对的值固定是PRESENT
    private static final Object PRESENT = new Object();

    /**
     * 默认的无参构造器，构造一个空的HashSet。
     * 实际底层会初始化一个空的HashMap，并使用默认初始容量为16和加载因子0.75。
     */
    public HashSet() {
        map = new java.util.HashMap<>();
    }

    /**
     * 构造一个包含指定collection中的元素的新set。
     *
     * 实际底层使用默认的加载因子0.75
     * 和足以包含指定collection中所有元素的初始容量来创建一个HashMap
     */
    public HashSet(Collection<? extends E> c) {
        map = new java.util.HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }

    /**
     * 以指定的initialCapacity和loadFactor构造一个空的HashSet。
     *
     * 实际底层以相应的参数构造一个空的HashMap
     *
     * @param   initialCapacity  哈希映射的初始容量
     * @param   loadFactor   哈希映射的负载因子
     * @throws     IllegalArgumentException 如果初始容量小于零，或者负载因子是非正性的
     */
    public HashSet(int initialCapacity, float loadFactor) {
        map = new java.util.HashMap<>(initialCapacity, loadFactor);
    }

    /**
     * 构造一个新的空集合;
     * 后台HashMap实例具有指定的初始容量和默认负载因子（0.75）。
     *
     * @param      initialCapacity   哈希表的初始容量
     */
    public HashSet(int initialCapacity) {
        map = new java.util.HashMap<>(initialCapacity);
    }

    /**
     * 以指定的initialCapacity和loadFactor构造一个新的空链接哈希集合。
     * 此构造函数为包访问权限，不对外公开，实际只是是对LinkedHashSet的支持。
     *
     *  实际底层会以指定的参数构造一个空LinkedHashMap实例来实现。
     * @param  initialCapacity  初始容量
     * @param  loadFactor      加载因子
     * @param      dummy            标记
     * @throws     IllegalArgumentException  如果初始容量小于零，或者负载因子是非正性的
     */
    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }

    /**
     * 返回对此set中元素进行迭代的迭代器。
     * 返回元素的顺序并不是特定的。
     *
     * 底层实际调用底层HashMap的keySet来返回所有的key。
     * 可见HashSet中的元素，只是存放在了底层HashMap的key上，
     * value使用一个static final的Object对象标识。
     */
    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    /**
     * 返回此set中的元素的数量（set的容量）。
     *
     * 底层实际调用HashMap的size()方法返回Entry的数量，就得到该Set中元素的个数。
     * @return 此set中的元素的数量（set的容量）。
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * 如果此set不包含任何元素，则返回true。
     *
     * 底层实际调用HashMap的isEmpty()判断该HashSet是否为空。
     */
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * 如果此set包含指定元素，则返回true。
     * 更确切地讲，当且仅当此set包含一个满足(o==null ? e==null : o.equals(e))的e元素时，返回true。
     *
     * 底层实际调用HashMap的containsKey判断是否包含指定key。
     */
    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    /**
     * 将指定的元素添加到此集合（如果尚未存在）。
     * 更正式地，将指定的元素e添加到此集合，如果此集合不包含元素e2 ，使得(e==null ? e2==null : e.equals(e2)) 。
     * 如果该集合已经包含该元素，则该调用将不更改set，并返回false 。
     *
     *底层实际将将该元素作为key放入HashMap。
     *由于HashMap的put()方法添加key-value对时，当新放入HashMap的Entry中key
     *与集合中原有Entry的key相同（hashCode()返回值相等，通过equals比较也返回true），
     * 新添加的Entry的value会将覆盖原来Entry的value，但key不会有任何改变，
     * 因此如果向HashSet中添加一个已经存在的元素时，新添加的集合元素将不会被放入HashMap中，
     * 原来的元素也不会有任何改变，这也就满足了Set中元素不重复的特性。
     *
     * @param e 将添加到此set中的元素。
     * @return 如果此set尚未包含指定元素，则返回true。
     */
    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }

    /**
     * 如果指定元素存在于此set中，则将其移除。
     * 更确切地讲，如果此set包含一个满足(o==null ? e==null : o.equals(e))的元素e，则将其移除。
     * 如果此set已包含该元素，则返回true
     * (或者：如果此set因调用而发生更改，则返回true)
     * (一旦调用返回，则此set不再包含该元素)
     *
     * 底层实际调用HashMap的remove方法删除指定Entry。
     *
     * @param o 如果存在于此set中则需要将其移除的对象。
     * @return 如果set包含指定元素，则返回true。
     */
    @Override
    public boolean remove(Object o) {
        return map.remove(o)==PRESENT;
    }

    /**
     * 从此set中移除所有元素。此调用返回后，该set将为空。
     *
     * 底层实际调用HashMap的clear方法清空Entry中所有元素
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * 返回此HashSet实例的浅表副本：并没有复制这些元素本身。
     *
     * 底层实际调用HashMap的clone()方法，获取HashMap的浅表副本，并设置到HashSet中。
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
        try {
            HashSet<E> newSet = (HashSet<E>) super.clone();
            newSet.map = (java.util.HashMap<E, Object>) map.clone();
            return newSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     * ava.io.Serializable的写入函数
     * 将HashSet的“总的容量，加载因子，实际容量，所有的元素”都写入到输出流中
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out HashMap capacity and load factor
        s.writeInt(map.capacity());
        s.writeFloat(map.loadFactor());

        // Write out size
        s.writeInt(map.size());

        // Write out all elements in the proper order.
        for (E e : map.keySet()) {
            s.writeObject(e);
        }
    }

    /**
     * java.io.Serializable的读取函数
     * 将HashSet的“总的容量，加载因子，实际容量，所有的元素”依次读出
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Read capacity and verify non-negative.
        int capacity = s.readInt();
        if (capacity < 0) {
            throw new InvalidObjectException("Illegal capacity: " +
                    capacity);
        }

        // Read load factor and verify positive and non NaN.
        float loadFactor = s.readFloat();
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new InvalidObjectException("Illegal load factor: " +
                    loadFactor);
        }

        // Read size and verify non-negative.
        int size = s.readInt();
        if (size < 0) {
            throw new InvalidObjectException("Illegal size: " +
                    size);
        }
        // Set the capacity according to the size and load factor ensuring that
        // the HashMap is at least 25% full but clamping to maximum capacity.
        capacity = (int) Math.min(size * Math.min(1 / loadFactor, 4.0f),
                HashMap.MAXIMUM_CAPACITY);

        // Constructing the backing map will lazily create an array when the first element is
        // added, so check it before construction. Call HashMap.tableSizeFor to compute the
        // actual allocation size. Check Map.Entry[].class since it's the nearest public type to
        // what is actually created.

        SharedSecrets.getJavaOISAccess()
                .checkArray(s, Map.Entry[].class, java.util.HashMap.tableSizeFor(capacity));

        // Create backing HashMap
        map = (((HashSet<?>)this) instanceof LinkedHashSet ?
                new LinkedHashMap<E,Object>(capacity, loadFactor) :
                new java.util.HashMap<E,Object>(capacity, loadFactor));

        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            @SuppressWarnings("unchecked")
            E e = (E) s.readObject();
            map.put(e, PRESENT);
        }
    }

    /**
     */
    @Override
    public Spliterator<E> spliterator() {
        return new HashMap.KeySpliterator<E,Object>(map, 0, -1, 0, 0);
    }
}
