package leetcode.solutions;

import java.util.stream.IntStream;

class Solution416 {

    public boolean canPartition(int[] nums) {
        int sum = IntStream.of(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }
        int n = nums.length;
        int c = sum / 2;
        boolean[] memo = new boolean[c + 1];
        for (int i = 0; i <= c; i ++) {
            memo[i] = (nums[0] == i);
        }
        for (int i = 1; i < n; i ++) {
            for (int j = c; j >= nums[i]; j --) {
                memo[j] = memo[j] || memo[j - nums[i]];
            }
        }
        return memo[c];
    }

    private static void printBool(boolean res){
        System.out.println(res ? "True" : "False");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        printBool((new Solution416()).canPartition(nums1));

        int[] nums2 = {1, 2, 3, 5};
        printBool((new Solution416()).canPartition(nums2));

        int[] nums3 = {1, 1};
        printBool((new Solution416()).canPartition(nums3));
    }
}
