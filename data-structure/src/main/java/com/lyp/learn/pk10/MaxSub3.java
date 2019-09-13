package com.lyp.learn.pk10;

public class MaxSub3 {
    public static void main(String[] args) {

        int [] arr = new int [] {-2,11,-4,13,-5,2,-5,-3,12,-9};
        //right = arr.length -1. 不然下面会数组越界
        int max = maxSum(arr,0,arr.length-1);
        System.out.println("max is : " + max);//max is : 21
    }

    public static int maxSum(int [] arr, int left,int right){
        if(left == right){
            //求最大值时，负数返回0
            if(arr[left] >= 0){
                return arr[left];
            }else{
                return 0;
            }
        }

        int middle = (left + right)/2;
        int maxLeftSum = maxSum(arr,left,middle);
        int maxRightSum = maxSum(arr,middle + 1,right);

        int maxLeftBorderSum = 0,leftBorderSum = 0;
        for(int i = middle; i >= left; i--){
            leftBorderSum += arr[i];
            if(leftBorderSum > maxLeftBorderSum){
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0,rightBorderSum = 0;
        for(int i = middle + 1; i <= right; i++){
            rightBorderSum += arr[i];
            if(rightBorderSum > maxRightBorderSum){
                maxRightBorderSum = rightBorderSum;
            }
        }
        return max3(maxLeftSum,maxRightSum,maxLeftBorderSum + maxRightBorderSum);
    }

    private static int max3(int a, int b, int c) {
        int max = 0;
        max = a > b ? a : b;
        max = max > c ? max : c;
        return max;

    }
}
