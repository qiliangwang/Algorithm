package leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * 通过次数650,183提交次数1,940,312
 */
public class Solution15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i ++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int sum = 0 - nums[i];
            int lowIndex = i + 1;
            int highIndex = nums.length - 1;

            while (lowIndex < highIndex) {
                if (nums[lowIndex] + nums[highIndex] == sum) {
                    results.add(Arrays.asList(nums[i], nums[lowIndex], nums[highIndex]));
                    //move lower dup element
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
