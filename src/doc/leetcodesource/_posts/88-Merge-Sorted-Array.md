---
title: 88 Merge Sorted Array
date: 2019-02-18 16:29:52
tags:
---

https://leetcode.com/problems/merge-sorted-array/

Given two sorted integer arrays *nums1* and *nums2*, merge *nums2* into *nums1* as one sorted array.

**Note:**

- The number of elements initialized in *nums1* and *nums2* are *m* and *n* respectively.
- You may assume that *nums1* has enough space (size that is greater or equal to *m* + *n*) to hold additional elements from *nums2*.

**Example:**

```
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
```

solution

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int ptr3 = nums1.length-1;
        int ptr1 = m-1;
        int ptr2 = n-1;

        while(ptr2 >= 0 && ptr1 >=0){
            if(nums1[ptr1] > nums2[ptr2]){
                nums1[ptr3] = nums1[ptr1];
                ptr1--;
            }
            else{
                nums1[ptr3] = nums2[ptr2];
                ptr2--;
            }
            ptr3--;
        }

        while(ptr2 >= 0){
            nums1[ptr3--] = nums2[ptr2--];
        }
    }
}
```

