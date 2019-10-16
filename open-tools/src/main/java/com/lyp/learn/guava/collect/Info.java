package com.lyp.learn.guava.collect;


/**
 * 适用于所有集合的静态方法。这是Guava最流行和成熟的部分之一。
 * 我们用相对直观的方式把工具类与特定集合接口的对应关系归纳如下：
 *
 *   集合接口	    属于JDK还是Guava	     对应的Guava工具类
 *   Collection	         JDK	           Collections2：不要和java.util.Collections混淆
 *     List	             JDK	            Lists
 *     Set	             JDK	            Sets
 *     SortedSet	     JDK	            Sets
 *     Map	             JDK	            Maps
 *     SortedMap	     JDK	            Maps
 *     Queue	         JDK	            Queues
 *     Multiset	         Guava	            Multisets
 *     Multimap	         Guava	            Multimaps
 *     BiMap	         Guava	            Maps
 *     Table	         Guava	            Tables
 *
 * 静态工厂方法
 *   在JDK 7之前，构造新的范型集合时要讨厌地重复声明范型：
 *        List<TypeThatsTooLongForItsOwnGood> list = new ArrayList<TypeThatsTooLongForItsOwnGood>();
 *   我想我们都认为这很讨厌。
 *   因此Guava提供了能够推断范型的静态工厂方法：
 *      List<TypeThatsTooLongForItsOwnGood> list = Lists.newArrayList();
 *      Map<KeyType, LongishValueType> map = Maps.newLinkedHashMap();
 *
 *  可以肯定的是，JDK7版本的钻石操作符(<>)没有这样的麻烦：
 *     List<TypeThatsTooLongForItsOwnGood> list = new ArrayList<>();
 *
 *  但Guava的静态工厂方法远不止这么简单。用工厂方法模式，我们可以方便地在初始化时就指定起始元素。
 *    Set<Type> copySet = Sets.newHashSet(elements);
 *    List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");
 *
 *  此外，通过为工厂方法命名（Effective Java第一条），我们可以提高集合初始化大小的可读性：
 *    List<Type> exactly100 = Lists.newArrayListWithCapacity(100);
 *    List<Type> approx100 = Lists.newArrayListWithExpectedSize(100);
 *    Set<Type> approx100Set = Sets.newHashSetWithExpectedSize(100);
 *
 *   注意：Guava引入的新集合类型没有暴露原始构造器，也没有在工具类中提供初始化方法。
 *   而是直接在集合类中提供了静态工厂方法，例如：
 *   Multiset<String> multiset = HashMultiset.create();
 */
public interface Info {
}
