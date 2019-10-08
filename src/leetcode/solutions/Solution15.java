package leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
//        for (int i : nums) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        for (int i = 0; i < nums.length - 2; i ++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int sum = 0 - nums[i];
            int lowIndex = i + 1;
            int highIndex = nums.length - 1;

            while (lowIndex < highIndex) {
                if (nums[lowIndex] + nums[highIndex] == sum) {
                    results.add(Arrays.asList(nums[i], nums[lowIndex], nums[highIndex]));
//                    results.add(Arrays.asList(i, lowIndex, highIndex));
                    while (lowIndex < highIndex && nums[lowIndex] == nums[lowIndex + 1]) lowIndex ++;
                    while (lowIndex < highIndex && nums[highIndex] == nums[highIndex - 1]) highIndex --;
                    lowIndex ++;
                    highIndex --;
                } else if (nums[lowIndex] + nums[highIndex] < sum){
                    lowIndex ++;
                } else {
                    highIndex --;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = new Solution15().threeSum(nums);
        System.out.println(result);
    }
}
