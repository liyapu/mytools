package com.lyp.likou.str;

import java.util.Objects;

/**
 *@author: liyapu
 *@description:
 *@date 2020-09-08 15:50
 */
public class HuiWenLength {
    public static void main(String[] args) {
        String str = "babad";
        Integer len = str.length();
        int start = 0;
        int end = len - 1;
        int max = 0;
        for (int i = 0; i < len; i++) {
            start = i;
            end = len - 1;
            int k = i;
            for (int j = len - 1; j >= k; j--) {
                if (Objects.equals(str.charAt(k), str.charAt(j))) {
                    k++;
                }else{

                }
            }
        }
    }
}
