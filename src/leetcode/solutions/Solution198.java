package leetcode.solutions;

import java.util.Arrays;

public class Solution198 {

    private int[] memo;

    /**
     * dp way
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        memo = new int[nums.length];
        memo[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            memo[i] = Math.max(memo[i - 1], nums[i] + (i - 2 >= 0 ? memo[i - 2] : 0));
        }
        return memo[nums.length - 1];
    }

    /**
     * recursive wat
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0);
    }

    private int tryRob(int[] nums, int start) {
        if (start >= nums.length)
            return 0;
        if (memo[start] != -1)
            return memo[start];
        return memo[start] = Math.max(tryRob(nums, start + 1), tryRob(nums, start + 2) + nums[start]);
    }

    public static void main(String[] args) {
        int[] nums = {};
        int result = new Solution198().rob(nums);
        System.out.println(result);
    }
}
