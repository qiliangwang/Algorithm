package leetcode;

public class Solution27 {

    public int removeElement2(int[] nums, int val) {
        int result = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != val) {
                nums[result] = nums[i];
                result ++;
            }
        }
        return result;
    }

    public int removeElement(int[] nums, int val) {
        int result = 0;
        for (int i: nums) {
            if (i != val) {
                nums[result++] = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int result = new Solution27().removeElement(nums, 3);
        System.out.println(result);
    }
}
