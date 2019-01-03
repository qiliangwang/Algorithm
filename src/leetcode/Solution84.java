package leetcode;

import java.util.Stack;

public class Solution84 {

    /**
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
     *
     * find the area of largest rectangle in the histogram.
     *
     * Input: [2,1,5,6,2,3]
     * Output: 10
     */
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i <= heights.length; i ++) {
            int h = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int start = stack.isEmpty() ? -1 : stack.peek();
                int area = (i - start - 1) * height;
                max = Math.max(max, area);
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] height = {2, 1, 5, 6, 2, 3, 0};
//        int[] height = {1, 2, 3, 4, 5, 6, 7, 8};
//        int[] height = {8, 7, 6, 5, 4, 3, 2, 1};
        int[] height = {2, 3, 4, 5, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4};

        int result = new Solution84().largestRectangleArea(height);
        System.out.println(result);
    }
}
