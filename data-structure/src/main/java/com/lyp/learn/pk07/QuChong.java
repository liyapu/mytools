package com.lyp.learn.pk07;

public class QuChong {
    public static void main(String[] args) {
        String str1 = "12ere2";
        System.out.println(quchong(str1)); // 12er
    }

    private static String quchong(String str) {
        String s = "";
        for(int i = 0; i < str.length(); i++){
            if(!s.contains(String.valueOf(str.charAt(i)))){
                s = s.concat(String.valueOf(str.charAt(i)));
            }
        }
        return s;
    }
}
