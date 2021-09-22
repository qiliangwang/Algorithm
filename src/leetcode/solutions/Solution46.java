package leetcode.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * @author wangql
 * @since 2021-09-16 12:18
 */
public class Solution46 {

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    backTrace(nums, new ArrayList<>(), new HashSet<>(), ans);
    return ans;
  }

  private void backTrace(int[] nums, List<Integer> permutation, Set<Integer> used, List<List<Integer>> ans) {
    if (nums.length == permutation.size()) {
      ans.add(new ArrayList<>(permutation));
    }

    for (int i = 0; i < nums.length; i ++) {
      if (!used.contains(nums[i])) {
        used.add(nums[i]);
        permutation.add(nums[i]);
        backTrace(nums, permutation, used, ans);
        used.remove(nums[i]);
        permutation.remove(permutation.size() - 1);
      }
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> permute = new Solution46().permute(new int[]{1, 2, 3});
    System.out.println(permute);
  }

}
