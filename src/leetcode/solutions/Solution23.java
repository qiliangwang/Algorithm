package leetcode.solutions;

import leetcode.base.ListNode;
import leetcode.util.ListUtil;

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


    /**
     * 23. 合并K个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     *
     *
     * 示例 1：
     *
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     *
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：lists = [[]]
     * 输出：[]
     *
     *
     * 提示：
     *
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] 按 升序 排列
     * lists[i].length 的总和不超过 10^4
     * 通过次数321,323提交次数574,455
     * 请问您在哪类招聘中遇到此题？
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> a.val - b.val);
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!minHeap.isEmpty()) {
            ListNode poll = minHeap.poll();
            cur.next = poll;
            cur = cur.next;
            if (poll.next != null) {
                minHeap.offer(poll.next);
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

        ListNode listNode = new Solution23().mergeKLists2(lists);

        ListUtil.printLinkedList(listNode);
//        ListNode cur = listNode;
//        while (cur != null) {
//            System.out.print(cur.val + " -> ");
//            cur = cur.next;
//        }
    }
}
