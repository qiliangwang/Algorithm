package leetcode;

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
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);

        ListNode result = new Solution24().swapPairs(root);
        ListNode cur = result;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
    }
}
