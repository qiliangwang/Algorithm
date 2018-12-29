package leetcode;

public class Solution62 {

    /**
     * Input: m = 3, n = 2
     * Output: 3
     * Explanation:
     * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down
     * 2. Right -> Down -> Right
     * 3. Down -> Right -> Right
     *
     * case 3, 2:
     * [1, 1,
     *  1, 2
     *  1, 3]
     *
     * Input: m = 7, n = 3
     * Output: 28
     * solve with dp
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i ++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j ++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int result = new Solution62().uniquePaths(7, 3);
        System.out.println(result);
    }
}
