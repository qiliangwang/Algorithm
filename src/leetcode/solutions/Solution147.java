package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution147 {

    /**
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     *
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode temp = null, prev = null;
        while (cur.next != null) {
            if (cur.val <= cur.next.val) {
                cur = cur.next;
            } else {
                temp = cur.next;
                cur.next = temp.next;
                prev = dummy;
                while (prev.next.val <= temp.val) {
                    prev = prev.next;
                }
                temp.next = prev.next;
                prev.next = temp;
            }
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
        l1.next = new ListNode(1);
//        l1.next.next = new ListNode(1);
//        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(-1);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(4);
        l2.next.next.next.next = new ListNode(0);

        ListNode result = new Solution147().insertionSortList(l1);
        printLinkedList(result);
    }
}
