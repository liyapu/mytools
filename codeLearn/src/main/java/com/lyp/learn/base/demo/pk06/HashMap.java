package java.util;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import sun.misc.SharedSecrets;

/**
 * https://blog.csdn.net/v123411739/article/details/78996181
 * 
 * HashMap是Map接口基于哈希表的实现。
 * 这种实现提供了所有可选的Map操作，并允许key和value为null
 * （除了HashMap是unsynchronized的和允许使用null外，HashMap和HashTable大致相同。）。
 * 此类不保证映射的顺序，特别是它不保证该顺序恒久不变。
 *
 * 此实现假设哈希函数在桶内适当地分布元素，为基本实现(get 和 put)提供了稳定的性能。
 * 迭代 collection 视图所需的时间与 HashMap 实例的“容量”（桶的数量）及其大小（键-值映射关系数）成比例。
 * 如果遍历操作很重要，就不要把初始化容量initial capacity设置得太高（或将加载因子load factor设置得太低），
 * 否则会严重降低遍历的效率。
 *
 * HashMap有两个影响性能的重要参数：初始化容量initial capacity、加载因子load factor。
 * 容量是哈希表中桶的数量，初始容量只是哈希表在创建时的容量。
 * 加载因子是哈希表在其容量自动增加之前可以达到多满的一种尺度。
 * initial capacity*load factor就是当前允许的最大元素数目，
 * 超过initial capacity*load factor之后，HashMap就会进行rehashed操作来进行扩容，扩容后的的容量为之前的两倍。
 *
 * 通常，默认加载因子 (0.75) 在时间和空间成本上寻求一种折衷。
 * 加载因子过高虽然减少了空间开销，但同时也增加了查询成本（在大多数 HashMap类的操作中，包括 get 和 put 操作，都反映了这一点）。
 * 在设置初始容量时应该考虑到映射中所需的条目数及其加载因子，以便最大限度地减少rehash操作次数。
 * 如果初始容量大于最大条目数除以加载因子，则不会发生rehash 操作。
 *
 * 如果很多映射关系要存储在 HashMap 实例中，
 * 则相对于按需执行自动的 rehash 操作以增大表的容量来说，使用足够大的初始容量创建它将使得映射关系能更有效地存储。
 *
 * 注意，此实现不是同步的。如果多个线程同时访问一个哈希映射，而其中至少一个线程从结构上修改了该映射，则它必须保持外部同步。
 * （结构上的修改是指添加或删除一个或多个映射关系的任何操作；仅改变与实例已经包含的键关联的值不是结构上的修改。）
 * 这一般通过对自然封装该映射的对象进行同步操作来完成。
 * 如果不存在这样的对象，则应该使用 Collections.synchronizedMap 方法来“包装”该映射。
 * 最好在创建时完成这一操作，以防止对映射进行意外的非同步访问，如下所示：
 * Map m = Collections.synchronizedMap(new HashMap(…));
 *
 * 由所有此类的“collection 视图方法”所返回的迭代器都是fail-fast 的：
 * 在迭代器创建之后，如果从结构上对映射进行修改，除非通过迭代器本身的remove方法，其他任何时间任何方式的修改，
 * 迭代器都将抛出 ConcurrentModificationException。
 * 因此，面对并发的修改，迭代器很快就会完全失败，而不冒在将来不确定的时间发生任意不确定行为的风险。
 *
 * 注意，迭代器的快速失败行为不能得到保证，
 * 一般来说，存在非同步的并发修改时，不可能作出任何坚决的保证。
 * 快速失败迭代器尽最大努力抛出 ConcurrentModificationException。
 * 因此，编写依赖于此异常的程序的做法是错误的，
 * 正确做法是：迭代器的快速失败行为应该仅用于检测bug。
 *
 * 此类是 Java Collections Framework 的成员。
 *
 */
