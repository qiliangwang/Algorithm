package leetcode.solutions;

/**
 * 695. 岛屿的最大面积
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 *
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 *
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * 示例 2：
 *
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 * @author wangql
 * @since 2021-09-26 12:26
 */
public class Solution695 {

  private int maxArea, m, n, tmpMaxArea;
  boolean[][] visited;
  int[][] dirs = {{-1,0}, {0,1}, {1,0},{0,-1}};

  /**
   * 使用dfs来计算岛屿最大面积 其实就是dfs的时候如果成功就+1
   * @param grid
   * @return
   */
  public int maxAreaOfIsland(int[][] grid) {
    maxArea = 0;
    tmpMaxArea = 0;
    m = grid.length;
    n = grid[0].length;

    visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j ++) {
        tmpMaxArea = 0;
        dfs(grid, i, j);
      }
    }
    return maxArea;
  }

  private void dfs(int[][] grid, int x, int y) {
    if (inArea(x, y) && !visited[x][y] && grid[x][y] == 1) {
      visited[x][y] = true;
      tmpMaxArea ++;
      maxArea = Math.max(maxArea, tmpMaxArea);
      for (int[] dir : dirs) {
        dfs(grid, x+dir[0], y+dir[1]);
      }
    }
  }

  private boolean inArea(int x, int y) {
    return (x >= 0 && x < m) && (y >=0 && y < n);
  }

  public static void main(String[] args) {
    int[][] grid = new int[][]{
        {0,0,1,0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,0,0,1,1,1,0,0,0},
        {0,1,1,0,1,0,0,0,0,0,0,0,0},
        {0,1,0,0,1,1,0,0,1,0,1,0,0},
        {0,1,0,0,1,1,0,0,1,1,1,0,0},
        {0,0,0,0,0,0,0,0,0,0,1,0,0},
        {0,0,0,0,0,0,0,1,1,1,0,0,0},
        {0,0,0,0,0,0,0,1,1,0,0,0,0}
    };

    int i = new Solution695().maxAreaOfIsland(grid);
    System.out.println(i);
  }

}
