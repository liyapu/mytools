package com.lyp.learn.sort;

public class QuickSort {

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
     * 快速排序
     * @param array
     * @return
     */
    public static void quickSort(int [] array,int low,int high){
        if(array == null || low >= high){
            return;
        }
        //从左边开始的游标
        int i = low;
        //从右边开始的游标
        int j = high;
        //存储比较的基准数，数组的第一个数字
        int key = array[low];

        while(i < j){
            // j 表示的数组上的数字，都 >= 基准数，从后向前
            while(i < j && array[j] >= key){
                j--;
            }
            //用上面找到的j 位置上的值
            //把上面找到的比基准数小的数字，放到 i 位置上，然后 i 向后移动 1位
            if(i < j){
                array[i++] = array[j];
            }
            //i 表示的数组上的数组，都 <= 基准数，从前向后
            while (i < j && array[i] <= key){
                i++;
            }
            //用上面找到的 i位置上的值
            //把上面找到的比基准数大的数字，放到 j 位置上，然后 j 向前移动 1位
            if(i < j ){
                array[j--] = array[i];
            }
        }
        //上面的判断都有 i < j 的判断，所以最终i 会等于j;设置上基准数
        array[i] = key;
        //基准数字移动到的位置 - 1
        quickSort(array,low,i-1);
        //基准数字移动到的位置 + 1
        quickSort(array,i+1,high);
    }

    public static void quickSort2(int[] array, int start, int end) {
        if (array == null || start >= end) {
            return;
        }
        int i = start, j = end;
        int pivotKey = array[start];
        while (i < j) {
            while (i < j && array[j] >= pivotKey) {
                j--;
            }
            if (i < j) {
                array[i++] = array[j];
            }
            while (i < j && array[i] <= pivotKey) {
                i++;
            }
            if (i < j) {
                array[j--] = array[i];
            }
        }
        array[i] = pivotKey;
        quickSort(array, start, i - 1);
        quickSort(array, i + 1, end);
    }



    public static void main(String[] args) {
        int [] array = new int [] {6,1,2,7,9,3,4,5,10,8};
        printArray(array);
        //这里传的是长度-1，即下标的最大索引
        int length = array.length -1;
        quickSort(array,0,length);
        printArray(array);

        System.out.println();

        int [] array2 = new int [] {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        int length2 = array2.length - 1;
        printArray(array2);
        quickSort2(array2,0,length2);
        printArray(array2);

    }
}
