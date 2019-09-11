package com.lyp.learn.sort;

public class SelectionSort {

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
     * 选择排序
     * @param array
     * @return
     */
    public static int [] selectionSort(int [] array){
        int length = array.length;
        if(length == 0){
            return array;
        }
        for(int i = 0 ; i < length ; i++){
            int minIndex = i;
            for(int j = i + 1; j < length; j++) {
                if (array[j] < array[minIndex]) {//找到最小的数
                    minIndex = j;//将最小的数的索引保存到minIndex中
                }
            }
            //下面的在第一个for循环内，与第二个for循环同级
            if(minIndex == i){
                continue;
            }else{
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int [] array = new int [] {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        printArray(array);
        array = selectionSort(array);
        printArray(array);
    }
}
