package leetcode.solutions;


import leetcode.base.ListNode;

public class Solution25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        ListNode cur = head;
        int count = 0;
        while (cur != null && count != k) {
            cur = cur.next;
            count ++;
        }

        if (count == k) {
            cur = reverseKGroup(cur, k);
            while (count > 0) {
                ListNode temp = head.next;
                head.next = cur;
                cur = head;
                head = temp;
                count--;
            }
            head = cur;
        }
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

    public static ListNode generateLinkedList() {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 1; i <= 10; i ++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

        ListNode node = generateLinkedList();
        ListNode result = new Solution25().reverseKGroup(node, 2);
        printLinkedList(result);
    }
}
