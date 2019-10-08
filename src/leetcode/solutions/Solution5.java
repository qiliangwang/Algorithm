package leetcode.solutions;

public class Solution5 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        for (int j = 0; j < s.length(); j ++) {
            for (int i = 0; i <= j; i ++) {
                //if (j - i <=2) is false the look at dp[i + 1][ j - 1]) like if statement
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i <=2) || dp[i + 1][ j - 1]);
                if (dp[i][j]) {
                    //the length of palindrome str
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String result = new Solution5().longestPalindrome("c");
        System.out.println(result);
    }
}
