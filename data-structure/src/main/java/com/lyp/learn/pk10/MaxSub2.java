package com.lyp.learn.pk10;


public class MaxSub2 {
    public static void main(String[] args) {

        int [] arr = new int [] {-2,11,-4,13,-5,2,-5,-3,12,-9};
        int length = arr.length;
        int max = 0;

        //控制开始的位置
        for(int i = 0; i < length; i++){
            int sum = 0;
            //控制长度，指定开始位置之后，分别计算长度为1-(length-i)的和
            for(int j = i; j < length;j++){
                sum += arr[j];
                //每次和都要和max比较
                if(sum > max){
                    max = sum;
                }
            }
        }
        System.out.println("max is : " + max);//max is : 21
    }
}
