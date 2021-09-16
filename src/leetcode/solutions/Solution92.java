package leetcode.solutions;

import leetcode.base.ListNode;
import leetcode.base.ListNodeUtil;

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



    /**
     * reverse
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 1; i < left; i ++) {
            pre = pre.next;
        }
        //
        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i ++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }


    public static void main(String[] args) {

        ListNode node = ListNodeUtil.generateLinkedList(10);
        ListNodeUtil.printLinkedList(node);
        ListNode result = new Solution92().reverseBetween2(node, 2, 4);
        ListNodeUtil.printLinkedList(result);
    }
}
