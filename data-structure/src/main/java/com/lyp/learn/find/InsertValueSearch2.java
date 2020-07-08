package com.lyp.learn.find;

/**
 * 插值查找
 *
 *  1) 插值查找原理介绍: 插值查找算法类似于二分查找，不同的是插值查找每次从自适应 mid 处开始查找。
 *  2) 将折半查找中的求mid索引的公式,low表示左边索引left,high表示右边索引right. key 就是前面我们讲的 findVal
 *  3) int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]) ;
 *     对应前面的代码公式:
 *     int mid = left+(right – left)*(findVal – arr[left])/(arr[right] – arr[left])
 */
public class InsertValueSearch2 {
    private static int [] arr = {2,6,9,11,16,20,23,25,26,29,33,35};

    /**
     * 查找为key 的数据元素
     * 若找到，则返回其下标，
     * 若没有找到，返回 -1；
     *
     * int [] arr = {2,6,9,11,16,20,23,25,26,29,33,35}; //查找 3 报异常
     * 说明 插值查找，不可用
     */

    public static int findKey(int key){
        if(arr == null){
            return -1;
        }
        int length = arr.length;
        int low = 0;
        int high = length - 1;

        if(length <= 0 || arr[low] > key || arr[high] < key){
            return  -1;
        }

        while(low <= high){
//            int mid = (low + high) / 2;
            int mid = low + (high-low)*(key -arr[low])/(arr[high]-arr[low]);
            if(key < arr[mid]){
                high = mid - 1;
            }else if(key > arr[mid]){
                low = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        testFindKey(2);
//        testFindKey(6);
//        testFindKey(9);
//        testFindKey(23);
//        testFindKey(33);
//        testFindKey(35);
        testFindKey(3);
        testFindKey(60);
    }

    private static void testFindKey(int i) {
        int index1 = findKey(i);
        if (index1 >= 0) {
            System.out.println(arr[index1]);
        } else {
            System.out.println("没有找到");
        }
    }
}
