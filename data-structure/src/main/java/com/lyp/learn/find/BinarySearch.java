package com.lyp.learn.find;

/**
 * 二分查找
 */
public class BinarySearch {
    private static int [] arr = {2,6,9,11,16,20,23,25,26,29,33,35};

    /**
     * 查找为key 的数据元素
     * 若找到，则返回其下标，
     * 若没有找到，返回 -1；
     */

    public static int findKey(int key){
        int length = arr.length;
        if(length <= 0){
            return  -1;
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
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        testFindKey(2);
        testFindKey(6);
        testFindKey(9);
        testFindKey(23);
        testFindKey(33);
        testFindKey(35);
        testFindKey(3);
        testFindKey(60);
    }

    private static void testFindKey(int i) {
        int index1 = findKey(i);
        if (index1 >= 0) {
            System.out.println(arr[index1]);
        } else {
            System.out.println("没有找到");
        }
    }
}
