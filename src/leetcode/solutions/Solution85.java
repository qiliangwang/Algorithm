package leetcode.solutions;

import java.util.Stack;

public class Solution85 {

    /**
     * Given a 2D binary matrix filled with 0's and 1's,
     * find the largest rectangle containing only 1's and return its area.
     *
     * Example:
     *
     * Input:
     * [
     *   ["1","0","1","0","0"],
     *   ["1","0","1","1","1"],
     *   ["1","1","1","1","1"],
     *   ["1","0","0","1","0"]
     * ]
     * Output: 6
     * @param matrix input matrix
     * @return max area
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int max = 0;
        int n = matrix[0].length;
        int[] heights = new int[n + 1];
        heights[n] = 0;

        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else
                    heights[j] ++;
            }
            max = Math.max(largestRectangleArea(heights), max);
        }
        return max;
    }

    private int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < heights.length; i ++) {
            int h = heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int start = stack.isEmpty() ? -1 : stack.peek();
                int area = height * (i - 1 - start);
                max = Math.max(area, max);
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
        int result = new Solution85().maximalRectangle(matrix);
        System.out.println(result);
    }
}
