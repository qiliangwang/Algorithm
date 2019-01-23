package leetcode;

/**
 * @author Vader Wang
 */
public class Solution344 {

    /**
     * 344. Reverse String
     *
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * You may assume all the characters consist of printable ascii characters.
     *
     *
     *
     * Example 1:
     *
     * Input: ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     * Example 2:
     *
     * Input: ["H","a","n","n","a","h"]
     * Output: ["h","a","n","n","a","H"]
     * @param s
     */
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0, right = s.length - 1;
        char temp;
        while (left < right) {
            temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

    public void reverseString2(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        char temp;
        for (int i = 0; i < s.length / 2 ; i ++) {
            temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }

    private static void printCharArr(char[] s) {
        for (int i = 0; i < s.length; i ++) {
            System.out.print(s[i] + " ");
        }
    }
    public static void main(String[] args) {
        char[] s = new char[] {'h', 'e', 'l', 'l', 'o'};
        new Solution344().reverseString(s);
        printCharArr(s);
    }
}
