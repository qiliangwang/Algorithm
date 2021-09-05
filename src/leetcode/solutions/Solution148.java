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
        if (head == null || head.next == null) return head;
        ListNode middle = getMiddle(head);
        ListNode next = middle.next;
        //cut down the linked list
        middle.next = null;
        return merge(sortList(head), sortList(next));
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

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        cur.next = a == null ? b : a;
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
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(3);


        printLinkedList(l1);
        ListNode result = new Solution148().sortList(l1);
        printLinkedList(result);
    }
}
