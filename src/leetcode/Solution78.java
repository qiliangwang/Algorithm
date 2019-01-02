package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution78 {

    /**
     * Given a set of distinct integers, nums, return all possible subsets (the power set).
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * Example:
     *
     * Input: nums = [1,2,3]
     * Output:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        findSubsets(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void findSubsets(List<List<Integer>> res, List<Integer> list, int[] nums, int level) {
        res.add(new ArrayList<>(list));
        for (int i = level; i < nums.length; i ++) {
            list.add(nums[i]);
            findSubsets(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new Solution78().subsets(nums);
        System.out.println(result);
    }
}
