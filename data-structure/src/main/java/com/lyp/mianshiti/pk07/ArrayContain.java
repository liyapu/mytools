package com.lyp.mianshiti.pk07;

import java.util.Objects;

/**
 * @author liyapu
 * @date 2023-06-13 22:28
 * @description
 */
public class ArrayContain {

    public static void main(String[] args) {
        System.out.println(index(new int[]{1, 5, 7, 1, 3, 8, 20, 50, 4, 5, 67, 1, 3, 9}, new int[]{1, 3, 2}));
    }


    /**
     * 查找 sourceArr 完整包含 targetArr的最先包含的下标值
     *
     * @param sourceArr 源数组
     * @param targetArr 目标数组
     * @return 找到返回最小的下标值；补不到返回-1,表示没有符合条件的数据
     */
    public static int index(int[] sourceArr, int[] targetArr) {
        if (Objects.isNull(sourceArr) || Objects.isNull(targetArr) || sourceArr.length < targetArr.length) {
            return -1;
        }
        for (int i = 0; i < sourceArr.length; ) {
            int tempI = i;
            int j = 0;
            for (; j < targetArr.length; ) {
                if (sourceArr[tempI] == targetArr[j]) {
                    tempI++;
                    j++;
                } else {
                    i++;
                    break;
                }
            }
            if (j == targetArr.length) {
                return i;
            }
        }
        return -1;
    }

}
