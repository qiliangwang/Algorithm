---
title: 4 Median of Two Sorted Arrays
date: 2019-02-16 16:25:25
tags:
---

## Median of Two Sorted Arrays

There are two sorted arrays **nums1** and **nums2** of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume **nums1** and **nums2** cannot be both empty.

**Example 1:**

```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```

**Example 2:**

```
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
```

## Solution

```java
class Solution {
    public double findMedianSortedArrays(int[] numbers1, int[] numbers2) {
        // make sure numbers1 < numbers2
        if (numbers1.length > numbers2.length) {
            return findMedianSortedArrays(numbers2, numbers1);
        }
        int length = numbers1.length +  numbers2.length;
        int cut1 = 0;
        int cut2 = 0;
        int left = 0;
        int right = numbers1.length;
        while (cut1 <= numbers1.length) {
            //binary search
            cut1 = left +  (right - left) / 2;
            cut2 = length / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : numbers1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : numbers2[cut2 - 1];
            double R1 = (cut1 == numbers1.length) ? Integer.MAX_VALUE : numbers1[cut1];
            double R2 = (cut2 == numbers2.length) ? Integer.MAX_VALUE : numbers2[cut2];
            if (L1 > R2) {
                //move left
                right = cut1 - 1;
            }else if (L2 > R1) {
                //move right
                left = cut1 + 1;
            }else {
                if (length % 2 == 0) {
                    L1 = (L1 > L2) ? L1 : L2;
                    R1 = (R1 < R2) ? R1 : R2;
                    return (L1 + R1) / 2;
                }else {
                    return (R1 < R2) ? R1 : R2;
                }
            }
        }
        return -1;
    }
}
```

