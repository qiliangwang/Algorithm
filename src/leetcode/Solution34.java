package leetcode;

public class Solution34 {

    /**
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     *
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int first = findFirst(nums, target);
        if (first == -1) return new int[]{-1, -1};
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target > nums[mid]) {
                start = mid;
            }else {
                end = mid;
            }

        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }

    private int findLast(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target < nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int[] result = new Solution34().searchRange(nums, 7);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
