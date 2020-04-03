---
title: 35 Search Insert Position
date: 2019-02-18 10:21:42
tags:
---

## Search Insert Position

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

**Example 1:**

```
Input: [1,3,5,6], 5
Output: 2
```

**Example 2:**

```
Input: [1,3,5,6], 2
Output: 1
```

**Example 3:**

```
Input: [1,3,5,6], 7
Output: 4
```

**Example 4:**

```
Input: [1,3,5,6], 0
Output: 0
```



## Solution

```java
public class Solution {

    public int searchInsert(int[] nums, int target) {
        //[a, b)
        int left = 0, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target <= nums[left]) {
            return left;
        } else if (target <= nums[right - 1]) {
            return right - 1;
        } else {
            return right;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int result = new Solution().searchInsert(nums, 2);
        System.out.println(result);
    }
}

```

