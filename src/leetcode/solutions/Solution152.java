package leetcode.solutions;

/**
 * @author Vader Wang
 */
public class Solution152 {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int res = nums[0];
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            int currentMin = Math.min(Math.min(min * nums[i], max * nums[i]), nums[i]);
            int currentMax = Math.max(Math.max(min * nums[i], max * nums[i]), nums[i]);
            res = Math.max(res, currentMax);
            min = currentMin;
            max = currentMax;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {-2, 0, -1};
        int result = new Solution152().maxProduct(nums);
        System.out.println(result);
    }
}
