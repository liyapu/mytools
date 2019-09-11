package com.lyp.learn.sort;

public class StraightSort {

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
     * 直接插入排序
     * @param array
     * @return
     */
    public static int [] straightSort(int [] array){
        int length = array.length;
        if(length == 0){
            return array;
        }
        //第一层控制：顺序往后：从第一个元素到末尾
        for(int i = 1; i < length; i++){
            //第二层控制：逆序往前逐个比较:
            for(int j = i; j > 0; j--){
                if(array[j] < array[j-1]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int [] array = new int [] {52,39,67,95,70,8,25,52};
        printArray(array);
        array = straightSort(array);
        printArray(array);
    }


}
