---
title: 22 Generate Parentheses
date: 2019-02-16 16:42:07
tags:
---

## Generate Parentheses

Given *n* pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given *n* = 3, a solution set is:

```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

## Solution

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> results = new ArrayList<>();
        if (n == 0) return results;
        generateParenthesis(results, "", n, n);
        return results;
    }

    private void generateParenthesis(List<String> results, String result, int left, int right) {
        if (left > right) return;
        if (left == 0 && right == 0) {
            results.add(result);
            return;
        }
        if (left > 0) {
            generateParenthesis(results, result + "(", left - 1, right);
        }
        if (right > 0) {
            generateParenthesis(results, result + ")", left, right - 1);
        }
    }
}
```

