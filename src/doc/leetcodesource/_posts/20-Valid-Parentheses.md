---
title: 20 Valid Parentheses
date: 2019-02-16 16:40:11
tags:
---

## Valid Parentheses

Given a string containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

**Example 1:**

```
Input: "()"
Output: true
```

**Example 2:**

```
Input: "()[]{}"
Output: true
```

**Example 3:**

```
Input: "(]"
Output: false
```

**Example 4:**

```
Input: "([)]"
Output: false
```

**Example 5:**

```
Input: "{[]}"
Output: true
```

这题目思路是这样的要是是左边 ( [ { 就push进，

## Solution

```java
class Solution {
     public boolean isValid (String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                if(c == ')' && pop != '('){
                    return false;
                }
                if(c == ']' && pop != '['){
                    return false;
                }
                if(c == '}' && pop != '{'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
```

