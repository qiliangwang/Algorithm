package leetcode;

public class Solution10 {

    /**
     * solve it with dp
     * @param s string
     * @param p patten
     * @return
     */
    public boolean isMatch(String s, String p) {
        int i = s.indexOf(p);

        return false;
    }

    public static void main(String[] args) {
        boolean result = new Solution10().isMatch("19087", "a");
        System.out.println(result);
    }
}
