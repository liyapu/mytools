//package com.lyp.learn.sort;
//
//
//import java.util.Arrays;
//
///**
// * @author: liyapu
// * @description:
// * @date 2020-07-13 15:48
// */
//class BubbleSortTest {
//    public static void main(String[] args) {
//        int[] arr = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
////        int[] arr = new int[]{5,4,3,2,1,6,9,7,8};
//         int [] a = mergeSort(arr);
//        System.out.println(Arrays.toString(a));
//    }
////
////    public static int[] mergeSort(int[] arr){
////        int len;
////        if(arr == null || (len = arr.length) == 0){
////            return arr;
////        }
////        if(len < 2){
////            return arr;
////        }
////        int middle = len/ 2;
////        int [] leftArr = Arrays.copyOfRange(arr,0,middle);
////        int[] rightArr = Arrays.copyOfRange(arr,middle,len);
////        return merge(mergeSort(leftArr),mergeSort(rightArr));
////    }
////
////    private static int[] merge(int[] leftArr, int[] rightArr) {
////        int leftLen = leftArr.length;
////        int rightLen = rightArr.length;
////        int len = leftLen + rightLen;
////        int [] arr = new int[len];
////        int i = 0,j = 0;
////        int index = 0;
////        while(i < leftLen && j < rightLen){
////            if(leftArr[i] < rightArr[j]){
////                arr[index++] = leftArr[i++];
////            }else if(leftArr[i] >=rightArr[j]){
////                arr[index++] = rightArr[j++];
////            }
////        }
////        while(i < leftLen){
////            arr[index++] = leftArr[i++];
////        }
////        while(j < rightLen){
////            arr[index++] = rightArr[j++];
////        }
////        return arr;
////    }
//
//
//
//
//}