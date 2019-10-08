package leetcode.solutions;

import leetcode.base.ListNode;

/**
 * @author Vader Wang
 */
public class Solution148 {

    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     *
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     *
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     */

    public ListNode sortList(ListNode head) {
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
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(3);


        printLinkedList(l1);
        ListNode result = new Solution148().sortList(l1);
        printLinkedList(result);
    }
}
