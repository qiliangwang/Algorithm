package leetcode;

/**
 * @author Vader Wang
 */
public class Solution162 {

    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return nums[start] > nums[end] ? start : end;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,1};
        int result = new Solution162().findPeakElement(nums);
        System.out.println(result);
    }
}
