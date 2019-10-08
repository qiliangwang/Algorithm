package leetcode.solutions;

public class Solution58 {

    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        for (; end > 0; end --) {
            if (s.charAt(end) == ' ') {
                break;
            }
        }
        return end;
    }

    public int lengthOfLastWord2(String s) {
        return s.length() - s.lastIndexOf(" ") - 1;
    }

    public static void main(String[] args) {
        int result = new Solution58().lengthOfLastWord2("Hello World");
        System.out.println(result);
    }
}
