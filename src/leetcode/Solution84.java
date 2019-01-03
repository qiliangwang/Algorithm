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
        for (int i = 1; i < heights.length; i ++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int area = (i - stack.peek()) * heights[stack.pop()];
                max = Math.max(max, area);
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = {2, 1, 5, 6, 2, 3, 0};
        int result = new Solution84().largestRectangleArea(height);
        System.out.println(result);
    }
}
