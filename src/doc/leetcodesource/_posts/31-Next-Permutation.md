---
title: 31 Next Permutation
date: 2019-02-18 10:16:13
tags:
---

## Next Permutation

Implement **next permutation**, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be **in-place** and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

```
1,2,3` → `1,3,2`
`3,2,1` → `1,2,3`
`1,1,5` → `1,5,1
```

## Solution

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int flag = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                System.out.println(i);
                flag = i;
                break;
            }
        }
        Arrays.sort(nums, flag, nums.length);
        if (flag == 0) {
            return;
        }
        for (int j = flag; j < nums.length; j ++) {
            if (nums[j] > nums[flag - 1]) {
                int temp = nums[j];
                nums[j] = nums[flag - 1];
                nums[flag - 1] = temp;
                break;
            }
        }
    }
}
```

