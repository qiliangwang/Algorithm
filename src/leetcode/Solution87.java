package leetcode;

import java.util.Arrays;

public class Solution87 {

    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        // time limit issue
        char[] s1Char = s1.toCharArray();
        char[] s2Char = s2.toCharArray();
        Arrays.sort(s1Char);
        Arrays.sort(s2Char);
        String str1 = new String(s1Char);
        String str2 = new String(s2Char);
        if (!str1.equals(str2)) return false;

        for (int i = 1; i < s1.length(); i ++) {
            String s1Left = s1.substring(0, i);
            String s1Right = s1.substring(i);
            if ((isScramble(s1Left, s2.substring(0, i)) && isScramble(s1Right, s2.substring(i))) ||
                    (isScramble(s1Left, s2.substring(s2.length() - i))) && isScramble(s1Right, s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean result = new Solution87().isScramble("abb", "bba");
        System.out.println(result);
    }
}
