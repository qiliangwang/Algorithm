package leetcode;

import java.util.Arrays;

public class Solution31 {

    public void nextPermutation(int[] nums) {
        int flag = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                System.out.println(i);
                flag = i;
                break;
            }
        }
        Arrays.sort(nums, flag, nums.length);
        if (flag == 0) {
            return;
        }
        for (int j = flag; j < nums.length; j ++) {
            if (nums[j] > nums[flag - 1]) {
                int temp = nums[j];
                nums[j] = nums[flag - 1];
                nums[flag - 1] = temp;
                break;
            }
        }
    }
    public static void main(String[] args) {
        int[] nums= {1, 3, 2};
        new Solution31().nextPermutation(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
