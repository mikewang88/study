package com.leetcode.day;

/**
 * @Author: MikeWang
 * @Date: 2021/3/18 6:58 PM
 * @Description: 最长递增子序列
 */
public class L300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i=0; i<nums.length;i++){
            dp[i] = 1;
        }

        for (int i=1; i<dp.length;i++){
            for (int j=0;j<i;j++){
                if (nums[i]>nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max =1;
        for (int i=0;i<dp.length;i++){
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}
