package leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution30 {


    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null || s.length() == 0 || words.length == 0) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();

        HashMap<String, Integer> wordDict = new HashMap<>();
        for (String word : words) {
            wordDict.put(word, wordDict.getOrDefault(word, 0) + 1);
        }

        int m = words.length;
        int n = words[0].length();

        for (int i = 0; i <= s.length() - m * n; i ++) {
            HashMap<String, Integer> copyDict = new HashMap<>(wordDict);
            int k = m;
            int j = i;
            while (k > 0) {
                String str = s.substring(j, j + n);
                if (!copyDict.containsKey(str) || copyDict.get(str) < 1) {
                    break;
                }
                copyDict.put(str, copyDict.get(str) - 1);
                k --;
                j += n;
                if (k == 0) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "";
        String[] words = {};
        List<Integer> result = new Solution30().findSubstring(s, words);
        System.out.println(result);
    }
}
