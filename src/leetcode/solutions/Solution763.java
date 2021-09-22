package leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution763 {
    /**
     * 先计算每个字母最后出现的idx 然后遍历这个数组 start end = 0
     * 如果之当前最end的和当前idx相等那么可以生成一个区间了
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> lastIdx = new HashMap<>();
        for (int i=0; i < s.length(); i ++) {
            lastIdx.put(s.charAt(i), i);
        }
        int start = 0, end = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i ++) {
            end = Math.max(lastIdx.get(s.charAt(i)), end);
            if (end == i) {
                ans.add(end - start + 1);
                start = i + 1;
                end = i + 1;
            }
        }
        return ans;
    }

    boolean[][] visited;

    int[][] dirs = {{-1, 0},{0, -1},{1, 0},{0, 1}};

    int m, n;
    /**
     * PRACTICE hh
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int nums = 0;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, i, j);
                    nums++;
                }
            }
        }
        return nums;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (inArea(i, j) && grid[i][j] == '1' && !visited[i][j]) {
            visited[i][j] = true;
            for (int[] dir : dirs) {
                dfs(grid, i + dir[0], j + dir[1]);
            }
        }
    }

    private boolean inArea(int i, int j) {
        return (i >= 0 && i < m) && (j >= 0 && j < n);
    }


    public static void main(String[] args) {
        List<Integer> abc = new Solution763().partitionLabels("abc");
        System.out.println(abc);


        char grid1[][] = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println((new Solution763()).numIslands(grid1));
        //1
        char grid2[][] = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println((new Solution763()).numIslands(grid2));
        //3
    }
}
