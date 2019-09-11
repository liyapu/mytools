package com.lyp.learn.sort;

public class BubbleSort {

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
     * 冒泡排序
     * @param array
     * @return
     */
    public static int [] bubbleSort(int [] array){
        int length = array.length;
        if(length == 0){
            return array;
        }
        //第一层控制遍历的变量个数：长度-1
        for(int i = 0 ; i < length - 1; i++){
            //第二层从0开始，到长度-1-第一层下沉的大数个数
            for(int j = 0 ; j < length - 1 - i; j++){
                //下面都是使用 j 变量计算的
                //当前值与其后一位，第二层已经-1，下面 +1,故不会访问越界
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int [] array = new int [] {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        printArray(array);
        array = bubbleSort(array);
        printArray(array);
    }
}
