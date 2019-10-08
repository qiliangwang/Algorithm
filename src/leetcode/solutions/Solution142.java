package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode dummy = head;
                while (dummy != slow) {
                    dummy = dummy.next;
                    slow = slow.next;
                }
                return dummy;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(3);
        root.next = new ListNode(2);
        root.next.next = new ListNode(0);
        root.next.next.next = new ListNode(-4);
        root.next.next.next.next = root.next;


        ListNode listNode = new Solution142().detectCycle(root);
    }
}
