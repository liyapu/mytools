package com.lyp.learn.pk05.linkstackalgorithm;

import java.util.Stack;

public class BigNum2 {
    //全局变量  表示是否进位
     private static boolean isCarry = false;
    /**
     * 将两个大数字字符串相加
     * @param stra
     * @param strb
     * @return
     */
    public static String add(String stra,String strb){
        Stack<Integer> sum = new Stack<>();
        Stack<Integer> stackA = numSplit(stra);
        Stack<Integer> stackB = numSplit(strb);

        while(!stackA.isEmpty() || !stackB.isEmpty()){
            if(stackA.isEmpty()){
                addDetail(sum, stackB.pop());
            }else if(stackB.isEmpty()){
                 addDetail(sum, stackA.pop());
            }else{
                addDetail(sum,(stackA.pop() + stackB.pop()));
            }

        }

        //处理最后两数有进位的情况
        if(isCarry){
            sum.push(1);
            isCarry = false;
        }

        StringBuilder sb = new StringBuilder();
        while(!sum.isEmpty()){
            sb.append(sum.pop());
        }
        return sb.toString();
    }

    private static void addDetail(Stack<Integer> sum, int num) {
        if(isCarry){
            num++;
        }
        if(num >= 10){
            num -= 10;
            isCarry = true;
        }else {
            isCarry = false;
        }
        sum.push(num);
    }

    /**
     * 把字符串数字，分割成 栈 存储的数字
     * @param str
     * @return
     */
    private  static Stack<Integer> numSplit(String str){
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < str.length(); i++){
            char c = str.charAt(i);
            if(c >= '0' && c <= '9'){
                stack.push(Integer.parseInt(String.valueOf(c)));
            }
        }
        return stack;
    }


    public static void main(String[] args) {
        String a1 = "18 452 543 389 945 209 752 345 473";
        String b1 =      "8 123 542 678 432 986 899 334 ";

        String a11 = "90";
        String b11 = "11";
        System.out.println("两个大数的和为：" + add(a1,b1));
        System.out.println("两个大数的和为：" + add(a11,b11));

    }
}
