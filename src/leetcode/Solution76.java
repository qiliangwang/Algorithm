package leetcode;

import java.util.HashMap;

public class Solution76 {

    /**
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
     *
     * Example:
     *
     * Input: S = "ADOBECODEBANC", T = "ABC"
     * Output: "BANC"
     * Note:
     *
     * If there is no such window in S that covers all characters in T, return the empty string "".
     * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        HashMap<Character, Integer> wordDict = constructWordDict(t);

        int index = 0, slow = 0, matchTotal = 0, minLen = Integer.MAX_VALUE;

        for (int fast = 0; fast < s.length(); fast ++) {
            char ch = s.charAt(fast);
            Integer count = wordDict.get(ch);
            if (count == null) {
                continue;
            }
            wordDict.put(ch, count - 1);
            // count == 1 means after count - 1, count is 0
            if (count == 1) {
                matchTotal ++;
            }
            //already covers all characters in T -> move slow
            while (matchTotal == wordDict.size()) {
                if (fast - slow + 1 < minLen) {
                    minLen = fast - slow + 1;
                    index = slow;
                }
                char left = s.charAt(slow ++);

                Integer leftCount = wordDict.get(left);
                if (leftCount == null) {
                    continue;
                }
                //get value from wordDict -> remove slow index value -> count + 1
                wordDict.put(left, leftCount + 1);
                if (leftCount == 0) {
                    matchTotal --;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(index, index + minLen);
    }

    private HashMap<Character, Integer> constructWordDict(String t) {
        HashMap<Character, Integer> wordDict = new HashMap<>();
        for (Character c: t.toCharArray()) {
            Integer count = wordDict.get(c);
            if (count != null) {
                wordDict.put(c, count + 1);
            } else {
                wordDict.put(c, 1);
            }
        }
        return wordDict;
    }

    public static void main(String[] args) {
        String result = new Solution76().minWindow("ADOBECODEBANC", "ABC");
        System.out.println(result);
    }
}
