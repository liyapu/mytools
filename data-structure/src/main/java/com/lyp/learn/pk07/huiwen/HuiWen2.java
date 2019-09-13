package com.lyp.learn.pk07.huiwen;

public class HuiWen2 {

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
     *  i < j ,不需要判断 i = j
     *  str 是奇数长度的话, 中间只有一个字符
     *  str 是偶然长度的话，i < j 时，都已经判断完了
     * @param str
     * @return
     */
    public static boolean isHuiWen(String str){
       for(int i = 0, j = str.length() - 1; i < j; i++,j--){
           if(!(str.charAt(i) == str.charAt(j))){
               return false;
           }
       }
       return  true;
    }
}
