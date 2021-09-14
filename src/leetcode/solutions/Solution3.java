package leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

public class Solution3 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] freq = new char[256];
        int left = 0, right = 0, res = 0;
        while (left < s.length()) {
            if (right < s.length() && freq[s.charAt(right)] == 0) {
                freq[s.charAt(right++)] ++;
            } else {
                freq[s.charAt(left++)] --;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }


    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0, res = 0;
        Set<Character> set = new HashSet<>();
        while (left < s.length()) {
            if (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right ++;
                res = Math.max(res, right - left);
            } else {
                set.remove(s.charAt(left));
                left ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int result = new Solution3().lengthOfLongestSubstring2("abcabcbb");
        System.out.println(result);
    }
}
