package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution92 {

    /**
     * Reverse a linked list from position m to n. Do it in one-pass.
     * insert next after pre
     * pre: 1 next: 3
     * 1->2->3->4->5
     * 1->3->2->4->5
     * 1->4->3->2->5
     * Note: 1 ≤ m ≤ n ≤ length of list.
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        for (int i = 1; i < m; i ++) {
            cur = cur.next;
            pre = pre.next;
        }

        for (int i = 0; i < n - m; i ++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }

    public static void printLinkedList(ListNode node) {
        while (node != null) {
            if (node.next != null) {
                System.out.print(node.val + "->");
            } else {
                System.out.print(node.val);
            }
            node = node.next;
        }
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode result = new Solution92().reverseBetween(l1, 2, 4);

        printLinkedList(result);
    }
}
