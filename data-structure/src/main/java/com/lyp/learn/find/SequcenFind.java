package com.lyp.learn.find;

/**
 * 顺序查找
 */
public class SequcenFind {
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
        int index1 = findKey(2);
        if(index1 >= 0){
            System.out.println(arr[index1]);
        }else{
            System.out.println("没有找到");
        }

        int index2 = findKey(5);
        if(index2 >= 0){
            System.out.println(arr[index2]);
        }else{
            System.out.println("没有找到");
        }
    }
}
