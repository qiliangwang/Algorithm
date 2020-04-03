---
title: 38. Count and Say
date: 2019-02-18 10:26:25
tags:
---

## Count and Say

The count-and-say sequence is the sequence of integers with the first five terms as following:

```
1.     1
2.     11
3.     21
4.     1211
5.     111221
```

`1` is read off as `"one 1"` or `11`.
`11` is read off as `"two 1s"` or `21`.
`21` is read off as `"one 2`, then `one 1"` or `1211`.

Given an integer *n* where 1 ≤ *n* ≤ 30, generate the *n*th term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

 

**Example 1:**

```
Input: 1
Output: "1"
```

**Example 2:**

```
Input: 4
Output: "1211"
```

## solution

```java
class Solution {
    public String countAndSay(int n) {
        int i = 1;
        String res = "1";
        while (i < n) {
            StringBuilder stringBuilder = new StringBuilder();
            int count = 1;
            char c = res.charAt(0);
            for (int j = 1; j < res.length(); j ++) {
                if (res.charAt(j) == c) {
                    count ++;
                } else {
                    stringBuilder.append(count);
                    stringBuilder.append(c);
                    count = 1;
                    c = res.charAt(j);
                }
            }
            stringBuilder.append(count);
            stringBuilder.append(c);
            res = stringBuilder.toString();
            i ++;
        }
        return res;
    }
}
```

