package com.lyp.learn.base.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <pre>
 * Comments:
 *
 *
 * <pre/>
 * <hr/>
 * Created by Zhimin Xu on 2017/4/13.
 */
public class ListUtils {
    private ListUtils() {

    }

    /**
     * <pre>
     *     list == null ==> false
     *     list.size() == 0 ==> flase
     * </pre>
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List list) {
        return  list != null && list.size() > 0;
    }

    /**
     * <pre>
     *      list == null ==> true
     *      or
     *      list.size() == 0 ==> true
     * </pre>
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 将Collection对象转化为List对象
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> List<T> convertCollection2List(Collection<T> collection) {
        List<T> list = new ArrayList<>();
        for (T t : collection) {
            list.add(t);
        }
        return list;
    }

}
