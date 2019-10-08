package leetcode.solutions;

import java.util.Arrays;

public class Solution16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int results = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i ++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target) {
                    right --;
                }else {
                 left ++;
                }
                if (Math.abs(sum - target) < Math.abs(results - target)) {
                    results = sum;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
//        int[] nums = {-1, 2, 1, -4};
        int[] nums = {0, 0, 0};
        int result = new Solution16().threeSumClosest(nums, -1);
        System.out.println(result);
    }
}
