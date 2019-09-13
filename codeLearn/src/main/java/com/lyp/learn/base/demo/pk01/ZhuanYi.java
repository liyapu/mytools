package com.lyp.learn.base.demo.pk01;

public class ZhuanYi {
    public static void main(String[] args) {
        String t = "a|b|c|d";

        String[] temp = t.split("\\|");

        System.out.println(temp.length);
        for(String str : temp){
            System.out.println(str);
        }
        /**
         * 主要是："\\|\\|" 代表什么意思？
         * \\会转义成反斜杠，反斜杠本身就是转义符，所有就成了“\|”，在进行转义就是|
         * 所以\\|实际上是“|”。
         */

    }
}
