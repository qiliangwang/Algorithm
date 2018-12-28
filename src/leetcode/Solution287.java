package leetcode;

public class Solution287 {

    /**
     *Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
     *prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     *
     * Example 1:
     *
     * Input: [1,3,4,2,2]
     * Output: 2
     * Example 2:
     *
     * tortoise: 1
     * hare: 1
     *
     * value [1, 3, 4, 2, 2]
     * index [0, 1, 2, 3, 4]
     *
     * index cycle: 1 -> 3 -> 2 -> 4 -> 2
     * value      : 3 -> 2 -> 4 -> 2 -> 4
     *
     *
     * Input: [3,1,3,4,2]
     * Output: 3
     * Note:
     *
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     */

    public int findDuplicate(int[] nums) {
        // find the enter point
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        int result = new Solution287().findDuplicate(nums);
        System.out.println(result);
    }
}
