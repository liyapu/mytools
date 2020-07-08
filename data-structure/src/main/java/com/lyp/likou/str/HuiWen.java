package com.lyp.likou.str;

/**
 *
 */
public class HuiWen {

    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "aa";
        String str3 = "aba";
        String str4 = "abba";
        String str5 = "rotator";
        String str6 = "98789";
        String str7 = "abctacba";
        String str8 = "ab";
        String str9 = "abca";


        testHW(str1);
        testHW(str2);
        testHW(str3);
        testHW(str4);
        testHW(str5);
        testHW(str6);
        testHW(str7);
        testHW(str8);
        testHW(str9);
    }

    public static void testHW(String str){
        System.out.println(str + " : " + isHuiWen(str));
        System.out.println(str + " : " + isHuiWen1(str));
        System.out.println(str + " : " + isHuiWen2(str));
        System.out.println(str + " : " + isHuiWen3(str));
        System.out.println();
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
     * 判断是否是回文
     *  i < j ,不需要判断 i = j
     *  str 是奇数长度的话, 中间只有一个字符
     *  str 是偶然长度的话，i < j 时，都已经判断完了
     * @param str
     * @return
     */
    public static boolean isHuiWen1(String str){
        for(int i = 0, j = str.length() - 1; i < j; i++,j--){
            if(!(str.charAt(i) == str.charAt(j))){
                return false;
            }
        }
        return  true;
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

    /**
     * 使用递归判断
     */
    public static boolean isHuiWen3(String str){
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
