---
title: 49 Group Anagrams
date: 2019-02-18 12:50:47
tags:
---

## Group Anagrams

Given an array of strings, group anagrams together.

**Example:**

```
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

**Note:**

- All inputs will be in lowercase.
- The order of your output does not matter.

## solution

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strList) {
        List<List<String>> results = new ArrayList<>();
        HashMap<String, List<String>> strIndexMap = new HashMap<>();
        for (String str: strList) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (strIndexMap.containsKey(key)) {
                strIndexMap.get(key).add(str);
            }else {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(str);
                strIndexMap.put(key, strings);
            }
        }
        for (String str: strIndexMap.keySet()) {
            results.add(strIndexMap.get(str));
        }
        return results;
    }
}
```

