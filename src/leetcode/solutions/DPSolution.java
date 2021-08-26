package leetcode.solutions;

import java.util.Arrays;

/**
 * @author wangql
 * @since 2021-01-05 18:16
 */
public class DPSolution {

  public int findLengthOfLISCount(int[] nums) {
    int n = nums.length; if (n==0) { return 0; }

    // 初始化状态
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int[] count = new int[n];
    Arrays.fill(count, 1);
    for (int j = 0; j < n; j++) {
      for (int i = 0; i < j; i++) {
        if (nums[i] < nums[j]) {
          if (dp[i]+1 > dp[j]) {
            dp[j] = dp[i]+1;
            count[j] = count[i];
          } else if (dp[i]+1==dp[j]) {
            count[j] += count[i];
          }
        }
      }
    }
    int maxLength = 0; // 求出 maxLength
    for (int it : dp) { maxLength = Math.max(maxLength, it); }
    int res = 0; // 定义备选答案的变量
    for (int i = 0; i < n; i++) {
      if (maxLength == dp[i]) {
        res+=count[i];
      }
    }
    return res; // 输出答案
  }

  public static void main(String[] args) {
    DPSolution dpSolution = new DPSolution();
    int[] nums = {10, 9, 1, 5, 2, 6, 66, 18};
    int lengthOfLISCount = dpSolution.findLengthOfLISCount(nums);

  }
}
