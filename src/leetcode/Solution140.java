package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution140 {

    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
     *
     * Note:
     *
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     *
     * Input:
     * s = "catsanddog"
     * wordDict = ["cat", "cats", "and", "sand", "dog"]
     * Output:
     * [
     *   "cats and dog",
     *   "cat sand dog"
     * ]
     *
     * Input:
     * s = "pineapplepenapple"
     * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
     * Output:
     * [
     *   "pine apple pen apple",
     *   "pineapple pen apple",
     *   "pine applepen apple"
     * ]
     * Explanation: Note that you are allowed to reuse a dictionary word.
     *
     * Input:
     * s = "catsandog"
     * wordDict = ["cats", "dog", "sand", "and", "cat"]
     * Output:
     * []
     * @param s
     * @param wordDict
     * @return
     */
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    public List<String> dfs(String s, List<String> wordDict, int start) {
        if (map.get(start) != null) {
            return map.get(start);
        }

        ArrayList<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end ++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = dfs(s, wordDict, end);
                for (String temp : list) {
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " " + temp));
                }
            }
        }

        map.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> result = new Solution140().wordBreak(s, wordDict);
        System.out.println(result);
    }
}
