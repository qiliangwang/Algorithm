package leetcode.solutions;

import java.util.HashSet;

/**
 * 128. Longest Consecutive Sequence
 * Hard
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * @author Vader Wang
 */
public class Solution128 {

    /**
     * using hashSet
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int low : nums) {
            if (!set.contains(low - 1)) {
                int high = low + 1;
                while (set.contains(high)) {
                    high ++;
                }
                res = Math.max(res, high - low);
            }
        }
        return res;
    }

    /**
     * using hashMap
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        int res = 0;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {100, 4, 200, 1, 3, 2};
        int[] nums2 = new int[] {1, 2, 3};
        int result = new Solution128().longestConsecutive(nums2);
        System.out.println(result);
    }
}
