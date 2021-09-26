package leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * @author wangql
 * @since 2021-09-16 12:18
 */
public class Solution47 {

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    backTrace(nums, new ArrayList<>(), new HashSet<>(), ans);
    return ans;
  }

  private void backTrace(int[] nums, List<Integer> permutation, Set<Integer> used, List<List<Integer>> ans) {
    if (nums.length == permutation.size()) {
      ans.add(new ArrayList<>(permutation));
    }
    for (int i = 0; i < nums.length; i ++) {
      if (used.contains(i) || ( i > 0 && nums[i] == nums[i -1] && !used.contains(i - 1))) {
        continue;
      }
      used.add(i);
      permutation.add(nums[i]);
      backTrace(nums, permutation, used, ans);
      used.remove(i);
      permutation.remove(permutation.size() - 1);
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> lists = new Solution47().permuteUnique(new int[]{1, 1, 2});
    System.out.println(lists);
  }
}
