package leetcode.solutions;

import java.util.Arrays;

class Solution300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);
        for (int i = 0; i < nums.length; i ++) {
            for (int j = 0; j < i; j ++) {
                if (nums[i] > nums[j]) {
                    memo[i] = Math.max(memo[j] + 1, memo[i]);
                }
            }
        }
        //select max number
        int maxSubSequence = 0;
        for (int num:  memo) {
            if (num > maxSubSequence) {
                maxSubSequence = num;
            }
        }
        return maxSubSequence;
    }


    /**
     * 利用dp来解决最长上升子序列（这里是子序列不是子数组）
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {return 0;}
        //dp
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int i = 0; i < nums.length; i ++) {
            for (int j = 0; j < i; j ++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                maxLength = Math.max(maxLength, dp[i]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] data = {10, 9, 2,5,3,7,101};
        int result = new Solution300().lengthOfLIS(data);
        System.out.println(result);
    }
}
