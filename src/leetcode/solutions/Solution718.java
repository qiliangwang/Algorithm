package leetcode.solutions;

/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 *
 *
 * 示例：
 *
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 *
 *
 * 提示：
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * 通过次数84,294提交次数149,352
 */
public class Solution718 {
    //dp[i][j] 代表 num[i] 和 nums[j]结尾的subarr的length
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        int max = 0;
        for (int i = 0; i < nums1.length; i ++) {
            for (int j = 0; j < nums2.length; j ++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = i > 0 && j >0 ? dp[i - 1][j -1] + 1 : 1;
                    max = Math.max(dp[i][j], max);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution718 solution = new Solution718();
        int length = solution.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
        System.out.println(length);
    }
}
