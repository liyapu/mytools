package com.lyp.learn.guava.primitives;

import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Ints 是原始的 int 类型的实用工具类。
 *
 * 其它7中基本类型，用法于此类似
 */
public class IntsTest {

    /**
     * asList 基本类型数组转化为包装类List
     */
    @Test
    public void testAsList(){
        int [] intArray = {1,2,3,4,5};
        List<Integer> result = Ints.asList(intArray);
        System.out.println(result);

        List<Integer> odds = Ints.asList(1, 3, 5, 7);
        System.out.println(odds);
    }


    /**
     * checkedCast long转int，如果long值超出int范围抛IllegalArgumentException
     */
    @Test
    public void testCheckedCast() {
        long input = 9998L;
        int result = Ints.checkedCast(input);
        System.out.println(result);

        System.out.println(Ints.checkedCast(9999999999999999L));
    }

    /**
     * compare 比较两个int值的大小
     */
    @Test
    public void compareTest() {
        System.out.println(Ints.compare(1,1));
        System.out.println(Ints.compare(1,2));
        System.out.println(Ints.compare(2,1));
    }

    /**
     * concat 将多个int数组拼接成一个数组
     */
    @Test
    public void concatTest() {
        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};
        int[] array3 = {7, 8};
        int[] result = Ints.concat(array1, array2, array3);
        System.out.println(result.length);
        System.out.println(Arrays.toString(result));
    }

    /**
     * constrainToRange 如果一个数字在某个范围内则输出该数字，否则输出范围的最大值或最小值
     */
    @Test
    public void constrainToRangeTest() {
        int result = Ints.constrainToRange(5, 0, 9);
        System.out.println(result);     // 5
        System.out.println(Ints.constrainToRange(10, 0, 9));    // 9
        System.out.println(Ints.constrainToRange(-1, 0, 9));    // 0
    }

    /**
     * contains 判断一个int数是否在int数组内
     */
    @Test
    public void containsTest() {
        int[] array = {1, 2, 3, 4};
        System.out.println(Ints.contains(array, 3));    // true
    }

    /**
     * ensureCapacity
     * 确保数组拥有一个最小的长度，
     * 如果array长度小于minLength，则会返回一个元素值与array相同，但是length = minLength + padding的数组.
     */
    @Test
    public void ensureCapacityTest() {
        int[] array = {1, 2, 3, 4};
        int[] result = Ints.ensureCapacity(array, 6, 3);
        System.out.println(result.length);              // 9
        System.out.println(Arrays.toString(result));    // [1, 2, 3, 4, 0, 0, 0, 0, 0]

        System.out.println();
        result = Ints.ensureCapacity(array, 3, 3);
        System.out.println(result.length);              // 4
        System.out.println(Arrays.toString(result));    // [1, 2, 3, 4]
    }

    /**
     * fromByteArray 通过byte数组前四个元素转int值
     */
    @Test
    public void fromByteArrayTest() {
        byte[] byteArray = {1, 1, 1, 1};
        int result = Ints.fromByteArray(byteArray);
        System.out.println(result);     // 16843009
    }

    /**
     * fromBytes 通过四个byte元素转int值
     */
    @Test
    public void fromBytesTest() {
        int result = Ints.fromBytes((byte) 1, (byte) 1, (byte) 1, (byte) 1);
        System.out.println(result);     // 16843009
    }

    /**
     * hashCode 返回int值的hashCode(元素值)
     */
    @Test
    public void hashCodeTest() {
        int hashCode = Ints.hashCode(1);
        System.out.println(hashCode);      // 1
        System.out.println(Ints.hashCode(100));
        System.out.println(Ints.hashCode(250));
    }

    /**
     * indexOf 返回一个int值在数组中的第一个index,没匹配到返回-1
     */
    @Test
    public void indexOfTest() {
        int[] array = {1, 2, 3, 4, 3};
        System.out.println(Ints.indexOf(array, 3));
        System.out.println(Ints.indexOf(array, 5));
    }

    /**
     * arrayIndexOf 返回int数组在另一个数组中的第一个index,没匹配到返回-1
     */
    @Test
    public void arrayIndexOf() {
        int[] array = {1, 2, 3, 4, 6, 5, 8};
        int[] target = {6, 5};
        System.out.println(Ints.indexOf(array, target));
    }

    /**
     * join 通过连接符连接数组转成String
     */
    @Test
    public void joinTest() {
        int [] intArray = {1,2,3};
        String str = "-";
        System.out.println(Ints.join(str,intArray));

        System.out.println(Ints.join("*", 1, 2, 3));
    }

    /**
     * lastIndexOf 返回一个int值在数组中的最后一个index,没匹配到返回-1
     */
    @Test
    public void lastIndexOfTest() {
        int[] array = {1, 2, 3, 4, 3};
        System.out.println(Ints.lastIndexOf(array, 3));
        System.out.println(Ints.lastIndexOf(array, 5));
    }

    /**
     * lexicographicalComparator
     * 返回一个int[]比较器，比较规则是从index0开始比较两个数组对应index上的元素大小，返回比较结果，到其中一个数组结束都完全一致，则通过长度比较，长度大的那个数组大
     */
    @Test
    public void lexicographicalComparatorTest() {
        Comparator<int[]> comparator = Ints.lexicographicalComparator();
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 3, 3};
        int result = comparator.compare(array1, array2);
        System.out.println(result);     // -1
    }

    /**
     * max 返回一个数组的最大元素
     * min 返回一个数组的最小元素
     */
    @Test
    public void maxMinTest() {
        int[] array = {1, 16, 3, 5, 3};
        System.out.println(Ints.max(array));
        System.out.println(Ints.min(array));

    }

    /**
     * reverse 将数组反转
     */
    @Test
    public void reverseTest() {
        int[] array = {1, 16, 3, 5, 3};
        Ints.reverse(array);
        System.out.println(Ints.asList(array));    //  [3, 5, 3, 16, 1]

        /* 将数组指定范围的元素反转(范围左闭右开) */
        int[] array1 = {1, 16, 8, 5, 3,4};
        Ints.reverse(array1, 0, 3);
        System.out.println( Ints.asList(array1));   // [8, 16, 1, 5, 3,4]
    }

    /**
     * saturatedCast 将long转化为int，超出int范围转化为2147483647
     */
    @Test
    public void saturatedCastTest() {
        long input = 9998L;
        int result = Ints.saturatedCast(input);
        System.out.println(result);     // 9998

        input = 2147483648L;
        result = Ints.saturatedCast(input);
        System.out.println(result);     // 2147483647
    }

    /**
     * Arrays 工具类
     * 正序排序数组
     */
    @Test
    public void test11(){
        int [] intArray = {3,8,4,6,1,2,6,9};
        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));
    }
    /**
     * sortDescending 数组按逆序排序
     */
    @Test
    public void sortDescendingTest() {
        int[] array = {1, 16, 8, 5, 3};
        Ints.sortDescending(array);
        System.out.println(Ints.asList(array));         // [16, 8, 5, 3, 1]

        /*将一定范围内的数组按照逆序排序(范围左闭右开)*/
        int[] array1 = {1, 16, 8, 5, 3};
        Ints.sortDescending(array1, 0, 3);
        System.out.println(Ints.asList(array1));        // [16, 8, 1, 5, 3]
    }

    /**
     * stringConverter 返回String与Integer的转换器
     */
    @Test
    public void stringConverterTest() {
        Converter<String, Integer> converter = Ints.stringConverter();
        int num = converter.convert("123");
        System.out.println(num);        // 123
    }

    /**
     * toArray List转数组
     */
    @Test
    public void toArrayTest() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        int[] arr = Ints.toArray(list);
        System.out.println(Arrays.toString(arr));   // [1, 2, 3, 4]
    }

    /**
     * toByteArray int值转byte数组
     */
    @Test
    public void toByteArrayTest() {
        byte[] byteArray = Ints.toByteArray(1);
        System.out.println(Arrays.toString(byteArray));

        System.out.println(Arrays.toString(Ints.toByteArray(6)));
    }

    /**
     * tryParse 十进制String转Integer, 如果String值存在非法字符，转为null
     */
    @Test
    public void tryParseTest() {
        Integer result = Ints.tryParse("1234");
        System.out.println(result.intValue());                 // 1234
        System.out.println(Ints.tryParse("1234ahd"));   // null

        System.out.println();
        /* radix进制String转Integer, 如果String值存在非法字符，转为null */
        Integer result1 = Ints.tryParse("0110", 2);
        System.out.println(result1.intValue());                // 6
        System.out.println(Ints.tryParse("0110ahd"));   // null
    }


}
