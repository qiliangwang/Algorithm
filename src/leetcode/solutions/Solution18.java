package leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> result = new Solution18().fourSum(nums, 0);
        System.out.println(result);
    }
}
