package leetcode.solutions;

/**
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 *
 *
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 * 通过次数137,831提交次数289,815
 */
public class Solution221 {

    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     *
     * Example:
     *
     * Input:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * Output: 4
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int res = 0, m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i ++) {
            for (int j = 1; j <= n; j ++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }

    /**
     * dp存储了最大的边长
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {return 0;}
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
                    }
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        int result = new Solution221().maximalSquare2(matrix);
        System.out.println(result);
        double pow = Math.pow(2, 2);
        System.out.println(pow);
    }
}
