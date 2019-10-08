package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution237 {

    /**
     * 1 -> 2 -> 3 -> 4
     * 1 -> 2 -> 4 -> 4
     * 1 -> 2 -> 4
     * @param node 3
     */
    public void deleteNode(ListNode node) {
        if (node == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
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
        ListNode node = l1.next.next;
        l1.next.next.next = new ListNode(4);

        new Solution237().deleteNode(node);
        printLinkedList(l1);
    }
}
