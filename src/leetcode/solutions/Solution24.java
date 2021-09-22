package leetcode.solutions;

import leetcode.base.ListNode;
import leetcode.util.ListUtil;

public class Solution24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode L1 = dummy;
        ListNode L2 = head;
        while (L2 != null && L2.next != null) {
            ListNode nextStart = L2.next.next;
            L1.next = L2.next;
            L1.next.next = L2;
            L2.next = nextStart;
            L1 = L2;
            L2 = L2.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode root = ListUtil.generateLinkedList(3);
        ListNode result = new Solution24().swapPairs(root);
        ListUtil.printLinkedList(result);
    }
}
