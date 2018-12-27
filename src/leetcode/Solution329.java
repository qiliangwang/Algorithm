package leetcode;

public class Solution329 {

    /**
     * Given an integer matrix, find the length of the longest increasing path.
     *
     * From each cell, you can either move to four directions: left, right, up or down.
     * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
     *
     * Example 1:
     *
     * Input: nums =
     * [
     *   [9,9,4],
     *   [6,6,8],
     *   [2,1,1]
     * ]
     * Output: 4
     * Explanation: The longest increasing path is [1, 2, 6, 9].
     * Example 2:
     *
     * Input: nums =
     * [
     *   [3,4,5],
     *   [3,2,6],
     *   [2,2,1]
     * ]
     * Output: 4
     * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
     * @param matrix
     * @return
     */

    private int m, n;
    private int[][] cache;
    //left right down up
    private int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        cache = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                int temp = dfs(matrix, i, j, Integer.MIN_VALUE);
                res = Math.max(temp, res);
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int x, int y, int min) {
        if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= min) return 0;
        if (cache[x][y] != 0) return cache[x][y];

        min = matrix[x][y];
        int res = 0;
        for (int i = 0; i < 4; i ++) {
            int temp = dfs(matrix, x + d[i][0], y + d[i][1], min) + 1;
            res = Math.max(temp, res);
        }
        cache[x][y] = res;
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        int result = new Solution329().longestIncreasingPath(matrix);
        System.out.println(result);
    }
}
