package com.lyp.learn.pk07.huiwen;

public class HuiWen1 {

    public static void main(String[] args) {
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
     * 判断是否是回文
     * @param str
     * @return
     */
    public static boolean isHuiWen(String str){
        StringBuilder sb = new StringBuilder(str);
        sb = sb.reverse();
        for(int i = 0; i < str.length(); i++){
            if(!(str.charAt(i) == sb.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 反转之后，直接用字符串的方法判断
     * @param str
     * @return
     */
    public static boolean isHuiWen2(String str){
        StringBuilder sb = new StringBuilder(str);
        sb = sb.reverse();
        return str.equals(sb.toString());
    }
}