public class HashMap<K,V> extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;

    /**
     * 默认初始化容量，值为16
     * 必须是2的n次幂.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * 最大容量, 容量不能超出这个值。
     * 如果一个更大的初始化容量在构造函数中被指定，将被MAXIMUM_CAPACITY替换.必须是2的倍数。最大容量为1<<30，即2的30次方。
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 默认装载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 将链表转化为红黑树的临界值。
     * 当添加一个元素被添加到有至少TREEIFY_THRESHOLD个节点的桶中，桶中链表将被转化为树形结构。
     * 临界值最小为8
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * 恢复成链式结构的桶大小临界值
     * 小于UNTREEIFY_THRESHOLD，临界值最大为6
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * 桶可能被转化为树形结构的最小容量。
     * 当哈希表的大小超过这个阈值，才会把链式结构转化成树型结构，否则仅采取扩容来尝试减少冲突。
     * 应该至少4*TREEIFY_THRESHOLD来避免扩容和树形结构化之间的冲突。
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * HashMap的节点类型。既是HashMap底层数组的组成元素，又是每个单向链表的组成元素
     */
    static class Node<K,V> implements Entry<K,V> {
        //key的哈希值
        final int hash;
        final K key;
        V value;
        //指向下个节点的引用
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey()        { return key; }
        @Override
        public final V getValue()      { return value; }
        @Override
        public final String toString() { return key + "=" + value; }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Entry<?,?> e = (Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* ---------------- Static utilities -------------- */

    /**
     * 计算key的哈希值
     * 不管增加、删除、查找键值对，定位到哈希桶数组的位置都是很关键的第一步。计算位置的方法如下
     * (n - 1) & hash
     * 其中的n为数组的长度，hash为hash(key)计算得到的值
     *
     * 从代码中可以看到，计算位置分为三步，
     * 第一步，取key的hashCode，
     * 第二步，key的hashCode高16位 异或 低16位，
     * 第三步，将第一步和第二部得到的结果进行取与运算。
     *
     *   h = key.hashCode   1111 1111 1010 1100 1111 0000 0011 0110
     *                   --------------------------------------------
     *                 h    1111 1111 1010 1100 1111 0000 0011 0110
     *           h >>> 16   0000 0000 0000 0000 1111 1111 1010 1100
     *                  ---------------------------------------------
     *   hash=h^(h >>> 16   1111 1111 1010 1100 0000 0000 1001 1010
     *      n-1(假设n为16)  0000 0000 0000 0000 0000 0000 0000 1111
     *                 ---------------------------------------------
     *         (n-1)&hash  0000 0000 0000 0000 0000 0000 0000 1010
     *         ----------------------------------------------------
     *                       1010  = 10
     *
     * 看到这里有个疑问，为什么要做异或运算？
     *
     * 设想一下，如果n很小，假设为16的话，那么n-1即为15（0000 0000 0000 0000 0000 0000 0000 1111），
     * 这样的值如果跟hashCode()直接做与操作，实际上只使用了哈希值的后4位。
     * 如果当哈希值的高位变化很大，低位变化很小，这样很容易造成碰撞，
     * 所以把高低位都参与到计算中，从而解决了这个问题，而且也不会有太大的开销。
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 如果参数x实现了Comparable接口，返回参数x的类名，否则返回null
     */
    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
            {
                return c;
            }
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() == Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                    {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 如果x的类型为kc，则返回 k.compareTo(x)，否则返回0.
     */
    @SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 : ((Comparable)k).compareTo(x));
    }

    /**
     * 返回大于等于cap的最小的二次幂数值(2,4,8,16,32,64)
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /* ---------------- Fields ---成员变量-------- */

    /**
     * 此数组table,在第一次使用时初始化，在需要时扩容。
     * 当重新分配时，长度总是2的次幂
     * 在某些操作中，我们可以容忍长度为0为了启动不是必要的。
     */
    transient Node<K,V>[] table;

    /**
     * 当被调用entrySet时被赋值。
     * 通过keySet()方法可以得到map key的集合，通过values方法可以得到map value的集合。
     */
    transient Set<Entry<K,V>> entrySet;

    /**
     *  键值对的实际个数
     */
    transient int size;

    /**
     * 记录HashMap被修改结构的次数。
     * 修改包括改变键值对的个数或者修改内部结构，比如rehash
     * 这个域被用作HashMap的迭代器的fail-fast机制中（参考ConcurrentModificationException）
     */
    transient int modCount;

    /**
     *  扩容的临界值，通过capacity * load factor可以计算出来。超过这个值HashMap将进行扩容
     */
    int threshold;

    /**
     * 加载因子
     */
    final float loadFactor;

    /* ---------------- Public operations --构造函数------------ */

    /**
     * 构造一个空的 HashMap具有指定的初始容量和负载因子。
     *
     * @param  initialCapacity  初始化容量
     * @param  loadFactor       加载因子
     * @throws IllegalArgumentException 如果指定的初始化容量为负数或者加载因子为非正数。
     */
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * 构造一个空的 HashMap ，具有指定的初始容量和默认负载系数（0.75）。
     *
     * @param  initialCapacity 初始容量
     * @throws IllegalArgumentException 如果初始容量为负。
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * 构造一个空的 HashMap ，默认初始容量（16）和默认负载因子（0.75）。
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

    /**
     * 使用指定Map m构造新的HashMap。
     * 使用指定的初始化容量（16）和默认加载因子DEFAULT_LOAD_FACTOR（0.75）
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException 如果指定的map是null
     */
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    /**
     * Map.putAll and Map constructor的实现需要的方法。
     * 将m的键值对插入本map中
     *
     * @param m the map
     * @param evict 初始化map时使用false，否则使用true
     */
    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
        int s = m.size();
        //如果参数map不为空
        if (s > 0) {
            //如果table没有初始化
            if (table == null) { // pre-size
                //前面讲到，initial capacity*load factor就是当前hashMap允许的最大元素数目。
                // 那么不难理解，s/loadFactor+1即为应该初始化的容量。
                float ft = ((float)s / loadFactor) + 1.0F;
                //如果ft小于最大容量MAXIMUM_CAPACITY，则容量为ft，否则容量为最大容量MAXIMUM_CAPACITY
                int t = ((ft < (float)MAXIMUM_CAPACITY) ? (int)ft : MAXIMUM_CAPACITY);
                //如果容量大于临界值
                if (t > threshold)
                    //根据容量初始化临界值
                {
                    threshold = tableSizeFor(t);
                }
            }
            //table已经初始化，并且map的大小大于临界值
            else if (s > threshold)
                //扩容处理
            {
                resize();
            }
            //将map中所有键值对添加到hashMap中
            for (Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                //putVal方法的实现在下面
                putVal(hash(key), key, value, false, evict);
            }
        }
    }

    /**
     * 返回map中键值对映射的个数
     *
     * @return map中键值对映射的个数
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * 如果map中没有键值对映射，返回true
     *
     * @return 如果map中没有键值对映射，返回true
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回到指定键所映射的值，或null如果此映射包含该键的映射。
     * 更正式地，如果该映射包含从键k到值v ，使得(key==null ? k==null : key.equals(k)) ，则该方法返回v ;
     * 否则返回null 。 （最多可以有一个这样的映射。）
     *
     * 返回值null并不一定表示该映射不包含该键的映射; Map也可能明确地将键映射到null 。
     * 可以使用containsKey操作来区分这两种情况。
     *
     * 从源码中可以看到，get(E e)可以分为三个步骤：
     * 1.通过hash(Object key)方法计算key的哈希值hash。
     * 2.通过getNode( int hash, Object key)方法获取node。
     * 3.如果node为null，返回null，否则返回node.value。
     */
    @Override
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    /**
     * 根据key的哈希值和key获取对应的节点
     *
     * @param hash 指定参数key的哈希值
     * @param key 指定参数key
     * @return 返回node，如果没有则返回null
     */
    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        //如果哈希表不为空，而且key对应的桶上不为空
        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
            //如果桶中的第一个节点就和指定参数hash和key匹配上了
            //总是检查第一个节点
            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k))))
                //返回桶中的第一个节点
            {
                return first;
            }
            //如果桶中的第一个节点没有匹配上，而且有后续节点
            if ((e = first.next) != null) {
                //如果当前的桶采用红黑树，则调用红黑树的get方法去获取节点
                if (first instanceof TreeNode) {
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                }
                //如果当前的桶不采用红黑树，即桶中节点结构为链式结构
                do {
                    //遍历链表，直到key匹配
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }
        //如果哈希表为空，或者没有找到节点，返回null
        return null;
    }

    /**
     * 如果map中含有key为指定参数key的键值对，返回true
     *
     * @param   key   指定参数key
     * @return 如果map中含有key为指定参数key的键值对，返回true
     */
    @Override
    public boolean containsKey(Object key) {
        return getNode(hash(key), key) != null;
    }


    /**
     * 将指定参数key和指定参数value插入map中，
     * 如果key已经存在，那就替换key对应的value
     *
     * @param key 指定key
     * @param value 指定value
     * @return 如果value被替换，则返回旧的value，否则返回null。当然，可能key对应的value就是null。
     */
    @Override
    public V put(K key, V value) {
        //putVal方法的实现就在下面
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * Map.put和其他相关方法的实现需要的方法
     *
     * putVal方法可以分为下面的几个步骤：
     *
     * 1. 如果哈希表为空，调用resize()创建一个哈希表。
     * 2. 如果指定参数hash在表中没有对应的桶，即为没有碰撞，直接将键值对插入到哈希表中即可。
     * 3. 如果有碰撞，遍历桶，找到key映射的节点
     *     1. 桶中的第一个节点就匹配了，将桶中的第一个节点记录起来。
     *     2. 如果桶中的第一个节点没有匹配，且桶中结构为红黑树，则调用红黑树对应的方法插入键值对。
     *     3. 如果不是红黑树，那么就肯定是链表。遍历链表，如果找到了key映射的节点，就记录这个节点，退出循环。
     *        如果没有找到，在链表尾部插入节点。
     *        插入后，如果链的长度大于TREEIFY_THRESHOLD这个临界值，则使用treeifyBin方法把链表转为红黑树。
     * 4.如果找到了key映射的节点，且节点不为null
     *     1.记录节点的vlaue。
     *     2.如果参数onlyIfAbsent为false，或者oldValue为null，替换value，否则不替换。
     *     3.返回记录下来的节点的value。
     * 5. 如果没有找到key映射的节点（2、3步中讲了，这种情况会插入到hashMap中），插入节点后size会加1，
     *    这时要检查size是否大于临界值threshold，如果大于会使用resize方法进行扩容。
     *
     * @param hash 指定参数key的哈希值
     * @param key 指定参数key
     * @param value 指定参数value
     * @param onlyIfAbsent 如果为true，即使指定参数key在map中已经存在，也不会替换value
     * @param evict 如果为false，数组table在创建模式中
     * @return 如果value被替换，则返回旧的value，否则返回null。当然，可能key对应的value就是null。
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //如果哈希表为空，调用resize()创建一个哈希表，并用变量n记录哈希表长度
        // table是否为空或者length等于0, 如果是则调用resize方法进行初始化
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        //如果指定参数hash在表中没有对应的桶，即为没有碰撞
        if ((p = tab[i = (n - 1) & hash]) == null)
            // 如果该下标下没有节点，则直接新建一个Node放在该位置。
        {
            tab[i] = newNode(hash, key, value, null);
        } else { // 如果哈希表当前位置上已经有节点的话，说明有hash冲突
            Node<K,V> e; K k;
            //如果碰撞了，且桶中的第一个节点就匹配了,hash和key都相等，则该Key已存在，获得该节点。
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                //将桶中的第一个节点记录起来
            {
                e = p;
            }
                //如果桶中的第一个节点没有匹配上，且桶内为红黑树结构，则调用红黑树对应的方法插入键值对
            else if (p instanceof TreeNode) {
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            }
                //不是红黑树结构，那么就肯定是链式结构
            else {
                //遍历链式结构
                for (int binCount = 0; ; ++binCount) { // 遍历此链表, binCount用于统计节点数
                    //如果到了链表尾部
                    if ((e = p.next) == null) {// p.next为空代表不存在目标节点则新增一个节点插入链表尾部
                        //在链表尾部插入键值对
                        p.next = newNode(hash, key, value, null);
                        //如果链的长度大于TREEIFY_THRESHOLD这个临界值，则把链变为红黑树
                        // 计算节点是否超过8个, 减一是因为循环是从p节点的下一个节点开始的
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        {
                            treeifyBin(tab, hash);
                        }
                        //跳出循环
                        break;
                    }
                    //如果找到了重复的key，判断链表中结点的key值与插入的元素的key值是否相等，如果相等，跳出循环
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        break;
                    }
                    //用于遍历桶中的链表，与前面的e = p.next组合，可以遍历链表
                    p = e;
                }
            }
            //如果key映射的节点不为null
            if (e != null) { // existing mapping for key
                //记录节点的vlaue
                V oldValue = e.value;
                //如果onlyIfAbsent为false，或者oldValue为null
                //如果找到了节点，说明关键字相同，进行覆盖操作，直接返回旧的关键字的值
                if (!onlyIfAbsent || oldValue == null)
                    //替换value
                {
                    e.value = value;
                }
                //访问后回调
                afterNodeAccess(e);
                //返回节点的旧值
                return oldValue;
            }
        }
        //结构型修改次数+1
        ++modCount;
        // 如果目前键值对个数已经超过阀值，重新构建
        if (++size > threshold) // 插入节点后超过阈值则进行扩容
        {
            resize();
        }
        //插入后回调
        afterNodeInsertion(evict);
        return null;
    }

    /**
     * 向hashMap对象里不停的添加元素，而HashMap对象内部的数组无法装载更多的元素时，hashMap就需要扩大数组的长度，
     * 以便能装入更多的元素。当然数组是无法自动扩容的，扩容方法使用一个新的数组代替已有的容量小的数组。
     *
     * resize方法非常巧妙，因为每次扩容都是翻倍，与原来计算（n-1）&hash的结果相比，
     * 节点要么就在原来的位置，要么就被分配到“原位置+旧容量”这个位置。
     *
     * 从代码中可以看到，扩容很耗性能。
     * 所以在使用HashMap的时候，先估算map的大小，初始化的时候给一个大致的数值，避免map进行频繁的扩容。
     *
     * 看完代码后，可以将resize的步骤总结为
     * 1. 计算扩容后的容量，临界值。
     * 2. 将hashMap的临界值修改为扩容后的临界值
     * 3. 根据扩容后的容量新建数组，然后将hashMap的table的引用指向新数组。
     * 4. 将旧数组的元素复制到table中。
     *
     * 在put操作时，如果发现目前的bucket占用程度已经超过了Load Factor所希望的比例，
     * 那么就会发生resize。resize的过程，简单的说就是把bucket扩充为2倍，之后重新计算index，把节点再放到新的bucket中。
     * 主要流程为：
     * 1、无节点，不处理；
     * 2、单节点，重新计算index（hash & (newCap - 1)）。
     * 3、多节点，跟单节同样的情况，只是没有重新计算所有的index，而是看看原来的hash值新增的那个bit是1还是0（因为容量扩大了一倍，
     * 因此影响结果的是hash之前没有参与运算的最右侧位值，通过 hash & oldCap 便能得到），是0的话索引没变，是1的话索引变成“原索引+oldCap”
     *
     * @return the table
     */
    final Node<K,V>[] resize() {
        //新建oldTab数组保存扩容前的数组table
        Node<K,V>[] oldTab = table;
        //使用变量oldCap扩容前table的容量
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        //保存扩容前的临界值
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) { // 如果老容量大于0，说明哈希表中已经有数据了，然后进行扩容
            //如果当前容量>=MAXIMUM_CAPACITY
            if (oldCap >= MAXIMUM_CAPACITY) {
                //扩容临界值提高到正无穷
                threshold = Integer.MAX_VALUE;
                //无法进行扩容，返回原来的数组
                return oldTab;
            }
            //如果现在容量的两倍小于MAXIMUM_CAPACITY且现在的容量大于DEFAULT_INITIAL_CAPACITY
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
                //临界值变为原来的2倍
            {
                newThr = oldThr << 1;
            }
        }//如果旧容量 <= 0，而且旧临界值 > 0
        else if (oldThr > 0)
            //数组的新容量设置为老数组扩容的临界值
        {
            newCap = oldThr;
        } else {//如果旧容量 <= 0，且旧临界值 <= 0，新容量扩充为默认初始化容量，新临界值为DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {//在当上面的条件判断中，只有oldThr > 0成立时，newThr == 0
            //ft为临时临界值，下面会确定这个临界值是否合法，如果合法，那就是真正的临界值
            float ft = (float)newCap * loadFactor;
            //当新容量< MAXIMUM_CAPACITY且ft < (float)MAXIMUM_CAPACITY，新的临界值为ft，否则为Integer.MAX_VALUE
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        //将扩容后hashMap的临界值设置为newThr
        threshold = newThr;
        //创建新的table，初始化容量为newCap
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        //修改hashMap的table为新建的newTab
        table = newTab;
        //如果旧table不为空，将旧table中的元素复制到新的table中
        if (oldTab != null) {
            //遍历旧哈希表的每个桶，将旧哈希表中的桶复制到新的哈希表中
            for (int j = 0; j < oldCap; ++j) {// 扩容之后进行rehash操作
                Node<K,V> e;
                //如果旧桶不为null，使用e记录旧桶
                if ((e = oldTab[j]) != null) {  // 无节点，不做处理
                    //将旧桶置为null
                    oldTab[j] = null;
                    //如果旧桶中只有一个node
                    if (e.next == null)  // 单节点，重新计算index
                        //将e也就是oldTab[j]放入newTab中e.hash & (newCap - 1)的位置
                    {
                        newTab[e.hash & (newCap - 1)] = e;
                    }
                        //如果旧桶中的结构为红黑树
                    else if (e instanceof TreeNode)
                        //将树中的node分离
                    {
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    } else { //如果旧桶中的结构为链表。这段没有仔细研究
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        //遍历整个链表中的节点
                        do {
                            next = e.next;
                            //
                            if ((e.hash & oldCap) == 0) {
                                // 第一次 loTail 为空，则 loHead 和 loTail 都指向了e
                                if (loTail == null) {
                                    loHead = e;
                                } else // 然后 loTail 不断向后移动来添加新的e
                                {
                                    loTail.next = e;
                                }
                                loTail = e;
                            }
                            else {// 原索引+oldCap，方法同上
                                if (hiTail == null) {
                                    hiHead = e;
                                } else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        // 原索引放到bucket里
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        // 原索引+oldCap放到bucket里
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    /**
     * 将链表转化为红黑树
     */
    final void treeifyBin(Node<K,V>[] tab, int hash) {
        int n, index; Node<K,V> e;
        //如果桶数组table为空，或者桶数组table的长度小于MIN_TREEIFY_CAPACITY，不符合转化为红黑树的条件
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
            //扩容
        {
            resize();
        } else if ((e = tab[index = (n - 1) & hash]) != null) {//如果符合转化为红黑树的条件，而且hash对应的桶不为null
            TreeNode<K,V> hd = null, tl = null;
            //遍历链表
            do {
                //替换链表node为树node，建立双向链表
                TreeNode<K,V> p = replacementTreeNode(e, null);
                //
                if (tl == null) {
                    hd = p;
                } else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            //遍历链表插入每个节点到红黑树
            if ((tab[index] = hd) != null) {
                hd.treeify(tab);
            }
        }
    }

    /**
     * 将参数map中的所有键值对映射插入到hashMap中，如果有碰撞，则覆盖value。
     * @param m 参数map
     * @throws NullPointerException 如果map为null
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        putMapEntries(m, true);
    }

    /**
     * 从该地图中删除指定键的映射（如果存在）。
     * 从源码中可以看到，remove方法的实现可以分为三个步骤：
     * 1.通过hash(Object key)方法计算key的哈希值。
     * 2.通过removeNode方法实现功能。
     * 3.返回被删除的node的value。
     * @param  key 要从地图中删除其映射的键
     * @return 前一个值与key相关联 ，或null如果没有映射为key 。（A null返回也可以指示以前关联的地图null与key）
     */
    @Override
    public V remove(Object key) {
        Node<K,V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ? null : e.value;
    }

    /**
     * Map.remove和相关方法的实现需要的方法
     * 删除node
     *可以将removeNode方法的步骤总结为
     * 1.如果数组table为空或key映射到的桶为空，返回null。
     * 2.如果key映射到的桶上第一个node的就是要删除的node，记录下来。
     * 3.如果桶内不止一个node，且桶内的结构为红黑树，记录key映射到的node。
     * 4.桶内的结构不为红黑树，那么桶内的结构就肯定为链表，遍历链表，找到key映射到的node，记录下来。
     * 5.如果被记录下来的node不为null，删除node，size-1被删除。
     * 6.返回被删除的node。
     *
     * @param hash key的哈希值
     * @param key 参数key
     * @param value 如果matchValue为true，则value也作为确定被删除的node的条件之一，否则忽略
     * @param matchValue 如果为true，则value也作为确定被删除的node的条件之一
     * @param movable 如果为false，删除node时不会删除其他node
     * @return 返回被删除的node，如果没有node被删除，则返回null（针对红黑树的删除方法）
     */
    final Node<K,V> removeNode(int hash, Object key, Object value,boolean matchValue, boolean movable) {
        Node<K,V>[] tab; Node<K,V> p; int n, index;
        //如果数组table不为空且key映射到的桶不为空
        if ((tab = table) != null && (n = tab.length) > 0 && (p = tab[index = (n - 1) & hash]) != null) {
            //
            Node<K,V> node = null, e; K k; V v;
            //如果桶上第一个node的就是要删除的node
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                //记录桶上第一个node
            {
                node = p;
            } else if ((e = p.next) != null) {//如果桶内不止一个node
                if (p instanceof TreeNode)//如果桶内的结构为红黑树
                    //记录key映射到的node
                {
                    node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
                } else {//如果桶内的结构为链表
                    do {//遍历链表，找到key映射到的node
                        if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                            //记录key映射到的node
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                }
            }
            //如果得到的node不为null且(matchValue为false||node.value和参数value匹配)
            if (node != null && (!matchValue || (v = node.value) == value || (value != null && value.equals(v)))) {
                //如果桶内的结构为红黑树
                if (node instanceof TreeNode)
                    //使用红黑树的删除方法删除node
                {
                    ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
                } else if (node == p)//如果桶的第一个node的就是要删除的node
                    //删除node
                {
                    tab[index] = node.next;
                } else//如果桶内的结构为链表，使用链表删除元素的方式删除node
                {
                    p.next = node.next;
                }
                //结构性修改次数+1
                ++modCount;
                //哈希表大小-1
                --size;
                afterNodeRemoval(node);
                //返回被删除的node
                return node;
            }
        }
        //如果数组table为空或key映射到的桶为空，返回null。
        return null;
    }

    /**
     * 删除map中所有的键值对
     */
    @Override
    public void clear() {
        Node<K,V>[] tab;
        modCount++;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i) {
                tab[i] = null;
            }
        }
    }

    /**
     * 如果hashMap中的键值对有一对或多对的value为参数value，返回true
     *
     * @param value 参数value
     * @return 如果hashMap中的键值对有一对或多对的value为参数value，返回true
     */
    @Override
    public boolean containsValue(Object value) {
        Node<K,V>[] tab; V v;
        //
        if ((tab = table) != null && size > 0) {
            //遍历数组table
            for (int i = 0; i < tab.length; ++i) {
                //遍历桶中的node
                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                    if ((v = e.value) == value || (value != null && value.equals(v))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 返回hashMap中所有key的视图。
     * 改变hashMap会影响到set，反之亦然。
     * 如果当迭代器迭代set时，hashMap被修改(除非是迭代器自己的remove()方法)，迭代器的结果是不确定的。
     * set支持元素的删除，通过Iterator.remove、Set.remove、removeAll、retainAll、clear操作删除hashMap中对应的键值对。不支持add和addAll方法。
     *
     * @return 返回hashMap中所有key的set视图
     */

    @Override
    public Set<K> keySet() {
        Set<K> ks = keySet;
        if (ks == null) {
            ks = new KeySet();
            keySet = ks;
        }
        return ks;
    }

    /**
     * 内部类 KeySet
     */
    final class KeySet extends AbstractSet<K> {
        @Override
        public final int size()                 { return size; }
        @Override
        public final void clear()               { HashMap.this.clear(); }
        @Override
        public final Iterator<K> iterator()     { return new KeyIterator(); }
        @Override
        public final boolean contains(Object o) { return containsKey(o); }
        @Override
        public final boolean remove(Object key) {
            return removeNode(hash(key), key, null, false, true) != null;
        }
        @Override
        public final Spliterator<K> spliterator() {
            return new KeySpliterator<>(HashMap.this, 0, -1, 0, 0);
        }
        @Override
        public final void forEach(Consumer<? super K> action) {
            Node<K,V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            }
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e.key);
                    }
                }
                if (modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /**
     * 返回hashMap中所有value的collection视图
     * 改变hashMap会改变collection，反之亦然。
     * 如果当迭代器迭代collection时，hashMap被修改（除非是迭代器自己的remove()方法），迭代器的结果是不确定的。
     * collection支持元素的删除，通过Iterator.remove、Collection.remove、removeAll、retainAll、
     * clear操作删除hashMap中对应的键值对。不支持add和addAll方法。
     *
     * @return 返回hashMap中所有key的collection视图
     */
    @Override
    public Collection<V> values() {
        Collection<V> vs = values;
        if (vs == null) {
            vs = new Values();
            values = vs;
        }
        return vs;
    }

    /**
     * 内部类Values
     */
    final class Values extends AbstractCollection<V> {
        @Override
        public final int size()                 { return size; }
        @Override
        public final void clear()               { HashMap.this.clear(); }
        @Override
        public final Iterator<V> iterator()     { return new ValueIterator(); }
        @Override
        public final boolean contains(Object o) { return containsValue(o); }
        @Override
        public final Spliterator<V> spliterator() {
            return new ValueSpliterator<>(HashMap.this, 0, -1, 0, 0);
        }
        @Override
        public final void forEach(Consumer<? super V> action) {
            Node<K,V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            }
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e.value);
                    }
                }
                if (modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /**
     * 返回hashMap中所有键值对的set视图
     * 改变hashMap会影响到set，反之亦然。
     * 如果当迭代器迭代set时，hashMap被修改(除非是迭代器自己的remove()方法)，迭代器的结果是不确定的。
     * set支持元素的删除，通过Iterator.remove、Set.remove、removeAll、retainAll、clear操作删除hashMap中对应的键值对。不支持add和addAll方法。
     *
     * @return 返回hashMap中所有键值对的set视图
     */
    @Override
    public Set<Entry<K,V>> entrySet() {
        Set<Entry<K,V>> es;
        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
    }

    /**
     * 内部类EntrySet
     */
    final class EntrySet extends AbstractSet<Entry<K,V>> {
        @Override
        public final int size()                 { return size; }
        @Override
        public final void clear()               { HashMap.this.clear(); }
        @Override
        public final Iterator<Entry<K,V>> iterator() {
            return new EntryIterator();
        }
        @Override
        public final boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Entry<?,?> e = (Entry<?,?>) o;
            Object key = e.getKey();
            Node<K,V> candidate = getNode(hash(key), key);
            return candidate != null && candidate.equals(e);
        }
        @Override
        public final boolean remove(Object o) {
            if (o instanceof Map.Entry) {
                Entry<?,?> e = (Entry<?,?>) o;
                Object key = e.getKey();
                Object value = e.getValue();
                return removeNode(hash(key), key, value, true, true) != null;
            }
            return false;
        }
        @Override
        public final Spliterator<Entry<K,V>> spliterator() {
            return new EntrySpliterator<>(HashMap.this, 0, -1, 0, 0);
        }
        @Override
        public final void forEach(Consumer<? super Entry<K,V>> action) {
            Node<K,V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            }
            if (size > 0 && (tab = table) != null) {
                int mc = modCount;
                for (int i = 0; i < tab.length; ++i) {
                    for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e);
                    }
                }
                if (modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    //JDK8重写的方法
    // Overrides of JDK8 Map extension methods

    /**
     * 通过key映射到对应node，如果没映射到则返回默认值defaultValue
     *
     * @return key映射到对应的node，如果没映射到则返回默认值defaultValue
     */
    @Override
    public V getOrDefault(Object key, V defaultValue) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;
    }

    /**
     * 在hashMap中插入参数key和value组成的键值对，如果key在hashMap中已经存在，不替换value
     *
     * @return 如果key在hashMap中存在，返回旧value
     */
    @Override
    public V putIfAbsent(K key, V value) {
        return putVal(hash(key), key, value, true, true);
    }

    /**
     * 删除hashMap中key为参数key，value为参数value的键值对。如果桶中结构为树，则级联删除
     *
     * @return 删除成功，返回true
     */
    @Override
    public boolean remove(Object key, Object value) {
        return removeNode(hash(key), key, value, true, true) != null;
    }

    /**
     * 使用newValue替换key和oldValue映射到的键值对中的value
     *
     * @return 替换成功，返回true
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        Node<K,V> e; V v;
        if ((e = getNode(hash(key), key)) != null &&
                ((v = e.value) == oldValue || (v != null && v.equals(oldValue)))) {
            e.value = newValue;
            afterNodeAccess(e);
            return true;
        }
        return false;
    }

    /**
     * 使用参数value替换key映射到的键值对中的value
     *
     * @return 替换成功，返回true
     */
    @Override
    public V replace(K key, V value) {
        Node<K,V> e;
        if ((e = getNode(hash(key), key)) != null) {
            V oldValue = e.value;
            e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
        return null;
    }

    /**
     * 如果指定的键尚未与值相关联（或映射到null ），则尝试使用给定的映射函数计算其值，并将其输入到此映射中，除非null 。
     * 如果函数返回null则不记录映射。
     * 如果函数本身引发（未检查）异常，则异常被重新引导，并且不记录映射。
     * 最常见的用法是构造一个用作初始映射值或记忆结果的新对象，如：
     *    map.computeIfAbsent(key, k -> new Value(f(k)));
     * 或者实现一个多值地图， Map<K,Collection<V>> ，每个键支持多个值：
     *    map.computeIfAbsent(key, k -> new HashSet<V>()).add(v);
     * @param key
     * @param mappingFunction
     * @return
     */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        if (mappingFunction == null) {
            throw new NullPointerException();
        }
        int hash = hash(key);
        Node<K,V>[] tab; Node<K,V> first; int n, i;
        int binCount = 0;
        TreeNode<K,V> t = null;
        Node<K,V> old = null;//存储根据key所找到的节点信息
        if (size > threshold || (tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        //根据ke查找节点
        if ((first = tab[i = (n - 1) & hash]) != null) {
            //key节点是 树节点
            if (first instanceof TreeNode) {
                old = (t = (TreeNode<K,V>)first).getTreeNode(hash, key);
            } else {
                //遍历链表，找key节点
                Node<K,V> e = first; K k;
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
            V oldValue;
            //key节点old找到了，并且其value 不是null,则返回其value
            if (old != null && (oldValue = old.value) != null) {
                afterNodeAccess(old);
                return oldValue;
            }
        }
        //映射函数作用key,计算其v = value
        V v = mappingFunction.apply(key);
        //计算新value v 为null,则返回null
        if (v == null) {
            return null;
        } else if (old != null) {
            //计算新value 不为null,并且根据key找到了节点old, 把新value赋给节点old
            old.value = v;
            afterNodeAccess(old);
            return v;
        } else if (t != null)//first节点有值，old节点是null(根据key 没有找到树形节点),新增此节点
        {
            t.putTreeVal(this, tab, hash, key, v);
        } else {
            //first节点没有找到，新增节点
            tab[i] = newNode(hash, key, v, first);
            //新增节点后，是否链表转红黑树
            if (binCount >= TREEIFY_THRESHOLD - 1) {
                treeifyBin(tab, hash);
            }
        }
        ++modCount;
        ++size;
        afterNodeInsertion(true);
        return v;
    }

    /**
     * 如果指定的键的值存在且非空，则尝试计算给定键及其当前映射值的新映射。
     * 如果函数返回null ，则删除映射。
     * 如果函数本身引发（未检查）异常，则异常被重新引导，并且当前映射保持不变。
     *
     * 默认实现等效于执行以下步骤执行这一map ，然后返回当前值或null如果缺席：
     * <pre> {@code
     * if (map.get(key) != null) {
     *     V oldValue = map.get(key);
     *     V newValue = remappingFunction.apply(key, oldValue);
     *     if (newValue != null)
     *         map.put(key, newValue);
     *     else
     *         map.remove(key);
     * }
     * }</pre>
     * @param key
     * @param remappingFunction
     * @return
     */
    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null) {
            throw new NullPointerException();
        }
        Node<K,V> e; V oldValue;
        int hash = hash(key);
        if ((e = getNode(hash, key)) != null && (oldValue = e.value) != null) {
            V v = remappingFunction.apply(key, oldValue);
            if (v != null) {
                e.value = v;
                afterNodeAccess(e);
                return v;
            }
            else {
                removeNode(hash, key, null, false, true);
            }
        }
        return null;
    }

    /**
     * 尝试计算用于指定键和其当前映射的值的映射（或null如果没有当前映射）。
     * 例如，要为值映射创建或附加一个String msg：
     *    map.compute(key, (k, v) -> (v == null) ? msg : v.concat(msg))
     *    （方法merge()通常更容易用于此类目的。）
     * 如果函数返回null ，映射将被删除（如果最初缺少，则保持不存在）。
     * 如果函数本身引发（未检查）异常，则异常被重新引导，并且当前映射保持不变。
     *
     * 默认实现等效于执行以下步骤执行这一map ，然后返回当前值或null如果缺席：
     *
     * <pre> {@code
     * V oldValue = map.get(key);
     * V newValue = remappingFunction.apply(key, oldValue);
     * if (oldValue != null ) {
     *    if (newValue != null)
     *       map.put(key, newValue);
     *    else
     *       map.remove(key);
     * } else {
     *    if (newValue != null)
     *       map.put(key, newValue);
     *    else
     *       return null;
     * }
     * }</pre>
     * @param key
     * @param remappingFunction
     * @return
     */
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null) {
            throw new NullPointerException();
        }
        int hash = hash(key);
        Node<K,V>[] tab; Node<K,V> first; int n, i;
        int binCount = 0;
        TreeNode<K,V> t = null;
        Node<K,V> old = null;
        if (size > threshold || (tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof TreeNode) {
                old = (t = (TreeNode<K,V>)first).getTreeNode(hash, key);
            } else {
                Node<K,V> e = first; K k;
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        V oldValue = (old == null) ? null : old.value;
        V v = remappingFunction.apply(key, oldValue);
        if (old != null) {
            if (v != null) {
                old.value = v;
                afterNodeAccess(old);
            } else {
                removeNode(hash, key, null, false, true);
            }
        } else if (v != null) {
            if (t != null) {
                t.putTreeVal(this, tab, hash, key, v);
            } else {
                tab[i] = newNode(hash, key, v, first);
                if (binCount >= TREEIFY_THRESHOLD - 1) {
                    treeifyBin(tab, hash);
                }
            }
            ++modCount;
            ++size;
            afterNodeInsertion(true);
        }
        return v;
    }

    /**
     * 如果指定的键尚未与值相关联或与null相关联，则将其与给定的非空值相关联。
     * 否则，将关联值替换为给定重映射函数的结果，如果结果为null 。
     * 当组合键的多个映射值时，该方法可能是有用的。
     * 例如，要创建或附加String msg到值映射：
     *    map.merge(key, msg, String::concat)
     *    如果函数返回null ，则删除映射。
     *    如果函数本身引发（未检查）异常，则异常被重新引导，并且当前映射保持不变。
     *
     * 实现要求：
     * 默认实现等效于执行以下步骤执行这一map ，然后返回当前值或null如果缺席：
     * <pre> {@code
     * V oldValue = map.get(key);
     * V newValue = (oldValue == null) ? value : remappingFunction.apply(oldValue, value);
     * if (newValue == null)
     *     map.remove(key);
     * else
     *     map.put(key, newValue);
     * }</pre>
     *
     *  默认实现不会保证此方法的同步或原子属性。提供原子性保证的任何实现都必须覆盖此方法并记录其并发属性。
     *  特别地，子接口ConcurrentMap的所有实现必须记录该函数是否仅在原子上应用，只有当该值不存在时。
     * @param key
     * @param value
     * @param remappingFunction
     * @return
     */
    @Override
    public V merge(K key, V value,
                   BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (remappingFunction == null) {
            throw new NullPointerException();
        }
        int hash = hash(key);
        Node<K,V>[] tab; Node<K,V> first; int n, i;
        int binCount = 0;
        TreeNode<K,V> t = null;
        Node<K,V> old = null;
        if (size > threshold || (tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((first = tab[i = (n - 1) & hash]) != null) {
            if (first instanceof TreeNode) {
                old = (t = (TreeNode<K,V>)first).getTreeNode(hash, key);
            } else {
                Node<K,V> e = first; K k;
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        old = e;
                        break;
                    }
                    ++binCount;
                } while ((e = e.next) != null);
            }
        }
        if (old != null) {
            V v;
            if (old.value != null) {
                v = remappingFunction.apply(old.value, value);
            } else {
                v = value;
            }
            if (v != null) {
                old.value = v;
                afterNodeAccess(old);
            }
            else {
                removeNode(hash, key, null, false, true);
            }
            return v;
        }
        if (value != null) {
            if (t != null) {
                t.putTreeVal(this, tab, hash, key, value);
            } else {
                tab[i] = newNode(hash, key, value, first);
                if (binCount >= TREEIFY_THRESHOLD - 1) {
                    treeifyBin(tab, hash);
                }
            }
            ++modCount;
            ++size;
            afterNodeInsertion(true);
        }
        return value;
    }

    /**
     * 对此映射中的每个条目执行给定的操作，直到所有条目都被处理或操作引发异常。
     * 除非实现类另有指定，否则按照进入设置迭代的顺序执行操作（如果指定了迭代顺序）。
     * 操作引发的异常被转发给调用者。
     * 实现要求：
     * 默认实现相当于，对于这个map ：
     *    for (Map.Entry<K, V> entry : map.entrySet()){
     *         action.accept(entry.getKey(), entry.getValue());
     *    }
     * 默认实现不会保证此方法的同步或原子属性。 提供原子性保证的任何实现都必须覆盖此方法并记录其并发属性。
     * @param action
     */
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Node<K,V>[] tab;
        if (action == null) {
            throw new NullPointerException();
        }
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                    action.accept(e.key, e.value);
                }
            }
            if (modCount != mc) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * 将每个条目的值替换为对该条目调用给定函数的结果，直到所有条目都被处理或该函数抛出异常。
     * 函数抛出的异常被转发给调用者。
     * 实现要求：
     * 默认实现相当于，对于这个map ：
     *    for (Map.Entry<K, V> entry : map.entrySet()) {
     *        entry.setValue(function.apply(entry.getKey(), entry.getValue()));
     *    }
     * 默认实现不会保证此方法的同步或原子属性。
     * 提供原子性保证的任何实现都必须覆盖此方法并记录其并发属性。
     * @param function
     */
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Node<K,V>[] tab;
        if (function == null) {
            throw new NullPointerException();
        }
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                    e.value = function.apply(e.key, e.value);
                }
            }
            if (modCount != mc) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /* ------------------------------------------------------------ */
    // Cloning and serialization

    /**
     * 浅拷贝。
     * clone方法虽然生成了新的HashMap对象，新的HashMap中的table数组虽然也是新生成的，
     * 但是数组中的元素还是引用以前的HashMap中的元素。
     *
     * 这就导致在对HashMap中的元素进行修改的时候，即对数组中元素进行修改，会导致原对象和clone对象都发生改变，
     * 但进行新增或删除就不会影响对方，因为这相当于是对数组做出的改变，clone对象新生成了一个数组。
     *
     * @return hashMap的浅拷贝
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        HashMap<K,V> result;
        try {
            result = (HashMap<K,V>)super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
        result.reinitialize();
        result.putMapEntries(this, false);
        return result;
    }


    // These methods are also used when serializing HashSets
    final float loadFactor() { return loadFactor; }
    final int capacity() {
        return (table != null) ? table.length : (threshold > 0) ? threshold : DEFAULT_INITIAL_CAPACITY;
    }

    /**
     * 序列化hashMap到ObjectOutputStream中
     * 将hashMap的总容量capacity、实际容量size、键值对映射写入到ObjectOutputStream中。键值对映射序列化时是无序的。
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws IOException {
        int buckets = capacity();
        // Write out the threshold, loadfactor, and any hidden stuff
        s.defaultWriteObject();
        //写入总容量
        s.writeInt(buckets);
        //写入实际容量
        s.writeInt(size);
        //写入键值对
        internalWriteEntries(s);
    }

    /**
     * 到ObjectOutputStream中读取hashMap
     * 将hashMap的总容量capacity、实际容量size、键值对映射读取出来
     */
    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
        // 将hashMap的总容量capacity、实际容量size、键值对映射读取出来
        s.defaultReadObject();
        //重置hashMap
        reinitialize();
        //如果加载因子不合法，抛出异常
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new InvalidObjectException("Illegal load factor: " + loadFactor);
        }
        //读出桶的数量，忽略
        s.readInt();                // Read and ignore number of buckets
        //读出实际容量size
        int mappings = s.readInt(); // Read number of mappings (size)
        //如果读出的实际容量size小于0，抛出异常
        if (mappings < 0) {
            throw new InvalidObjectException("Illegal mappings count: " + mappings);
        } else if (mappings > 0) { // (if zero, use defaults)
            //调整hashMap大小
            // Size the table using given load factor only if within range of 0.25...4.0。为什么？
            // 加载因子
            float lf = Math.min(Math.max(0.25f, loadFactor), 4.0f);
            //初步得到的总容量，后续还会处理
            float fc = (float)mappings / lf + 1.0f;
            //处理初步得到的容量，确认最终的总容量
            int cap = ((fc < DEFAULT_INITIAL_CAPACITY) ?
                    DEFAULT_INITIAL_CAPACITY :
                    (fc >= MAXIMUM_CAPACITY) ?
                            MAXIMUM_CAPACITY :
                            tableSizeFor((int)fc));
            //计算临界值，得到初步的临界值
            float ft = (float)cap * lf;
            //得到最终的临界值
            threshold = ((cap < MAXIMUM_CAPACITY && ft < MAXIMUM_CAPACITY) ? (int)ft : Integer.MAX_VALUE);
            @SuppressWarnings({"rawtypes","unchecked"})
            //新建桶数组table
                    Node<K,V>[] tab = (Node<K,V>[])new Node[cap];
            table = tab;

            // 读出key和value，并组成键值对插入hashMap中
            for (int i = 0; i < mappings; i++) {
                @SuppressWarnings("unchecked")
                K key = (K) s.readObject();
                @SuppressWarnings("unchecked")
                V value = (V) s.readObject();
                putVal(hash(key), key, value, false, false);
            }
        }
    }

    /* ------------------------------------------------------------ */
    // iterators

    abstract class HashIterator {
        Node<K,V> next;        // next entry to return
        Node<K,V> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        HashIterator() {
            expectedModCount = modCount;
            Node<K,V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K,V> nextNode() {
            Node<K,V>[] t;
            Node<K,V> e = next;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (e == null) {
                throw new NoSuchElementException();
            }
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove() {
            Node<K,V> p = current;
            if (p == null) {
                throw new IllegalStateException();
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            current = null;
            K key = p.key;
            removeNode(hash(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }

    final class KeyIterator extends HashIterator
            implements Iterator<K> {
        @Override
        public final K next() { return nextNode().key; }
    }

    final class ValueIterator extends HashIterator
            implements Iterator<V> {
        @Override
        public final V next() { return nextNode().value; }
    }

    final class EntryIterator extends HashIterator
            implements Iterator<Entry<K,V>> {
        @Override
        public final Entry<K,V> next() { return nextNode(); }
    }

    /* ------------------------------------------------------------ */
    // spliterators

    static class HashMapSpliterator<K,V> {
        final HashMap<K,V> map;
        Node<K,V> current;          // current node
        int index;                  // current index, modified on advance/split
        int fence;                  // one past last index
        int est;                    // size estimate
        int expectedModCount;       // for comodification checks

        HashMapSpliterator(HashMap<K,V> m, int origin,
                           int fence, int est,
                           int expectedModCount) {
            this.map = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence() { // initialize fence and size on first use
            int hi;
            if ((hi = fence) < 0) {
                HashMap<K,V> m = map;
                est = m.size;
                expectedModCount = m.modCount;
                Node<K,V>[] tab = m.table;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            return hi;
        }

        public final long estimateSize() {
            getFence(); // force init
            return (long) est;
        }
    }

    static final class KeySpliterator<K,V>
            extends HashMapSpliterator<K,V>
            implements Spliterator<K> {
        KeySpliterator(HashMap<K,V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override
        public KeySpliterator<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new KeySpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        @Override
        public void forEachRemaining(Consumer<? super K> action) {
            int i, hi, mc;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap<K,V> m = map;
            Node<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            else {
                mc = expectedModCount;
            }
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K,V> p = current;
                current = null;
                do {
                    if (p == null) {
                        p = tab[i++];
                    } else {
                        action.accept(p.key);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super K> action) {
            int hi;
            if (action == null) {
                throw new NullPointerException();
            }
            Node<K,V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null) {
                        current = tab[index++];
                    } else {
                        K k = current.key;
                        current = current.next;
                        action.accept(k);
                        if (map.modCount != expectedModCount) {
                            throw new ConcurrentModificationException();
                        }
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
                    Spliterator.DISTINCT;
        }
    }

    static final class ValueSpliterator<K,V>
            extends HashMapSpliterator<K,V>
            implements Spliterator<V> {
        ValueSpliterator(HashMap<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override
        public ValueSpliterator<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new ValueSpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        @Override
        public void forEachRemaining(Consumer<? super V> action) {
            int i, hi, mc;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap<K,V> m = map;
            Node<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            else {
                mc = expectedModCount;
            }
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K,V> p = current;
                current = null;
                do {
                    if (p == null) {
                        p = tab[i++];
                    } else {
                        action.accept(p.value);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super V> action) {
            int hi;
            if (action == null) {
                throw new NullPointerException();
            }
            Node<K,V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null) {
                        current = tab[index++];
                    } else {
                        V v = current.value;
                        current = current.next;
                        action.accept(v);
                        if (map.modCount != expectedModCount) {
                            throw new ConcurrentModificationException();
                        }
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
        }
    }

    static final class EntrySpliterator<K,V>
            extends HashMapSpliterator<K,V>
            implements Spliterator<Entry<K,V>> {
        EntrySpliterator(HashMap<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override
        public EntrySpliterator<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid || current != null) ? null :
                    new EntrySpliterator<>(map, lo, index = mid, est >>>= 1,
                            expectedModCount);
        }

        @Override
        public void forEachRemaining(Consumer<? super Entry<K,V>> action) {
            int i, hi, mc;
            if (action == null) {
                throw new NullPointerException();
            }
            HashMap<K,V> m = map;
            Node<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = (tab == null) ? 0 : tab.length;
            }
            else {
                mc = expectedModCount;
            }
            if (tab != null && tab.length >= hi &&
                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
                Node<K,V> p = current;
                current = null;
                do {
                    if (p == null) {
                        p = tab[i++];
                    } else {
                        action.accept(p);
                        p = p.next;
                    }
                } while (p != null || i < hi);
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super Entry<K,V>> action) {
            int hi;
            if (action == null) {
                throw new NullPointerException();
            }
            Node<K,V>[] tab = map.table;
            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null) {
                        current = tab[index++];
                    } else {
                        Node<K,V> e = current;
                        current = current.next;
                        action.accept(e);
                        if (map.modCount != expectedModCount) {
                            throw new ConcurrentModificationException();
                        }
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public int characteristics() {
            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
                    Spliterator.DISTINCT;
        }
    }

    /* ------------------------------------------------------------ */
    // LinkedHashMap support


    /*
     * The following package-protected methods are designed to be
     * overridden by LinkedHashMap, but not by any other subclass.
     * Nearly all other internal methods are also package-protected
     * but are declared final, so can be used by LinkedHashMap, view
     * classes, and HashSet.
     */

    // Create a regular (non-tree) node
    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
        return new Node<>(hash, key, value, next);
    }

    // For conversion from TreeNodes to plain nodes
    Node<K,V> replacementNode(Node<K,V> p, Node<K,V> next) {
        return new Node<>(p.hash, p.key, p.value, next);
    }

    // Create a tree bin node
    TreeNode<K,V> newTreeNode(int hash, K key, V value, Node<K,V> next) {
        return new TreeNode<>(hash, key, value, next);
    }

    // For treeifyBin
    TreeNode<K,V> replacementTreeNode(Node<K,V> p, Node<K,V> next) {
        return new TreeNode<>(p.hash, p.key, p.value, next);
    }

    /**
     * Reset to initial default state.  Called by clone and readObject.
     */
    void reinitialize() {
        table = null;
        entrySet = null;
        keySet = null;
        values = null;
        modCount = 0;
        threshold = 0;
        size = 0;
    }

    // Callbacks to allow LinkedHashMap post-actions
    void afterNodeAccess(Node<K,V> p) { }
    void afterNodeInsertion(boolean evict) { }
    void afterNodeRemoval(Node<K,V> p) { }

    //只会被writeObject调用，确保兼容的顺序
    // 写入hashMap键值对到ObjectOutputStream中
    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException {
        Node<K,V>[] tab;
        if (size > 0 && (tab = table) != null) {
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                    s.writeObject(e.key);
                    s.writeObject(e.value);
                }
            }
        }
    }

    /* ------------------------------------------------------------ */
    // Tree bins

    /**
     * 红黑树
     * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn
     * extends Node) so can be used as extension of either regular or
     * linked node.
     */
    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
        TreeNode<K,V> parent;  // red-black tree links
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolean red;
        TreeNode(int hash, K key, V val, Node<K,V> next) {
            super(hash, key, val, next);
        }

        /**
         * Returns root of tree containing this node.
         */
        final TreeNode<K,V> root() {
            for (TreeNode<K,V> r = this, p;;) {
                if ((p = r.parent) == null) {
                    return r;
                }
                r = p;
            }
        }

        /**
         * Ensures that the given root is the first node of its bin.
         */
        static <K,V> void moveRootToFront(Node<K,V>[] tab, TreeNode<K,V> root) {
            int n;
            if (root != null && tab != null && (n = tab.length) > 0) {
                int index = (n - 1) & root.hash;
                TreeNode<K,V> first = (TreeNode<K,V>)tab[index];
                if (root != first) {
                    Node<K,V> rn;
                    tab[index] = root;
                    TreeNode<K,V> rp = root.prev;
                    if ((rn = root.next) != null) {
                        ((TreeNode<K,V>)rn).prev = rp;
                    }
                    if (rp != null) {
                        rp.next = rn;
                    }
                    if (first != null) {
                        first.prev = root;
                    }
                    root.next = first;
                    root.prev = null;
                }
                assert checkInvariants(root);
            }
        }

        /**
         * 从调用此方法的结点开始查找, 通过hash值和key找到对应的节点
         * 此处是红黑树的遍历, 红黑树是特殊的自平衡二叉查找树
         * 平衡二叉查找树的特点：左节点<根节点<右节点
         */
        final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
            TreeNode<K,V> p = this; // this为调用此方法的节点
            do {
                int ph, dir; K pk;
                TreeNode<K,V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h)  // 传入的hash值小于p节点的hash值, 则往p节点的左边遍历
                {
                    p = pl; // p赋值为p节点的左节点
                } else if (ph < h)    // 传入的hash值大于p节点的hash值, 则往p节点的右边遍历
                {
                    p = pr; // p赋值为p节点的右节点
                }
                    // 传入的hash值和key值等于p节点的hash值和key值,则p节点为目标节点,返回p节点
                else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
                    return p;
                } else if (pl == null)    // p节点的左节点为空则将向右遍历
                {
                    p = pr;
                } else if (pr == null)    // p节点的右节点为空则向左遍历
                {
                    p = pl;
                } else if ((kc != null ||
                        // 如果传入的key(k)所属的类实现了Comparable接口,则将传入的key跟p节点的key比较
                        (kc = comparableClassFor(k)) != null) && // 此行不为空代表k实现了Comparable
                        (dir = compareComparables(kc, k, pk)) != 0)//k<pk则dir<0, k>pk则dir>0
                {
                    p = (dir < 0) ? pl : pr;    // k < pk则向左遍历(p赋值为p的左节点), 否则向右遍历
                }
                    // 代码走到此处, 代表key所属类没有实现Comparable, 直接指定向p的右边遍历
                else if ((q = pr.find(h, k, kc)) != null) {
                    return q;
                } else// 代码走到此处代表上一个向右遍历（pr.find(h, k, kc)）为空, 因此直接向左遍历
                {
                    p = pl;
                }
            } while (p != null);
            return null;
        }

        /**
         * Calls find for root node.
         */
        final TreeNode<K,V> getTreeNode(int h, Object k) {
            return ((parent != null) ? root() : this).find(h, k, null);
        }


        /**
         * 用于不可比较或者hashCode相同时进行比较的方法, 只是一个一致的插入规则，用来维护重定位的等价性。
         */
        static int tieBreakOrder(Object a, Object b) {
            int d;
            if (a == null || b == null || (d = a.getClass().getName().compareTo(b.getClass().getName())) == 0) {
                d = (System.identityHashCode(a) <= System.identityHashCode(b) ? -1 : 1);
            }
            return d;
        }
        /**
         * Forms tree of the nodes linked from this node.
         * @return root of tree
         */
        final void treeify(Node<K,V>[] tab) {
            TreeNode<K,V> root = null;
            for (TreeNode<K,V> x = this, next; x != null; x = next) {
                next = (TreeNode<K,V>)x.next;
                x.left = x.right = null;
                if (root == null) {
                    x.parent = null;
                    x.red = false;
                    root = x;
                }
                else {
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (TreeNode<K,V> p = root;;) {
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hash) > h) {
                            dir = -1;
                        } else if (ph < h) {
                            dir = 1;
                        } else if ((kc == null &&
                                (kc = comparableClassFor(k)) == null) ||
                                (dir = compareComparables(kc, k, pk)) == 0) {
                            dir = tieBreakOrder(k, pk);
                        }

                        TreeNode<K,V> xp = p;
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.parent = xp;
                            if (dir <= 0) {
                                xp.left = x;
                            } else {
                                xp.right = x;
                            }
                            root = balanceInsertion(root, x);
                            break;
                        }
                    }
                }
            }
            moveRootToFront(tab, root);
        }

        /**
         * Returns a list of non-TreeNodes replacing those linked from
         * this node.
         */
        final Node<K,V> untreeify(HashMap<K,V> map) {
            Node<K,V> hd = null, tl = null;
            for (Node<K,V> q = this; q != null; q = q.next) {
                Node<K,V> p = map.replacementNode(q, null);
                if (tl == null) {
                    hd = p;
                } else {
                    tl.next = p;
                }
                tl = p;
            }
            return hd;
        }

        /**
         * Tree version of putVal.
         * 红黑树插入会同时维护原来的链表属性, 即原来的next属性
         *
         * 1.查找当前红黑树的根结点，将根结点赋值给p节点，开始进行查找
         * 2.如果传入的hash值小于p节点的hash值，将dir赋值为-1，代表向p的左边查找树
         * 3.如果传入的hash值大于p节点的hash值， 将dir赋值为1，代表向p的右边查找树
         * 4.如果传入的hash值等于p节点的hash值，并且传入的key值跟p节点的key值相等, 则该p节点即为目标节点，返回p节点
         * 5.如果k所属的类没有实现Comparable接口，或者k和p节点的key使用compareTo方法比较相等：
         *    第一次会从p节点的左节点和右节点分别调用find方法（见上文代码块2）进行查找，如果查找到目标节点则返回；
         *    如果不是第一次或者调用find方法没有找到目标节点，
         *    则调用tieBreakOrder方法（见下文代码块5）比较k和p节点的key值的大小，以决定向树的左节点还是右节点查找
         * 6. 如果dir <= 0则向左节点查找（p赋值为p.left，并进行下一次循环），否则向右节点查找，
         *    如果已经无法继续查找（p赋值后为null），则代表该位置即为x的目标位置，
         *   另外变量xp用来记录查找的最后一个节点，即下文新增的x节点的父节点。
         * 7. 以传入的hash、key、value参数和xp节点的next节点为参数，构建x节点
         *   （注意：xp节点在此处可能是叶子节点、没有左节点的节点、没有右节点的节点三种情况，
         *   即使它是叶子节点，它也可能有next节点，红黑树的结构跟链表的结构是互不影响的，
         *   不会因为某个节点是叶子节点就说它没有next节点，红黑树在进行操作时会同时维护红黑树结构和链表结构，
         *   next属性就是用来维护链表结构的），
         *   根据dir的值决定x决定放在xp节点的左节点还是右节点，将xp的next节点设为x，将x的parent和prev节点设为xp，
         *   如果原xp的next节点（xpn）不为空, 则将该节点的prev节点设置为x节点, 与上面的将x节点的next节点设置为xpn对应。
         * 8. 进行红黑树的插入平衡调整，见文末的解释2
         */
        final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
                                       int h, K k, V v) {
            Class<?> kc = null;
            boolean searched = false;
            // 查找根节点, 索引位置的头节点并不一定为红黑树的根结点
            TreeNode<K,V> root = (parent != null) ? root() : this;
            for (TreeNode<K,V> p = root;;) {    // 将根节点赋值给p, 开始遍历
                int dir, ph; K pk;
                if ((ph = p.hash) > h)  // 如果传入的hash值小于p节点的hash值
                {
                    dir = -1;	// 则将dir赋值为-1, 代表向p的左边查找树
                } else if (ph < h)    // 如果传入的hash值大于p节点的hash值,
                {
                    dir = 1;	// 则将dir赋值为1, 代表向p的右边查找树
                }
                    // 如果传入的hash值和key值等于p节点的hash值和key值, 则p节点即为目标节点, 返回p节点
                else if ((pk = p.key) == k || (k != null && k.equals(pk))) {
                    return p;
                }
                    // 如果k所属的类没有实现Comparable接口 或者 k和p节点的key相等
                else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                        (dir = compareComparables(kc, k, pk)) == 0) {
                    if (!searched) {    // 第一次符合条件, 该方法只有第一次才执行
                        TreeNode<K,V> q, ch;
                        searched = true;
                        // 从p节点的左节点和右节点分别调用find方法进行查找, 如果查找到目标节点则返回
                        if (((ch = p.left) != null &&
                                (q = ch.find(h, k, kc)) != null) ||
                                ((ch = p.right) != null &&
                                        (q = ch.find(h, k, kc)) != null)) {
                            return q;
                        }
                    }
                    // 否则使用定义的一套规则来比较k和p节点的key的大小, 用来决定向左还是向右查找
                    dir = tieBreakOrder(k, pk); // dir<0则代表k<pk，则向p左边查找；反之亦然
                }

                TreeNode<K,V> xp = p;   // xp赋值为x的父节点,中间变量,用于下面给x的父节点赋值
                // dir<=0则向p左边查找,否则向p右边查找,如果为null,则代表该位置即为x的目标位置
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    // 走进来代表已经找到x的位置，只需将x放到该位置即可
                    Node<K,V> xpn = xp.next;    // xp的next节点
                    // 创建新的节点, 其中x的next节点为xpn, 即将x节点插入xp与xpn之间
                    TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
                    if (dir <= 0)   // 如果时dir <= 0, 则代表x节点为xp的左节点
                    {
                        xp.left = x;
                    } else        // 如果时dir> 0, 则代表x节点为xp的右节点
                    {
                        xp.right = x;
                    }
                    xp.next = x;    // 将xp的next节点设置为x
                    x.parent = x.prev = xp; // 将x的parent和prev节点设置为xp
                    // 如果xpn不为空,则将xpn的prev节点设置为x节点,与上文的x节点的next节点对应
                    if (xpn != null) {
                        ((TreeNode<K,V>)xpn).prev = x;
                    }
                    moveRootToFront(tab, balanceInsertion(root, x)); // 进行红黑树的插入平衡调整
                    return null;
                }
            }
        }


        /**
         * Removes the given node, that must be present before this call.
         * This is messier than typical red-black deletion code because we
         * cannot swap the contents of an interior node with a leaf
         * successor that is pinned by "next" pointers that are accessible
         * independently during traversal. So instead we swap the tree
         * linkages. If the current tree appears to have too few nodes,
         * the bin is converted back to a plain bin. (The test triggers
         * somewhere between 2 and 6 nodes, depending on tree structure).
         */
        final void removeTreeNode(HashMap<K,V> map, Node<K,V>[] tab,
                                  boolean movable) {
            int n;
            if (tab == null || (n = tab.length) == 0) {
                return;
            }
            int index = (n - 1) & hash;
            TreeNode<K,V> first = (TreeNode<K,V>)tab[index], root = first, rl;
            TreeNode<K,V> succ = (TreeNode<K,V>)next, pred = prev;
            if (pred == null) {
                tab[index] = first = succ;
            } else {
                pred.next = succ;
            }
            if (succ != null) {
                succ.prev = pred;
            }
            if (first == null) {
                return;
            }
            if (root.parent != null) {
                root = root.root();
            }
            if (root == null || root.right == null ||
                    (rl = root.left) == null || rl.left == null) {
                tab[index] = first.untreeify(map);  // too small
                return;
            }
            TreeNode<K,V> p = this, pl = left, pr = right, replacement;
            if (pl != null && pr != null) {
                TreeNode<K,V> s = pr, sl;
                while ((sl = s.left) != null) // find successor
                {
                    s = sl;
                }
                boolean c = s.red; s.red = p.red; p.red = c; // swap colors
                TreeNode<K,V> sr = s.right;
                TreeNode<K,V> pp = p.parent;
                if (s == pr) { // p was s's direct parent
                    p.parent = s;
                    s.right = p;
                }
                else {
                    TreeNode<K,V> sp = s.parent;
                    if ((p.parent = sp) != null) {
                        if (s == sp.left) {
                            sp.left = p;
                        } else {
                            sp.right = p;
                        }
                    }
                    if ((s.right = pr) != null) {
                        pr.parent = s;
                    }
                }
                p.left = null;
                if ((p.right = sr) != null) {
                    sr.parent = p;
                }
                if ((s.left = pl) != null) {
                    pl.parent = s;
                }
                if ((s.parent = pp) == null) {
                    root = s;
                } else if (p == pp.left) {
                    pp.left = s;
                } else {
                    pp.right = s;
                }
                if (sr != null) {
                    replacement = sr;
                } else {
                    replacement = p;
                }
            }
            else if (pl != null) {
                replacement = pl;
            } else if (pr != null) {
                replacement = pr;
            } else {
                replacement = p;
            }
            if (replacement != p) {
                TreeNode<K,V> pp = replacement.parent = p.parent;
                if (pp == null) {
                    root = replacement;
                } else if (p == pp.left) {
                    pp.left = replacement;
                } else {
                    pp.right = replacement;
                }
                p.left = p.right = p.parent = null;
            }

            TreeNode<K,V> r = p.red ? root : balanceDeletion(root, replacement);

            if (replacement == p) {  // detach
                TreeNode<K,V> pp = p.parent;
                p.parent = null;
                if (pp != null) {
                    if (p == pp.left) {
                        pp.left = null;
                    } else if (p == pp.right) {
                        pp.right = null;
                    }
                }
            }
            if (movable) {
                moveRootToFront(tab, r);
            }
        }

        /**
         * Splits nodes in a tree bin into lower and upper tree bins,
         * or untreeifies if now too small. Called only from resize;
         * see above discussion about split bits and indices.
         *
         * @param map the map
         * @param tab the table for recording bin heads
         * @param index the index of the table being split
         * @param bit the bit of hash to split on
         */
        final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
            TreeNode<K,V> b = this;
            // Relink into lo and hi lists, preserving order
            TreeNode<K,V> loHead = null, loTail = null;
            TreeNode<K,V> hiHead = null, hiTail = null;
            int lc = 0, hc = 0;
            for (TreeNode<K,V> e = b, next; e != null; e = next) {
                next = (TreeNode<K,V>)e.next;
                e.next = null;
                if ((e.hash & bit) == 0) {
                    if ((e.prev = loTail) == null) {
                        loHead = e;
                    } else {
                        loTail.next = e;
                    }
                    loTail = e;
                    ++lc;
                }
                else {
                    if ((e.prev = hiTail) == null) {
                        hiHead = e;
                    } else {
                        hiTail.next = e;
                    }
                    hiTail = e;
                    ++hc;
                }
            }

            if (loHead != null) {
                if (lc <= UNTREEIFY_THRESHOLD) {
                    tab[index] = loHead.untreeify(map);
                } else {
                    tab[index] = loHead;
                    if (hiHead != null) // (else is already treeified)
                    {
                        loHead.treeify(tab);
                    }
                }
            }
            if (hiHead != null) {
                if (hc <= UNTREEIFY_THRESHOLD) {
                    tab[index + bit] = hiHead.untreeify(map);
                } else {
                    tab[index + bit] = hiHead;
                    if (loHead != null) {
                        hiHead.treeify(tab);
                    }
                }
            }
        }

        /* ------------------------------------------------------------ */
        // Red-black tree methods, all adapted from CLR

        static <K,V> TreeNode<K,V> rotateLeft(TreeNode<K,V> root,
                                              TreeNode<K,V> p) {
            TreeNode<K,V> r, pp, rl;
            if (p != null && (r = p.right) != null) {
                if ((rl = p.right = r.left) != null) {
                    rl.parent = p;
                }
                if ((pp = r.parent = p.parent) == null) {
                    (root = r).red = false;
                } else if (pp.left == p) {
                    pp.left = r;
                } else {
                    pp.right = r;
                }
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        static <K,V> TreeNode<K,V> rotateRight(TreeNode<K,V> root,
                                               TreeNode<K,V> p) {
            TreeNode<K,V> l, pp, lr;
            if (p != null && (l = p.left) != null) {
                if ((lr = p.left = l.right) != null) {
                    lr.parent = p;
                }
                if ((pp = l.parent = p.parent) == null) {
                    (root = l).red = false;
                } else if (pp.right == p) {
                    pp.right = l;
                } else {
                    pp.left = l;
                }
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root,
                                                    TreeNode<K,V> x) {
            x.red = true;
            for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                }
                else if (!xp.red || (xpp = xp.parent) == null) {
                    return root;
                }
                if (xp == (xppl = xpp.left)) {
                    if ((xppr = xpp.right) != null && xppr.red) {
                        xppr.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    else {
                        if (x == xp.right) {
                            root = rotateLeft(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp);
                            }
                        }
                    }
                }
                else {
                    if (xppl != null && xppl.red) {
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    else {
                        if (x == xp.left) {
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }

        static <K,V> TreeNode<K,V> balanceDeletion(TreeNode<K,V> root,
                                                   TreeNode<K,V> x) {
            for (TreeNode<K,V> xp, xpl, xpr;;)  {
                if (x == null || x == root) {
                    return root;
                } else if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                }
                else if (x.red) {
                    x.red = false;
                    return root;
                }
                else if ((xpl = xp.left) == x) {
                    if ((xpr = xp.right) != null && xpr.red) {
                        xpr.red = false;
                        xp.red = true;
                        root = rotateLeft(root, xp);
                        xpr = (xp = x.parent) == null ? null : xp.right;
                    }
                    if (xpr == null) {
                        x = xp;
                    } else {
                        TreeNode<K,V> sl = xpr.left, sr = xpr.right;
                        if ((sr == null || !sr.red) &&
                                (sl == null || !sl.red)) {
                            xpr.red = true;
                            x = xp;
                        }
                        else {
                            if (sr == null || !sr.red) {
                                if (sl != null) {
                                    sl.red = false;
                                }
                                xpr.red = true;
                                root = rotateRight(root, xpr);
                                xpr = (xp = x.parent) == null ?
                                        null : xp.right;
                            }
                            if (xpr != null) {
                                xpr.red = (xp == null) ? false : xp.red;
                                if ((sr = xpr.right) != null) {
                                    sr.red = false;
                                }
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateLeft(root, xp);
                            }
                            x = root;
                        }
                    }
                }
                else { // symmetric
                    if (xpl != null && xpl.red) {
                        xpl.red = false;
                        xp.red = true;
                        root = rotateRight(root, xp);
                        xpl = (xp = x.parent) == null ? null : xp.left;
                    }
                    if (xpl == null) {
                        x = xp;
                    } else {
                        TreeNode<K,V> sl = xpl.left, sr = xpl.right;
                        if ((sl == null || !sl.red) &&
                                (sr == null || !sr.red)) {
                            xpl.red = true;
                            x = xp;
                        }
                        else {
                            if (sl == null || !sl.red) {
                                if (sr != null) {
                                    sr.red = false;
                                }
                                xpl.red = true;
                                root = rotateLeft(root, xpl);
                                xpl = (xp = x.parent) == null ?
                                        null : xp.left;
                            }
                            if (xpl != null) {
                                xpl.red = (xp == null) ? false : xp.red;
                                if ((sl = xpl.left) != null) {
                                    sl.red = false;
                                }
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateRight(root, xp);
                            }
                            x = root;
                        }
                    }
                }
            }
        }

        /**
         * Recursive invariant check
         */
        static <K,V> boolean checkInvariants(TreeNode<K,V> t) {
            TreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
                    tb = t.prev, tn = (TreeNode<K,V>)t.next;
            if (tb != null && tb.next != t) {
                return false;
            }
            if (tn != null && tn.prev != t) {
                return false;
            }
            if (tp != null && t != tp.left && t != tp.right) {
                return false;
            }
            if (tl != null && (tl.parent != t || tl.hash > t.hash)) {
                return false;
            }
            if (tr != null && (tr.parent != t || tr.hash < t.hash)) {
                return false;
            }
            if (t.red && tl != null && tl.red && tr != null && tr.red) {
                return false;
            }
            if (tl != null && !checkInvariants(tl)) {
                return false;
            }
            if (tr != null && !checkInvariants(tr)) {
                return false;
            }
            return true;
        }
    }

}
