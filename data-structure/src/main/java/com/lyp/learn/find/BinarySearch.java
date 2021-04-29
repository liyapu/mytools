package com.lyp.learn.find;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 *    针对的是一个有序数列
 *
 *
 *    二分查找细节详解，顺便赋诗一首
 *    https://leetcode-cn.com/problems/binary-search/solution/er-fen-cha-zhao-xiang-jie-by-labuladong/
 *
 *
 *    写对二分查找不能靠模板，需要理解加练习 （附练习题，持续更新） liweiwei1419
 *    https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {2, 6, 9, 11, 16, 20, 23, 25, 26, 29, 33, 35};
        System.out.println(findKey(arr, 1));
        System.out.println(findKey(arr, 2));
        System.out.println(findKey(arr, 11));
        System.out.println(findKey(arr, 29));
        System.out.println(findKey(arr, 35));
        System.out.println(findKey(arr, 50));
        System.out.println("--------------");
        System.out.println(search(arr, 1));
        System.out.println(search(arr, 2));
        System.out.println(search(arr, 11));
        System.out.println(search(arr, 29));
        System.out.println(search(arr, 35));
        System.out.println(search(arr, 50));
        System.out.println("--------------");

        // 查找可以返回多个重复key
        int[] array = {2, 6, 9, 11, 16, 20, 20, 20, 20, 20, 20, 20, 23, 25, 26, 29, 33, 35};
        List<Integer> allKey = findAllKey(array, 20);
        System.out.println(allKey);

    }

    /**
     * 查找为key 的数据元素
     * 若找到，则返回其下标，
     * 若没有找到，返回 -1；
     *
     * int mid = (left + right) >>> 1;
     * 这是参考 Java 的 JDK 中 Arrays.binarySearch() 函数的写法。理由是 left + right 即使是在整型溢出以后，
     * 由于无符号右移 >>> 1 ，仍然能够得到正确的结果（我掌握的语言中，只有 Java 语言中有 >>> 这个操作符）。
     *
     * 虽然 >> 1 和 /2 ，但是有些语言编译器都会将 /2 转换成位运算的操作，这是编译器内部的优化。
     * 因此我们没有必要手动去做这一步优化，写代码的时候还是写 /2。
     *
     *
     * 另外声明一下，计算 mid 时需要防止溢出，代码中 left + (right - left) / 2 就和 (left + right) / 2 的结果相同，
     * 但是有效防止了 left 和 right 太大直接相加导致溢出
     */
    public static int findKey(int[] arr, int key) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return -1;
        }

        int low = 0;
        int high = len - 1;
        while (low <= high) {
//            int mid = (low + high) / 2;
//            int mid = (low + high) >> 1; // >>1 可以，别人
            // 为了防止 low + high 整形溢出,超出 int型最大值，写成这样
            int mid = low + (high - low) / 2;
//            int mid = low + ((high - low)>>1); //  (high - low)>>1 这种写法，会形成死循环, ((high - low)>>1) 这种写法可以; 因为 移位运算符的优先级 低于 加减操作符的优先级
            System.out.println("mid = " + mid);

            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 力扣
     * https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        // 在 [left..right] 里查找 target
        while (left <= right) {
            // 为了防止 left + right 整形溢出，写成这样
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 下一轮搜索区间：[left..mid - 1]
                right = mid - 1;
            } else {
                // 此时：nums[mid] < target，下一轮搜索区间：[mid + 1..right]
                left = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 针对的是一个有序数列
     * 有多个重复值，并且都要返回
     * 若没有找到，返回 -1
     *
     * @param arr
     * @param key
     * @return
     */
    public static List<Integer> findAllKey(int[] arr, int key) {
        List<Integer> al = new ArrayList<>();
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return al;
        }

        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                //先向左扫描
                int index = mid - 1;
                while (index >= 0 && arr[index] == key) {
                    al.add(index);
                    index--;
                }
                //添加找到的下标
                al.add(mid);
                //然后向右描述
                index = mid + 1;
                while (index < len - 1 && arr[index] == key) {
                    al.add(index);
                    index++;
                }
                return al;
            }
        }
        return al;
    }

}
