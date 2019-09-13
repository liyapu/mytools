package com.lyp.learn.pk07.huiwen;

public class HuiWen3 {

    public static void main(String[] args) {
        System.out.println("aba".substring(1,1));
        System.out.println("aba".substring(1,2));
        String str1 = "a";
        String str2 = "aa";
        String str3 = "aba";
        String str4 = "abba";
        String str5 = "rotator";
        String str6 = "98789";
        String str7 = "abctacba";
        System.out.println(str1 + " : " + isHuiWen(str1));
        System.out.println(str2 + " : " + isHuiWen(str2));
        System.out.println(str3 + " : " + isHuiWen(str3));
        System.out.println(str4 + " : " + isHuiWen(str4));
        System.out.println(str5 + " : " + isHuiWen(str5));
        System.out.println(str6 + " : " + isHuiWen(str6));
        System.out.println(str7 + " : " + isHuiWen(str7));
    }

    /**
     * 使用递归判断
     */
    public static boolean isHuiWen(String str){
        //1. 递归结束的条件
       int length = str.length();
       if(length == 0 || length == 1){
           return true;
       }
       //2.判断本次需要判断的字符
       if(!(str.charAt(0) == str.charAt(length-1))){
           return false;
       }
       return isHuiWen(str.substring(1,length-1));
    }
}
