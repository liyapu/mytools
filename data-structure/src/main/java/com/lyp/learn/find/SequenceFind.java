package com.lyp.learn.find;

/**
 * 顺序查找
 */
public class SequenceFind {
    private static int [] arr = {3,6,9,1,2,8,7,4};

    /**
     * 查找为key 的数据元素
     * 若找到，则返回其下标，
     * 若没有找到，返回 -1；
     */

    public static int findKey(int key){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == key){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        testFindKey(2);

        testFindKey(5);
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
