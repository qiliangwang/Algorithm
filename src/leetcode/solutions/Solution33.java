package leetcode.solutions;

public class Solution33 {

    /**
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int start = 0, end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) return mid;
            //left part is ordered
            if (nums[start] < nums[mid]) {
                // start < target < mid
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else start = mid;
            } else {
                // mid < target < end
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else end = mid;
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4};
        int result = new Solution33().search(nums, 4);
        System.out.println(result);
    }
}
