
package com.lyp.learn.base.collections;

import com.lyp.learn.base.threads.pk01.Object;

import java.util.*;
import java.util.function.Consumer;

/**
 * 双链表实现了List和Deque接口。 实现所有可选列表操作，并允许所有元素（包括null ）。
 * 所有的操作都能像双向列表一样预期。 索引到列表中的操作将从开始或结束遍历列表，以更接近指定的索引为准。
 *
 * 请注意，此实现不同步。 如果多个线程同时访问链接列表，并且至少有一个线程在结构上修改列表，则必须在外部进行同步。
 * （结构修改是添加或删除一个或多个元素的任何操作;仅设置元素的值不是结构修改。）
 * 这通常通过在自然封装列表的对象上进行同步来实现。
 * 如果没有这样的对象存在，列表应该使用 Collections.synchronizedList 方法“包装”。
 *
 * 这最好在创建时完成，以防止意外的不同步访问列表：
 * List list = Collections.synchronizedList(new LinkedList(...));
 *
 * 这个类的iterator和listIterator方法返回的迭代器是故障快速的 ：
 * 如果列表在迭代器创建之后的任何时间被结构化地修改，除了通过迭代器自己的remove或add方法之外，
 * 迭代器将会抛出一个ConcurrentModificationException 。
 * 因此，面对并发修改，迭代器将快速而干净地失败，而不是在未来未确定的时间冒着任意的非确定性行为。
 *
 * 请注意，迭代器的故障快速行为无法保证，因为一般来说，在不同步并发修改的情况下，无法做出任何硬性保证。
 * 失败快速迭代器尽力投入ConcurrentModificationException 。
 * 因此，编写依赖于此异常的程序的正确性将是错误的：迭代器的故障快速行为应仅用于检测错误。
 */

