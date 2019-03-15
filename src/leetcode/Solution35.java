package leetcode;

public class Solution35 {

    public int searchInsert(int[] nums, int target) {
        //[a, b)
        int left = 0, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target <= nums[left]) {
            return left;
        } else if (target <= nums[right - 1]) {
            return right - 1;
        } else {
            return right;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int result = new Solution35().searchInsert(nums, 2);
        System.out.println(result);
    }
}
