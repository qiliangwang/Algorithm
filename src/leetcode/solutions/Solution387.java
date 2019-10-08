package leetcode.solutions;

public class Solution387 {

    public int firstUniqChar(String s) {

        char[] memo = new char[26];
        for (int i = 0; i < s.length(); i ++) {
            memo[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < s.length(); i ++) {
            if (memo[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int result = new Solution387().firstUniqChar("leetcode");
        System.out.println(result);
    }
}
