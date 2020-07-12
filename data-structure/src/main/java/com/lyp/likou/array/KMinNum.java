package com.lyp.likou.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 查找第k小数
 * 查找一个数组的第K小的数，注意同样大小算一样大。
 * 如  2 1 3 4 5 2 第三小数为3
 */
public class KMinNum {
    public static void main(String[] args) {
//        int arr[] = {2, 1, 3, 4, 5, 2};
        int arr[] = {1, 2, 2, 2, 2, 3, 4, 5};

        List<Integer> lists = Arrays.asList(1, 2, 2, 2, 2, 3, 4, 5);

        System.out.println("k1 = " + keyNum(arr, 3));
        System.out.println("k1 = " + findNumberK(lists, 3));
        System.out.println("k1 = " + quickSelect(arr,0,lists.size()-1, 3));
    }

    /**
     * 有序的数组，注意同样大小算一样大,相同的数跳过
     *
     * @param arr
     * @param k
     * @return
     */
    public static int keyNum(int[] arr, int k) {
        int length = arr.length;
        // 第一位就是 key
        int times = 1;
        for (int i = 0; i < length; i++) {
            if (times == k) {
                return arr[i];
            }
            //跳过重复的数
            while (i + i < length && arr[i] == arr[i + 1]) {
                i++;
            }
            times++;
        }
        return -1;
    }

    public static int findNumberK(List<Integer> lists, int k) {
        if (lists == null || lists.isEmpty()) {
            return -1;
        }
        List<Integer> smallList = new ArrayList<>();
        List<Integer> equalList = new ArrayList<>();
        List<Integer> largeList = new ArrayList<>();
        int pivot = lists.get(0);

        for (int i = 0; i < lists.size(); i++) {
            int temp = lists.get(i);
            if (temp < pivot) {
                smallList.add(temp);
            } else if (temp > pivot) {
                largeList.add(temp);
            } else {
                equalList.add(temp);
            }
        }

        if (k <= smallList.size()) {
            return findNumberK(smallList, k);
        } else if (k > (smallList.size() + equalList.size())) {
            return findNumberK(largeList, k - smallList.size() - equalList.size());
        } else {
            return pivot;
        }
    }


    static int quickSelect(int list[], int s, int t, int k) {
        int i = s, j = t;
        int temp;
        if (s < t) {
            temp = list[s];
            while (i != j) {
                while (j > i && list[j] >= temp) {
                    j--;
                }
                list[i] = list[j];
                while (i < j && list[i] <= temp) {
                    i++;
                }
                list[j] = list[i];
            }
            list[i] = temp;
            if (k - 1 == i) {
                return list[i];
            } else if (k - 1 < i) {
                return quickSelect(list, s, i - 1, k);
            } else {
                return quickSelect(list, i + 1, t, k);
            }
        } else if (s == t && s == k - 1){
            return list[k - 1];
        }
        return -1;
    }

}
