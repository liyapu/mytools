package com.lyp.learn.dp.pattern.prototype3;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 浅拷贝和深拷贝的区别在于，
 * 浅拷贝只会复制图中的索引（散列表），不会复制数据（SearchWord对象）本身。
 * 相反，深拷贝不仅仅会复制索引，还会复制数据本身。
 * 浅拷贝得到的对象（newKeywords）跟原始对象（currentKeywords）共享数据（SearchWord对象），
 * 而深拷贝得到的是一份完完全全独立的对象。具体的对比如下图所示：
 * <p>
 * 在Java语言中，Object类的clone()方法执行的就是我们刚刚说的浅拷贝。它只会拷贝对象中的基本数据类型的数据（比如，int、long），
 * 以及引用对象（SearchWord）的内存地址，不会递归地拷贝引用对象本身。
 * <p>
 * 那如何实现深拷贝呢？总结一下的话，有下面两种方法。
 * <p>
 * 第一种方法：递归拷贝对象、对象的引用对象以及引用对象的引用对象……直到要拷贝的对象只包含基本数据类型数据，没有引用对象为止。根据这个思路对之前的代码进行重构。重构之后的代码如下所示：
 * <p>
 * public class Demo {
 * private HashMap currentKeywords=new HashMap<>();
 * private long lastUpdateTime = -1;
 * <p>
 * public void refresh() {
 * // Deep copy
 * HashMap newKeywords = new HashMap<>();
 * for (HashMap.Entry e : currentKeywords.entrySet()) {
 * SearchWord searchWord = e.getValue();
 * SearchWord newSearchWord = new SearchWord(
 * searchWord.getKeyword(), searchWord.getCount(), searchWord.getLastUpdateTime());
 * newKeywords.put(e.getKey(), newSearchWord);
 * }
 * <p>
 * // 从数据库中取出更新时间>lastUpdateTime的数据，放入到newKeywords中
 * List toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);
 * long maxNewUpdatedTime = lastUpdateTime;
 * for (SearchWord searchWord : toBeUpdatedSearchWords) {
 * if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
 * maxNewUpdatedTime = searchWord.getLastUpdateTime();
 * }
 * if (newKeywords.containsKey(searchWord.getKeyword())) {
 * SearchWord oldSearchWord = newKeywords.get(searchWord.getKeyword());
 * oldSearchWord.setCount(searchWord.getCount());
 * oldSearchWord.setLastUpdateTime(searchWord.getLastUpdateTime());
 * } else {
 * newKeywords.put(searchWord.getKeyword(), searchWord);
 * }
 * }
 * <p>
 * lastUpdateTime = maxNewUpdatedTime;
 * currentKeywords = newKeywords;
 * }
 * <p>
 * private List getSearchWords(long lastUpdateTime) {
 * //  从数据库中取出更新时间>lastUpdateTime的数据
 * return null;
 * }
 * <p>
 * }
 * <p>
 * 第二种方法：先将对象序列化，然后再反序列化成新的对象。具体的示例代码如下所示：
 * public Object deepCopy(Object object) {
 * ByteArrayOutputStream bo = new ByteArrayOutputStream();
 * ObjectOutputStream oo = new ObjectOutputStream(bo);
 * oo.writeObject(object);
 * <p>
 * ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
 * ObjectInputStream oi = new ObjectInputStream(bi);
 * <p>
 * return oi.readObject();
 * }
 * <p>
 * 刚刚的两种实现方法，不管采用哪种，深拷贝都要比浅拷贝耗时、耗内存空间。针对我们这个应用场景，有没有更快、更省内存的实现方式呢？
 * 我们可以先采用浅拷贝的方式创建newKeywords。对于需要更新的SearchWord对象，我们再使用深度拷贝的方式创建一份新的对象，替换newKeywords中的老对象。毕竟需要更新的数据是很少的。
 * 这种方式即利用了浅拷贝节省时间、空间的优点，又能保证currentKeywords中的中数据都是老版本的数据。
 * 具体的代码实现如下所示。这也是标题中讲到的，在我们这个应用场景下，最快速clone散列表的方式。
 * <p>
 * public class Demo {
 * private HashMap currentKeywords=new HashMap<>();
 * private long lastUpdateTime = -1;
 * <p>
 * public void refresh() {
 * // Shallow copy
 * HashMap newKeywords = (HashMap) currentKeywords.clone();
 * <p>
 * // 从数据库中取出更新时间>lastUpdateTime的数据，放入到newKeywords中
 * List toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);
 * long maxNewUpdatedTime = lastUpdateTime;
 * for (SearchWord searchWord : toBeUpdatedSearchWords) {
 * if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
 * maxNewUpdatedTime = searchWord.getLastUpdateTime();
 * }
 * if (newKeywords.containsKey(searchWord.getKeyword())) {
 * newKeywords.remove(searchWord.getKeyword());
 * }
 * newKeywords.put(searchWord.getKeyword(), searchWord);
 * }
 * <p>
 * lastUpdateTime = maxNewUpdatedTime;
 * currentKeywords = newKeywords;
 * }
 * <p>
 * private List getSearchWords(long lastUpdateTime) {
 * // 从数据库中取出更新时间>lastUpdateTime的数据
 * return null;
 * }
 * }
 */
public class HashMapPrototype {

    private HashMap<String, SearchWord> currentKeywords = new HashMap<>();
    private long lastUpdateTime = -1;

    public void refresh() {
        // Shallow copy
        HashMap<String, SearchWord> newKeywords = (HashMap<String, SearchWord>) currentKeywords.clone();

        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到newKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWord searchWord : toBeUpdatedSearchWords) {
            if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getLastUpdateTime();
            }
            if (newKeywords.containsKey(searchWord.getKeyword())) {
                newKeywords.remove(searchWord.getKeyword());
            }
            newKeywords.put(searchWord.getKeyword(), searchWord);
        }

        lastUpdateTime = maxNewUpdatedTime;
        currentKeywords = newKeywords;
    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        // 从数据库中取出更新时间>lastUpdateTime的数据
        return Collections.emptyList();
    }
}
