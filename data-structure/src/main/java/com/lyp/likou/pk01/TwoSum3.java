package com.lyp.likou.pk01;

import java.util.HashMap;

/**
 * 两数之和
 */
public class TwoSum3 {

    /**
     * 一遍哈希表
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) throws Exception {
        HashMap<Integer,Integer> hash = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            hash.put(nums[i],i);
        }

        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(hash.containsKey(complement)){
                return new int[]{i,hash.get(complement)};
            }
            hash.put(nums[i],i);
        }

        throw  new Exception("没有结果");
    }

    public static void main(String[] args) throws Exception {
        int [] nums1 = {2, 7, 11, 15};
        int[] result1 = twoSum(nums1,9);
        System.out.println(result1[0] + ", " + result1[1]);

        System.out.println();

        int [] nums2 = {2,7,1,8};
        int [] result2 = twoSum(nums2,9);
        System.out.println(result2[0] + " ," + result2[1]);
    }


}
