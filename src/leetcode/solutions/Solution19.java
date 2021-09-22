package leetcode.solutions;

import leetcode.base.ListNode;
import leetcode.util.ListUtil;

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


    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = head;
        ListNode pre = dummy;
        for (int i = 0; i < n; i ++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }



    public static void main(String[] args) {
//        1->2->3->4->5, and n = 2.
        ListNode node = ListUtil.generateLinkedList(10);
        ListNode result = new Solution19().removeNthFromEnd2(node, 2);
        ListUtil.printLinkedList(result);
    }
}
