package leetcode.solutions;

public class Solution64 {


    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     *
     * Example:
     *
     * Input:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * Output: 7
     * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
     *
     *   [1,4,5],
     *   [2,5,1],
     *   [6,2,1]
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //init
        for (int i = 1; i < m; i ++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int j = 1; j < n; j ++) {
            grid[0][j] += grid[0][j - 1];
        }
        //dp
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }














































    public int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i ++) {
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }
        for (int i = 1; i < n; i ++) {
            dp[0][i] = grid[0][i] + dp[0][i-1];
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {

        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        int result = new Solution64().minPathSum(grid);
        System.out.println(result);
    }
}
