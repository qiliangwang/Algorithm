package leetcode.solutions;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 *
 * 提示：
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class Solution1143 {

    /**
     * 这里是子序列，所以dp[i][j]定义为i,j内的最长 最后的结果直接用dp[i][j]表示
     * dp[i][j] = i == j ? dp[i - 1][j -1] + 1 : max(dp[i][j-1], dp[i-1][j])
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i ++) {
            for (int j = 0; j < text2.length(); j ++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = getWithBorder(dp, i -1, j -1) + 1;
                } else {
                    dp[i][j] = Math.max(getWithBorder(dp, i -1, j), getWithBorder(dp, i, j -1));
                }
            }
        }
        return dp[text1.length() - 1][text2.length() -1];
    }

    private int getWithBorder(int[][] dp, int i, int j) {
        if (i < 0 || j < 0) {return 0;}
        return dp[i][j];
    }

    public static void main(String[] args) {
        Solution1143 solution = new Solution1143();
        int i = solution.longestCommonSubsequence("abc", "def");
        System.out.println(i);
    }
}
