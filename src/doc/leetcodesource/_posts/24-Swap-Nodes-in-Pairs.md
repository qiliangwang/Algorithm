---
title: 24 Swap Nodes in Pairs
date: 2019-02-16 16:45:18
tags:
---

## Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head.

You may **not** modify the values in the list's nodes, only nodes itself may be changed.

 

**Example:**

```
Given 1->2->3->4, you should return the list as 2->1->4->3.
```

## Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode L1 = dummy;
        ListNode L2 = head;
        while (L2 != null && L2.next != null) {
            ListNode nextStart = L2.next.next;
            L1.next = L2.next;
            L1.next.next = L2;
            L2.next = nextStart;
            L1 = L2;
            L2 = L2.next;
        }
        return dummy.next;
    }
}
```

