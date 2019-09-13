package com.lyp.learn.pk01;

import java.util.Arrays;

public class MergeSort {

    public static void printArray(int [] array){
        int length = array.length;
        for(int i = 0; i < length; i++){
            if(i != 0){
                System.out.print(" , ");
            }
            System.out.print(array[i]);
        }
        System.out.println();
    }

    /**
     * 归并排序
     * @param array
     * @return
     */
    public static int [] mergeSort(int [] array){
        int length = array.length;
        if(length < 2){
            return array;
        }
        int mid = length /2;
        int [] left = Arrays.copyOfRange(array,0,mid);
        int [] right = Arrays.copyOfRange(array,mid,length);
        return merge(mergeSort(left),mergeSort(right));
    }

    /**
     * 归并排序---将两段排序好的数组结合成一个排序好的数组
     * @param left
     * @param right
     * @return
     */
    public static int [] merge(int [] left, int [] right){
        int leftLength = left.length;
        int rightLength = right.length;
        int length = leftLength + rightLength;
        int [] array = new int [length];
        //index 表示 left,right 数组总长度遍历次数
        //i 表示 left数组的下标
        //j 表示 right数组的下标
        for(int index = 0,i = 0,j = 0; index < length; index++){
            //i >= left数组长度，表示left中已经遍历完了，剩下的把right中的数组元素直接放到array中
            if(i >= leftLength){
                array[index] = right[j++];
            //j >= right数组长度，表示right中已经遍历完了，剩下的把left中的数组元素直接放到array中
            }else if(j >= rightLength){
                array[index] = left[i++];
            //比较left数组与right数组元素大小
            }else if(left[i] > right[j]){
                array[index] = right[j++];
            }else {
                array[index] = left[i++];
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int [] array = new int [] {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        printArray(array);
        array = mergeSort(array);
        printArray(array);
    }
}
