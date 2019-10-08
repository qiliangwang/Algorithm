package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode middle = getMiddle(head);
        middle.next = reverse(middle.next);
        ListNode p = head;
        ListNode q = middle.next;
        while (p != null && q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }

    private ListNode getMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        boolean result = new Solution234().isPalindrome(null);
        System.out.println(result);
    }
}
