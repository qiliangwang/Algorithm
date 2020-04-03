---
title: 7 Reverse Integer
date: 2019-02-16 16:29:34
tags:
---

## Reverse Integer

Given a 32-bit signed integer, reverse digits of an integer.

**Example 1:**

```
Input: 123
Output: 321
```

**Example 2:**

```
Input: -123
Output: -321
```

**Example 3:**

```
Input: 120
Output: 21
```

## solution

```java
class Solution {
    public int reverse(int x) {
        Long result = 0L;
        while (x != 0) {
            int lastNumber = x % 10;
            x = x /10;
            result = result * 10 + lastNumber;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
                return 0;
        }
        return result.intValue();
    }
}
```

