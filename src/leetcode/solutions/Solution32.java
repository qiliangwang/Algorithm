package leetcode.solutions;

import java.util.Stack;

/**
 * @author Vader Wang
 */
public class Solution32 {

    /**
     * 32. Longest Valid Parentheses
     * Hard
     *
     * 1430
     *
     * 68
     *
     * Favorite
     *
     * Share
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     *
     * Example 1:
     *
     * Input: "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()"
     * Example 2:
     *
     * Input: ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()"
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int leftMax = -1;
        int res = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    leftMax = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - leftMax);
                    } else {
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int result = new Solution32().longestValidParentheses(")()())");
        System.out.println(result);
    }
}
