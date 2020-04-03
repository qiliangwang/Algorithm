---
title: 30 Substring with Concatenation of All Words
date: 2019-02-18 10:09:16
tags:
---

## Substring with Concatenation of All Words

You are given a string, **s**, and a list of words, **words**, that are all of the same length. Find all starting indices of substring(s) in **s** that is a concatenation of each word in **words** exactly once and without any intervening characters.

**Example 1:**

```
Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
```

**Example 2:**

```
Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
```

## solution

```java
class Solution {
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
}
```

