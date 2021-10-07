package leetcode.solutions;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * 通过次数71,506提交次数171,010
 */
public class Solution224 {
    //2 stack
    int idx = 0;
    public int calculate(String s) {
        return doCalculate(s);
    }

    private int doCalculate(String s) {
        int res = 0, sign = 1;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                //parse digit
                int val = 0;
                while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
                    val = val * 10 + s.charAt(idx) - '0';
                    idx ++;
                }
                res += sign * val;
            } else if (s.charAt(idx) == '(') {
                idx ++;
                res += sign * doCalculate(s);
            } else if (s.charAt(idx) == '+') {
                sign = 1;
                idx ++;
            } else if (s.charAt(idx) == '-') {
                sign = -1;
                idx ++;
            } else if (s.charAt(idx) == ')') {
                idx ++;
                return res;
            } else {
                idx ++;
            }
        }
        return res;
    }

    private int dfs(String s, int[] idx) {
        int res = 0, sign = 1;
        while (idx[0] < s.length()) {
            char c = s.charAt(idx[0]++);
            if (Character.isDigit(c)) {
                int val = c - '0';

                while (idx[0] < s.length() && Character.isDigit(s.charAt(idx[0]))) {
                    val = val * 10 + s.charAt(idx[0]++) - '0';
                }

                res += val * sign;

            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                int ret = dfs(s, idx);
                res += ret * sign;
            } else if (c == ')') {
                return res;
            }
        }

        return res;
    }
    //recursive
    public int calculate2(String s) {
        return 0;
    }

    public static void main(String[] args) {
        Solution224 solution = new Solution224();
        int calculate = solution.calculate(" 2-1 + 2 ");
        System.out.println(calculate);
    }

}
