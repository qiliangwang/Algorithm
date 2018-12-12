package leetcode.company;

import java.util.HashMap;

public class Solution8 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            int value = target - nums[i];
            if (valueIndexMap.containsKey(value)) {
                return (new int[] {valueIndexMap.get(value), i});
            }
            valueIndexMap.put(nums[i], i);
        }
        return null;
    }


    public static void main(String[] args) {
        int[] data = {2, 7, 11, 15};
        int[] result = new Solution8().twoSum(data, 13);
        for (int res: result) {
            System.out.print(res + " ");
        }
    }
}
