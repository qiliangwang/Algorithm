package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //dummy for [1, 2] 2, [1] 1
        ListNode dummy = new ListNode(-1);
        ListNode fast = dummy;
        ListNode slow = dummy;
        // i <= n while(fast != null), i < n while(fast.next != null)
        dummy.next = head;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //skip N-TH node
        slow.next = slow.next.next;
        return dummy.next;
    }

    private static void printListNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
//        1->2->3->4->5, and n = 2.
        ListNode head = new ListNode(1);
//        ListNode cur = head;
//        for (int i = 1; i < 10; i ++) {
//            cur.next = new ListNode(i + 1);
//            cur = cur.next;
//        }
        ListNode result = new Solution19().removeNthFromEnd(head, 1);
        printListNode(result);
    }
}
