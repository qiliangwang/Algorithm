package leetcode.solutions;


/**
 * 二分搜索
 */
public class Solution704 {

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        return binarySearchRecursive(nums, 0, nums.length-1, target);
    }

    private int binarySearch(int[] nums, int low, int high, int target) {
        while (low <= high) {

            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {return mid;}

            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private int binarySearchRecursive(int[] nums, int low ,int high, int target) {
        if (low > high) {return -1;}

        int mid = low + (high - low) / 2;
        if (nums[mid] == target) {return mid;}

        if (nums[mid] < target) {
            return binarySearchRecursive(nums, mid + 1, high, target);
        } else {
            return binarySearchRecursive(nums, low, mid - 1, target);
        }
    }
}
