package leetcode.solutions;

import leetcode.base.ListNode;

/**
 * @author Vader Wang
 */
public class Solution160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a.val != b.val) {
            a = a == null ? headB : a.next;
            b = b == null ? headA :b.next;
        }
        return a;
    }

    public static void main(String[] args) {

    }
}
