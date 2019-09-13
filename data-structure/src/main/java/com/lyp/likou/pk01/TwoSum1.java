package com.lyp.likou.pk01;

/**
 * 两数之和
 */
public class TwoSum1 {

    /**
     * 暴力解法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int [] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++ ){
                if(nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums1 = {2, 7, 11, 15};
        int[] result1 = twoSum(nums1,9);
        System.out.println(result1[0] + ", " + result1[1]);

        System.out.println();

        int [] nums2 = {2,7,1,8};
        int [] result2 = twoSum(nums2,9);
        System.out.println(result2[0] + " ," + result2[1]);
    }


}
