package leetcode;

public class Solution213 {

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
    }

    private int rob(int[] nums, int start, int end) {
        int rob = 0, notRob = 0;
        for (int i = start; i < end; i ++) {
            int temp = Math.max(rob, notRob);
            rob = nums[i] + notRob;
            notRob = temp;
        }
        return Math.max(rob, notRob);
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1};
        int result = new Solution213().rob(nums);
        System.out.println(result);
    }
}
