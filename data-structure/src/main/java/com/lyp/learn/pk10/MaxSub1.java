package com.lyp.learn.pk10;

public class MaxSub1 {
    public static void main(String[] args) {

        int [] arr = new int [] {-2,11,-4,13,-5,2,-5,-3,12,-9};
        int length = arr.length;
        int max = 0;

        //第一层循环，控制子序列的长度，计算长度为i的子序列的和
        for(int i = 1; i < length; i++){
            //控制整个数组的遍历访问
            for(int j = 0; j < length; j++){
                int sum = 0; //每次计算清零
                for(int k = j; k < j + i &&  k < length; k++){
                    sum += arr[k];
                    if(sum > max){
                        max = sum;
                    }
                }
            }
        }
        System.out.println("max is : " + max);
    }
}
