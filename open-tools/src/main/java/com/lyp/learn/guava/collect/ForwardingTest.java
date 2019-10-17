package com.lyp.learn.guava.collect;

import com.google.common.collect.ForwardingIterator;
import com.google.common.collect.ForwardingList;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Forwarding 装饰器模式
 *
 * Guava为你，也为我们自己提供了若干工具方法，以便让扩展集合的工作变得更简单
 *
 * 针对所有类型的集合接口，Guava都提供了Forwarding抽象类以简化装饰者模式的使用。
 *
 * Forwarding抽象类定义了一个抽象方法：delegate()，你可以覆盖这个方法来返回被装饰对象。所有其他方法都会直接委托给delegate()。
 * 例如说：ForwardingList.get(int)实际上执行了delegate().get(int)。
 *
 * 通过创建ForwardingXXX的子类并实现delegate()方法，可以选择性地覆盖子类的方法来增加装饰功能，而不需要自己委托每个方法——
 * 译者注：因为所有方法都默认委托给delegate()返回的对象，你可以只覆盖需要装饰的方法。
 *
 * 此外，很多集合方法都对应一个”标准方法[standardxxx]”实现，可以用来恢复被装饰对象的默认行为，以提供相同的优点。
 * 比如在扩展AbstractList或JDK中的其他骨架类时，可以使用类似standardAddAll这样的方法。
 *
 *
 * 记住，默认情况下，所有方法都直接转发给委托，因此重写 ForwardingMap.put 将不会改变 ForwardingMap.putAll 的行为。
 * 请注意覆盖必须更改其行为的每个方法，并确保装饰后的集合满足其契约。
 *
 * 通常, 抽象几何框架如AbstractList 提供的大多数方法在Forwarding 装饰器中也作为标准实现提供。
 *
 * 提供特殊视图的接口有时提供这些视图的标准实现
 * 例如, ForwardingMap  提供 tandardKeySet, StandardValues, 与 StandardEntrySet,
 * 其中每个方法都尽可能地将其方法委托给修饰过的映射，否则，它们将留下不能被委托为抽象的方法。
 *
 * 	Interface	Forwarding Decorator
 * 	Collection	ForwardingCollection
 * 	List	    ForwardingList
 * 	Set	        ForwardingSet
 * 	SortedSet	ForwardingSortedSet
 * 	Map	        ForwardingMap
 * 	SortedMap	ForwardingSortedMap
 * 	ConcurrentMap	ForwardingConcurrentMap
 * 	Map.Entry	ForwardingMapEntry
 * 	Queue	    ForwardingQueue
 * 	Iterator	ForwardingIterator
 * 	ListIterator	ForwardingListIterator
 * 	Multiset	ForwardingMultiset
 * 	Multimap	ForwardingMultimap
 * 	ListMultimap	ForwardingListMultimap
 * 	SetMultimap	ForwardingSetMultimap
 */
public class ForwardingTest {

    @Test
    public void testAddLoggingList(){
        List<String> list = new ArrayList<>();
        AddLoggingList<String> addLoggingList = new AddLoggingList<>(list);

        addLoggingList.add("python");
        list.add("aa");
        addLoggingList.add("java");
    }


    @Test
    public void testListWithDefault(){
        List<String> names = new ListWithDefault<>(Arrays.asList("Alice", null, "Bob", "Carol", null), "UNKNOWN");

        for (String name : names) {
            System.out.println(name);
        }

        System.out.println();
        // toString（）方法仍然返回委托的 toString（），它不知道默认值
        System.out.println(names);
    }
}

/**
 * 假设你想装饰一个列表，以便它记录添加到它里面的所有元素。
 * 当然，无论使用哪个方法添加元素add(int, E), add(E), 或者 addAll(Collection)——
 * 我们都希望记录元素，因此我们必须覆盖所有这些方法。
 */
class AddLoggingList<E> extends ForwardingList<E> {
    final List<E> delegate; // backing list

    AddLoggingList(List<E> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<E> delegate() {
        return delegate;
    }
    @Override
    public void add(int index, E elem) {
        myListLog(index, elem);
        super.add(index, elem);
    }

    private void myListLog(int index, E elem) {
        System.out.println("list["+ index +"] add element:" + elem);
    }

    @Override
    public boolean add(E elem) {
        return standardAdd(elem); // 用add(int, E)实现
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return standardAddAll(c); // 用add实现
    }

}

/**
 * null值具有默认值的List
 */
class ListWithDefault<E> extends ForwardingList<E> {
    final E defaultValue;
    final List<E> delegate;

    ListWithDefault(List<E> delegate, E defaultValue) {
        this.delegate = delegate;
        this.defaultValue = defaultValue;
    }
    @Override
    protected List delegate() {
        return delegate;
    }
    @Override
    public E get(int index) {
        E v = super.get(index);
        return (v == null ? defaultValue : v);
    }
    @Override
    public Iterator<E> iterator() {
        final Iterator<E> iter = super.iterator();
        return new ForwardingIterator<E>() {
            @Override protected Iterator<E> delegate() {
                return iter;
            }
            @Override public E next() {
                E v = super.next();
                return (v == null ? defaultValue : v);
            }
        };
    }
}