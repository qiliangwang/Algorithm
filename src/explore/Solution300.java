package explore;

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

    public static void main(String[] args) {
        int[] data = {10, 9, 2,5,3,7,101};
        int result = new Solution300().lengthOfLIS(data);
        System.out.println(result);
    }
}
