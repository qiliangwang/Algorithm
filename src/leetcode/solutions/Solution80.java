package leetcode.solutions;

public class Solution80 {

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int count = 2;
        for (int i = 2; i < nums.length; i ++) {
            if (nums[i] != nums[count - 2]) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3}; // should return 5
        int[] nums2 = {0, 0, 1, 1, 1, 1, 2, 3, 3}; // should return 7

        int result = new Solution80().removeDuplicates(nums2);
        System.out.println(result);
    }
}
