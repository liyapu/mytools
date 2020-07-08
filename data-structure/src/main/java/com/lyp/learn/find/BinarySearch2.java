package com.lyp.learn.find;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 *    针对的是一个有序数列
 *    有多个重复值，并且都要返回
 */
public class BinarySearch2 {
    private static int [] arr = {2,6,9,11,16,20,20,20,20,20,20,20,23,25,26,29,33,35};

    /**
     * 查找为key 的数据元素
     * 若找到，则返回其下标，
     * 若没有找到，返回 -1；
     */

    public static List<Integer> findKey(int key){
        List<Integer> al = new ArrayList<>();
        int length = arr.length;
        if(length <= 0){
            return  al;
        }

        int low = 0;
        int high = length - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(key < arr[mid]){
                high = mid - 1;
            }else if(key > arr[mid]){
                low = mid + 1;
            }else{
                //先向左扫描
                int index = mid -1;
                while (index >=0 && arr[index] == key){
                    al.add(index);
                    index--;
                }
                //添加找到的下标
                al.add(mid);
                //然后向右描述
                index = mid + 1;
                while (index < length-1 && arr[index] == key){
                    al.add(index);
                    index++;
                }
                return al;
            }
        }
        return al;
    }

    public static void main(String[] args) {
        testFindKey(20);
        testFindKey(10);

    }

    private static void testFindKey(int key) {
        List<Integer> al = findKey(key);

        if (al.size() > 0) {
            System.out.println(al);
        } else {
            System.out.println("没有找到");
        }
    }
}
