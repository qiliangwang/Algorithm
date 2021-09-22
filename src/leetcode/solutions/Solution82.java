package leetcode.solutions;

import leetcode.base.ListNode;

public class Solution82 {

    /**
     * Given a sorted linked list, delete all nodes that have duplicate numbers,
     * leaving only distinct numbers from the original list.
     *
     * Example 1:
     *
     * Input: 1->2->3->3->4->4->5
     * Output: 1->2->5
     * Example 2:
     *
     * Input: 1->1->1->2->3
     * Output: 2->3
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            if (prev.next.val == prev.next.next.val) {
                int value = prev.next.val;
                while (prev.next != null && prev.next.val == value) {
                    prev.next = prev.next.next;
                }
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }


    /**
     * 82. 删除排序链表中的重复元素 II
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * 返回同样按升序排列的结果链表。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：head = [1,2,3,3,4,4,5]
     * 输出：[1,2,5]
     * 示例 2：
     *
     *
     * 输入：head = [1,1,1,2,3]
     * 输出：[2,3]
     *
     *
     * 提示：
     *
     * 链表中节点数目在范围 [0, 300] 内
     * -100 <= Node.val <= 100
     * 题目数据保证链表已经按升序排列
     * 通过次数178,983提交次数337,696
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        //
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            if (pre.next.val == pre.next.next.val) {
                int val = pre.next.val;
                while (pre.next != null && pre.next.val == val) {
                    pre.next = pre.next.next;
                }
            } else {
                pre = pre.next;
            }
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
//        head.next.next = new ListNode(1);
//        head.next.next.next = new ListNode(2);
//        head.next.next.next.next = new ListNode(3);

        ListNode result = new Solution82().deleteDuplicates2(head);

    }
}
