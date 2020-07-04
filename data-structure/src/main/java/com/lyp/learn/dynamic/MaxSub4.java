package com.lyp.learn.dynamic;

public class MaxSub4 {
    public static void main(String[] args) {

        int [] arr = new int [] {-2,11,-4,13,-5,2,-5,-3,12,-9};
        System.out.println("max is : " + maxSum(arr));//max is : 21
    }

    public static int maxSum(int [] arr){
        int length = arr.length;
        int max = 0;
        int sum = 0;
        for(int i = 0; i < length; i++){
            sum += arr[i];
            if(sum > max){
                max = sum;
            }else if(sum < 0){
                sum = 0;
            }
        }
        return max;
    }

}