public class LinkedList<E>
        extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    //定义了三个成员变量，链表元素个数的size，指向第一个元素的first，指向最后一个元素的last

    //队列中元素的个数
    transient int size = 0;

    /**
     * 永远指向链表中的第一个节点
     * Invariant: (first == null && last == null) ||
     *            (first.prev == null && first.item != null)
     */
    transient Node<E> first;

    /**
     * 永远指向链表中的最后一个节点
     * Invariant: (first == null && last == null) ||
     *            (last.next == null && last.item != null)
     */
    transient Node<E> last;

    //构造方法有两个，一个是构造一个空的链表，一个是根据已有的集合构造新的链表
    /**
     * 初始化空队列
     */
    public LinkedList() {
    }

    /**
     * 构造一个包含指定集合的元素的列表，按照它们由集合的迭代器返回的顺序。
     *
     * @param  c 要将元素放入此列表的集合
     * @throws NullPointerException 如果指定的集合为空
     */
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    /**
     * 在首位置插入节点
     * ---------第一次添加------
     * first 为 null
     * f 为 null （先把首节点first存储下来）
     * newNode : 以新元素e, 以 first 为下一个节点构造
     * first : 存储新节点
     * f 为null ,处理 尾节点last
     * size 加 1，modCount 加 1
     *
     * ------------第二次添加----
     * first 有值
     * f 保存first的引用
     * newNode : 以新元素e, 以 first 为下一个节点构造
     * first : 存储新节点
     * f 不为null, f（旧首节点）的前驱设置为新节点
     * size 加 1，modCount 加 1
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 向链表 尾部最后一个位置 添加一个元素
     * --------第一次添加------------
     * last 为 null
     * l 为 null
     * newNode :  null  元素e  null ,以链表最后一个元素last作为新节点的前一个节点，以null作为后一个节点
     * last节点存放新节点(上次的last节点已经存到到新节点的前一个节点上了)
     * l 为 null, 首节点first存放新节点 （此时，first,last都指向第一个元素节点）
     * size 加1，modCount加1
     *
     * -------第二次添加----------
     * last 节点不为null，是链表的最后一个元素
     * l 存储last节点值
     * newNode : last 元素e  null,
     * last节点存放新节点(上次的last节点已经存到到新节点的前一个节点上了)
     * l 不为null,
     * l 的下一个元素存储新节点
     * size 加1，modCount加1
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 在指定非空元素之前添加节点元素
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        //pred : 指定节点succ 的前驱
        final Node<E> pred = succ.prev;
        //nodeNode : 以 前驱pred, e ,后继succ 构造新节点元素
        final Node<E> newNode = new Node<>(pred, e, succ);
        //指定节点succ 的前驱为新节点
        succ.prev = newNode;
        if (pred == null)
            //在第一个元素之前插入 : 首节点重新指向新节点
        {
            first = newNode;
        } else
            //指定元素的前驱 : 指向新的后继节点
        {
            pred.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 移除链表的第一个元素
     * 返回移除节点的元素内容
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        //first : 首节点重新指定
        first = next;
        if (next == null)
            //只有一个节点删除时(next节点为null)
            //处理尾节点
        {
            last = null;
        } else
            //设置新的首节点前驱为null
        {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * 移除最后一个节点元素
     */
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        //last:尾节点重新指定
        last = prev;
        if (prev == null)
            //只有一个节点删除时(prev节点为null)
            //重新指定首节点
        {
            first = null;
        } else
            //设置新的尾节点的后继为null
        {
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * 移除非空节点
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            //删除元素为第一个元素
            //重置首节点
            first = next;
        } else {
            //删除节点的前驱指向删除节点的后继
            prev.next = next;
            //删除节点的前驱置为null
            x.prev = null;
        }

        if (next == null) {
            //删除节点为尾节点
            //重置尾节点
            last = prev;
        } else {
            //删除节点的后继指向删除节点前驱
            next.prev = prev;
            //删除节点的后继置为null
            x.next = null;
        }

        //节点元素置为null
        x.item = null;
        //长度减1
        size--;
        //结构修改变量加1
        modCount++;
        return element;
    }

    /**
     * 获取首节点元素
     *@throws NoSuchElementException 当列表为空时
     */
    @Override
    public E getFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    /**
     * 返回尾节点的元素值
     * @throws NoSuchElementException 当列表为空时
     */
    @Override
    public E getLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    /**
     * 删除首节点，并返回首节点元素值
     * @throws NoSuchElementException 当列表为空时
     */
    @Override
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    /**
     * 删除尾节点，并返回尾节点的元素值
     *
     * @throws NoSuchElementException 当列表为空时
     */
    @Override
    public E removeLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }

    /**
     * 在列表开始位置添加指定元素
     */
    @Override
    public void addFirst(E e) {
        linkFirst(e);
    }

    /**
     * 在列表尾部添加指定元素
     */
    @Override
    public void addLast(E e) {
        linkLast(e);
    }

    /**
     * 如果此列表包含指定的元素，则返回true 。
     * 更正式地说，返回true当且仅当此列表包含至少一个元素e这样(o==null ? e==null : o.equals(e))。
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * 返回此列表中的元素数。
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 将指定的元素追加到此列表的末尾。
     * 此方法相当于addLast(E)
     */
    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 从列表中删除指定元素的第一个出现（如果存在）。
     * 如果此列表不包含该元素，则它将保持不变。
     * 更正式地，删除具有最低索引i的元素，使得(o==null ? get(i)==null : o.equals(get(i))) （如果存在这样的元素）。
     * 如果此列表包含指定的元素（或等效地，如果此列表作为调用的结果而更改），则返回true 。
     */
    @Override
    public boolean remove(Object o) {
        //删除元素o为null, 使用 == 比较，删除元素
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            //删除元素o不为null,使用equals比较，删除元素
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 按照指定集合的迭代器返回的顺序将指定集合中的所有元素追加到此列表的末尾。
     *如果在操作进行中修改了指定的集合，则此操作的行为是未定义的。
     * （注意，如果指定的集合是此列表，并且它是非空的，则会发生这种情况。）
     *
     * @param c 包含要添加到此列表的元素的集合
     * @return {@code true} 如果此列表因调用而更改
     * @throws NullPointerException 如果指定的集合为空
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /**
     * 将指定集合中的所有元素插入到此列表中，从指定的位置开始。
     * 将当前位于该位置（如果有的话）的元素和随后的任何元素移动到右边（增加其索引）。
     * 新元素将按照指定集合的迭代器返回的顺序显示在列表中。
     *
     * @param index 从中指定集合插入第一个元素的索引
     * @param c 包含要添加到此列表的元素的集合
     * @return {@code true} 如果此列表因调用而更改
     * @throws IndexOutOfBoundsException 如果索引超出范围（ index < 0 || index > size() ）
     * @throws NullPointerException 如果指定的集合为空
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        //校验下标是否越界
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }

        Node<E> pred, succ;
        if (index == size) {
            //从列表末尾添加元素
            //succ : 为null,表示从尾部位置添加节点元素
            succ = null;
            //pred : 指向尾节点
            pred = last;
        } else {
            //从指定位置(除尾部位置之外)添加元素
            //succ : index位置上的节点元素
            succ = node(index);
            //pred : index位置上的前一个节点元素
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                //向空列表中添加节点元素
                //处理首节点
            {
                first = newNode;
            } else
                //向pred指定好的位置添加节点元素
            {
                pred.next = newNode;
            }
            //始终指向最后添加的那个新节点位置
            pred = newNode;
        }

        if (succ == null) {
            //从尾部位置添加节点元素
            //处理last指向最后一个节点
            last = pred;
        } else {
            //从指定位置(除尾部位置之外)添加元素
            //链接源列表插入点之后的剩余节点元素
            pred.next = succ;
            //源列表插入点之后的首元素(即index在源列表中指定位置的元素)指定一个新的前一个元素节点
            succ.prev = pred;
        }

        //列表长度增加 添加集合中的元素个数
        size += numNew;
        modCount++;
        return true;
    }

    /**
     * 从列表中删除所有元素。 此调用返回后，列表将为空。
     */
    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    /**
     * 返回此列表中的指定的元素值
     */
    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 用指定的元素替换此列表中指定位置的元素。
     *
     * @param index 要替换的元素的索引
     * @param element 要存储在指定位置的元素
     * @return  指定位置之前的元素值
     * @throws IndexOutOfBoundsException 如果指数超出范围（ index < 0 || index >= size() ）
     */
    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    /**
     * 在此列表中的指定位置插入指定的元素。
     * 将当前位于该位置的元素（如果有）和任何后续元素（向其索引添加一个）移动。
     *
     * @param index 要在其中插入指定元素的索引
     * @param element 要插入的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围（ index < 0 || index > size() ）
     */
    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    /**
     * 删除该列表中指定位置的元素。
     * 将任何后续元素移动到左侧（从其索引中减去一个元素）。 返回从列表中删除的元素。
     */
    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * 校验参数是否是存在元素的下标
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 为迭代器iterator 或者 添加操作
     * 校验下标index是否合法
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 构造下标越界绑定异常 IndexOutOfBoundsException 的详细信息
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    //检查元素下标
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    //校验下标是否合法
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     * 返回指定索引处的非空元素
     * index 在 size 的前一半: 从首节点顺序遍历到index节点，然后返回index节点上元素
     * index 在 size 的后一半: 从尾节点逆序遍历到index节点，然后返回index节点上元素
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * 返回此列表中指定元素的第一次出现的索引，
     * 如果此列表不包含元素，则返回-1。
     *
     * 更正式地，返回最低指数i ，使(o==null ? get(i)==null : o.equals(get(i))) ，或-1如果没有这样的指数。
     *
     * @param o 要搜索的元素
     */
    @Override
    public int indexOf(Object o) {
        int index = 0;
        //当元素o 为 null时，使用 == 判断，index自增
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            //当元素o不为null时，使用equals判断，index自增
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * 返回此列表中指定元素的最后一次出现的索引，如果此列表不包含元素，则返回-1。
     * 更正式地，返回最高指数i ，如(o==null ? get(i)==null : o.equals(get(i))) ，或-1如果没有这样的指数。
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        //元素o为null时，倒序遍历，使用==判断
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null) {
                    return index;
                }
            }
        } else {
            //元素o不为null时，倒序遍历，使用equals判断
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item)) {
                    return index;
                }
            }
        }
        return -1;
    }

    // 队列操作

    /**
     * 返回但不删除此列表的头（第一个元素）。
     * @return 该列表的头，或 null如果此列表为空
     */
    @Override
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 检索但不删除此列表的头（第一个元素）
     */
    @Override
    public E element() {
        return getFirst();
    }

    /**
     * 返回并删除此列表的头（第一个元素）。
     */
    @Override
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * 返回并删除此列表的头（第一个元素）。
     */
    @Override
    public E remove() {
        return removeFirst();
    }

    /**
     * 将指定的元素添加为此列表的尾部（最后一个元素）。
     */
    @Override
    public boolean offer(E e) {
        return add(e);
    }

    // 双端队列操作
    /**
     * 在此列表的开始位置插入指定的元素。
     */
    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    /**
     * 在列表尾部添加指定元素
     */
    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    /**
     * 检索但不删除此列表的第一个元素，如果此列表为空，则返回 null 。
     */
    @Override
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 检索但不删除此列表的最后一个元素，如果此列表为空，则返回 null 。
     */
    @Override
    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    /**
     * 返回并删除此列表的第一个元素，如果此列表为空，则返回 null 。
     */
    @Override
    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * 返回并删除此列表的最后一个元素，如果此列表为空，则返回 null 。
     */
    @Override
    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    /**
     * 栈操作，压入元素
     * 将元素推送到由此列表表示的堆栈上。 换句话说，在该列表的前面插入元素。
     * 此方法相当于addFirst(E)
     *
     */
    @Override
    public void push(E e) {
        addFirst(e);
    }

    /**
     * 栈操作，弹出元素
     * 从此列表表示的堆栈中弹出一个元素。 换句话说，删除并返回此列表的第一个元素。
     * 此方法相当于removeFirst()
     */
    @Override
    public E pop() {
        return removeFirst();
    }

    /**
     * 删除此列表中指定元素的第一个出现（从头到尾遍历列表时）。
     * 如果列表不包含该元素，则它不会更改。
     */
    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    /**
     * 删除此列表中指定元素的最后一次出现（从尾到头遍历列表时）。
     * 如果列表不包含该元素，则它不会更改。
     */
    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从列表中的指定位置开始，返回此列表中元素的列表迭代器（按适当的顺序）。
     * 服从的总承包List.listIterator(int) 。
     *
     * list-iterator是fail-fast ：
     * 如果列表在创建迭代器之后的任何时间被结构化地修改，
     * 除了通过list-iterator自己的remove或add方法之外，
     * list-iterator将以任何方式抛出ConcurrentModificationException 。
     * 因此，面对并发修改，迭代器将快速而干净地失败，而不是在未来未确定的时间冒着任意的非确定性行为。

     *
     * @param index 要从list-iterator返回的第一个元素的索引（通过调用 next ）
     * @return 一个ListIterator的列表中的元素（按顺序），从列表中的指定位置开始
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            checkForComodification();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            checkForComodification();
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
            expectedModCount++;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            checkForComodification();
            lastReturned.item = e;
        }

        @Override
        public void add(E e) {
            checkForComodification();
            lastReturned = null;
            if (next == null) {
                linkLast(e);
            } else {
                linkBefore(e, next);
            }
            nextIndex++;
            expectedModCount++;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.item);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    //内部类，定义链表上的一个节点对象
    //每个节点有三部分组成，中间的是节点自身的值，和指向链表前一个节点的指针prev,以及指向链表后一个节点的指针next
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * 以相反的顺序返回此deque中的元素的迭代器。
     * 元素将从最后（尾）到第一（头）的顺序返回。
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    /**
     * 通过适配 ListItr.previous 产生逆序的iterators
     */
    private class DescendingIterator implements Iterator<E> {
        private final ListItr itr = new ListItr(size());
        @Override
        public boolean hasNext() {
            return itr.hasPrevious();
        }
        @Override
        public E next() {
            return itr.previous();
        }
        @Override
        public void remove() {
            itr.remove();
        }
    }

    @SuppressWarnings("unchecked")
    private LinkedList<E> superClone() {
        try {
            return (LinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     * 返回此LinkedList的浅拷贝。
     * （元素本身不被克隆。）
     */
    @Override
    public Object clone() {
        LinkedList<E> clone = superClone();

        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initialize clone with our elements
        for (Node<E> x = first; x != null; x = x.next) {
            clone.add(x.item);
        }

        return clone;
    }

    /**
     * 以正确的顺序（从第一个到最后一个元素）返回一个包含此列表中所有元素的数组。
     * 返回的数组将是“安全的”，因为该列表不保留对它的引用。（换句话说，这个方法必须分配一个新的数组）。
     *
     * 因此，调用者可以自由地修改返回的数组。
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    /**
     * 以正确的顺序返回一个包含此列表中所有元素的数组（从第一个到最后一个元素）;
     * 返回的数组的运行时类型是指定数组的运行时类型。
     * 如果列表适合指定的数组，则返回其中。 否则，将为指定数组的运行时类型和此列表的大小分配一个新数组。
     *
     * 如果列表适用于指定的数组，其余空间（即数组的列表数量多于此元素），则紧跟在列表末尾的数组中的元素将设置为null 。
     * （这仅在调用者知道列表不包含任何空元素的情况下才能确定列表的长度。）
     *
     * 像toArray()方法一样，此方法充当基于阵列和基于集合的API之间的桥梁。
     * 此外，该方法允许精确地控制输出阵列的运行时类型，并且在某些情况下可以用于节省分配成本。

     * 假设x是一个已知只包含字符串的列表。 下面的代码可以被用来将该列表转储到一个新分配的阵列String ：
     * String[] y = x.toArray(new String[0]);
     * 请注意， toArray(new Object[0])的功能与toArray() 。
     *
     * @param a 要存储列表的元素的数组，如果它够大; 否则，为此目的分配相同运行时类型的新数组
     * @return 一个包含列表元素的数组
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private static final long serialVersionUID = 876323262645176354L;

    /**
     * 序列化
     * 保存LinkedList 实例的状态
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out size
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (Node<E> x = first; x != null; x = x.next) {
            s.writeObject(x.item);
        }
    }

    /**
     * 反序列化
     * 复原LinkedList 实例的状态
     */
    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Read in size
        int size = s.readInt();

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++) {
            linkLast((E)s.readObject());
        }
    }

    /**

     */
    @Override
    public Spliterator<E> spliterator() {
        return new LLSpliterator<E>(this, -1, 0);
    }

    /** A customized variant of Spliterators.IteratorSpliterator */
    static final class LLSpliterator<E> implements Spliterator<E> {
        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        final LinkedList<E> list; // null OK unless traversed
        Node<E> current;      // current node; null until initialized
        int est;              // size estimate; -1 until first needed
        int expectedModCount; // initialized when est set
        int batch;            // batch size for splits

        LLSpliterator(LinkedList<E> list, int est, int expectedModCount) {
            this.list = list;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getEst() {
            int s; // force initialization
            final LinkedList<E> lst;
            if ((s = est) < 0) {
                if ((lst = list) == null) {
                    s = est = 0;
                } else {
                    expectedModCount = lst.modCount;
                    current = lst.first;
                    s = est = lst.size;
                }
            }
            return s;
        }

        @Override
        public long estimateSize() { return (long) getEst(); }

        @Override
        public Spliterator<E> trySplit() {
            Node<E> p;
            int s = getEst();
            if (s > 1 && (p = current) != null) {
                int n = batch + BATCH_UNIT;
                if (n > s) {
                    n = s;
                }
                if (n > MAX_BATCH) {
                    n = MAX_BATCH;
                }
                Object[] a = new Object[n];
                int j = 0;
                do { a[j++] = p.item; } while ((p = p.next) != null && j < n);
                current = p;
                batch = j;
                est = s - j;
                return Spliterators.spliterator(a, 0, j, Spliterator.ORDERED);
            }
            return null;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Node<E> p; int n;
            if (action == null) {
                throw new NullPointerException();
            }
            if ((n = getEst()) > 0 && (p = current) != null) {
                current = null;
                est = 0;
                do {
                    E e = p.item;
                    p = p.next;
                    action.accept(e);
                } while (p != null && --n > 0);
            }
            if (list.modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            Node<E> p;
            if (action == null) {
                throw new NullPointerException();
            }
            if (getEst() > 0 && (p = current) != null) {
                --est;
                E e = p.item;
                current = p.next;
                action.accept(e);
                if (list.modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }

}
