package leetcode;

import java.util.HashMap;

public class Solution169 {

    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        int result = new Solution169().majorityElement(nums);
        System.out.println(result);
    }
}
