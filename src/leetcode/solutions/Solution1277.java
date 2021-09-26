package leetcode.solutions;

/**
 * 1277. 统计全为 1 的正方形子矩阵
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 *
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 * 通过次数18,786提交次数25,931
 *
 *  这题和221是一样的，没啥特别的，
 */
public class Solution1277 {

    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {return 0;}
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
                    }
                    res += dp[i][j];
                }
            }
        }
        return res;
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
