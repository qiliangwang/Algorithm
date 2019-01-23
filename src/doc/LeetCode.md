---
title: LeetCode
date: 2018-10-07 12:53:42
tags:
---

## LeetCode 1 Two Sum

### Problem:

Given an array of integers, return **indices** of the two numbers such that they add up to a specific target.

You may assume that each input would have **exactly** one solution, and you may not use the *same* element twice.

**Example:**

```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

### Thinking:

我们需要找到数组中相加和为target的2个数的索引，对于这题可以用双重for循环来解决，也可以换一个思路，使用HashMap，遍历数组，从map里面找target - nums[i]，要是找到了，问题的解就出来了，要是没找到，把遍历的元素和对应的索引放入map中。

### Solution:

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i ++) {
            int complement = target - nums[i];
            if (hashMap.containsKey(complement))
                return new int[]{hashMap.get(complement), i};
            hashMap.put(nums[i], i);
        }
        return new int[]{};
    }
}
```

```java
class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(result));
    }
}
```

## LeetCode 2 Add Two Numbers

### Problem:

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order** and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Example:**

```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

### Thinking:

对于一个整数，我们可以通过取模操作把它反转， 对于121而言，先得到最后一位的值也就是1，然后把这个值作为第一位，然后获取倒数第二位也就是2，把这个2作为第二位，最后获得倒数第三位作为第三位，完成这个步骤需要先取余，取余结束后把这个数字与之前的结果乘10后相加，重复当前的过程即可。

### Solution:

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode itr = head;
        int carry = 0;

        while(l1 != null || l2 != null || carry > 0)
        {
            int sum = ( (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry );
            carry = sum / 10;
            ListNode temp = new ListNode(sum % 10);
            itr.next = temp;
            itr = itr.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }

        return head.next;
    }
}
```

```java
class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        ListNode L1 = new ListNode(2);
        L1.next = new ListNode(4);
        L1.next.next = new ListNode(3);

        ListNode L2 = new ListNode(5);
        L2.next = new ListNode(6);
        L2.next.next = new ListNode(4);

        ListNode listNode = solution.addTwoNumbers(L1, L2);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }
}
```

## LeetCode 9 Palindrome Number 

### Problem:

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

**Example 1:**

```
Input: 121
Output: true
```

**Example 2:**

```
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```

**Example 3:**

```
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```

**Follow up:**

Coud you solve it without converting the integer to a string?

### Thinking:

对于一个整数，我们可以通过取模操作把它反转， 对于121而言，先得到最后一位的值也就是1，然后把这个值作为第一位，然后获取倒数第二位也就是2，把这个2作为第二位，最后获得倒数第三位作为第三位，完成这个步骤需要先取余，取余结束后把这个数字与之前的结果乘10后相加，重复当前的过程即可。

### Solution:

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        
        int origin = x;
        int reversed = 0;

        while (x != 0) {
            reversed = reversed * 10 + x % 10;
            x = x / 10;
        }

        return reversed == origin;
    }
}
```

```java
class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(121));
    }
}
```