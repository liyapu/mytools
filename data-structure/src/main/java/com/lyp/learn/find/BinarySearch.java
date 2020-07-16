package com.lyp.learn.find;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 *    针对的是一个有序数列
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
        System.out.println();

        // 查找可以返回多个重复key
        int[] array = {2, 6, 9, 11, 16, 20, 20, 20, 20, 20, 20, 20, 23, 25, 26, 29, 33, 35};
        List<Integer> allKey = findAllKey(array, 20);
        System.out.println(allKey);

    }

    /**
     * 查找为key 的数据元素
     * 若找到，则返回其下标，
     * 若没有找到，返回 -1；
     */
    public static int findKey(int[] arr, int key) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return -1;
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
                return mid;
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
