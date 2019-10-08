package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution206 {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        //reverse
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
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
        l1.next.next = new ListNode(4);

        ListNode result = new Solution206().reverseList(l1);
        printLinkedList(result);
    }
}
