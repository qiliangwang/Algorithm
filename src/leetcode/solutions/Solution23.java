package leetcode.solutions;

import leetcode.base.ListNode;

import java.util.PriorityQueue;

public class Solution23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (ListNode list: lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) {
                queue.add(cur.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
//        [
//        1->4->5,
//        1->3->4,
//        2->6
//        ]
        ListNode[] lists = new ListNode[3];
        ListNode root = new ListNode(1);
        root.next = new ListNode(4);
        root.next.next = new ListNode(5);

        ListNode root2 = new ListNode(1);
        root2.next = new ListNode(3);
        root2.next.next = new ListNode(4);

        ListNode root3 = new ListNode(2);
        root3.next = new ListNode(6);

        lists[0] = root;
        lists[1] = root2;
        lists[2] = root3;

        ListNode listNode = new Solution23().mergeKLists(lists);
        ListNode cur = listNode;
        while (cur != null) {
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
    }
}
