package leetcode.solutions;

import leetcode.base.ListNode;

import java.util.HashSet;

public class Solution141 {

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head, fast = head.next;

        while (slow != null && fast != null && fast.next != null) {
            if (slow == fast)
                return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        ListNode cur = head;

        HashSet<ListNode> set = new HashSet<>();

        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            } else {
                set.add(cur);
            }
            cur = cur.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(3);
        root.next = new ListNode(2);
        root.next.next = new ListNode(0);
        root.next.next.next = new ListNode(-4);
        root.next.next.next.next = root.next;

        boolean result = new Solution141().hasCycle2(root);
        System.out.println(result);
    }
}
