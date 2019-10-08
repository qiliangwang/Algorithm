package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution61 {

    /**
     * Given a linked list, rotate the list to the right by k places, where k is non-negative.
     *
     *
     * Input: 1->2->3->4->5->NULL, k = 2
     * Output: 4->5->1->2->3->NULL
     * Explanation:
     * rotate 1 steps to the right: 5->1->2->3->4->NULL
     * rotate 2 steps to the right: 4->5->1->2->3->NULL
     *
     * Input: 0->1->2->NULL, k = 4
     * Output: 2->0->1->NULL
     * Explanation:
     * rotate 1 steps to the right: 2->0->1->NULL
     * rotate 2 steps to the right: 1->2->0->NULL
     * rotate 3 steps to the right: 0->1->2->NULL
     * rotate 4 steps to the right: 2->0->1->NULL
     *
     */

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        //build the circle
        ListNode dummy = head;
        int len = 1;
        while (dummy.next != null) {
            dummy = dummy.next;
            len ++;
        }
        dummy.next = head;
        for (int i = 0; i < len - (k % len); i ++) {
            dummy = dummy.next;
        }
        //break the circle
        head = dummy.next;
        dummy.next = null;
        return head;
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
        System.out.println();
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        printLinkedList(head);
        ListNode result = new Solution61().rotateRight(head, 4);
//        System.out.println();
        printLinkedList(result);
    }
}
