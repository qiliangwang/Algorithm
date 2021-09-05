package leetcode.solutions;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * 0 - lt 是 < 1的
 * lt + 1 - gt -1 是 1
 * gt - last 是 > 1 的
 * 使用三路快排的思路来实现这个的排序o(N)的复杂度
 */
public class Solution75 {

    public void sortColors(int[] nums) {
        int lt = -1;
        int gt = nums.length;

        int i = 0;
        while (i < gt) {
            if (nums[i] == 0) {
                lt ++;
                swap(nums, lt, i);
                i ++;
            } else if (nums[i] == 1) {
                i ++;
            } else {
                gt --;
                swap(nums, gt, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
