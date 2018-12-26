package leetcode;

public class Solution41 {

    /**
     * Given an unsorted integer array, find the smallest missing positive integer.
     *
     * Input: [1,2,0] -> len is 3 -> [1, 2, 3] -> missing 3
     * [1, 2, 0] -> [1, 2, 0] -> (2( index of 0) + 1) -> 3
     * Output: 3
     *
     * Input: [3,4,-1,1] -> len is 4 -> [1, 2, 3, 4] -> missing 2
     * [3, 4, -1, 1] -> [-1, 4, 3, 1] -> [-1, 1, 3, 4] -> [1, -1, 3, 4]
     * Output: 2
     *
     * Input: [7,8,9,11,12] -> len is 5 -> [1, 2, 3, 4, 5] -> missing 1
     * [7, 8, 9, 11, 12] -> [7, 8, 9, 11, 12]
     * Output: 1
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] < nums.length && nums[i] != nums[nums[i] - 1]) {
                //put nums[i] to right position
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        //for loop to find first missing position
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int result = new Solution41().firstMissingPositive(nums);
        System.out.println(result);
    }
}
