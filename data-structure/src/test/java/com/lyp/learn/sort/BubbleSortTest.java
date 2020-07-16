package com.lyp.learn.sort;


/**
 * @author: liyapu
 * @description:
 * @date 2020-07-13 15:48
 */
class BubbleSortTest {
    public static void main(String[] args) {
//        int[] arr = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
////        int[] arr = new int[]{5, 4, 3, 2, 1};
////        int[] arr = new int[]{1,2,3};
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr));

//        int arr[] = {0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int arr[] = {10,20,30,40};
        int index = binarySearchKey(arr,5);
        System.out.println(index);
    }

    public static int binarySearchKey(int [] arr,int key){
        int len;
        if(arr == null || (len = arr.length) ==0){
            return -1;
        }
        int low = 0;
        int high = len;
        while(low <= high){
            int middle = (low+ high)/2;
            if(key > middle){
                low = middle + 1;
            }else if(key < middle){
                high = middle -1;
            }else{
                return middle;
            }
        }
        return -1;
    }

}